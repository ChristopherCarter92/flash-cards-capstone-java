<template>
  <div>
  <form v-on:submit.prevent="editCard">
    <label for="question">Question: </label>
    <textarea v-model="currentCard.question" type="text" id="question" />
    <label for="answer">Answer: </label>
    <textarea v-model="currentCard.answer" type="text" id="answer" />
    <label for="tags">Tags: </label>
    <textarea v-model="currentCard.tags" type="tags" id="tags" />
    <b-button v-on:click.prevent="editCard">Update Card</b-button>
    <!-- button to call to update api then refresh store with updated cards SET CARDS -->
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
      currentCard: {
        question: '',
        answer: '',
        tags: ''
      }
    };
  },

  created() {

       if (this.$store.state.cards.length > 0) {
        let editedCard = this.$store.state.cards.find(card => card.cardId === this.$route.params.cardId);
        this.currentCard.cardId = editedCard.cardId;
        this.currentCard.question = editedCard.question;
        this.currentCard.answer = editedCard.answer;
        this.currentCard.tags = editedCard.tags;

       } else {

            this.currentCard.question = 'This is the window to your soul....';
            this.currentCard.answer ='...coding never ends...';
            this.currentCard.tags = 'spaghetti code';
       }
     },
    
    
    methods: {

       editCard() {
        CardServices.updateCard(this.$route.params.cardId, this.currentCard).then((response) => { 
             if (response.status === 200) {
               //update store
             this.$router.push({name: 'home'});
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