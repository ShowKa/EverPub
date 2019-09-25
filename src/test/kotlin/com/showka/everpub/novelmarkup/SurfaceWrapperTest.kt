package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SurfaceWrapperTest {

    @Test
    fun isHiragana_01() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").isHiragana(0)
        assertFalse(result)
    }

    @Test
    fun isHiragana_02() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").isHiragana(1)
        assertTrue(result)
    }

    @Test
    fun indexOf_01() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").indexOf(0)
        assertEquals(1, result)
    }

    @Test
    fun indexOf_02() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").indexOf(1)
        assertEquals(2, result)
    }

    @Test
    fun indexOf_03() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").indexOf(2)
        assertEquals(3, result)
    }

    @Test
    fun indexOf_04() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").indexOf(3)
        assertEquals(-1, result)
    }

    @Test
    fun indexOf_05() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").indexOf(4)
        assertEquals(-1, result)
    }

    @Test
    fun split_01() {
        val result = NmHanReadingMapList.SurfaceWrapper("追い抜く").split()
        assertEquals("追", result[0])
        assertEquals("抜", result[2])
    }
}

