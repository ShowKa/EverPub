package com.showka.everpub.web.u02

import com.showka.everpub.evernote.Note
import com.showka.everpub.novelmarkup.NmHanGrade
import com.showka.everpub.novelmarkup.NmLine
import com.showka.everpub.novelstructure.Chapter
import com.showka.everpub.publish.PublishService
import com.showka.everpub.publish.docx.PublisherDocx
import com.showka.everpub.publish.epub.PublisherEpub
import com.showka.everpub.publish.notemu.PublisherNotemu
import com.showka.everpub.service.NoteSearch
import freemarker.template.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

@Controller
@RequestMapping("/u02")
open class U02G001Controller {

    @Autowired
    lateinit var noteSearch: NoteSearch

    @Autowired
    lateinit var publisherService: PublishService

    @RequestMapping("/g001/epub")
    open fun epub(@ModelAttribute form: U02G001Form, model: ModelAndView): ModelAndView {
        // search
        val notes = noteSearch.search(tag = form.tag, intitle = form.title)
        val chapters = mutableListOf<Map<String, Any>>()
        notes.forEach {
            println(it.title)
            val note: Note = Note(it)
            val lines = note.getSentences().map { sentence ->
                NmLine(sentence.getPlainText())
            }
            val chapter = Chapter(it.title, lines)
            val publisher = PublisherEpub(chapter)
            publisher.setGrade(NmHanGrade.ELEMENTARY_6)
            val title = it.title.replace("\\s+".toRegex(), "")
            val fileName = "$title.xhtml"
            publisherService.publish(publisher, "${form.path}/OEBPS/text/$fileName")

            // chapter data
            val chap = mutableMapOf<String, Any>()
            chap["fileName"] = fileName
            chap["title"] = title
            chap["id"] = title
            chapters.add(chap)
        }
        // data for templates
        val dataForTemplates = mutableMapOf<String, Any>()
        dataForTemplates["chapters"] = chapters
        // free marker config
        val freeMarkerConfig = Configuration(Configuration.VERSION_2_3_23)
        val file = ClassPathResource("/epub").file
        freeMarkerConfig.setDirectoryForTemplateLoading(file)
        /*
         * content.opf
         */
        val contentTemplate = freeMarkerConfig.getTemplate("/content.opf.ftl")
        val contentFile = File("${form.path}/OEBPS/content.opf")
        val contentWriter = PrintWriter(BufferedWriter(FileWriter(contentFile)))
        contentTemplate.process(dataForTemplates, contentWriter)
        /*
         * toc.ncx
         */
        val tocTemplate = freeMarkerConfig.getTemplate("/toc.ncx.ftl")
        val tocFile = File("${form.path}/OEBPS/toc.ncx")
        val tocWriter = PrintWriter(BufferedWriter(FileWriter(tocFile)))
        tocTemplate.process(dataForTemplates, tocWriter)
        // set form
        model.addObject("tag", form.tag)
        model.addObject("title", form.title)
        model.addObject("path", form.path)
        model.addObject("notebook", "default note book....")
        // set view
        model.viewName = "/u02/u02g001"
        return model
    }

    @RequestMapping("/g001/docx")
    open fun docx(@ModelAttribute form: U02G001Form, model: ModelAndView): ModelAndView {
        // search
        val notes = noteSearch.search(tag = form.tag, intitle = form.title)
        notes.forEach {
            println(it.title)
            val note: Note = Note(it)
            val lines = note.getSentences().map { sentence ->
                NmLine(sentence.getPlainText())
            }
            val chapter = Chapter(it.title, lines)
            val publisher = PublisherDocx(chapter)
            val title = it.title.replace("\\s+".toRegex(), "")
            val fileName = "$title.docx"
            publisherService.publish(publisher, "${form.path}/$fileName")
        }
        // set form
        model.addObject("tag", form.tag)
        model.addObject("title", form.title)
        model.addObject("path", form.path)
        model.addObject("notebook", "default note book....")
        // set view
        model.viewName = "/u02/u02g001"
        return model
    }

    @RequestMapping("/g001/notemu")
    open fun notemu(@ModelAttribute form: U02G001Form, model: ModelAndView): ModelAndView {
        // search
        val notes = noteSearch.search(tag = form.tag, intitle = form.title)
        val chapters = mutableListOf<Map<String, Any>>()
        notes.forEach {
            println(it.title)
            val note: Note = Note(it)
            val lines = note.getSentences().map { sentence ->
                NmLine(sentence.getPlainText())
            }
            val chapter = Chapter(it.title, lines)
            val publisher = PublisherNotemu(chapter)
            val title = it.title.replace("\\s+".toRegex(), "")
            val fileName = "$title.html"
            publisherService.publish(publisher, "${form.path}/$fileName")
        }
        // set form
        model.addObject("tag", form.tag)
        model.addObject("title", form.title)
        model.addObject("path", form.path)
        model.addObject("notebook", "default note book....")
        // set view
        model.viewName = "/u02/u02g001"
        return model
    }
}