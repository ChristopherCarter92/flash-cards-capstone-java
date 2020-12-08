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
    <div>
    <b-button id="delete-button" v-on:click.prevent="deleteThisCard(thisCard.id)">Delete Card</b-button>
    </div>
  </div>
</template>

<script>

import cardServices from '@/services/CardServices.js';

export default {
    data() {
        return {
            
            faceUp: true,
            thisCard: {
              id: '',
              question: '',
              answer: '',
              tags: ''
            }
        }
    },

    computed: {
     
    },

    created() {
      this.thisCard.question = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].question;
      this.thisCard.answer = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].answer;
      this.thisCard.tags = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].tags;
      this.thisCard.id = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].cardId;
    },

  methods: {
    flipCard() {
      this.faceUp = !this.faceUp;
    },

    deleteThisCard(id) {
      cardServices.deleteCard(id).then((response) => {
        if (response.status === 200) {
          this.$store.commit("DELETE_CARD", id);
        }
      }).catch(error => {
        this.handleErrorResponse(error, 'deleting');
      });
      this.$router.push({ name: "home"});
      },


     handleErrorResponse(error, verb) {
      if (error.response) {
        console.log(error.response);
        this.errorMsg =
          'Error ' + verb + ' card. Response received was "' +
          error.response.data.errors[0].defaultMessage + '".';
      } else if (error.request) {
        this.errorMsg =
          'Error ' + verb + ' card. Server could not be reached.';
      } else {
        this.errorMsg =
          'Error ' + verb + ' card. Request could not be created.';
      }
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

#delete-button { 
  text-align: center;
  margin: 0.5em 0 0.5em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  width: 70%;
  display: flex;
  flex-direction: column;
  align-items: center;

}

</style>