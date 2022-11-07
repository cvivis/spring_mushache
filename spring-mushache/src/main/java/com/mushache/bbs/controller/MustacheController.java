package com.mushache.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {

    @GetMapping(value = "/hi")
    public String mustacheCon(Model model) {
        model.addAttribute("username", "ghkd");
        return "greetings";
    }

    @GetMapping(value = "/hi/{id}")
    public String mustacheCon(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("username", "빰빰");
        return "greetings";
    }
}
