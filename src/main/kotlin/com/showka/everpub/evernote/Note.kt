package com.showka.everpub.evernote

import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

/**
 * EvernoteのNote
 */
// Constructor parameter から private val を省略すると init 以外からアクセスできない。
class Note(file: File) {

    private val document: Document

    init {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        // evernoteの DOCTYPE 定義の文字列を含んでいると、parse が尋常じゃないほど遅い
        document = builder.parse(file)
    }

    fun getSentences(): Sentences {
        val ss = Sentences()
        val divs = this.getDivisions()
        val list = divs.map { div -> Sentence(div) }.toList()
        ss.addAll(list)
        return ss
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


