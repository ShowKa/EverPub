package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class NmHanGradeTest {
    @Test
    fun test_isHigher_01() {
        assertTrue(NmHanGrade.ELEMENTARY_2.isHigher(NmHanGrade.ELEMENTARY_1))
    }

    @Test
    fun test_getValue_01() {
        assertEquals(0, NmHanGrade.PRE_ELEMENTARY.getValue())
    }
}