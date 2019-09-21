package com.showka.everpub.novelmarkup

class NmLine(private val text: String) {

    private val prefix: NmLinePrefix
    private val tokens: NmTokens

    init {
        var textWithoutPrefix: String
        when {
            // セリフ
            text.matches(NmLinePrefixRegex.QUOTE.regex) -> {
                this.prefix = NmLinePrefix.QUOTE
                textWithoutPrefix = text.replace(NmLinePrefixRegex.QUOTE.regex, "")
            }
            // セリフ間地の文
            text.matches(NmLinePrefixRegex.DESCRIPTIVE_BETWEEN_QUOTES.regex) -> {
                this.prefix = NmLinePrefix.DESCRIPTIVE_BETWEEN_QUOTES
                textWithoutPrefix = text.replace(NmLinePrefixRegex.DESCRIPTIVE_BETWEEN_QUOTES.regex, "")
            }
            // 強調
            text.matches(NmLinePrefixRegex.EMPHASIS.regex) -> {
                this.prefix = NmLinePrefix.EMPHASIS
                textWithoutPrefix = text.replace(NmLinePrefixRegex.EMPHASIS.regex, "")
            }
            // ブロック分割
            text.matches(NmLinePrefixRegex.BLOCK_SEPARATOR.regex) -> {
                this.prefix = NmLinePrefix.BLOCK_SEPARATOR
                textWithoutPrefix = text.replace(NmLinePrefixRegex.BLOCK_SEPARATOR.regex, "")
            }
            // なし
            else -> {
                this.prefix = NmLinePrefix.NOTHING
                textWithoutPrefix = text
            }
        }
        tokens = NmTokens(textWithoutPrefix)
    }
}