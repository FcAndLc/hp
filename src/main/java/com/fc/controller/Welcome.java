package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("welcome")
@Controller
public class Welcome {
    @RequestMapping("welcome")
    public ModelAndView welcome(){
        return new ModelAndView("welcome");
    }
}
