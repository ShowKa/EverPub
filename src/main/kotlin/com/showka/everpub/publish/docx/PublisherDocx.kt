package com.showka.everpub.publish.docx

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Chapter
import com.showka.everpub.publish.Publisher
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.FileOutputStream

class PublisherDocx(private val chapter: Chapter) : Publisher {

    private val templatePath = "/docx/template.docx"
    val template = ClassPathResource(templatePath).file
    val document = XWPFDocument(template.inputStream())

    override fun generateFile(path: String) {
        // paragraph
        val pubChapter = PublisherChapter(this.chapter, document)
        pubChapter.publish()
        // output
        val target = File(path)
        document.write(FileOutputStream(target))
    }
}
