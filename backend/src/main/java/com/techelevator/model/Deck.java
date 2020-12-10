package com.techelevator.model;


import java.util.List;

public class Deck {

    private int deckId;
    private String title;
    private String username;
    private List<Integer> allCardIds;

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getCards() {
        return null;
    }

    public void setCards(List<Integer> cards) {
        this.allCardIds = cards;
    }
}
