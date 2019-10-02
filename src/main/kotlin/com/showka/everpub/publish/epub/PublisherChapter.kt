package com.showka.everpub.publish.epub

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Chapter

class PublisherChapter constructor(private val chapter: Chapter) {

    private var grade: NmHanGrade = NmHanGrade.MIDDLE

    fun setGrade(grade: NmHanGrade) {
        this.grade = grade
    }

    fun publish(): String {
        val header = "<head><title>${this.chapter.title}</title></head>"
        val start = "<body><section class=\"chapter\" epub:type=\"chapter\">"
        val pubParagraphs = PublisherParagraphs(this.chapter.paragraphs)
        pubParagraphs.setGrade(this.grade)
        val contents = pubParagraphs.publish()
        val end = "</section></body>"
        return header + start + contents + end
    }
}