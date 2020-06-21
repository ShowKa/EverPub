package com.showka.everpub.publish.notemu

import com.showka.everpub.novelstructure.Chapter
import com.showka.everpub.publish.Publisher
import java.io.PrintWriter

class PublisherNotemu (private val chapter: Chapter) : Publisher {

    fun publish(): String {
        val header = """
            |<?xml version="1.0" encoding="utf-8" standalone="no"?>
            |<!DOCTYPE html>
            |<html xmlns="http://www.w3.org/1999/xhtml" xmlns:epub="http://www.idpf.org/2007/ops" lang="ja" xml:lang="ja">
        """.trimMargin()
        // <title /><body> chapter </body>
        val publisher = PublisherChapter(this.chapter)
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