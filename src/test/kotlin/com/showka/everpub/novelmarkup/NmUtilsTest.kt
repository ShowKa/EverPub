package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NmUtilsTest {
    @Test
    fun test_convertKatakanaToHiragana_01() {
        val hiragana = convertKatakanaToHiragana("アイウエオワヲンガギグゲゴパピプペポ")
        assertEquals("あいうえおわをんがぎぐげごぱぴぷぺぽ", hiragana)
    }
}
