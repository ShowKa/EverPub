package com.showka.everpub.novelmarkup

import com.showka.everpub.novelmarkup.NmHanGrade.*
import org.springframework.core.io.ClassPathResource

object NmHanGradeFile {

    private const val pathOfFile = "/data/han_grade.txt"
    // ひらがな
    private val hiragana = '\u3040'..'\u309F'
    // カタカナ
    private val katakana = '\u30A0'..'\u30FF'
    // 漢字
    private val han = '\u4E00'..'\u9FFF'
    // 、 。 「 」
    private val symbol = arrayOf('\u3001', '\u3002', '\u300C', '\u300D')
    // 漢数字
    private val hanNumber = "一二三四五六七八九〇".toCharArray()

    private val E1: String
    private val E2: String
    private val E3: String
    private val E4: String
    private val E5: String
    private val E6: String

    init {
        val file = ClassPathResource(pathOfFile).file
        val lines = file.readLines()
        require(lines.size == 6) { "漢字グレードファイルは6行! でも ${lines.size} 行でした。" }
        E1 = lines[0]
        E2 = lines[1]
        E3 = lines[2]
        E4 = lines[3]
        E5 = lines[4]
        E6 = lines[5]
    }

    /**
     * 単語の漢字グレードを評価する。
     * 難しい漢字が含まれていれば、グレードが高くなる。
     */
    fun determine(token: NmToken): NmHanGrade {
        return this.determine(token.getSurface())
    }

    internal fun determine(word: String): NmHanGrade {
        var tmpGrade: NmHanGrade = PRE_ELEMENTARY
        loop@ for (c in word) {
            if (!this.validateHan(c)) {
                // 評価対象漢字ではない
                // 次の文字の処理へ
                continue@loop
            }
            when {
                E1.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isHigher(ELEMENTARY_1)) tmpGrade else ELEMENTARY_1
                }
                E2.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isHigher(ELEMENTARY_2)) tmpGrade else ELEMENTARY_2
                }
                E3.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isHigher(ELEMENTARY_3)) tmpGrade else ELEMENTARY_3
                }
                E4.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isHigher(ELEMENTARY_4)) tmpGrade else ELEMENTARY_4
                }
                E5.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isHigher(ELEMENTARY_5)) tmpGrade else ELEMENTARY_5
                }
                E6.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isHigher(ELEMENTARY_6)) tmpGrade else ELEMENTARY_6
                }
                else -> {
                    tmpGrade = MIDDLE
                    // 最高難易度と判明
                    // loop終了
                    break@loop
                }
            }
        }
        return tmpGrade
    }

    /**
     * 引数.文字 = 評価対象の漢字 -> true
     */
    internal fun validateHan(c: Char): Boolean {
        if (c in hiragana || c in katakana || c in symbol || c in hanNumber || c !in han) {
            return false
        }
        return true
    }
}