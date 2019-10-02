package com.showka.everpub.web.u01

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/u01")
open class U01G001Controller {
    @RequestMapping("/g001")
    open fun login(@ModelAttribute form: U01G001Form, model: ModelAndView): ModelAndView {
        model.addObject("message", "login...")
        model.viewName = "/u01/u01g001"
        return model
    }
}