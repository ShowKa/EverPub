package com.showka.everpub.novelmarkup

import com.atilika.kuromoji.ipadic.Token

/**
 * Kuromoji#Token Wrapper
 */
class NmToken(private val token: Token) {

    /** 漢字グレード(必要になったら代入) */
    private var hanGrade: NmHanGrade? = null

    /** 表層形取得 */
    fun getSurface(): String = this.token.surface

    /** カタカナ取得 */
    fun getKatakana(): String = this.token.reading

    /** ひらがな取得 */
    fun getHiragana(): String = convertKatakanaToHiragana(this.getKatakana())

    /** 位置取得 */
    fun getPosition(): Int = this.token.position

    /** 漢字グレード取得 */
    fun getHanGrade(): NmHanGrade {
        val hanGrade: NmHanGrade = this.hanGrade ?: NmHanGradeFile.determine(this)
        this.hanGrade = hanGrade
        return hanGrade
    }

    // override
    override fun toString(): String = this.token.allFeatures
}

