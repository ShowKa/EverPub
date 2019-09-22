package com.showka.everpub.evernote

import org.w3c.dom.Element
import org.w3c.dom.NodeList

class Sentence internal constructor(private val element: Element) {

    init {
        require(element.tagName == "div") {
            "element tagName should be 'div' but '${element.tagName}'"
        }
    }

    /**
     * テキスト取得。
     */
    fun getPlainText(): String {
        return this.element.textContent.trim()
    }

    /**
     * 改行、もしくは空白行の場合true。
     * スペースだけの場合も空白行とみなす。
     */
    fun isBlankLine(): Boolean {
        if (!this.hasChildren()) {
            return true
        }
        if (this.childElementTagIs("br")) {
            return true
        }
        // 全角スペース置換したtext
        // isBlank = 半角スペースだけの場合も空としてくれる。
        val text = this.getPlainText().replace("　", "")
        if (text.isBlank()) {
            return true
        }
        return false
    }

    /**
     * 子から要素ノードを取得。
     * 最初の要素ノード。
     * 要素を含有しない場合null。
     * 注意 : 子の中に複数の要素を含まないという前提での実装。
     */
    private fun getChildElementNode(): Element? {
        val children = this.getChildNode()
        for (i in 0 until children.length) {
            val child = children.item(i)
            if (child is Element) {
                return child
            }
        }
        return null
    }

    private fun childElementTagIs(tagName: String): Boolean {
        val child = this.getChildElementNode()
        return child?.tagName == tagName
    }

    private fun hasChildren(): Boolean {
        return element.hasChildNodes()
    }

    private fun getChildNode(): NodeList {
        return this.element.childNodes
    }
}