package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ChunkListTest {

    fun isHiragana_01() {
        val result = NmHanReadingMapList.ChunkList("追い抜く").isHiraganaChunk(0)
        assertFalse(result)
    }

    @Test
    fun isHiragana_02() {
        val result = NmHanReadingMapList.ChunkList("追い抜く").isHiraganaChunk(1)
        assertTrue(result)
    }

    @Test
    fun split_01() {
        val result = NmHanReadingMapList.ChunkList("追い抜く")
        assertEquals("追", result[0])
        assertEquals("抜", result[2])
    }
}

