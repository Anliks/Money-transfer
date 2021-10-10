package com.anliks.transaction.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Здрасте");
        return "home";
    }


   // @GetMapping("/transfer")
   // public String transfer(Model model) {
     //   model.addAttribute("title", "Здрасте");
      //  return "transfer";

    //}
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Здрасте");
        return "about";
    }
}
