package com.showka.everpub.novelmarkup

import com.atilika.kuromoji.ipadic.Token

/**
 * 漢字グレード判定
 */
internal fun determineGrade(token: Token): NmHanGrade {
    return NmHanGrade.MIDDLE
}

/**
 * カタカナからひらがなへのコンバート
 * 注意 : カタカナ以外は対象外。
 */
internal fun convertKatakanaToHiragana(katakana: String): String {
    val buf = StringBuffer()
    for (code in katakana) {
        var hiragana = code.minus(0x60)
        buf.append(hiragana)
    }
    return buf.toString()
}

