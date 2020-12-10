package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDAO implements DeckDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcDeckDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Deck createDeck() {


        return null;
    }

    @Override
    public Deck getDeck(int deckId, String username) {
        Deck deck = new Deck();
        String sql = "SElECT decks.deck_id, title, username, card_deck.card_id " +
                     "FROM decks JOIN card_deck ON decks.deck_id = card_deck.deck_id " +
                     "WHERE decks.deck_id = ? AND username = ?;";
        SqlRowSet rowSet1 = jdbcTemplate.queryForRowSet(sql, deckId, username.trim());
        SqlRowSet rowSet2 = jdbcTemplate.queryForRowSet(sql, deckId, username.trim());
        List<Integer> list = new ArrayList<>();
        while (rowSet1.next()) {
            mapRowToDeck(rowSet1, deck);
        }
        while (rowSet2.next()) {
            list.add(rowSet2.getInt("card_id"));
        }
        deck.setCardsList(list);
        return deck;
    }

    @Override
    public List<String> getDeckTitles(String username) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT title FROM decks WHERE username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        while (rowSet.next()) {
            list.add(rowSet.getString("title"));
        }
        return list;
    }

    @Override
    public Deck updateDeck() {
        return null;
    }

    @Override
    public boolean deleteDeck() {
        return false;
    }


    private void mapRowToDeck(SqlRowSet rowSet, Deck deck) {

        deck.setDeckId(rowSet.getInt("deck_id"));
        deck.setTitle(rowSet.getString("title"));
        deck.setUsername(rowSet.getString("username"));

    }
}
