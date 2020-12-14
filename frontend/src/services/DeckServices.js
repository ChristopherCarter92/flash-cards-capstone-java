import axios from 'axios';
import store from '@/store/index.js';

export default {

    getDeck(deckId) {
        return axios.get(`/decks/${deckId}`)
    },

    getAllDecks() {
        return axios.get('/decks').then(response => {
            store.commit('SET_DECKS', response.data);
            return response;
        });
    },

    addDeck(deck) {

        return axios.post('/decks', deck);
    },

    addNewCardsToDeck(deckId, cardList) {
        return axios.post(`/decks/${deckId}/cards`, cardList );
    },

    updateDeck(deck) {
        return axios.put(`/decks/${deck.deckId}`, deck);
    },

    addMultipleCardsToDeck(deckId, cardIdList) {
        return axios.post(`/decks/${deckId}/cardIds`, cardIdList);
    },

    deleteThisDeck(deckId) {
        return axios.delete('/decks', deckId);
    }
    

}