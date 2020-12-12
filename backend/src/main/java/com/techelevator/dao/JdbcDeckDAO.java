package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.CardDTO;
import com.techelevator.model.Deck;
import com.techelevator.model.DeckDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDAO implements DeckDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcDeckDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Deck createDeck(DeckDTO deckDTO, String username) {
        String sql = "INSERT INTO decks (username, title) VALUES (?, ?) RETURNING deck_id;";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, username, deckDTO.getTitle());
        Deck myDeck = new Deck();
        myDeck.setDeckId(id.intValue());
        myDeck.setUsername(username);
        myDeck.setTitle(deckDTO.getTitle());
        return myDeck;
    }



    @Override
    public boolean addCardToDeck(int cardId, int deckId) {
        String sql = "INSERT INTO card_deck (deck_id, card_id) VALUES (?, ?);";
        int count = jdbcTemplate.update(sql, deckId, cardId);
        return count == 1;
    }

    @Override
    public void addCardsToDeck(int deckId, List<Integer> cardIds) {
        for (int cardId : cardIds) {
            addCardToDeck(cardId, deckId);
        }

    }

//mapping for remove card from deck, delete record from card_deck table

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
    public List<DeckDTO> getDecksInfo(String username) {
        List<DeckDTO> list = new ArrayList<>();
        String sql = "SELECT title, deck_id FROM decks WHERE username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        while (rowSet.next()) {
            DeckDTO deckDTO = new DeckDTO();
            deckDTO.setDeckId(rowSet.getInt("deck_id"));
            deckDTO.setTitle(rowSet.getString("title"));
            list.add(deckDTO);
        }
        return list;
    }

    @Override
    public Deck updateDeck(DeckDTO deckDTO, String username, int deckId) {
        String sql = "UPDATE decks SET title = ? WHERE username = ? AND deck_id = ?;";
        int returnedValue = jdbcTemplate.update(sql, deckDTO.getTitle(), username, deckId);
        if(returnedValue == 1) {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT title, username, deck_id FROM decks WHERE deck_id = ?;", deckId);
            if(rowSet.next()) {
                Deck myDeck = mapRowToDeck2(rowSet);
                return myDeck;
            }
        }
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

    private Deck mapRowToDeck2(SqlRowSet rowSet) {
        Deck newDeck = new Deck();
        newDeck.setDeckId(rowSet.getInt("deck_id"));
        newDeck.setTitle(rowSet.getString("title"));
        newDeck.setUsername(rowSet.getString("username"));
        return newDeck;
    }
}
