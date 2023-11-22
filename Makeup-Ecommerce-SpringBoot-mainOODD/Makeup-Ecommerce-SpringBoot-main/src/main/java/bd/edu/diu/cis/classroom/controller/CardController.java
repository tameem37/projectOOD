package bd.edu.diu.cis.classroom.controller;

import bd.edu.diu.cis.classroom.model.Card;
import bd.edu.diu.cis.classroom.model.Makeup;
import bd.edu.diu.cis.classroom.model.User;
import bd.edu.diu.cis.classroom.service.CardService;
import bd.edu.diu.cis.classroom.service.MakeupService;
import bd.edu.diu.cis.classroom.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CardController {

    @Autowired
    private UserDetailsServiceImplement userService;

    @Autowired
    private MakeupService makeupService;

    @Autowired
    private CardService cardService;

    @GetMapping("/card")
    public String card(Model model,
                       Principal principal) {

        if (principal == null) return "redirect:/login";

        List<Card> cards = cardService.listAll(principal.getName());

        double totalPrice = cards.stream()
                .mapToDouble(card -> card.getMakeup().getPrice() * card.getQuantity())
                .sum();

        model.addAttribute("cards", cards);
        model.addAttribute("title", "Card");
        model.addAttribute("makeup", new Makeup());
        model.addAttribute("totalPrice", totalPrice);

        return "card";
    }


    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long makeupId,
            Principal principal,
            RedirectAttributes attributes){

        if (principal == null) return "redirect:/login";

        Makeup makeup = makeupService.get(makeupId);
        User user = userService.getByUserEmail(principal.getName());
        cardService.addToCard(makeup, user);

        attributes.addFlashAttribute("success", "Makeup added successfully");
        return "redirect:/card";
    }

    @GetMapping(value = "/delete-cart/{id}")
    public String deleteItemFromCart(Principal principal, @PathVariable String id){
        if(principal == null){
            return "redirect:/login";
        }else{
            cardService.deleteCard(cardService.get(Long.parseLong(id)));
            return "redirect:/card";
        }
    }

    @PostMapping(value = "/update-cart")
    public String updateItemFromCart(@RequestParam("id") Long cardId,
                                     @RequestParam("quantity") int quantity,
                                     Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            cardService.updateCard(cardId, quantity);
            return "redirect:/card";
        }
    }
}
