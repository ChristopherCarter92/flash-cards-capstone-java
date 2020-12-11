<template>
  <div>
    <form>
      <label for="deckTitle">Title: </label>
      <input type="text" v-model="newDeck.title" id="deckTitle" />
    </form>
    <br />
    <div v-for="card in this.$store.state.cards" v-bind:key="card.cardId">
      <div>
        <p>{{ `Question: ${card.question}` }}</p>
        <p>{{ `Answer: ${card.answer}` }}</p>
        <p>{{ `Tag(s): ${card.tags}` }}</p>
      </div>
      <div>
        <b-button v-on:click.prevent="addCardIdToNewdeck(card.cardId)">Add To Deck</b-button>
        <b-button>Delete Card</b-button>
        <b-button>Edit Card</b-button>
      </div>
    </div>
    <b-button v-on:click.prevent="createDeck">Save Deck</b-button>
  </div>
</template>

<script>
//have data property called "deck title " that is populated by the AddDeck component at top of page
//see all cards in your store --- DONE
//have a way to indicate that you want to add these cards to your deck
//consider having add card component (make new one called AddCardInDeck) tied with a v-if to an add card button
//button to save the deck (sends axios request) --- MOSTLY DONE
//search functionality

import DeckServices from "@/services/DeckServices.js";

export default {
  data() {
    return {
      newDeck: {
        deckId: 0,
        title: "New Deck",
        username: this.$store.state.user.username
      },

      cardsInNewDeck: []

    };
  },

  methods: {
    createDeck() {
      DeckServices.addDeck(this.newDeck);
      //add DeckService method to add to joiner table
    },

    addTitle(title) {
      this.newDeck.title = title;
    },

    addCardIdToNewdeck(cardId) {
      // for (let i of this.newDeck.cardsInNewDeck) {
      //   if (cardId !== i) {
          this.cardsInNewDeck.push(cardId);
        //}
      //}
    },
  },
};
</script>

<style>
</style>