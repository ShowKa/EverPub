package com.showka.everpub.novelmarkup

import com.showka.everpub.novelmarkup.NmHanGrade.*
import java.io.File

object NmHanGradeFile {

    private const val pathOfFile = "/data/han_grade.csv"

    private val E1: String
    private val E2: String
    private val E3: String
    private val E4: String
    private val E5: String
    private val E6: String

    init {
        val file = File(pathOfFile)
        val lines = file.readLines()
        require(lines.size == 6) { "漢字グレードファイルは6行! でも ${lines.size} 行でした。" }
        E1 = lines[0]
        E2 = lines[1]
        E3 = lines[2]
        E4 = lines[3]
        E5 = lines[4]
        E6 = lines[5]
    }

    fun determine(word: String): NmHanGrade {
        var tmpGrade: NmHanGrade = PRE_ELEMENTARY
        var hanRegex = "\\p{InCjkUnifiedIdeographs}".toRegex()
        loop@ for (c in word) {
            if (true) {
                // TODO 漢字ではないので、次の文字の処理へ
                continue
            }
            when {
                E1.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isGreater(ELEMENTARY_1)) tmpGrade else ELEMENTARY_1
                }
                E2.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isGreater(ELEMENTARY_2)) tmpGrade else ELEMENTARY_2
                }
                E3.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isGreater(ELEMENTARY_3)) tmpGrade else ELEMENTARY_3
                }
                E4.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isGreater(ELEMENTARY_4)) tmpGrade else ELEMENTARY_4
                }
                E5.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isGreater(ELEMENTARY_5)) tmpGrade else ELEMENTARY_5
                }
                E6.contains(c, true) -> {
                    tmpGrade = if (tmpGrade.isGreater(ELEMENTARY_6)) tmpGrade else ELEMENTARY_6
                }
                else -> {
                    tmpGrade = MIDDLE
                    // 最高難易度だとわかったのでloop終了
                    break@loop
                }
            }
        }
        return tmpGrade
    }

    fun determine(token: NmToken): NmHanGrade {
        return this.determine(token.getSurface())
    }
}