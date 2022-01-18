package com.showka.everpub.publish.notemu

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Paragraph
import com.showka.everpub.novelstructure.TextComponent

class PublisherParagraphs constructor(private val paragraphs: List<Paragraph>) {

    private var grade: NmHanGrade = NmHanGrade.ELEMENTARY_6

    private val separator = "<p class=\"separator\">◇</p>"
    private val letterBorder = "<hr class=\"letterBorder\" />"

    fun publish(): String {
        return this.paragraphs.joinToString(separator = "") { this.publishForEach(it) }
    }

    private fun publishForEach(p: Paragraph): String {
        val ret = when {
            p.isBlockSeparator() -> this.publishSeparator()
            p.isLetterBorder() -> this.publishLetterBorder()
            p.isDescriptive() -> this.publishDescriptive(p)
            p.isQuote() -> this.publishQuote(p)
            else -> ""
        }
        return ret
            .replace("。」".toRegex(), "」")
            .replace("！".toRegex(), "！　").replace("！　」".toRegex(), "！」")
            .replace("？".toRegex(), "？　").replace("？　」".toRegex(), "？」")
    }

    private fun publishLetterBorder() : String {
        return this.letterBorder
    }

    private fun publishSeparator(): String {
        return this.separator
    }

    private fun publishDescriptive(p: Paragraph): String {
        return "<p class=\"descriptive\">" + p.texts.joinToString("") { this.getText(it) } + "</p>"
    }

    private fun publishQuote(p: Paragraph): String {
        var inQuote = true
        val start = "<p class=\"quote\">「"
        val content = p.texts.joinToString("") {
            return@joinToString when {
                it.isSpeakerDefinition() -> ""
                it.isDescriptiveBetweenQuotes() -> {
                    var ret = ""
                    if (inQuote) {
                        ret += "」"
                    }
                    // new line & insert BLANK at the top of line
                    ret += "<br>"
                    ret += "　"
                    ret += this.getText(it);
                    inQuote = false
                    ret
                }
                // it is quote
                else -> {
                    var ret = ""
                    if (!inQuote) {
                        ret += "<br>"
                        ret += "「"
                    }
                    ret += this.getText(it)
                            .replace("「".toRegex(), "『").replace("」".toRegex(), "』")
                    inQuote = true
                    ret
                }
            }
        }
        val end = if (inQuote) "」</p>" else "</p>"

        return (start + content + end)
    }

    private fun getText(text: TextComponent): String {
        val content = text.line.tokens.joinToString("") {
            if (it.getHanGrade().isHigher(this.grade)) {
                // "|春樹《はるき》"
                val hrList = it.getHanReading()
                hrList.joinToString("") { map ->
                    if (map.isHan) {
                        "|${map.surface}《${map.reading}》"
                    } else {
                        map.surface
                    }
                }
            } else {
                it.getSurface()
            }
        }
        return if (text.isEmphasis()) "<strong>$content</strong>" else content
    }
}