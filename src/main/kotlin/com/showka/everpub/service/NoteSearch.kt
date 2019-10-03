package com.showka.everpub.service

import com.evernote.clients.ClientFactory
import com.evernote.edam.notestore.NoteFilter
import com.evernote.edam.type.Note
import com.showka.everpub.auth.AuthBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoteSearch {

    @Autowired
    lateinit var authBean: AuthBean

    fun search(tag: String, intitle: String): List<Note> {
        // query
        val query = "intitle:\"$intitle\" tag:\"$tag\""
        // get notebook
        val noteStore = ClientFactory(authBean.evernoteAuth).createNoteStoreClient()
        val noteBook = noteStore.defaultNotebook
        val filter = NoteFilter()
        filter.notebookGuid = noteBook.guid
        filter.words = query
        val notes = noteStore.findNotes(filter, 0, 99)
        // convert
        val list = arrayListOf<Note>()
        val iterator = notes.notesIterator
        while (iterator.hasNext()) {
            val note = iterator.next()
            val full = noteStore.getNote(note.guid, true, true,
                    true, true)
            list.add(full)
        }
        return list
    }

}