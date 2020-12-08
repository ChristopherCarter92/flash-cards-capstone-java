<template>
  <div id="card-details-container">
    <div class="card-face" v-show="faceUp">
      <h2>{{ thisCard.question }}</h2>
      <b-button class="flip-button" v-on:click.prevent="flipCard">Show me the answer</b-button>
      <footer>{{ `Tag(s): ${thisCard.tags}` }}</footer>
    </div>
    <div class="card-face" v-show="!faceUp">
      <h2>{{ thisCard.answer }}</h2>
      <b-button class="flip-button" v-on:click.prevent="flipCard">Show Question</b-button>
    </div>
  </div>
</template>

<script>

export default {
    data() {
        return {
            
            faceUp: true,
            thisCard: {
            }
        }
    },

    computed: {
     
    },

    created() {
      this.thisCard = this.$store.state.cards[parseInt(this.$route.params.cardId) -1];
    },

  methods: {
    flipCard() {
      this.faceUp = !this.faceUp;
    }
  },
 
};
</script>

<style scoped>

* {
  box-sizing: border-box;
}

h2 {
  padding: 10vh;
  font-size: 5em;
  text-align: center;
}


.card-face {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10% 0 0 0;
  border-radius: 5px;
  border: solid  #95B0B6 5px;
  height: 70vh;
  width: 70vh;
  justify-content: center;
}

.flip-button {
  width: 70%; 
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
}

</style>