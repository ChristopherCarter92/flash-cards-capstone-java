package com.techelevator.controller;
import com.techelevator.dao.CardDAO;
import com.techelevator.dao.DeckDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DeckController {

    private CardDAO cardDAO;
    private DeckDAO deckDAO;
    private UserDAO userDAO;

    public DeckController(DeckDAO deckDAO, UserDAO userDAO, CardDAO cardDAO) {
        this.deckDAO = deckDAO;
        this.userDAO = userDAO;
        this.cardDAO = cardDAO;
    }

    @GetMapping(path="/decks/{deckId}")
    public Deck fetchDeckById(@PathVariable int deckId, Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        return deckDAO.getDeck(deckId, currentUser.getUsername());
    }

    @GetMapping(path="/decks")
    public List<DeckDTO> fetchDecksInfo(Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        return deckDAO.getDecksInfo(currentUser.getUsername());
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/decks")
    @ResponseStatus(HttpStatus.CREATED)
    public Deck addDeck(@RequestBody DeckDTO deckDTO, Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        return deckDAO.createDeck(deckDTO, currentUser.getUsername());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/decks/{deckId}/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCardsToDeck(@PathVariable int deckId, @RequestBody List<CardDTO> cardDTOs, Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        for(CardDTO item : cardDTOs) {
            Card card = cardDAO.createCard(item, currentUser.getId().intValue());
            deckDAO.addCardToDeck(card.getCardId(), deckId);
        }
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/decks/{deckId}/cardIds")
    public void addCardIdsToDeck(@PathVariable int deckId, @RequestBody List<Integer> cardIds, Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        deckDAO.addCardsToDeck(deckId, cardIds);
    }

//    @PreAuthorize("isAuthenticated()")
//    @PutMapping("/decks/{deckId}")
//    public Deck updateDeck(@PathVariable int deckId, Principal principal, @RequestBody DeckDTO deckDTO) {
//        User currentUser = userDAO.findByUsername(principal.getName());
//        Deck deck = deckDAO.updateDeck(deckDTO, currentUser.getUsername(), deckId);
//        if (deck == null) {
//            throw new ResourceAccessException("You are not authorized to update this Deck");
//        }else {
//            return deck;
//        }
//    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/decks/{deckId}")
    public Deck updateDeck(@PathVariable int deckId, Principal principal, @RequestBody Deck deck) {
        User currentUser = userDAO.findByUsername(principal.getName());
        Deck myDeck = deckDAO.modifyDeck(deck, currentUser.getUsername());
        if (myDeck == null) {
            throw new ResourceAccessException("You are not authorized to update this Deck");
        } else {
            return myDeck;
        }
    }


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/decks/{deckId}")
    public void removeCardsFromDeck(@PathVariable int deckId, List<Integer> cardIds, Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        boolean deleted = deckDAO.removeCardsInDeck(deckId, cardIds);
        if(!deleted) {
            throw new ResourceAccessException("You are not authorized to remove that card");
        }
    }

    @ResponseStatus(HttpStatus.GONE)
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/decks")
    public void removeDeck( Principal principal, @RequestBody Integer deckId) {
        User currentUser = userDAO.findByUsername(principal.getName());
        deckDAO.deleteDeck(deckId);

    }

}
