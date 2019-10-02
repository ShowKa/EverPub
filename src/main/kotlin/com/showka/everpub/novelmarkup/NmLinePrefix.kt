package com.showka.everpub.novelmarkup

/**
 * 行頭記号
 */
enum class NmLinePrefix(private val mark: String) {
    /** #セリフ開始 */
    QUOTE("#"),
    /** >セリフ間の地の文 */
    DESCRIPTIVE_BETWEEN_QUOTES(">"),
    /** !強調 */
    EMPHASIS("!"),
    /** ◇ブロック分割=段落の集まり */
    BLOCK_SEPARATOR("◇"),
    /** ◇ブロック分割=段落の集まり */
    COMMENT_BORDER("ーー"),
    /** なし */
    NOTHING("");

    /** Prefix除去 */
    fun removePrefix(text: String): String {
        if (this == NOTHING) {
            return text
        }
        return text.replace("^${this.mark}".toRegex(), "")
    }

    // static
    companion object {
        /** マッチング */
        @JvmStatic
        fun type(text: String): NmLinePrefix {
            return when {
                text.startsWith(QUOTE.mark) -> QUOTE
                text.startsWith(DESCRIPTIVE_BETWEEN_QUOTES.mark) -> DESCRIPTIVE_BETWEEN_QUOTES
                text.startsWith(EMPHASIS.mark) -> EMPHASIS
                text.startsWith(BLOCK_SEPARATOR.mark) -> BLOCK_SEPARATOR
                text.startsWith(COMMENT_BORDER.mark) -> COMMENT_BORDER
                else -> NOTHING
            }
        }
    }
}