package com.showka.everpub.novelmarkup

class NmLine(private val text: String) {

    private val prefix: NmLinePrefix
    private val nodes: NmNodes

    init {
        var textWithoutPrefix: String
        when {
            text.matches(NmLinePrefixRegex.QUOTE.regex) -> {
                this.prefix = NmLinePrefix.QUOTE
                textWithoutPrefix = text.replace(NmLinePrefixRegex.QUOTE.regex, "")
            }
            text.matches(NmLinePrefixRegex.DESCRIPTIVE_BETWEEN_QUOTES.regex) -> {
                this.prefix = NmLinePrefix.DESCRIPTIVE_BETWEEN_QUOTES
                textWithoutPrefix = text.replace(NmLinePrefixRegex.DESCRIPTIVE_BETWEEN_QUOTES.regex, "")
            }
            text.matches(NmLinePrefixRegex.EMPHASIS.regex) -> {
                this.prefix = NmLinePrefix.EMPHASIS
                textWithoutPrefix = text.replace(NmLinePrefixRegex.EMPHASIS.regex, "")
            }
            text.matches(NmLinePrefixRegex.BLOCK_SEPARATOR.regex) -> {
                this.prefix = NmLinePrefix.BLOCK_SEPARATOR
                textWithoutPrefix = text.replace(NmLinePrefixRegex.BLOCK_SEPARATOR.regex, "")
            }
            else -> {
                this.prefix = NmLinePrefix.NOTHING
                textWithoutPrefix = text
            }
        }
        nodes = NmNodes(textWithoutPrefix)
    }
}