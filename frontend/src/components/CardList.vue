<template>
  <div>
    <div v-for="card in cardsInCurrentDeck" v-bind:key="card.cardId">
      <div>
        <p>{{ `Question: ${card.question}` }}</p>
        <p>{{ `Answer: ${card.answer}` }}</p>
        <p>{{ `Tag(s): ${card.tags}` }}</p>
      </div>
      <div>
        <b-button>Delete Card</b-button>
        <b-button>Edit Card</b-button>
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

  created() {
    this.currentDeck = DeckServices.getDeck(parseInt(this.currentDeckId)).then(
      (response) => {
        if (response.status === 200) {
            this.cardsInCurrentDeck = 'Yay!';
        //   for (let card in this.$store.state.cards) {
        //     if (this.currentDeck.allCardIds.includes(card.cardId)) {
        //       this.cardsInCurrentDeck.push(card);
              
        //     }

        //   }
        }
        
      }
    );
  },
  props: ["currentDeckId"],
};
</script>

<style>
</style>

