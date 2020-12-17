<template>
  <div id="card-details-container" v-show="!answered">
    <div class="card-face" v-show="faceUp">
      <h2>{{ thisCard.question }}</h2>
      <b-button class="flip-button" v-on:click.prevent="flipCard"
        >Show me the answer</b-button
      >
    </div>
    <div class="card-face" v-show="!faceUp">
      <h2>{{ thisCard.answer }}</h2>
      <div class="study-buttons">
        <b-button class="correct" v-on:click.prevent="toggleRight">I got it right!</b-button>
        <b-button class="incorrect" v-on:click.prevent="toggleWrong">I need to study :( </b-button>
      </div>
      <b-button class="flip-button" v-on:click.prevent="flipCard"
        >Show Question</b-button
      >
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      faceUp: true,
      answered: false
    };
  },

  props: ["thisCard"],

  
  methods: {
    flipCard() {
      this.faceUp = !this.faceUp;
    },

    toggleRight() {
      this.answered = true;
      this.$emit('clicked-right');
    },

    toggleWrong() {
      this.answered = true;
      this.$emit('clicked-wrong', this.thisCard.cardId)
    }
  },


};

</script>

<style scoped>

* {
  box-sizing: border-box;
}

h2 {
  padding: 10%;
  font-size: 150%;
  text-align: center;
}

.card-face {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 2em 1em 0 1em;
  border-radius: 5px;
  border: solid #95b0b6 5px;
  justify-content: center;
  justify-items: center;
}

.flip-button {
  width: 70%;
  margin: 1em 0 1em 0;
  background-color: #569fad;
  border: 1px solid black;
  padding: 7px;
  color:white;
  font-weight: bold;
}

#delete-button {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#actual-delete-card-btn {
  text-align: center;
  margin: 1em 0 0 0;
  background-color: #569fad;
  border: 1px solid black;
  padding: 7px;
  color: #324b50;
  width: 50%;
}
.study-buttons {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-items: space-around;
  
}

.correct {
  margin-bottom: 20px;
  
  width: 10em;
  background-color: #569FAD;
  border: 1px solid black;
  padding: 7px;
  color:white;
  font-weight: bold;
}
.incorrect {
  margin-bottom: 20px;
  width: 10em;
  background-color: #569FAD;
  border: 1px solid black;
  padding: 7px;
  color:white;
  font-weight: bold;
}


</style>