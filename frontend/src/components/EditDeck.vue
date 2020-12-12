<template>
  <div id="edit-deck-component-container">
    <div id="add-deck-title">
      <form>
        <label for="deckTitle">Title: </label>
        <input type="text" v-model="currentDeck.title" id="deckTitle" />
      </form>
    </div>

    <div id="add-card-ele-editDeck">
      <div v-for="i in numOfBlankCards" v-bind:key="i.name">
        <form>
          <label for="question">Question: </label>
          <input v-model="i.question" type="text" />
          <label for="answer">Answer: </label>
          <input v-model="i.answer" type="text" />
          <label for="tags">Tags: </label>
          <select option value="Math" v-model="i.tags" type="tags">
            <option value="Math">Math</option>
            <option value="Science">Science</option>
            <option value="History">History</option>
            <option value="English">English</option>
          </select>  
        </form>
      </div>
    </div>

    <div id="all-cards-editDeck-ele">
      <div
        id="single-card-editDeck-ele"
        v-for="card in this.$store.state.cards"
        v-bind:key="card.cardId"
      >
        <div>
          <p>{{ `Question: ${card.question}` }}</p>
          <p>{{ `Answer: ${card.answer}` }}</p>
          <p>{{ `Tag(s): ${card.tags}` }}</p>
          <b-form-checkbox>In This Deck</b-form-checkbox>
        </div>
      </div>
    </div>

    <div id="save-card-btn-editDeck-ele">
      <b-button v-on:click.prevent="createDeck">Save Deck</b-button>
    </div>
  </div>
</template>

<script>
import DeckServices from "@/services/DeckServices.js";


export default {
  components: {
    
  },

  data() {
    return {
      numOfBlankCards: [
         {
          'name': 'card1', 
          'question': '',
          'answer': '',
          'tags': ''
        },
        {
          'name': 'card2', 
          'question': '',
          'answer': '',
          'tags': ''
        },
        {
          'name': 'card3', 
          'question': '',
          'answer': '',
          'tags': ''
        },
        {
          'name': 'card4', 
          'question': '',
          'answer': '',
          'tags': ''
        },
        {
          'name': 'card5', 
          'question': '',
          'answer': '',
          'tags': ''
        }
      ],
      wantToAdd: false,
      currentDeck: {
        deckId: this.currentDeckId,
        title: "New Deck",
        username: this.$store.state.user.username,
      },

      newCardsToAdd: [

      ],

      cardsInDeck: [

      ],
    };
  },

  props: ["currentDeckId"],

  methods: {
    createDeck() {
      if (this.currentDeckId.deckId === 0) {
        //language for creating new deck
        DeckServices.addDeck(this.currentDeck.deckId, this.currentDeck);
      } else {
        //language for updating deck
        DeckServices.updateDeck(this.currentDeck.deckId, this.currentDeck);
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
  },
};
</script>

<style>
#edit-deck-component-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "title title"
    "addNewCards addExisting"
    "submit submit";
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
</style>