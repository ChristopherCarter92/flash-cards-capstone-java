<template>
  <div id="card-details-container">
    <p v-if="errorMsg !== ''">{{ errorMsg }}</p>
    <div class="card-face" v-show="faceUp">
      <h2>{{ thisCard.question }}</h2>
      <b-button class="flip-button" v-on:click.prevent="flipCard">Show me the answer</b-button>
      <footer>{{ `Tag(s): ${thisCard.tags}` }}</footer>
    </div>
    <div class="card-face" v-show="!faceUp">
      <h2>{{ thisCard.answer }}</h2>
      <b-button class="flip-button" v-on:click.prevent="flipCard">Show Question</b-button>
    </div>
    <div id="delete-button">
    <b-button id="actual-delete-card-btn" v-on:click.prevent="deleteThisCard(thisCard.cardId)">Delete Card</b-button>
    </div>
  </div>
</template>

<script>

import cardServices from '@/services/CardServices.js';

export default {
    data() {
        return {
            errorMsg: '',
            faceUp: true
            
        }
    },

    computed: {

      thisCard() {
       if (this.$store.state.cards.length > 0) {
       return this.$store.state.cards[parseInt(this.$route.params.cardId) -1];
       } else {
         return {
              id: '',
              question: 'This is the window to your soul....',
              answer: '...coding never ends...',
              tags: 'spaghetti code'
            };
       }

      }

     
    },

    created() {
      // this.thisCard.question = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].question;
      // this.thisCard.answer = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].answer;
      // this.thisCard.tags = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].tags;
      // this.thisCard.id = this.$store.state.cards[parseInt(this.$route.params.cardId) -1].cardId;
    },

  methods: {
    flipCard() {
      this.faceUp = !this.faceUp;
    },

    deleteThisCard(id) {
      if (this.$store.state.cards.length < 1) {
        this.$router.push({ name: "home"});
      }
      cardServices.deleteCard(id).then((response) => {
        if (response.status === 200) {
          this.$store.commit("DELETE_CARD", id);
        }
      }).catch(error => {
        this.handleErrorResponse(error, 'deleting');
      });
       cardServices.getAllCards().then(response => {
        this.$store.commit('SET_CARDS', response.data);
       });
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
  margin: 2em 1em 0 1em;
  border-radius: 5px;
  border: solid  #95B0B6 5px;
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
  display: flex;
  flex-direction: column;
  align-items: center;
}

#actual-delete-card-btn {
  text-align: center;
  margin: 1em 0 0 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  width: 50%;

}

</style>