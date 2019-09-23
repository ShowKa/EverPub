package com.showka.everpub.novelmarkup

class NmLine(private val text: String) {

    /** 空行のコンストラクタ */
    constructor() : this("")

    // members
    val prefix: NmLinePrefix = NmLinePrefix.type(this.text)
    private val plainText: String = this.prefix.removePrefix(this.text)
    val tokens: NmTokens = NmTokens(this.plainText)

    // methods
    /** 空白行ならtrue */
    fun isBlank(): Boolean = (this.text.isBlank())
}