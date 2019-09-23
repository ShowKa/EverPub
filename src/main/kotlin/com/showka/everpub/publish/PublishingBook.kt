package com.showka.everpub.publish

import com.showka.everpub.novelmarkup.NmLine

abstract class PublishingBook(val title: String, lines: List<NmLine>) {

    val paragraphs = mutableListOf<Paragraph>()

    init {
        // 行リスト
        var tmpList = mutableListOf<PublishingText>()
        // コメント処理中ならtrue
        var inComment = false
        // 最初と最後の空行を予め除去
        val trimmed = lines.dropWhile { it.isBlank() }.dropLastWhile { it.isBlank() }
        // パラグラフ生成
        for (line in trimmed) {
            val text = PublishingText(line)
            // コメントテキスト
            // コメントボーダー内の行はすべて対象外
            if (text.isCommentBorder()) {
                inComment = !inComment
                continue
            }
            if (inComment) {
                continue
            }
            // 通常テキスト
            when {
                text.shouldBeOneInParagraph() -> {
                    if (tmpList.isNotEmpty()) {
                        val paragraph = Paragraph(tmpList)
                        paragraphs.add(paragraph)
                        tmpList = mutableListOf()
                    }
                    tmpList.add(text)
                    val paragraph = Paragraph(tmpList)
                    paragraphs.add(paragraph)
                    tmpList = mutableListOf()
                }
                text.shouldBeStartOfParagraph() -> {
                    if (tmpList.isNotEmpty()) {
                        val paragraph = Paragraph(tmpList)
                        paragraphs.add(paragraph)
                        tmpList = mutableListOf()
                    }
                    tmpList.add(text)
                }
                text.shouldBeEndOfParagraph() -> {
                    tmpList.add(text)
                    val paragraph = Paragraph(tmpList)
                    paragraphs.add(paragraph)
                    tmpList = mutableListOf()
                }
                else -> tmpList.add(text)
            }
        }
        // 最後に処理が残っていたパラグラフ
        if (tmpList.isNotEmpty()) {
            val paragraph = Paragraph(tmpList)
            paragraphs.add(paragraph)
        }
    }
}
