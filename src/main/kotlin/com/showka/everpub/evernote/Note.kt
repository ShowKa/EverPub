package com.showka.everpub.evernote

import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

/**
 * EvernoteのNote
 */
// Constructor parameter から private val を省略すると init 以外からアクセスできない。
class Note(inputStream: InputStream) {

    // member
    private val document: Document

    // constructor
    constructor(file: File) : this(file.inputStream())

    constructor(note: com.evernote.edam.type.Note) : this(ByteArrayInputStream(note.content.toByteArray(Charsets.UTF_8)))

    init {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        // disable validation -> parse quickly
        factory.isValidating = false
        factory.setFeature("http://xml.org/sax/features/namespaces", false)
        factory.setFeature("http://xml.org/sax/features/validation", false)
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false)
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        // parse
        val builder = factory.newDocumentBuilder()
        document = builder.parse(inputStream)
    }

    // method
    fun getSentences(): Sentences {
        val divs = this.getDivisions()
        val list = divs.map { div -> Sentence(div) }.toList()
        return Sentences(list)
    }

    internal fun getRoot(): Element {
        val noteElementTagName = "en-note"
        val node = document.getElementsByTagName(noteElementTagName)
        for (i in 0 until node.length) {
            val n = node.item(i)
            if (n is Element) {
                return n
            }
        }
        throw RuntimeException("<$noteElementTagName> が含まれていません")
    }

    internal fun getDivisions(): List<Element> {
        val root = this.getRoot()
        val divNodeList = root.getElementsByTagName("div")
        val list: MutableList<Element> = mutableListOf()
        for (i in 0 until divNodeList.length) {
            val node = divNodeList.item(i)
            if (node is Element) {
                list.add(node)
            }
        }
        return list
    }

}


