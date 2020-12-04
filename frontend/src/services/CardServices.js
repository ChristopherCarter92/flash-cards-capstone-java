import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080'
});

export default {

    getCard(id) {
        return http.get(`/card/${id}`);
    },

    addCard(card) {
        return http.post('/card', card);
    }

}