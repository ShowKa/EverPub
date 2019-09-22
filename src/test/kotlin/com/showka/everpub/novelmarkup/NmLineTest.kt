package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NmLineTest {

    @Test
    fun getPrefix_01() {
        val text = ">僕は言った。"
        val result = NmLine(text)
        assertEquals(NmLinePrefix.DESCRIPTIVE_BETWEEN_QUOTES, result.prefix)
    }

    @Test
    fun getPrefix_02() {
        val text = "僕は言った。"
        val result = NmLine(text)
        assertEquals(NmLinePrefix.NOTHING, result.prefix)
    }

    @Test
    fun getTokens_01() {
        val text = ">僕は言った。"
        val result = NmLine(text)
        assertEquals("僕", result.tokens[0].getSurface())
    }
}