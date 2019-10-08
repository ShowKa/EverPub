package com.showka.everpub.novelstructure

import com.showka.everpub.novelmarkup.NmLine
import com.showka.everpub.novelmarkup.NmLinePrefix

class TextComponent(val line: NmLine) {

    // methods
    /** 行 = 空 ならtrue */
    fun isBlank(): Boolean = this.line.isBlank()

    /** 行 = 話者定義 ならtrue */
    fun isSpeakerDefinition(): Boolean = (this.line.prefix == NmLinePrefix.QUOTE)

    /** 行 = セリフ間地の文 ならtrue */
    fun isDescriptiveBetweenQuotes(): Boolean = (this.line.prefix == NmLinePrefix.DESCRIPTIVE_BETWEEN_QUOTES)

    /** 行 = ブロック分割 ならtrue */
    fun isBlockSeparator(): Boolean = (this.line.prefix == NmLinePrefix.BLOCK_SEPARATOR)

    /** 行 = コメントボーダー ならtrue */
    fun isCommentBorder(): Boolean = (this.line.prefix == NmLinePrefix.COMMENT_BORDER)

    /** 行 = 強調 ならtrue */
    fun isEmphasis(): Boolean = (this.line.prefix == NmLinePrefix.EMPHASIS)

    /** この行でパラグラフを開始すべきならtrue */
    fun shouldBeStartOfParagraph(): Boolean = (this.isBlockSeparator() || this.isSpeakerDefinition())

    /** この行でパラグラフを終了すべきならtrue */
    fun shouldBeEndOfParagraph(): Boolean = (this.isBlockSeparator() || this.isBlank())

    /** この行だけでパラグラフを構成すべきならtrue */
    fun shouldBeOneInParagraph(): Boolean = (this.shouldBeStartOfParagraph() && this.shouldBeEndOfParagraph())

    /** 連結文字 */
    fun getSimpleText():String = this.line.tokens.joinToString("") { it.getSurface() }
}