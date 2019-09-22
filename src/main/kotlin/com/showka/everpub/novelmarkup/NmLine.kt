package com.showka.everpub.novelmarkup

class NmLine(private val text: String) {

    val prefix: NmLinePrefix = NmLinePrefix.type(this.text)
    private val plainText: String = this.prefix.removePrefix(this.text)
    val tokens: NmTokens = NmTokens(this.plainText)
}