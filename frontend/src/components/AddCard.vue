<template>
  <form action="">
    <label for="question">Question: </label>
    <input v-model="newCard.question" type="text" />
    <label for="answer">Answer: </label>
    <input v-model="newCard.answer" type="text" />
    <label for="tags">Tags: </label>
    <textarea v-model="newCard.tags" type="tags" />
    <button v-on:click.prevent="createCard">Add Card</button>
  </form>
</template>

<script>
import cardServices from "@/services/CardServices.vue";

export default {
  data() {
    return {
      newCard: {
        question: "",
        answer: "",
        tags: {},
      },
    };
  },

  methods: {
    createCard() {
      cardServices.addCard(newCard).then((response) => {
        if (response.status === 201) {
          this.$router.push(`/card/${id}`);
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