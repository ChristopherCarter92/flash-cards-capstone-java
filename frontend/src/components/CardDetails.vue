<template>
  <div>
    <div>
      <h2>{{ getCard.question }}</h2>
      <button v-on:click.prevent="flipCard">Show me the answer</button>
      <footer>{{ getCard.tags }}</footer>
    </div>
    <div v-show="!flipCard">
      <h2>{{ getCard.answer }}</h2>
      <button v-on:click.prevent="flipCard">Show Question</button>
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            currentCardId: ''
        }
    },
    computed: {
        getCard() {
            for (let c of this.$store.state.cards) {
                if (c.cardId === this.currentCardId) {
                return c; 
                }
            }
            return null;
        }
    },
    created() {
        this.currentCardId = this.$route.params.cardId;
    },
  methods: {
    flipCard() {
      if (this.$store.state.testCard.faceUp) {
        !this.$store.state.testCard.faceUp;
      } else {
        this.$store.state.testCard.faceUp;
      }
    },
  },
 
};
</script>

<style>
</style>