package com.showka.everpub.publish

import com.showka.everpub.novelmarkup.NmLine

abstract class PublishingBook(val title: String, lines: List<NmLine>) {

    private val paragraphs = mutableListOf<Paragraph>()

    init {
        // 行リスト
        var tmpList = mutableListOf<PublishingText>()
        // 前行
        var prev: PublishingText? = null
        // パラグラフ生成
        for (line in lines) {
            if (!line.isBlank()) {
                // 空じゃない
                // 行追加
                var text = PublishingText(line, prev)
                prev = text
                tmpList.add(text)
                // ブロック分割など、パラグラフ終了条件を満たす場合、次のパラグラフに移行
                if (text.shouldBeEndOfParagraph()) {
                    var paragraph = Paragraph(tmpList)
                    paragraphs.add(paragraph)
                    tmpList = mutableListOf()
                }
            } else {
                // 行リストをパラグラフに
                // ただし行リストが空の場合は次の処理へ
                if (tmpList.isEmpty()) {
                    continue
                }
                var paragraph = Paragraph(tmpList)
                paragraphs.add(paragraph)
                tmpList = mutableListOf()
            }
        }
    }
}