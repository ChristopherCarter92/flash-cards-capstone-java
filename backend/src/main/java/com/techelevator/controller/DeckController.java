package com.techelevator.controller;
import com.techelevator.dao.DeckDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Deck;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DeckController {

    private DeckDAO deckDAO;
    private UserDAO userDAO;

    public DeckController(DeckDAO deckDAO, UserDAO userDAO) {
        this.deckDAO = deckDAO;
        this.userDAO = userDAO;
    }

    @GetMapping(path="/decks/{deckId}")
    public Deck fetchDeckById(@PathVariable int deckId, Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        return deckDAO.getDeck(deckId, currentUser.getUsername());
    }

    @GetMapping(path="/decks")
    public List<String> fetchDeckTitles(Principal principal) {
        User currentUser = userDAO.findByUsername(principal.getName());
        return deckDAO.getDeckTitles(currentUser.getUsername());
    }


}
