package com.showka.everpub.publish.epub

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Chapter

class PublisherChapter constructor(private val chapter: Chapter) {

    private var grade: NmHanGrade = NmHanGrade.MIDDLE

    fun setGrade(grade: NmHanGrade) {
        this.grade = grade
    }

    fun publish(): String {
        val header = """
            |<head>
            |<title>${this.chapter.title}</title>
            |<link rel="stylesheet" type="text/css" href="../styles/common8616PTMR.css" />
            |<link rel="stylesheet" type="text/css" href="../styles/vertical5648PTMR.css" class="vertical" title="縦組" />
            |<link rel="alternate stylesheet" type="text/css" href="../styles/horizontal6460PTMR.css" class="horizontal" title="横組" />
            |</head>
        """.trimMargin()
        val start = "<body><section class=\"chapter\" epub:type=\"chapter\">"
        val chapterTitle = "<p class=\"chapter_title\">${this.chapter.title}</p>"
        val pubParagraphs = PublisherParagraphs(this.chapter.paragraphs)
        pubParagraphs.setGrade(this.grade)
        val contents = pubParagraphs.publish()
        val end = "</section></body>"
        return header + start + chapterTitle + contents + end
    }
}