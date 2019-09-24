package com.showka.everpub.publish

interface Publisher {

    /** 出力ファイルへのパース用文字列 */
    fun publish(): String

    /** チャプターのファイル生成 */
    fun generateFile(path: String): Unit
}