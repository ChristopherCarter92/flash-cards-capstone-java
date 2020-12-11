package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;
import com.techelevator.model.Deck;
import com.techelevator.model.DeckDTO;

import javax.validation.Valid;
import java.util.List;

public interface DeckDAO {

    public Deck createDeck(DeckDTO deckDTO, String username);

    public boolean addCardToDeck(int cardId, int deckId);

    public Deck getDeck(int deckId, String username);

    public List<DeckDTO> getDecksInfo(String username);

    public Deck updateDeck();

    public boolean deleteDeck();

}
