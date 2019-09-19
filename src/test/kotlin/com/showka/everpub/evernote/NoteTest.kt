package com.showka.everpub.evernote

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource

internal class NoteTest {

    private val pathOfTestFile = "/data/note.xml"

    @Test
    fun test_getRoot_01() {
        val file = ClassPathResource(pathOfTestFile).file
        val note = Note(file)
        val root = note.getRoot()
        assertEquals("en-note", root.tagName)
    }

    @Test
    fun test_getDivisions_01() {
        val file = ClassPathResource(pathOfTestFile).file
        val note = Note(file)
        val divs = note.getDivisions()
        assertTrue(divs.isNotEmpty())
        assertEquals("div", divs[0].tagName)
    }

    @Test
    fun test_getSentences_01() {
        val file = ClassPathResource(pathOfTestFile).file
        val note = Note(file)
        val sentences = note.getSentences()
        assertTrue(sentences.isNotEmpty())
    }
}

