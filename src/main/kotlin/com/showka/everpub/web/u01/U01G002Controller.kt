package com.showka.everpub.web.u01

import com.evernote.auth.EvernoteAuth
import com.evernote.auth.EvernoteService
import com.evernote.clients.ClientFactory
import com.showka.everpub.auth.AuthBean
import org.scribe.model.Token
import org.scribe.model.Verifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/u01")
class U01G002Controller {

    @Autowired
    lateinit var authBean: AuthBean

    @RequestMapping("/g002")
    open fun menu(@ModelAttribute form: U01G002Form, model: ModelAndView): ModelAndView {
        // User Authorization
        model.viewName = "/u01/u01g002"
        model.addObject("token", form.oauth_token)
        model.addObject("lnb", form.sandbox_lnb)
        model.addObject("verifier", form.oauth_verifier)
        // get Access Token
        val token = Token(authBean.requestToken, authBean.requestTokenSecret)
        val verifier = Verifier(form.oauth_verifier)
        val service = authBean.service
        val accessToken = service.getAccessToken(token, verifier)
        val evernoteAuth = EvernoteAuth.parseOAuthResponse(EvernoteService.PRODUCTION, accessToken.rawResponse)
        authBean.accessToken = accessToken
        authBean.evernoteAuth = evernoteAuth
        model.addObject("accessToken", evernoteAuth.token)
        model.addObject("noteStoreUrl", evernoteAuth.noteStoreUrl)
        // get note
        val noteStore = ClientFactory(evernoteAuth).createNoteStoreClient()
        val noteBook = noteStore.defaultNotebook
        model.addObject("defaultNotebook", noteBook.name)
        return model
    }
}