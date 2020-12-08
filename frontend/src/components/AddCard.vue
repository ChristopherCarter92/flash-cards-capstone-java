<template>
  <form v-on:submit.prevent="createCard">
    <label for="question">Question: </label>
    <input v-model="newCard.question" type="text" />
    <label for="answer">Answer: </label>
    <input v-model="newCard.answer" type="text" />
    <label for="tags">Tags: </label>
    <textarea v-model="newCard.tags" type="tags" />
    <b-button>Add Card</b-button>
  </form>
</template>

<script>
import cardServices from "@/services/CardServices.js";

export default {
  data() {
    return {
      newCard: {
        question: "",
        answer: "",
        tags: "",
      },
    };
  },

  methods: {
    createCard() {
      cardServices.addCard(this.newCard).then((response) => {
        if (response.status === 201) {
          this.$store.commit('ADD_CARD', response.data);
          let parameter = this.$store.length -1;
          this.$router.push(`/card/${parameter}`);
        }
    }).catch(error => {
        this.handleErrorResponse(error, 'adding')
    });
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
    }
  },
};
</script>

<style>
</style>