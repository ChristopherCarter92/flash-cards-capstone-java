<template>
  <div>
    <b-button class="end-session-btn" v-on:click.prevent="endSession"
      >End Study Session</b-button
    >
    <div id="card-container-card-view">
      <div
        v-for="card in cardsInCurrentDeck"
        v-bind:key="card.cardId"
        v-show="!scoreCard"
      >
        <study-cards
          v-on:clicked-wrong="onClickWrong(card.cardId)"
          v-on:clicked-right="onClickRight"
          v-bind:this-card="card"
        />
      </div>
      <div class="score-card" v-show="scoreCard">
        <h3>
          You got {{ cardsInCurrentDeck.length - cardsToReview.length }} right
          out of {{ cardsInCurrentDeck.length }}!
        </h3>
        <h5>Save the cards you got wrong for review?</h5>
        <b-button v-on:click.prevent="handleReviewDeck"
          >Save Review Deck</b-button
        >
        <b-button v-on:click.prevent="returnToDeckView"
          >Return To Deck</b-button
        >
      </div>
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
      reviewDeck: {
        title: `${this.$store.state.user.username}'s Review Deck`,
        username: this.$store.state.user.username,
      },
      cardsToReview: [],
      cardsAnswered: 0,
    };
  },

  methods: {
    endSession() {
      this.scoreCard = true;
    },

    onClickWrong(cardId) {
      this.cardsAnswered++;
      this.cardsToReview.push(cardId);
      if (this.cardsAnswered === this.cardsInCurrentDeck.length) {
        this.scoreCard = true;
      }
    },

    onClickRight() {
      this.cardsAnswered++;
      if (this.cardsAnswered === this.cardsInCurrentDeck.length) {
        this.scoreCard = true;
      }
    },

    handleReviewDeck() {
      let counter = 0;
      for (let i = 0; i < this.$store.state.decks.length; i++) {
        if (this.$store.state.decks[i].title === this.reviewDeck.title) {
          counter++;
        } 
        console.log(counter);
      }
      if (counter > 0) {
        this.updateReviewDeck();
      } else {
        this.addReviewDeck();
      }
    },

    updateReviewDeck() {
      DeckServices.addMultipleCardsToDeck(this.currentDeckId, this.cardsToReview);
      this.returnToDeckView();
    },

    addReviewDeck() {
      DeckServices.addDeck(this.reviewDeck).then((response) => {
        if (response.status === 201) {
          DeckServices.addMultipleCardsToDeck(
            response.data.deckId,
            this.cardsToReview
          ).then(() => {
            DeckServices.getAllDecks().then(() => {
              this.returnToDeckView();
            });
          });
        }
      });
    },

    returnToDeckView() {
      this.$router.push({ name: "currentDeck", params: this.currentDeckId });
    },
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
.score-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 2em 1em 0 1em;
  border-radius: 5px;
  border: solid #95b0b6 5px;
  justify-content: center;
}

.score-card h3 {
  font-size: 35px;
  font-weight: bolder;
}
.score-card h5 {
  text-align: center;
}
</style>