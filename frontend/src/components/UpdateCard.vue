<template>
  <div class="update-card-background">
    <div class="card-face">
      <form v-on:submit.prevent="editCard">
        <div class="md-form mb-4 pink-textarea active-pink-textarea">
          <label for="form18">Question: </label>
          <textarea
            v-model="currentCard.question"
            id="form18"
            class="md-textarea form-control"
            rows="2"
          ></textarea>
        </div>
        <div class="md-form mb-4 pink-textarea active-pink-textarea">
          <label for="form18">Answer: </label>
          <textarea
            v-model="currentCard.answer"
            id="form18"
            class="md-textarea form-control"
            rows="2"
          ></textarea>
        </div>
        <div class="md-form mb-4 pink-textarea active-pink-textarea">
          <label for="form18">Tags: </label>
          <textarea
            v-model="currentCard.tags"
            id="form18"
            class="md-textarea form-control"
            rows="2"
            v-on:change="updateTags($event, currentCard)"
            type="tags"
          ></textarea>
        </div>
        <div class="update-btn">
          <b-button v-on:click.prevent="editCard">Update Card</b-button>
        </div>
        <!-- button to call to update api then refresh store with updated cards SET CARDS -->
      </form>
      <p v-if="errorMsg !== ''">{{ errorMsg }}</p>
    </div>
  </div>
</template>

<script>
import CardServices from "@/services/CardServices.js";

export default {
  data() {
    return {
      errorMsg: "",
      currentCard: {
        question: "",
        answer: "",
        tags: "",
      },
    };
  },

  created() {
    if (this.$store.state.cards.length > 0) {
      let editedCard = this.$store.state.cards.find(
        (card) => card.cardId === this.$route.params.cardId
      );
      this.currentCard.cardId = editedCard.cardId;
      this.currentCard.question = editedCard.question;
      this.currentCard.answer = editedCard.answer;
      this.currentCard.tags = editedCard.tags;
    } else {
      this.currentCard.question = "This is the window to your soul....";
      this.currentCard.answer = "...coding never ends...";
      this.currentCard.tags = "spaghetti code";
    }
  },

  methods: {
    updateTags(event, info) {
      let tags = event.target.value.toLowerCase().replaceAll(/[^a-z ]/g, "");
      tags = tags.replaceAll(/ +/g, " ");
      info.tags = tags;
      event.target.value = tags;
    },

    editCard() {
      CardServices.updateCard(this.$route.params.cardId, this.currentCard)
        .then((response) => {
          if (response.status === 200) {
            CardServices.getAllCards().then(() => {
              this.$router.push({
                name: "currentDeck",
                params: { deckId: this.$route.params.deckId },
              });
            });
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "updating");
        });
    },

    handleErrorResponse(error, verb) {
      if (error.response) {
        console.log(error.response);
        this.errorMsg =
          "Error " +
          verb +
          ' card. Response received was "' +
          error.response.data.errors[0].defaultMessage +
          '".';
      } else if (error.request) {
        this.errorMsg = "Error " + verb + " card. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " card. Request could not be created.";
      }
    },
  },
};
</script>

<style scoped>

.update-card-background {
  background-color: #21677ca6;
  padding: 1%;
}

form {
  width: 80%;
}

textarea {
  min-height: 5em;
  max-height: 50vh;
  width: 100%;
}

.pink-textarea textarea.md-textarea:focus:not([readonly]) {
  border-bottom: 1px solid #95b0b6;
  box-shadow: 0 1px 0 0 #95b0b6;
}
.active-pink-textarea.md-form label.active {
  color: #95b0b6;
}
.active-pink-textarea.md-form
  textarea.md-textarea:focus:not([readonly])
  + label {
  color: #95b0b6;
}

.card-face {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 2em 20em 0 20em;
  border-radius: 5px;
  border: solid #95b0b6 5px;
  justify-content: center;
  background-color: rgb(159, 187, 212);
}

.update-btn {
  display: flex;
  justify-content: center;
  margin: 0% 0% 1% 0%;
}
</style>