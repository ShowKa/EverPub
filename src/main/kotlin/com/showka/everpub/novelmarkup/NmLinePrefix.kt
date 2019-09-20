package com.showka.everpub.novelmarkup

/**
 * 行頭記号
 */
enum class NmLinePrefix() {
    /** #セリフ開始 */
    QUOTE,
    /** >セリフ間の地の文 */
    DESCRIPTIVE_BETWEEN_QUOTES,
    /** !強調 */
    EMPHASIS,
    /** ◇ブロック分割=段落の集まり */
    BLOCK_SEPARATOR,
    /** なし */
    NOTHING
}