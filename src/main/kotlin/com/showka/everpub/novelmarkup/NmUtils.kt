package com.showka.everpub.novelmarkup


// カタカナ
private val katakana = '\u30A0'..'\u30FF'
// ひらがな
private val hiragana = '\u3040'..'\u309F'
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

/**
 * ひらがなならtrue
 */
internal fun isHiragana(code: Char): Boolean {
    return hiragana.contains(code)
}
