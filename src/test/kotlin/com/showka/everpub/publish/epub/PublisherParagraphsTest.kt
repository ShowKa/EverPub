package com.showka.everpub.publish.epub

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelmarkup.NmLine
import com.showka.everpub.novelstructure.Chapter
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class PublisherParagraphsTest {
    @Test
    fun test() {
        val nmLines = novels.map { NmLine(it) }
        println(LocalDateTime.now())
        val chap = Chapter(title = "月を目指す生活", lines = nmLines)
        val paragraphs = chap.paragraphs
        val pub = PublisherParagraphs(paragraphs)
        pub.setGrade(NmHanGrade.ELEMENTARY_6)
        println(pub.publish())
    }

    @Test
    fun test02() {
        val nmLines = arrayListOf<String>("あっという間に追い抜かれたわけだけどな。").map { NmLine(it) }
        println(LocalDateTime.now())
        val chap = Chapter(title = "月を目指す生活", lines = nmLines)
        val paragraphs = chap.paragraphs
        val pub = PublisherParagraphs(paragraphs)
        pub.setGrade(NmHanGrade.ELEMENTARY_6)
        println(pub.publish())
    }
}

private val novels = arrayListOf<String>(
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
