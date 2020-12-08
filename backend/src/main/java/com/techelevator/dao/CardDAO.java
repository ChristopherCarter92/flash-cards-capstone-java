package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;

import java.util.List;


public interface CardDAO {

    Card getCard(int cardId);

    List<Card> getCards(long userID);

    void deleteCard(long cardId);

    Card createCard(CardDTO cardDTO, int userId);

}
