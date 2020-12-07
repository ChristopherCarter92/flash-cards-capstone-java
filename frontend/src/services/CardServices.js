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
    }

}