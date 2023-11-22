package bd.edu.diu.cis.classroom.controller;

import bd.edu.diu.cis.classroom.model.Makeup;
import bd.edu.diu.cis.classroom.service.MakeupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class MakeupController {
    @Autowired
    private MakeupService makeupService;

    @PostMapping("/makeup/new")
    public String makeupSave(@ModelAttribute("makeup") Makeup makeup,
                              RedirectAttributes attributes) {
        if (makeup.getName() == null || makeup.getPrice() == 0 || makeup.getImage() == null || makeup.getCategory() == null) {
            attributes.addFlashAttribute("error", "Value cannot be null");
            return "redirect:/";
        }

        makeupService.save(makeup);
        attributes.addFlashAttribute("success", "Makeup Successfully Added");
        return "redirect:/";
    }

    @GetMapping("/makeup/edit/{id}")
    public String makeupEdit(@PathVariable String id,
                              Model model, Principal principal) {

        if (principal == null) return "redirect:/login";

        Makeup makeup = makeupService.get(Long.parseLong(id));
        model.addAttribute("makeupEdit", makeup);
        model.addAttribute("makeup", new Makeup());

        return "edit_makeup";
    }

    @PostMapping("/makeup/edit/save")
    public String makeupEditSave(@ModelAttribute("makeupEdit") Makeup makeupEdit,
                                  Principal principal, RedirectAttributes attributes) {
        if (principal == null) return "redirect:/login";

        Makeup makeup = makeupService.get(makeupEdit.getId());

        makeup.setCategory(makeupEdit.getCategory());
        makeup.setName(makeupEdit.getName());
        makeup.setImage(makeupEdit.getImage());
        makeup.setDescription(makeupEdit.getDescription());
        makeup.setPrice(makeupEdit.getPrice());

        makeupService.save(makeup);
        attributes.addFlashAttribute("success", "Makeup edit successful");
        return "redirect:/";
    }


    @GetMapping("/makeup/delete/{id}")
    public String makeupEdit(@PathVariable String id,
                              Model model, Principal principal,
                              RedirectAttributes attributes) {

        if (principal == null) return "redirect:/login";
        makeupService.delete(Long.parseLong(id));
        attributes.addFlashAttribute("success", "Makeup delete successful");
        return "redirect:/";
    }
}
