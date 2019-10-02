package com.showka.everpub.novelmarkup

class NmHanReadingMapList(private val token: NmToken) : ArrayList<NmHanReadingMapList.NmHanReadingMap>(5) {
    init {
        // 平仮名・漢字の塊で分割
        val chunkList = ChunkList(this.token.getSurface())
        // NmHanReadingMap
        if (chunkList.size == 1) {
            // 漢字のみ or 平仮名のみの場合
            this.add(NmHanReadingMap(chunkList[0], this.token.getHiragana(), isHan = !chunkList.isHiraganaChunk(0)))
            // end init
        } else {
            var reading = this.token.getHiragana()
            var firstRemoved = false
            var lastRemoved = false
            // 一時保管庫
            val tmpList = ArrayList<NmHanReadingMap?>(chunkList.size)
            for (i in 0..chunkList.lastIndex) tmpList.add(null)
            // 最初・最後が平仮名の場合確実に抽出できる
            if (chunkList.isHiraganaChunk(0)) {
                val element = NmHanReadingMap(chunkList.originList[0], reading.substring(0, chunkList[0].length), false)
                tmpList[0] = element
                reading = reading.substring(chunkList[0].length, reading.length)
                firstRemoved = true
            }
            if (chunkList.isHiraganaChunk(chunkList.lastIndex)) {
                val startIndex = reading.length - chunkList.last().length
                val element = NmHanReadingMap(chunkList.originList.last(), reading.substring(startIndex), false)
                tmpList[chunkList.lastIndex] = element
                reading = reading.substring(0, startIndex)
                lastRemoved = true
            }
            // remove終了後、漢字チャンク一つだけの場合
            if (1 == (chunkList.size
                            - (if (firstRemoved) 1 else 0)
                            - (if (lastRemoved) 1 else 0))) {
                val i = if (firstRemoved && lastRemoved) 1 else if (firstRemoved) 1 else 0
                val element = NmHanReadingMap(chunkList.originList[i], reading, true)
                tmpList[i] = element
            } else {
                // 残っているパターン「漢字・ひらがな・漢字」
                // おそらくはないパターン「漢字・ひらがな・漢字・ひらがな・漢字」は無視....
                val i = 1 + (if (firstRemoved) 1 else 0)
                // 平仮名チャンクを抽出
                val chunk = chunkList[i]
                val element = NmHanReadingMap(chunkList.originList[i], chunk, false)
                tmpList[i] = element
                // 平仮名開始位置
                val indexOfReading = reading.indexOf(chunk, 1)
                val elementPrev = NmHanReadingMap(chunkList.originList[i - 1], reading.substring(0, indexOfReading), true)
                tmpList[i - 1] = elementPrev
                val elementNext = NmHanReadingMap(chunkList.originList[i + 1], reading.substring(indexOfReading + 1), true)
                tmpList[i + 1] = elementNext
            }
            // 上では解析不可の場合ここでエラー
            try {
                tmpList.requireNoNulls()
            } catch (e: Exception) {
                throw RuntimeException("漢字よみがなマッピング失敗 : ${this.token.getSurface()} ")
            }
            this.addAll(tmpList.filterNotNull())
        }
    }

    class NmHanReadingMap(val surface: String, val reading: String, val isHan: Boolean) {
        override fun toString(): String {
            return "$surface : $reading : 漢字=$isHan"
        }
    }

    internal class ChunkList(private val origin: String) : ArrayList<String>(4) {

        // 表層 (カタカナを平仮名に加工)
        private val inHiragana = origin.map {
            convertKatakanaToHiragana(it)
        }.joinToString("")

        // 加工前のチャンク
        internal val originList = arrayListOf<String>()

        init {
            var currentOffset = 0
            var nextIndex = indexOf(currentOffset)
            if (nextIndex == -1) {
                this.add(this.inHiragana)
                this.originList.add(origin)
            } else {
                do {
                    this.add(this.inHiragana.substring(currentOffset, nextIndex))
                    this.originList.add(this.origin.substring(currentOffset, nextIndex))
                    currentOffset = nextIndex
                    nextIndex = indexOf(currentOffset)
                } while (nextIndex != -1)
                this.add(this.inHiragana.substring(currentOffset, this.inHiragana.length))
                this.originList.add(this.origin.substring(currentOffset, this.inHiragana.length))
            }
        }

        /**
         * 引数.indexで指定したのが平仮名チャンクならtrue
         */
        internal fun isHiraganaChunk(index: Int): Boolean {
            return isHiragana(this[index][0])
        }

        /**
         * 漢字・平仮名の境界のindexを返却
         */
        internal fun indexOf(startIndex: Int): Int {
            // トークンは1文字以上という前提でindexを設ける
            for (i in (startIndex + 1) until this.inHiragana.length) {
                if (isHiragana(this.inHiragana[i - 1]) == isHiragana(this.inHiragana[i])) {
                    continue
                }
                return i
            }
            return -1
        }
    }
}

