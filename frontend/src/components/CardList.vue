<template>
  <div>
    <div v-for="card in cardsInCurrentDeck" v-bind:key="card.cardId">
      <div>
        <p>{{ `Question: ${card.question}` }}</p>
        <p>{{ `Answer: ${card.answer}` }}</p>
        <p>{{ `Tag(s): ${card.tags}` }}</p>
      </div>
      <div>
        <b-button class="card-list-edit-button" v-on:click="
        $router.push({name: 'update', params: {deckId: currentDeckId, cardId: card.cardId}})
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
.card-list-edit-button {
   width: 20%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  flex-direction: row;
  justify-content: center;
}
</style>

