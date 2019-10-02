package com.showka.everpub.web.u01

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/u01")
class U01G002Controller {
    @RequestMapping("/g002")
    open fun menu(@ModelAttribute form: U01G002Form, model: ModelAndView): ModelAndView {
        model.viewName = "/u01/u01g002"
        model.addObject("token", form.oauth_token)
        model.addObject("lnb", form.sandbox_lnb)
        model.addObject("verifier", form.oauth_verifier)
        return model
    }
}