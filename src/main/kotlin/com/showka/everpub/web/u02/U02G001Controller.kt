package com.showka.everpub.web.u02

import com.evernote.clients.ClientFactory
import com.evernote.edam.notestore.NoteFilter
import com.showka.everpub.auth.AuthBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/u02")
open class U02G001Controller {

    @Autowired
    lateinit var authBean: AuthBean

    @RequestMapping("/g001")
    open fun menu(@ModelAttribute form: U02G001Form, model: ModelAndView): ModelAndView {
        // query
        val query = "intitle:\"${form.title}\" tag:\"${form.tag}\""
        // get notebook
        val noteStore = ClientFactory(authBean.evernoteAuth).createNoteStoreClient()
        val noteBook = noteStore.defaultNotebook
        val filter = NoteFilter()
        filter.notebookGuid = noteBook.guid
        filter.words = query
        val notes = noteStore.findNotes(filter, 0, 50)
        val iterator = notes.notesIterator
        while (iterator.hasNext()) {
            val note = iterator.next()
            val full = noteStore.getNote(note.guid, true, true,
                    true, true)
            val content = full.content
        }
        // set form
        model.addObject("tag", form.tag)
        model.addObject("title", form.title)
        model.addObject("notebook", noteBook.name)
        // set view
        model.viewName = "/u02/u02g001"
        return model
    }
}