<template>
  <div id="card-container-card-view">
    <div v-for="card in cardsInCurrentDeck" v-bind:key="card.cardId">
        <card-details v-bind:this-card="card" />
    </div>
  </div>
</template>

<script>
import DeckServices from '@/services/DeckServices.js';
import CardDetails from '../components/CardDetails.vue';

export default {
  components: { CardDetails },

data() {
    return {
      currentDeckId: this.$route.params.deckId,
      cardsInCurrentDeck: []
    };
  },

  
   created() {
    DeckServices.getDeck(parseInt(this.currentDeckId)).then(
      (response) => {
        if (response.status === 200) {
            this.currentDeck = response.data;
          for (let card of this.$store.state.cards) {
            if (this.currentDeck.allCardIds.includes(card.cardId)) {
              this.cardsInCurrentDeck.push(card);
              
            }

          }
        }
        
      }
    );
  },
}

 
</script>

<style>

</style>