package com.showka.everpub.novelmarkup

class NmHanReadingMapList(private val token: NmToken) : ArrayList<NmHanReadingMap>(5) {
    //    アッという間に
    //    あっというまに
    //    --------------
    //    言い方
    //    いいかた
    //    --------------
    //    追い抜く
    //    おいぬく
    init {
        // 表層 (カタカナを平仮名に)
        val surface = this.token.getSurface().map {
            convertKatakanaToHiragana(it)
        }.joinToString("")
        // 平仮名・漢字の塊で分割
        val surfaceW = SurfaceWrapper(surface)
        var splits = surfaceW.split()
        // NmHanReadingMap
        if (splits.size == 1) {
            // 漢字のみ or 平仮名のみの場合
            this.add(NmHanReadingMap(splits[0], this.token.getHiragana(), isHan = !surfaceW.isHiragana(0)))
        } else {
            // 読み方
            var reading = this.token.getHiragana()
            var removed = ""
            for (i in 0 until splits.size) {
                val chunk = splits[i]
                val indexInReading = reading.indexOf(chunk)
                if (indexInReading >= 0) {
                    // 平仮名 (漢字の場合かならず -1)
                    val element = NmHanReadingMap(chunk, reading.substring(indexInReading, indexInReading + chunk.length), false)
                    this[i] = element
                    // 前セクション (漢字のはず)
                    if (i > 0) {
                        val elementPrev = NmHanReadingMap(splits[i - 1], removed + reading.substring(0, indexInReading), true)
                        this[i - 1] = elementPrev
                        removed = ""
                    }
                    reading = reading.substring(indexInReading + chunk.length, reading.length)
                } else {
                    removed = reading.substring(0, chunk.length)
                    reading = reading.substring(chunk.length, reading.length)
                }
            }
        }
    }

    internal class SurfaceWrapper(private val surface: String) {

        // ひらがな
        private val hiragana = '\u3040'..'\u309F'

        internal fun split(): List<String> {
            var currentOffset = 0
            var nextIndex = indexOf(currentOffset)
            if (nextIndex == -1) {
                return arrayListOf(this.surface)
            }

            val result = ArrayList<String>(5)
            do {
                result.add(this.surface.substring(currentOffset, nextIndex))
                currentOffset = nextIndex
                nextIndex = indexOf(currentOffset)
            } while (nextIndex != -1)

            result.add(this.surface.substring(currentOffset, this.surface.length))
            return result
        }

        internal fun indexOf(current: Int): Int {
            // トークンは1文字以上という前提でindexを設ける
            for (i in (current + 1) until this.surface.length) {
                if (isHiragana(i - 1) == isHiragana(i)) {
                    continue
                }
                return i
            }
            return -1
        }

        internal fun isHiragana(index: Int): Boolean {
            return hiragana.contains(this.surface[index])
        }
    }
}

class NmHanReadingMap(val surface: String, val reading: String, val isHan: Boolean) {
    override fun toString(): String {
        return "$surface : $reading : $isHan"
    }
}

