package com.showka.everpub.publish.epub

import com.showka.everpub.novelstructure.Chapter
import com.showka.everpub.publish.Publisher
import java.io.PrintWriter

class PublisherEpub(private val chapter: Chapter) : Publisher {

    override fun publish(): String {
        // <title>...<body> chapter </body>
        return ""
    }

    override fun generateFile(path: String): Unit {
        val text = this.publish()
        val out = PrintWriter(path)
        out.println(text)
        out.close()
    }
}