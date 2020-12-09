<template>
  <div id="card-container-card-view">
    <div id="add-new-card-btn-cards-view">
      <b-button class="cards-btns" id="b-btn-add-new" v-on:click="$router.push({name: 'create'})">Add New Card</b-button>
    </div>
    <div id="current-card-index">
      <card-details v-bind:key="currentCardIndex" id="card-details-card-view" />
    </div>
    <div id="btn-to-change-current-index">
      <b-button class="cards-btns" id="previous-card-index" v-if="currentCardIndex > 0" v-on:click="handlePreviousButton()"
      >Previous Card</b-button
    >
    <b-button
      class="cards-btns"
      id="next-card-index"
      v-if="currentCardIndex < cards.length - 1"
      v-on:click="handleNextButton()"
      >Next Card</b-button
    >
    </div>
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


#current-card-index {
  display: flex;
  align-content: center;
  align-self: center;
}

#btn-to-change-current-index {
  display: flex;
  justify-content: center;
}

.cards-btns {
  width: 20%; 
  margin: 1em 0 1em 1em;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
}

#card-container-card-view {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
}

#card-details-card-view {
  grid-area: card;
  justify-self: center;
  height: 50%;
  width: auto;
}
</style>