package com.techelevator.dao;


import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
        String sql = "SELECT id, question, answer, tags, user_id FROM cards WHERE id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        while (rowSet.next()) {
            newCard = mapRowToCard(rowSet);
        }
        return newCard;
    }

    @Override
    public List<Card> getCards(long userID) {
        List<Card> myCards = new ArrayList<>();
        String sql = "SELECT id, question, answer, tags, user_id FROM cards WHERE user_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userID);
        while (rowSet.next()) {
            Card newCard = mapRowToCard(rowSet);
            myCards.add(newCard);
        }
        return myCards;
    }

    @Override
    public void deleteCard(long cardId) {
        String sql = "DELETE FROM cards WHERE id = ?;";
        jdbcTemplate.update(sql, cardId);
    }

    @Override
    public Card createCard(CardDTO cardDTO, int userId) {
        String sql = "INSERT INTO cards (question, answer, tags, user_id) VALUES (?, ?, ?, ?) RETURNING id;";
        Long id =jdbcTemplate.queryForObject(sql, Long.class, cardDTO.getQuestion(), cardDTO.getAnswer(), cardDTO.getTags(), userId);
        Card myCard = new Card();
        myCard.setCardId(id.intValue());
        myCard.setAnswer(cardDTO.getAnswer());
        myCard.setQuestion(cardDTO.getQuestion());
        myCard.setTags(cardDTO.getTags());
        myCard.setUserId(userId);
        return myCard;
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card newCard = new Card();
        newCard.setCardId(rowSet.getInt("id"));
        newCard.setQuestion(rowSet.getString("question"));
        newCard.setAnswer(rowSet.getString("answer"));
        newCard.setTags(rowSet.getString("tags"));
        newCard.setUserId(rowSet.getInt("user_id"));
        return newCard;
    }

}
