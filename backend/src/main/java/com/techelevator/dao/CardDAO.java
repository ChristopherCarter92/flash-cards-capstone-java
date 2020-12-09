package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;

import java.util.List;


public interface CardDAO {

    Card getCard(int cardId);

    List<Card> getCards(long userID);

    boolean deleteCard(long cardId, long userId);

    Card createCard(CardDTO cardDTO, int userId);

    Card updateCard(CardDTO cardDTO, int userId, int cardId);

}
