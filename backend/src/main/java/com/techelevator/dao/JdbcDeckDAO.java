package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
    public Deck getDeck(int deckId) {
        Deck deck = new Deck();
        String sql = "SElECT decks.deck_id, title, username, card_deck.card_id " +
                     "FROM decks JOIN card_deck ON decks.deck_id = card_deck.deck_id " +
                     "WHERE decks.deck_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, deckId);

        while (rowSet.next()) {
            deck = mapRowToDeck(rowSet);
        }
        return deck;
    }

    @Override
    public Deck updateDeck() {
        return null;
    }

    @Override
    public boolean deleteDeck() {
        return false;
    }


    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck mappedDeck = new Deck();

        mappedDeck.setDeckId(rowSet.getInt("deck_id"));
        mappedDeck.setTitle(rowSet.getNString("title"));
        mappedDeck.setUsername(rowSet.getString("username"));
        mappedDeck.setCards(rowSet.getInt("card_id"));

        return mappedDeck;
    }
}
