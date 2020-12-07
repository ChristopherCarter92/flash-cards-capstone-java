<template>
  <div>
    <div v-show="faceUp">
      <h2>{{ thisCard.question }}</h2>
      <button v-on:click.prevent="flipCard">Show me the answer</button>
      <footer>{{ thisCard.tags }}</footer>
    </div>
    <div v-show="!faceUp">
      <h2>{{ thisCard.answer }}</h2>
      <button v-on:click.prevent="flipCard">Show Question</button>
    </div>
  </div>
</template>

<script>

import cardServices from '@/services/CardServices.js'

export default {
    data() {
        return {
            currentCardId: '',
            faceUp: true,
            thisCard: {
              question: '',
              answer: '',
              tags: ''
            }
        }
    },

   
    computed: {
     
    },

    created() {

       cardServices.getCard(this.$route.params.cardId).then(response => {
        
         this.thisCard = response.data;
      
       });
    },


  methods: {
    flipCard() {
      this.faceUp = !this.faceUp;
    }
  },
 
};
</script>

<style>
</style>