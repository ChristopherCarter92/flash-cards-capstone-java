import axios from 'axios';


export default {

    getCard(id) {
        return axios.get(`/cards/${id}`);
    },

    addCard(card) {
        return axios.post('/cards', card);
    },

    getAllCards() {
        return axios.get('/cards');
    },

    deleteCard(id) {
        return axios.delete(`/cards/${id}`);
    },

    updateCard(cardId, card) {
        return axios.put(`/cards/${cardId}`, card);
    }

}