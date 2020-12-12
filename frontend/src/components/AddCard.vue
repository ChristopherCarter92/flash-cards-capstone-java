<template>
<div>
  <form >
    <label for="question">Question: </label>
    <input v-model="newCard.question" type="text" />
    <label for="answer">Answer: </label>
    <input v-model="newCard.answer" type="text" />
    <label for="tags">Tags: </label>
    <textarea v-model="newCard.tags" type="tags" />
    <b-button v-on:submit.prevent="createCard" type="submit">Add Card</b-button>
    <!-- <b-button v-on:submit.prevent="addNewCardToDeck" type="submit">Add Card To Deck</b-button> -->

  </form>
  <p v-if="errorMsg !== ''"> {{ errorMsg }} </p>
</div>
  
</template>

<script>
import cardServices from "@/services/CardServices.js";
import DeckServices from '@/services/DeckServices.js';

export default {
  data() {
    return {
      errorMsg: '',
      newCard: {
        question: "",
        answer: "",
        tags: "",
      },
    };
  },
  
  props: ['deckId'],

  methods: {
    createCard() {
      cardServices.addCard(this.newCard).then((response) => {
        if (response.status === 201) {
          this.$store.commit('ADD_CARD', response.data);
          let parameter = this.$store.state.cards.length;
          this.$router.push(`/cards/${parameter}`);
        }
    }).catch(error => {
        this.handleErrorResponse(error, 'adding')
    });
    },

    addNewCardToDeck(){
      DeckServices.addNewCardToDeck(this.deckId, this.newCard);
    },

    handleErrorResponse(error, verb) {
      if (error.response) {
        console.log(error.response);
        this.errorMsg =
          'Error ' + verb + ' card. Response received was "' +
          error.response.data.errors[0].defaultMessage + '".';
      } else if (error.request) {
        this.errorMsg =
          'Error ' + verb + ' card. Server could not be reached.';
      } else {
        this.errorMsg =
          'Error ' + verb + ' card. Request could not be created.';
      }
    },

    
  },
};
</script>

<style>
</style>