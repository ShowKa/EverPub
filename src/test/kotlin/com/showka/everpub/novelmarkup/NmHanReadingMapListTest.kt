package com.showka.everpub.novelmarkup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NmHanReadingMapListTest {
    @Test
    fun init() {
        val text = "久々に「百聞はTESTに如かず」が追い抜く。読む。ち密設計。"
        val tokens = NmTokens(text)
        // [追い抜く] 抽出
        val mapList = NmHanReadingMapList(tokens[11])
        // [抜] 抽出
        assertEquals("ぬ", mapList[2].reading)
        // OUT
        //tokens.forEach { token ->
        //    val ml = NmHanReadingMapList(token)
        //    ml.forEach {
        //         println (it)
        //    }
        //}
    }
}
