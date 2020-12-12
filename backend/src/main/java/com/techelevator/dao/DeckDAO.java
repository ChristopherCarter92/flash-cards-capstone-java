package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;
import com.techelevator.model.Deck;
import com.techelevator.model.DeckDTO;

import javax.validation.Valid;
import java.util.List;

public interface DeckDAO {

    Deck createDeck(DeckDTO deckDTO, String username);

    boolean addCardToDeck(int cardId, int deckId);

    Deck getDeck(int deckId, String username);

    List<DeckDTO> getDecksInfo(String username);

    Deck updateDeck(DeckDTO deckDTO, String username, int deckId);

    void addCardsToDeck(int deckId, List<Integer> cardIds);

    boolean removeCardsInDeck(DeckDTO deckDTO, String username, int deckId, int cardId);

    boolean deleteDeck();

}
