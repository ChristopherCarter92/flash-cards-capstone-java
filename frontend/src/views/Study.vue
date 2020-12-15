<template>
  <div id="card-container-card-view">
    <div v-for="card in cardsInCurrentDeck" v-bind:key="card.cardId">
      <study-cards  v-on:clicked-wrong="onClickWrong(card.cardId)" 
                    v-on:clicked-right="onClickRight"
                    v-bind:this-card="card" />        
    </div>
    <div v-show="scoreCard">
      You got {{cardsInCurrentDeck.length - cardsToReview.length}} right out of {{cardsInCurrentDeck.length}}
    </div>
  </div>
</template>

<script>
import DeckServices from "@/services/DeckServices.js";
import StudyCards from "../components/StudyCards.vue";

export default {
  components: { StudyCards },

  data() {
    return {
      scoreCard: false,
      currentDeckId: this.$route.params.deckId,
      cardsInCurrentDeck: [],
      cardsToReview: [],
      cardsAnswered: 0
    };
  },

  methods: {
    onClickWrong (cardId) {
        this.cardsAnswered++
        this.cardsToReview.push(cardId);
        if (this.cardsAnswered === this.cardsInCurrentDeck.length) {
            this.scoreCard = true;
        }
      },

       onClickRight () {
        this.cardsAnswered++
        if (this.cardsAnswered === this.cardsInCurrentDeck.length) {
            this.scoreCard = true;
        }
      }
  },

  created() {
    DeckServices.getDeck(parseInt(this.currentDeckId)).then((response) => {
      if (response.status === 200) {
        this.currentDeck = response.data;
        for (let card of this.$store.state.cards) {
          if (this.currentDeck.allCardIds.includes(card.cardId)) {
            this.cardsInCurrentDeck.push(card);
          }
        }
      }
    });
  },
};
</script>

<style>
</style>