<template>
  <div id="card-container-card-view">
    <card-details id="card-details-card-view" />
    <b-button v-if="currentCardIndex > 0" v-on:click="handlePreviousButton()"
      >Previous Card</b-button
    >
    <b-button
      v-if="currentCardIndex < cards.length - 1"
      v-on:click="handleNextButton()"
      >Next Card</b-button
    >
  </div>
</template>

<script>
import CardDetails from "../components/CardDetails.vue";

export default {
  components: {
    CardDetails,
  },

  data() {
    return {
      cards: this.$store.state.cards,
      currentCardIndex: "",
    };
  },

  created() {
    this.currentCardIndex = this.$route.params.cardId - 1;
  },

  methods: {
    handlePreviousButton() {
      if (this.currentCardIndex > 0) {
        this.$router.push({
          name: "cards",
          params: { cardId: this.currentCardIndex },
        });
        this.currentCardIndex--;
      }
    },
    handleNextButton() {
      if (this.currentCardIndex < this.cards.length - 1) {
        this.$router.push({
          name: "cards",
          params: { cardId: this.currentCardIndex + 2 },
        });
        this.currentCardIndex++;
      }
    },
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

#card-container-card-view {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "card"
    "button";
  height: 100vh;
}

#card-details-card-view {
  grid-area: card;
  justify-self: center;
  height: 50vh;
  width: auto;
}
</style>