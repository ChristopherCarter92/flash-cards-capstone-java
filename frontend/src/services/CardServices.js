import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:3000'
});

export default {

    getCard(id) {
        return http.get(`/cards/${id}`);
    },

    addCard(card) {
        return http.post('/cards', card);
    }

}