package com.showka.everpub.publish.docx

import com.showka.everpub.novelstructure.Chapter
import org.apache.poi.xwpf.usermodel.XWPFDocument

class PublisherChapter(private val chapter: Chapter, private val document: XWPFDocument) {

    fun publish() {
        // title
        val title = chapter.title
        val pTitle = this.document.createParagraph()
        pTitle.style = "title"
        val rTitle = pTitle.createRun()
        rTitle.setText(title)
        // paragraphs
        val pubParagraph = PublisherParagraph(this.chapter.paragraphs, document)
        pubParagraph.publish()
    }
}