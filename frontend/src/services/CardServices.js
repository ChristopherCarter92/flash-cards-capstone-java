import axios from 'axios';
import store from '@/store/index.js';


export default {

    getCard(id) {
        return axios.get(`/cards/${id}`);
    },

    addCard(card) {
        return axios.post('/cards', card);
    },

    getAllCards() {
        return axios.get('/cards').then(response => {
            store.commit('SET_CARDS', response.data);
        });
    },

    deleteCard(id) {
        return axios.delete(`/cards/${id}`);
    },

    updateCard(cardId, card) {
        return axios.put(`/cards/${cardId}`, card);
    }

}