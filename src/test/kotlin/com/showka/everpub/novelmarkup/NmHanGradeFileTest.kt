package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NmHanGradeFileTest {

    // 右引悪愛圧異亜
    @Test
    fun test_determine_01() {
        val grade = NmHanGradeFile.determine("あいうえお")
        assertEquals(NmHanGrade.PRE_ELEMENTARY, grade)
    }

    @Test
    fun test_determine_02() {
        val grade = NmHanGradeFile.determine("右")
        assertEquals(NmHanGrade.ELEMENTARY_1, grade)
    }

    @Test
    fun test_determine_03() {
        val grade = NmHanGradeFile.determine("右引")
        assertEquals(NmHanGrade.ELEMENTARY_2, grade)
    }

    @Test
    fun test_determine_04() {
        val grade = NmHanGradeFile.determine("右引悪")
        assertEquals(NmHanGrade.ELEMENTARY_3, grade)
    }

    @Test
    fun test_determine_05() {
        val grade = NmHanGradeFile.determine("右引悪愛")
        assertEquals(NmHanGrade.ELEMENTARY_4, grade)
    }

    @Test
    fun test_determine_06() {
        val grade = NmHanGradeFile.determine("右引悪愛圧")
        assertEquals(NmHanGrade.ELEMENTARY_5, grade)
    }

    @Test
    fun test_determine_07() {
        val grade = NmHanGradeFile.determine("右引悪愛圧異")
        assertEquals(NmHanGrade.ELEMENTARY_6, grade)
    }

    @Test
    fun test_determine_08() {
        val grade = NmHanGradeFile.determine("右引悪愛圧異亜")
        assertEquals(NmHanGrade.MIDDLE, grade)
    }

    @Test
    fun test_determine_09() {
        val grade = NmHanGradeFile.determine("あ引")
        assertEquals(NmHanGrade.ELEMENTARY_2, grade)
    }

    @Test
    fun test_validateHan_01() {
        val result = NmHanGradeFile.validateHan("あ".toCharArray()[0])
        assertFalse(result)
    }

    @Test
    fun test_validateHan_02() {
        val result = NmHanGradeFile.validateHan("ア".toCharArray()[0])
        assertFalse(result)
    }

    @Test
    fun test_validateHan_03() {
        val result = NmHanGradeFile.validateHan("。".toCharArray()[0])
        assertFalse(result)
    }

    @Test
    fun test_validateHan_04() {
        val result = NmHanGradeFile.validateHan("一".toCharArray()[0])
        assertFalse(result)
    }

    @Test
    fun test_validateHan_05() {
        val result = NmHanGradeFile.validateHan("A".toCharArray()[0])
        assertFalse(result)
    }

    @Test
    fun test_validateHan_06() {
        val result = NmHanGradeFile.validateHan("右".toCharArray()[0])
        assertTrue(result)
    }
}