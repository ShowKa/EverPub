package com.showka.everpub.web.u01

import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.EvernoteApi
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/u01")
open class U01G001Controller {

    private val apiKey: String = System.getenv("apiKey")
    private val apiSecret: String = System.getenv("apiSecret")

    @RequestMapping("/g001")
    open fun login(@ModelAttribute form: U01G001Form, model: ModelAndView): String {
        // val production = EvernoteApi()
        val environment = EvernoteApi()
        val authService = ServiceBuilder()
                .provider(environment)
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .callback("http://localhost:8080/u01/g002")
                .build()
        val token = authService.requestToken
        val url = authService.getAuthorizationUrl(token)
        return "redirect:$url"
    }

}