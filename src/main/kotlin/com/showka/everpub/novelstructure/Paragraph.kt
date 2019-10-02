package com.showka.everpub.novelstructure

class Paragraph(val texts: MutableList<TextComponent>) {

    // constructor
    init {
        // 空行は不要なので除去
        texts.removeIf { it.isBlank() }
        require(texts.isNotEmpty()) { "1行以上のテキストを設定してください。" }
    }

    // method
    /** セリフパラグラフならtrue */
    fun isQuote(): Boolean = this.texts[0].isSpeakerDefinition()

    /** ブロック分割パラグラフならtrue */
    fun isBlockSeparator(): Boolean = this.texts[0].isBlockSeparator()

    /** 地の文パラグラフならtrue */
    fun isDescriptive(): Boolean = (!this.isQuote() && !this.isBlockSeparator())

}