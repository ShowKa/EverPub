package com.showka.everpub.sample

import com.evernote.auth.EvernoteAuth
import com.evernote.auth.EvernoteService
import com.evernote.clients.ClientFactory
import com.evernote.edam.notestore.NoteFilter

fun main(args: Array<String>): Unit {
    // get token from https://sandbox.evernote.com/api/DeveloperToken.action
    val token = System.getenv("token")
    val auth = EvernoteAuth(EvernoteService.SANDBOX, token)
    val factory = ClientFactory(auth)
    val noteStore = factory.createNoteStoreClient()
    val noteBook = noteStore.defaultNotebook
    val filter = NoteFilter()
    filter.notebookGuid = noteBook.guid
    val notes = noteStore.findNotes(filter, 0, 50)
    val iterator = notes.notesIterator
    while (iterator.hasNext()) {
        val note = iterator.next()
        val full = noteStore.getNote(note.guid, true, true,
                true, true)
        val content = full.content
        println("${full.title} --------------------------------------------------")
        // Stringだけど、内容はXML
        println(content)
    }
}