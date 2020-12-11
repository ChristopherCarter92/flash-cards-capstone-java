import axios from 'axios';

export default {

    getDeck(deckId) {
        return axios.get(`/decks/${deckId}`);
    },

    getAllDecks() {
        return axios.get('/decks');
    },

    addDeck(deck) {

        return axios.post('/decks', deck);
    },
    

}