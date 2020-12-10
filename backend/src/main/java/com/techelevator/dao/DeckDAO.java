package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.DeckDTO;

import java.util.List;

public interface DeckDAO {

    public Deck createDeck();

    public Deck getDeck(int deckId, String username);

    public List<DeckDTO> getDecksInfo(String username);

    public Deck updateDeck();

    public boolean deleteDeck();

}
