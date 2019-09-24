package com.showka.everpub.publish

import java.io.PrintWriter

interface Publisher {

    /** 出力ファイルへのパース用文字列 */
    fun publish(): String

    /** チャプターのファイル生成 */
    fun generateFile(path: String) {
        val text = this.publish()
        val writer = PrintWriter(path)
        writer.println(text)
        writer.close()
    }
}