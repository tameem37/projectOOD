package bd.edu.diu.cis.classroom.controller;

import bd.edu.diu.cis.classroom.model.Makeup;
import bd.edu.diu.cis.classroom.service.MakeupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PrimaryController {

    @Autowired
    private MakeupService makeupService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        if (principal == null)
            return "redirect:/login";

        model.addAttribute("title", "Makeup Ecommerce");
        model.addAttribute("makeup", new Makeup());
        model.addAttribute("makeups", makeupService.getAll());
        return "index";
    }
}
