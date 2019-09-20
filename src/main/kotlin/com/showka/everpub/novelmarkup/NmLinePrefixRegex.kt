package com.showka.everpub.novelmarkup

enum class NmLinePrefixRegex(val regex: Regex) {
    /** #セリフ開始 */
    QUOTE("^#".toRegex()),
    /** >セリフ間の地の文 */
    DESCRIPTIVE_BETWEEN_QUOTES("^>".toRegex()),
    /** !強調 */
    EMPHASIS("^!".toRegex()),
    /** ◇ブロック分割=段落の集まり */
    BLOCK_SEPARATOR("^◇".toRegex())
}