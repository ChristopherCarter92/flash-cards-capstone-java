package com.techelevator.controller;


import com.techelevator.dao.CardDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Card;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("permitAll")
@CrossOrigin
public class CardController {

    private CardDAO cardDAO;
    private UserDAO userDAO;

    public CardController(CardDAO cardDAO, UserDAO userDAO) {
        this.cardDAO = cardDAO;
        this.userDAO = userDAO;
    }

    @GetMapping(path = "/cards/{id}")
    public Card fetchCardById(@PathVariable int id) {
        return cardDAO.getCard(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = "/cards")
    public List<Card> fetchAllCards(Principal principal){
        User currentUser = userDAO.findByUsername(principal.getName());
        return cardDAO.getCards(currentUser.getId());
    }


}
