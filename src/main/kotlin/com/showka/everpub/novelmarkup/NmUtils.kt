package com.showka.everpub.novelmarkup

/**
 * カタカナからひらがなへのコンバート
 */
internal fun convertKatakanaToHiragana(katakana: String): String {
    val buf = StringBuffer()
    for (code in katakana) {
        var hiragana = convertKatakanaToHiragana(code)
        buf.append(hiragana)
    }
    return buf.toString()
}

internal fun convertKatakanaToHiragana(code: Char): Char {
    return if (katakana.contains(code)) code.minus(0x60) else code
}

// カタカナ
private val katakana = '\u30A0'..'\u30FF'
