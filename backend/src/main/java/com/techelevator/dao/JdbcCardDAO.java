package com.techelevator.dao;


import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDAO implements CardDAO {

    private JdbcTemplate jdbcTemplate;


    public JdbcCardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Card getCard(int id) {
        Card newCard = new Card();
        String sql = "SELECT card_id, question, answer, tags, user_id FROM cards WHERE card_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        while (rowSet.next()) {
            newCard = mapRowToCard(rowSet);
        }
        return newCard;
    }

    @Override
    public List<Card> getCards(long userID) {
        List<Card> myCards = new ArrayList<>();
        String sql = "SELECT card_id, question, answer, tags, user_id FROM cards WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userID);
        while (rowSet.next()) {
            Card newCard = mapRowToCard(rowSet);
            myCards.add(newCard);
        }
        return myCards;
    }

    @Override
    public void deleteCard(long cardId) {
        String sql = "DELETE FROM card_deck WHERE card_id = ?;";
        jdbcTemplate.update(sql, cardId);

        String sql1 = "DELETE FROM cards WHERE card_id = ?;";
        jdbcTemplate.update(sql1, cardId);
    }

    @Override
    public Card createCard(@Valid CardDTO cardDTO, int userId) {
        String sql = "INSERT INTO cards (question, answer, tags, user_id) VALUES (?, ?, ?, ?) RETURNING card_id;";
        Long id =jdbcTemplate.queryForObject(sql, Long.class, cardDTO.getQuestion(), cardDTO.getAnswer(), cardDTO.getTags(), userId);
        Card myCard = new Card();
        myCard.setCardId(id.intValue());
        myCard.setAnswer(cardDTO.getAnswer());
        myCard.setQuestion(cardDTO.getQuestion());
        myCard.setTags(cardDTO.getTags());
        myCard.setUserId(userId);
        return myCard;
    }

    @Override
    public Card updateCard(@Valid CardDTO cardDTO, int userId, int cardId) {
            String sql = "UPDATE cards SET question = ?, answer = ?, tags = ?, user_id = ?WHERE card_id = ? AND user_id = ?;";
            int returnedValue = jdbcTemplate.update(sql, cardDTO.getQuestion(), cardDTO.getAnswer(), cardDTO.getTags(), userId, cardId, userId);
            if(returnedValue == 1) {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT user_id, card_id, question, answer, tags FROM cards WHERE card_id = ?;", cardId );
            if(rowSet.next()) {
                Card myCard = mapRowToCard(rowSet);
                return myCard;
            }
        }

        return null;

    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card newCard = new Card();
        newCard.setCardId(rowSet.getInt("card_id"));
        newCard.setQuestion(rowSet.getString("question"));
        newCard.setAnswer(rowSet.getString("answer"));
        newCard.setTags(rowSet.getString("tags"));
        newCard.setUserId(rowSet.getInt("user_id"));
        return newCard;
    }

}
