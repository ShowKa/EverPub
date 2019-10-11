package com.showka.everpub.publish.docx

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Paragraph
import com.showka.everpub.novelstructure.TextComponent
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign

private const val styleDescriptive = "descriptive"
private const val styleEmphasis = "emphasis"
private const val styleSeparator = "Separator"
private const val styleQuote = "quote"
private const val styleLetterBorder = "letterBorder"

class PublisherParagraph(private val paragraphs: List<Paragraph>, private val document: XWPFDocument) {

    private var alignProperty: CTRubyAlign? = null

    init {
        val ite = document.paragraphsIterator
        while (ite.hasNext()) {
            val p = ite.next()
            val runs = p.runs
            runs.forEach { r ->
                val ctr = r.ctr
                val c = ctr.newCursor()
                c.selectPath("./*")
                while (c.toNextSelection()) {
                    val o = c.getObject()
                    if (o is CTRuby) {
                        val rp = o.rubyPr
                        alignProperty = rp.rubyAlign
                        break
                    }
                }
            }
        }
    }

    fun publish() {
        paragraphs.forEach { paragraph ->
            when {
                paragraph.isDescriptive() -> this.publishDescriptive(paragraph)
                paragraph.isQuote() -> this.publishQuote(paragraph)
                paragraph.isBlockSeparator() -> this.publishBlockSeparator()
                paragraph.isLetterBorder() -> this.publishLetterBorder()
            }
        }
    }

    private fun publishLetterBorder() {
        val p = this.document.createParagraph()
        p.style = styleLetterBorder
        val pNext = this.document.createParagraph()
    }

    private fun publishDescriptive(para: Paragraph) {
        val p = this.document.createParagraph()
        p.style = styleDescriptive
        para.texts.forEach { textComponent ->
            this.publishText(textComponent, p)
        }
    }

    private fun publishText(textComponent: TextComponent, p: XWPFParagraph) {
        // style
        val style = when {
            textComponent.isEmphasis() -> styleEmphasis
            else -> "標準"
        }
        // publish
        for (i in 0..textComponent.line.tokens.lastIndex) {
            val token = textComponent.line.tokens[i]
            if (token.getHanGrade().isHigher(NmHanGrade.ELEMENTARY_6)) {
                val hrList = token.getHanReading()
                hrList.forEach { hr ->
                    if (hr.isHan) {
                        val r = p.createRun()
                        val ctr = r.ctr
                        val ruby = ctr.addNewRuby()
                        // property
                        val property = ruby.addNewRubyPr()
                        val align = property.addNewRubyAlign()
                        property.rubyAlign = alignProperty
                        // read
                        val rt = ruby.addNewRt()
                        val rtR = rt.addNewR()
                        val rtRT = rtR.addNewT()
                        rtRT.stringValue = hr.reading
                        // base
                        val base = ruby.addNewRubyBase()
                        val baseR = base.addNewR()
                        val baseRT = baseR.addNewT()
                        baseRT.stringValue = hr.surface
                        r.setStyle(style)
                    } else {
                        val r = p.createRun()
                        val text = hr.surface
                        r.setText(hr.surface)
                        r.setStyle(style)
                    }
                }
            } else {
                val r = p.createRun()
                val surface = token.getSurface()
                val text = surface
                        .replace("！".toRegex(), "！　")
                        .replace("？".toRegex(), "？　")
                        .replace("「".toRegex(), "『")
                        .replace("」".toRegex(), "』")
                r.setText(text)
                // 三点リーダーが強制的に英字フォントになるので、無理やり和フォントにする。
                if (text == "…") {
                    r.fontFamily = "メイリオ"
                }
                r.setStyle(style)
            }
        }
    }

    private fun publishQuote(para: Paragraph) {
        val p = this.document.createParagraph()
        p.style = styleQuote
        // start
        val sr = p.createRun()
        sr.setText("「")
        // content
        var inQuote = true
        for (textComponent in para.texts) {
            if (textComponent.isSpeakerDefinition()) {
                continue
            }
            val descriptive = textComponent.isDescriptiveBetweenQuotes()
            if (inQuote && descriptive) {
                this.removeLastSpace(p)
                val er = p.createRun()
                er.setText("」")
            } else if (!inQuote && !descriptive) {
                val sr = p.createRun()
                sr.setText("「")
            }
            inQuote = !descriptive
            this.publishText(textComponent, p)
        }
        if (inQuote) {
            // remove last space
            this.removeLastSpace(p)
            // end
            val er = p.createRun()
            er.setText("」")
        }
    }

    private fun removeLastSpace(p: XWPFParagraph) {
        val last = p.runs.last()
        val lastPosition = last.textPosition
        val lastText = last.getText(lastPosition)
        if (lastText.contains("　$".toRegex())) {
            val replacedText = lastText.replace("　$".toRegex(), "")
            p.removeRun(p.runs.lastIndex)
            val newer = p.createRun()
            newer.setText(replacedText)
        }
    }

    private fun publishBlockSeparator() {
        val p = this.document.createParagraph()
        p.style = styleSeparator
        val r = p.createRun()
        r.setText("■")
    }
}
