package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NmLinePrefixTest {

    @Test
    fun removePrefix_01() {
        val text = "!強調する"
        val prefix = NmLinePrefix.EMPHASIS
        val result = prefix.removePrefix(text)
        assertEquals("強調する", result)
    }

    @Test
    fun removePrefix_02() {
        val text = "なにもない"
        val prefix = NmLinePrefix.NOTHING
        val result = prefix.removePrefix(text)
        assertEquals("なにもない", result)
    }

    @Test
    fun type_01() {
        val text = ">僕は言った。"
        val result = NmLinePrefix.type(text)
        assertEquals(NmLinePrefix.DESCRIPTIVE_BETWEEN_QUOTES, result)
    }
}