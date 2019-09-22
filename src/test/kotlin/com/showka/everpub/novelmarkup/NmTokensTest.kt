package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NmTokensTest {

    @Test
    fun init_01() {
        val text = "僕は言った。"
        val result = NmTokens(text)
        assertEquals("僕", result[0].getSurface())
    }

    @Test
    fun init_02() {
        val result = NmTokens()
        assertEquals(0, result.size)
    }
}