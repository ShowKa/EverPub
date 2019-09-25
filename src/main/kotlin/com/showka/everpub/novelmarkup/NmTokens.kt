package com.showka.everpub.novelmarkup

import com.atilika.kuromoji.TokenizerBase
import com.atilika.kuromoji.ipadic.Tokenizer

class NmTokens(text: String) : ArrayList<NmToken>() {

    constructor() : this("")

    init {
        val kuromojiTokens = tokenizer.tokenize(text)
        val tokens = kuromojiTokens.map { NmToken(it) }
        this.addAll(tokens)
    }
}

private val tokenizer = Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
