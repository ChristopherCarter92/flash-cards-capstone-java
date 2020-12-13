<template>
  <div id="edit-deck-component-container">
    <div id="add-deck-title">
      <form>
        <label for="deckTitle">Title: </label>
        <input type="text" v-model="currentDeck.title" id="deckTitle" />
      </form>
    </div>
    <div id="save-card-btn-editDeck-ele">
      <b-button v-on:click.prevent="createDeck">Save Deck</b-button>
    </div>

    <div id="add-card-ele-editDeck">
      <div v-for="i in newCards" v-bind:key="i.name">
        <form id="new-card-form">
          <textarea placeholder="Question" v-model="i.question" type="text" />
          <textarea placeholder="Answer" v-model="i.answer" type="text" />
          <label for="tags">Tags: </label>
          <select option value="Math" v-model="i.tags" type="tags">
            <option value="Math">Math</option>
            <option value="Science">Science</option>
            <option value="History">History</option>
            <option value="English">English</option>
          </select>
        </form>
        <br />
      </div>
    </div>

    <div id="all-cards-editDeck-ele">
      <b-input type="text" v-model="search" placeholder="search cards" />
      <div
        v-for="cardsInDeck in filteredCards"
        v-bind:key="cardsInDeck.tags"
      ></div>

      <div
        id="single-card-editDeck-ele"
        v-for="card in this.$store.state.cards"
        v-bind:key="card.cardId"
      >
        <div>
          <p>{{ `Question: ${card.question}` }}</p>
          <p>{{ `Answer: ${card.answer}` }}</p>
          <p>{{ `Tag(s): ${card.tags}` }}</p>
          <b-form-checkbox v-model="cardsInDeck" v-bind:value="card.cardId"
            >In This Deck</b-form-checkbox
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DeckServices from "@/services/DeckServices.js";

export default {
  components: {},

  data() {
    return {
      newCards: [
        {
          name: "card1",
          question: "",
          answer: "",
          tags: "",
        },
        {
          name: "card2",
          question: "",
          answer: "",
          tags: "",
        },
        {
          name: "card3",
          question: "",
          answer: "",
          tags: "",
        },
        {
          name: "card4",
          question: "",
          answer: "",
          tags: "",
        },
        {
          name: "card5",
          question: "",
          answer: "",
          tags: "",
        },
      ],
      wantToAdd: false,
      currentDeck: {
        title: "New Deck",
        username: this.$store.state.user.username,
      },

      cardsInDeck: [],
      search: "",
    };
  },

  props: ["currentDeckId"],

  methods: {
    createDeck() {
      if (this.currentDeckId === 0) {
        //language for creating new deck
        DeckServices.addDeck(this.currentDeck, this.currentDeck).then(
          (response) => {
            if (response.status === 201) {
              DeckServices.addMultipleCardsToDeck(
                response.data.deckId,
                this.cardsInDeck
              ).then((response3) => {
                if (response3.status === 200) {
                  console.log("We did it!");
                }
              });

              for (let cardInfo of this.newCards) {
                if (
                  cardInfo.question !== "" &&
                  cardInfo.answer !== "" &&
                  cardInfo.tags !== ""
                ) {
                  let cardDTO = {
                    question: cardInfo.question,
                    answer: cardInfo.answer,
                    tags: cardInfo.tags,
                  };
                  DeckServices.addNewCardToDeck(
                    response.data.deckId,
                    cardDTO
                  ).then((response2) => {
                    if (response2.status === 200) {
                      console.log("New cards were added.");
                    } else {
                      console.log(
                        "An error occured when adding new cards to this deck.No new cards to add."
                      );
                    }
                  });
                } else {
                  console.log("No new cards to add.");
                }
              }
            }
          }
        );
      } else {
        //language for updating deck
        DeckServices.updateDeck(this.currentDeckId, this.currentDeck);
      }

      //TODO: add CardService method to add cards created on page
      //TODO: add DeckService method to add to joiner table
    },

    addTitle(title) {
      this.newDeck.title = title;
    },

    addCardIdToNewdeck(cardId) {
      this.cardsInDeck.push(cardId);
    },
    //   computed: {
    //   filteredCards: function(){
    //     return this.cardsInDeck.filter((card) => {
    //       if (this.card.tags.match(this.search)) {
    //         return this.card;
    //       }
    //     });
    //   }
    // }
  },
};
</script>

<style>
#edit-deck-component-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "title title"
    "submit submit"
    "addNewCards addExisting";
}

#add-card-ele-editDeck {
  grid-area: addNewCards;
  border: solid black 1px;
  margin: 1em;
}

#add-deck-title {
  grid-area: title;
  display: flex;
  flex-direction: column;
  border: solid black 1px;
  margin: 1em;
}

#add-deck-title form {
  align-self: center;
  margin: 1em;
}

#single-card-editDeck-ele {
  border: solid black 1px;
  margin: 1em;
}

#all-cards-editDeck-ele {
  grid-area: addExisting;
}

#save-card-btn-editDeck-ele {
  grid-area: submit;
  display: flex;
  justify-content: center;
  border: solid black 1px;
  margin: 1em;
}

#new-card-form {
  display: flex;
  border: solid 1px black;
  padding: 20px;
  border-radius: 10px;
  background-color: #95b0b6;
  align-content: space-between;
}

@media screen and (max-width: 680px) {
}
</style>