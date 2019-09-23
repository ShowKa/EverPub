package com.showka.everpub.publish

import com.showka.everpub.novelmarkup.NmLine
import com.showka.everpub.novelmarkup.NmLinePrefix

class PublishingText(private val line: NmLine, private val prev: PublishingText?) {

    // members
    // TODO 危険だから使わないなら消す。てかprevもいらない？
    private var next: PublishingText? = null

    // constructor
    init {
        // テキストを生成したら、前行テキストの次行を設定する
        if (this.prev != null && !prev.hasNext()) {
            this.prev.next = this
        }
    }

    // methosds
    /** 次の行があればtrue */
    fun hasNext(): Boolean = (this.next != null)

    /** 行 = 話者定義 ならtrue */
    fun isSpeakerDefinition(): Boolean = (this.line.prefix == NmLinePrefix.QUOTE)

    /** 行 = ブロック分割 ならtrue */
    fun isBlockSeparator(): Boolean = (this.line.prefix == NmLinePrefix.BLOCK_SEPARATOR)

    /** この行でパラグラフを分割すべきならtrue */
    fun shouldBeEndOfParagraph(): Boolean = this.isBlockSeparator()
}