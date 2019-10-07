package com.showka.everpub.service

import com.evernote.clients.ClientFactory
import com.evernote.edam.notestore.NoteFilter
import com.evernote.edam.type.Note
import com.evernote.edam.type.NoteSortOrder
import com.showka.everpub.auth.AuthBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoteSearch {

    @Autowired
    lateinit var authBean: AuthBean

    fun search(tag: String, intitle: String): List<Note> {
        // query
        val query = when {
            tag.isNotBlank() && intitle.isNotBlank() ->
                "intitle:\"$intitle\" tag:\"$tag\""
            tag.isNotBlank() ->
                "tag:\"$tag\""
            intitle.isNotBlank() ->
                "intitle:\"$intitle\""
            else -> ""
        }
        // get notebook
        val noteStore = ClientFactory(authBean.evernoteAuth).createNoteStoreClient()
        val noteBook = noteStore.defaultNotebook
        val filter = NoteFilter()
        filter.notebookGuid = noteBook.guid
        filter.words = query
        filter.order = NoteSortOrder.TITLE.value
        filter.isAscending = true
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