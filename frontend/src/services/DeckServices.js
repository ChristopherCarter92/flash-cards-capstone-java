import axios from 'axios';

export default {

    getDeck(id) {
        return axios.get(`/decks/${id}`);
    },

    getAllDecks() {
        return axios.get('/decks');
    },

    addDeck(deck) {

        return axios.post('/decks', deck);
    },
    

}