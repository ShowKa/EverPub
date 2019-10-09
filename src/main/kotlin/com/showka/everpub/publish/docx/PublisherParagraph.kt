package com.showka.everpub.publish.docx

import com.showka.everpub.novelstructure.Paragraph
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.FileOutputStream

private const val styleDescriptive = "descriptive"
private const val styleEmphasis = "emphasis"
private const val styleSeparator = "Separator"
private const val styleQuote = "quote"

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
            }
        }
    }

    private fun publishDescriptive(para: Paragraph) {
        val p = this.document.createParagraph()
        p.style = styleDescriptive
        this.publishText(para, p)
    }

    private fun publishText(paragraph: Paragraph, p: XWPFParagraph) {
        for (textComponent in paragraph.texts) {
            val text = textComponent.getSimpleText()
            val r = p.createRun()
            r.setText(text)
            when {
                textComponent.isEmphasis() -> {
                    r.setStyle(styleEmphasis)
                }
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
        this.publishText(para, p)
        // end
        val er = p.createRun()
        er.setText("」")
    }

    private fun publishBlockSeparator() {
        val p = this.document.createParagraph()
        p.style = styleSeparator
        val r = p.createRun()
        r.setText("■")
    }
}

fun main(args: Array<String>) {
    // document
    val templatePath = "/docx/text.docx"
    val template = ClassPathResource(templatePath).file
    val document = XWPFDocument(template.inputStream())
    // get ruby align property from template document
    val ite = document.paragraphsIterator
    var alignProperty: CTRubyAlign? = null
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
                    println(alignProperty)
                    break
                }
            }
        }
    }
    // ruby
    val p = document.createParagraph()
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
    rtRT.stringValue = "さいだいじつきみ"
    // base
    val base = ruby.addNewRubyBase()
    val baseR = base.addNewR()
    val baseRT = baseR.addNewT()
    baseRT.stringValue = "西大寺月美"
    // output
    val dir = System.getenv("path")
    val target = File("$dir/ruby.docx")
    document.write(FileOutputStream(target))
}