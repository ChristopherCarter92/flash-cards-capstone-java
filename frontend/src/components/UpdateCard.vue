<template>
  <div>
  <form v-on:submit.prevent="editCard">
    <label for="question">Question: </label>
    <input v-model="thisCard.question" type="text" id="question" />
    <label for="answer">Answer: </label>
    <input v-model="thisCard.answer" type="text" id="answer" />
    <label for="tags">Tags: </label>
    <textarea v-model="thisCard.tags" type="tags" id="tags" />
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

      thisCard() { //returns an array of objects (should only have one object)
       if (this.$store.state.cards.length > 0) {
         let currentCardId = this.$route.params.cardId;
         return this.findCardById(currentCardId);
        
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

      findCardById(cardId){
        let currentCard = this.$store.state.cards.find(card => card.cardId === cardId);

        return currentCard;

      },

      findCurrentCardIndex(){
        let currentIndex = '';
        let arrayOfCards = this.$store.state.cards;
        let currentCardId = this.$route.params.cardId;
        for(let i = 0; i < this.$store.state.cards.length -1; i++) {
          if (arrayOfCards[i].cardId === currentCardId) {
            currentIndex = i;
          }
        }
        return currentIndex;
      },

       editCard() {
        CardServices.updateCard(this.thisCard).then((response) => { 
             if (response.status === 200) {
               //update store
             this.$router.push(`/cards/${this.findCurrentCardIndex() + 1}`);
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