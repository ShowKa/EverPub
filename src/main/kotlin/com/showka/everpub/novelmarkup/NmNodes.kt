package com.showka.everpub.novelmarkup

class NmNodes(text: String) : ArrayList<NmNode>() {
    constructor() : this("")

    init {
        // TODO 形態素解析
        val words = text.split("".toRegex())
        words.forEach {
            val node = NmNodePlainText(it)
            this.add(node)
        }
    }
}