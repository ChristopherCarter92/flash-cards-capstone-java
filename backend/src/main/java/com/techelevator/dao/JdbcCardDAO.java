package com.techelevator.dao;


import com.techelevator.model.Card;
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
    public List<Card> getCards() {
        List<Card> myCards = new ArrayList<>();
        String sql = "SELECT id, question, answer, tags, user_id FROM cards;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            Card newCard = mapRowToCard(rowSet);
            myCards.add(newCard);
        }
        return myCards;
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
