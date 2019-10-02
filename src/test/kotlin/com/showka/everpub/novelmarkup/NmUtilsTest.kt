package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NmUtilsTest {
    @Test
    fun test_convertKatakanaToHiragana_01() {
        val hiragana = convertKatakanaToHiragana("アイウエオワヲンガギグゲゴパピプペポ平がな")
        assertEquals("あいうえおわをんがぎぐげごぱぴぷぺぽ平がな", hiragana)
    }

    @Test
    fun test_isHiragana_01() {
        val result = isHiragana("あ".toCharArray()[0])
        assertEquals(true, result)
    }
}
