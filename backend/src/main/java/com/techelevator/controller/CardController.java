package com.techelevator.controller;


import com.techelevator.dao.CardDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
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

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(path = "/cards/{id}")
    public void removeCard(@PathVariable int id) {
        cardDAO.deleteCard(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public Card addCard(@RequestBody CardDTO cardDTO, Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        return cardDAO.createCard(cardDTO, currentUser.getId().intValue());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/cards/{id}")
    public Card changeCard(@PathVariable int id, Principal principal, @RequestBody CardDTO cardDTO) {
        User currentUser = userDAO.findByUsername(principal.getName());
        return cardDAO.updateCard(cardDTO, currentUser.getId().intValue(), id);
    }


}
