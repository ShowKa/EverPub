package com.showka.everpub.web.u01

import com.showka.everpub.auth.AuthBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/u01")
open class U01G001Controller {

    @Autowired
    lateinit var authBean: AuthBean

    // methods
    @RequestMapping("/g001")
    open fun login(@ModelAttribute form: U01G001Form, model: ModelAndView): String {
        val authService = authBean.service
        val token = authService.requestToken
        authBean.requestToken = token.token
        authBean.requestTokenSecret = token.secret
        // auth
        val url = authService.getAuthorizationUrl(token)
        return "redirect:$url"
    }

}