<template>
  <div>
  <form v-on:submit.prevent="editCard">
    <label for="question">Question: </label>
    <input v-model="thisCard.question" type="text" />
    <label for="answer">Answer: </label>
    <input v-model="thisCard.answer" type="text" />
    <label for="tags">Tags: </label>
    <textarea v-model="thisCard.tags" type="tags" />
    <b-button type="submit">Edit Card</b-button>
  </form>
  <p v-if="errorMsg !== ''"> {{ errorMsg }} </p>
</div>
</template>

<script>

import CardServices from '@/services/CardServices.js';

export default {

    data() {
    return {
      errorMsg: '',

    };
  },

  computed: {

      thisCard() {
       if (this.$store.state.cards.length > 0) {
       return this.$store.state.cards[parseInt(this.$route.params.cardId) -1];
       } else {
         return {
              id: '',
              question: 'This is the window to your soul....',
              answer: '...coding never ends...',
              tags: 'spaghetti code'
            };
       }

      }

     
    },

    methods: {

        editCard() {
        CardServices.updateCard(this.thisCard).then((response) => { 
             if (response.status === 200) {
             this.$router.push(`/cards/${parameter}`);
         } 
         }).catch(error => {
             this.handleErrorResponse(error, 'updating')
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
    }

}
</script>

<style>

</style>