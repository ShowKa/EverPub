package com.showka.everpub.publish

class Paragraph(private val texts: List<PublishingText>) {

    // constructor
    init {
        require(texts.isNotEmpty()) {
            "1行以上のテキストを設定してください。"
        }
    }

    // method
    /** セリフパラグラフならtrue */
    fun isQuote(): Boolean = this.texts[0].isSpeakerDefinition()

    /** ブロック分割パラグラフならtrue */
    fun isBlockSeparator(): Boolean = this.texts[0].isBlockSeparator()

    /** 地の文パラグラフならtrue */
    fun isDescriptive(): Boolean = (!this.isQuote() && !this.isBlockSeparator())

    // override
    override fun toString(): String {
        return ""
    }

}