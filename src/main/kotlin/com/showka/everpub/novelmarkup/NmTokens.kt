package com.showka.everpub.novelmarkup

import com.atilika.kuromoji.TokenizerBase
import com.atilika.kuromoji.ipadic.Tokenizer
import org.springframework.core.io.ClassPathResource
import java.io.InputStream

class NmTokens(text: String) : ArrayList<NmToken>() {

    constructor() : this("")

    init {
        val kuromojiTokens = tokenizer.tokenize(text)
        val tokens = kuromojiTokens.map { NmToken(it) }
        this.addAll(tokens)
    }
}

private const val pathOfDictionary = "/kuromoji/dictionary.csv"
private val dic: InputStream = ClassPathResource(pathOfDictionary).file.inputStream()
private val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL)
        .userDictionary(dic).build<Tokenizer>()
