package com.showka.everpub.novelstructure

import com.showka.everpub.novelmarkup.NmLine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class ChapterTest {

    @Test
    fun init() {
        val novels = arrayListOf<String>(
                ""
                , "#y"
                , "我が子ながらおどろきよ。"
                , "私とは似ても似つかない逸材ね。"
                , ""
                , "ーーーー"
                , "メモ"
                , "ーーーー"
                , ""
                , "#"
                , "陽子そっくりだよ。"
                , ">月美は言った。"
                , "努力家だ。"
                , "最近は口うるさいところも似てきた。"
                , "#y"
                , "月美には感謝している。"
                , "芽衣に色々と教えてくれた。"
                , ""
                , "月美はうなずいた。"
                , "◇"
                , ""
                , "たしかに先生気取りで色々と教えた。"
                , "機械工学、ソフトウェア工学、開発手法、言葉や文化のちがう人間との話し方。"
                , "スポンジのように吸収する芽衣に教えるのは楽しかった。"
                , "教えている自分も有能になった気分だった。"
                , ""
                , "#"
                , "あっという間に追い抜かれたわけだけどな。"
                , ">月見は自嘲した。"
                , ""
        )
        val nmLines = novels.map { NmLine(it) }
        println(LocalDateTime.now())
        val chap = Chapter(title = "月を目指す生活", lines = nmLines)
        // パラグラフは7つ
        assertEquals(7, chap.paragraphs.size)
        // 第一パラグラフはセリフ
        val para1 = chap.paragraphs[0]
        assertTrue(para1.isQuote())
        // 第五パラグラフはブロック分割
        val para5 = chap.paragraphs[4]
        assertTrue(para5.isBlockSeparator())
    }
}