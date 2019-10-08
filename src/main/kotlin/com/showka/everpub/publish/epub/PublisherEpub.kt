package com.showka.everpub.publish.epub

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Chapter
import com.showka.everpub.publish.Publisher
import java.io.PrintWriter

class PublisherEpub(private val chapter: Chapter) : Publisher {

    private var grade: NmHanGrade = NmHanGrade.MIDDLE

    /**
     * 3年生を指定すれば、4年生レベルの漢字からルビをふる。
     */
    fun setGrade(grade: NmHanGrade) {
        this.grade = grade
    }

    fun publish(): String {
        val header = """
            |<?xml version="1.0" encoding="utf-8" standalone="no"?>
            |<!DOCTYPE html>
            |<html xmlns="http://www.w3.org/1999/xhtml" xmlns:epub="http://www.idpf.org/2007/ops" lang="ja" xml:lang="ja">
        """.trimMargin()
        // <title /><body> chapter </body>
        val publisher = PublisherChapter(this.chapter)
        publisher.setGrade(this.grade)
        val contents = publisher.publish()
        val footer = "</html>"
        return header + contents + footer
    }

    override fun generateFile(path: String) {
        val text = this.publish()
        val writer = PrintWriter(path)
        writer.println(text)
        writer.close()
    }

}