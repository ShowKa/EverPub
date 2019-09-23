package com.showka.everpub.evernote

class Sentences(sentenceList: List<Sentence>) : ArrayList<Sentence>() {

    init {
        this.addAll(sentenceList)
    }
}


