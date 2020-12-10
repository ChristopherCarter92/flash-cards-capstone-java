package com.techelevator.dao;

import com.techelevator.model.Deck;

import java.util.List;

public interface DeckDAO {

    public Deck createDeck();

    public Deck getDeck(int deckId, String username);

    public List<String> getDeckTitles(String username);

    public Deck updateDeck();

    public boolean deleteDeck();

}
