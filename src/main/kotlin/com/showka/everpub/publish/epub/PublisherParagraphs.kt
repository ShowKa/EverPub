package com.showka.everpub.publish.epub

import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelstructure.Paragraph
import com.showka.everpub.novelstructure.TextComponent

class PublisherParagraphs constructor(private val paragraphs: List<Paragraph>) {

    private var grade: NmHanGrade = NmHanGrade.MIDDLE

    private val separator = "<p class=\"empty\"><br /></p>"

    fun setGrade(grade: NmHanGrade) {
        this.grade = grade
    }

    fun publish(): String {
        return this.paragraphs.joinToString(separator = "") { this.publishForEach(it) }
    }

    private fun publishForEach(p: Paragraph): String {
        return when {
            p.isBlockSeparator() -> this.publishSeparator()
            p.isDescriptive() -> this.publishDescriptive(p)
            p.isQuote() -> this.publishQuote(p)
            else -> ""
        }
    }

    private fun publishSeparator(): String {
        return this.separator
    }

    private fun publishDescriptive(p: Paragraph): String {
        return "<p>" + p.texts.joinToString("") { this.getText(it) } + "</p>"
    }

    private fun publishQuote(p: Paragraph): String {
        var inQuote = true
        val start = "<p>「"
        val content = p.texts.joinToString("") {
            return@joinToString when {
                it.isSpeakerDefinition() -> ""
                it.isDescriptiveBetweenQuotes() -> {
                    var ret = ""
                    if (inQuote) {
                        ret += "」"
                    }
                    ret += this.getText(it)
                    inQuote = false
                    ret
                }
                // it is quote
                else -> {
                    var ret = ""
                    if (!inQuote) {
                        ret += "「"
                    }
                    // TODO 必要なら括弧内の括弧「」を置換
                    ret += this.getText(it)
                    inQuote = true
                    ret
                }
            }
        }
        val end = if (inQuote) "」</p>" else "</p>"
        return start + content + end
    }

    private fun getText(text: TextComponent): String {
        val content = text.line.tokens.joinToString("") {
            if (it.getHanGrade().isHigher(this.grade)) {
                // "<ruby>${it.getHanSection()}<rt>${it.getRuby()}</rt></ruby>${it.getNotHanSection()}"
                val hrList = it.getHanReading()
                hrList.joinToString("") { map ->
                    if (map.isHan) {
                        "<ruby>${map.surface}<rt>${map.reading}</rt></ruby>"
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