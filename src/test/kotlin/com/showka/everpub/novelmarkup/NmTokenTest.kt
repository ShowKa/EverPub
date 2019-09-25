package com.showka.everpub.novelmarkup

import com.atilika.kuromoji.TokenizerBase
import com.atilika.kuromoji.ipadic.Tokenizer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class NmTokenTest {

    @Test
    fun getHiragana() {
        val text = "僕は言った。"
        val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
        val token = tokenizer.tokenize(text)
        val result = NmToken(token[0])
        assertEquals("ぼく", result.getHiragana())
    }

    @Test
    fun getHanGrade() {
        val text = "僕は言った。"
        val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
        val token = tokenizer.tokenize(text)
        val result = NmToken(token[0])
        assertEquals(NmHanGrade.MIDDLE, result.getHanGrade())
        // 二回呼ぶ
        assertEquals(NmHanGrade.MIDDLE, result.getHanGrade())
    }

    @Test
    fun getPosition() {
        val text = "僕は言った。"
        val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
        val token = tokenizer.tokenize(text)
        val result1 = NmToken(token[0])
        assertEquals(0, result1.getPosition())
        val result2 = NmToken(token[1])
        assertEquals(1, result2.getPosition())
    }

    @Test
    fun getRuby() {
        val text = "僕は召使いだ。"
        val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
        val token = tokenizer.tokenize(text)
        val result = NmToken(token[2])
        assertEquals("めしつか", result.getRuby())
    }

    @Test
    fun getHanSection() {
        val text = "僕は召使いだ。"
        val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
        val token = tokenizer.tokenize(text)
        val result = NmToken(token[2])
        assertEquals("召使", result.getHanSection())
    }

    @Test
    fun getHiraganaSection() {
        val text = "僕は召使いだ。"
        val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
        val token = tokenizer.tokenize(text)
        val result = NmToken(token[2])
        assertEquals("い", result.getNotHanSection())
    }

}