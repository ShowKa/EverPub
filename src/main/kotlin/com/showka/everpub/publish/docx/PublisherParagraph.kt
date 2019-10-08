package com.showka.everpub.publish.docx

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Paragraph
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph

class PublisherParagraph(private val paragraph: Paragraph, private val document: XWPFDocument) {

    private val styleNormal = "標準"
    private val styleEmphasis = "emphasis"
    private val styleSeparator = "Separator"
    private val styleQuote = "quote"

    fun publish() {
        when {
            this.paragraph.isDescriptive() -> this.publishDescriptive()
            this.paragraph.isQuote() -> this.publishQuote()
            this.paragraph.isBlockSeparator() -> this.publishBlockSeparator()
        }
    }

    private fun publishDescriptive() {
        val p = this.document.createParagraph()
        this.publishText(p)
    }

    private fun publishText(p : XWPFParagraph) {
        this.paragraph.texts.forEach { textComponent ->
            val text = textComponent.getSimpleText()
            val r = p.createRun()
            r.setText(text)
            val style = when {
                textComponent.isEmphasis() -> this.styleEmphasis
                else -> this.styleNormal
            }
            r.setStyle(style)
        }
    }

    private fun publishQuote() {
        val p = this.document.createParagraph()
        p.style = this.styleQuote
        // start
        val sr = p.createRun()
        sr.setStyle(this.styleNormal)
        sr.setText("「")
        // content
        this.publishText(p)
        // end
        val er = p.createRun()
        er.setStyle(this.styleNormal)
        er.setText("」")
    }

    private fun publishBlockSeparator() {
        val p = this.document.createParagraph()
        p.style = this.styleSeparator
        val r = p.createRun()
        r.setText("")
    }

}