package com.showka.everpub.publish.notemu

import com.showka.everpub.novelstructure.Chapter

class PublisherChapter constructor(private val chapter: Chapter) {
    fun publish(): String {
        val header = """
            |<head>
            |<title>${this.chapter.title}</title>
            |</head>
        """.trimMargin()
        val start = "<body><section class=\"chapter\" >"
        val chapterTitle = "<h3 class=\"chapter_title\">${this.chapter.title}</h3>"
        val pubParagraphs = PublisherParagraphs(this.chapter.paragraphs)
        val contents = pubParagraphs.publish()
        val end = "</section></body>"
        return header + start + chapterTitle + contents + end
    }
}