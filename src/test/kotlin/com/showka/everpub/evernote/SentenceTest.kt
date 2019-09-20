package com.showka.everpub.evernote

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.xml.parsers.DocumentBuilderFactory

internal class SentenceTest {

    // error if not div
    @Test()
    fun test_init_01() {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()
        val span = doc.createElement("span")
        assertThrows<IllegalArgumentException> { Sentence(span) }
    }

    // <div> abc</div>
    // -> abc
    @Test
    fun test_getPlainText_01() {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()
        val div = doc.createElement("div")
        val text = doc.createTextNode(" abc")
        div.appendChild(text)
        val sentence = Sentence(div)
        assertEquals("abc", sentence.getPlainText())
    }

    // <div><b> abc</b></div>
    // abc
    @Test
    fun test_getPlainText_02() {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()
        val div = doc.createElement("div")
        val b = doc.createElement("b")
        val text = doc.createTextNode(" abc")
        b.appendChild(text)
        div.appendChild(b)
        val sentence = Sentence(div)
        assertEquals("abc", sentence.getPlainText())
    }

    // <div> abc</div>
    // -> Not Blank
    @Test
    fun test_isBlankLine_01() {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()
        val div = doc.createElement("div")
        val text = doc.createTextNode(" abc")
        div.appendChild(text)
        val sentence = Sentence(div)
        assertFalse(sentence.isBlankLine())
    }

    // <div></div>
    // -> Blank
    @Test
    fun test_isBlankLine_02() {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()
        val div = doc.createElement("div")
        val sentence = Sentence(div)
        assertTrue(sentence.isBlankLine())
    }

    // <div><br/></div>
    // -> Blank
    @Test
    fun test_isBlankLine_03() {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()
        val div = doc.createElement("div")
        val br = doc.createElement("br")
        div.appendChild(br)
        val sentence = Sentence(div)
        assertTrue(sentence.isBlankLine())
    }

    // <div>全角スペース</div>
    // -> Blank
    @Test
    fun test_isBlankLine_04() {
        val factory = DocumentBuilderFactory.newDefaultInstance()
        val builder = factory.newDocumentBuilder()
        val doc = builder.newDocument()
        val div = doc.createElement("div")
        val text = doc.createTextNode("　")
        div.appendChild(text)
        val sentence = Sentence(div)
        assertTrue(sentence.isBlankLine())
    }
}


