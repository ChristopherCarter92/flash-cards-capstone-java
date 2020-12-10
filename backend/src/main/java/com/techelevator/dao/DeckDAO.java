package com.techelevator.dao;

import com.techelevator.model.Deck;

public interface DeckDAO {

    public Deck createDeck();

    public Deck getDeck(int deckId, String username);

    public Deck updateDeck();

    public boolean deleteDeck();

}
