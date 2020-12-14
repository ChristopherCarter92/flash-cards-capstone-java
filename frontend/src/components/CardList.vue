<template>
  <div>
      <b-button>Start A Study Session</b-button>
    <div v-for="card in cardsInCurrentDeck" v-bind:key="card.cardId">
      <div>
        <p>{{ `Question: ${card.question}` }}</p>
        <p>{{ `Answer: ${card.answer}` }}</p>
        <p>{{ `Tag(s): ${card.tags}` }}</p>
      </div>
      <div>
        <b-button v-on:click="
        $router.push({name: 'update', params: {cardId: card.cardId}})
      ">Edit Card</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import DeckServices from "@/services/DeckServices.js";

export default {
  data() {
    return {
      currentDeck: "",
      cardsInCurrentDeck: [],
    };
  },

   props: ["currentDeckId"],

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
 
};
</script>

<style>
</style>

