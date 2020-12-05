package com.techelevator.controller;


import com.techelevator.dao.CardDAO;
import com.techelevator.model.Card;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("permitAll")
@CrossOrigin
public class CardController {

    private CardDAO cardDAO;

    public CardController(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @GetMapping(path = "/cards/{id}")
    public Card fetchCardById(@PathVariable int id) {
        return cardDAO.getCard(id);
    }

    @GetMapping(path = "/cards")
    public List<Card> fetchAllCards(){
        return cardDAO.getCards();
    }


}
