<template>
  <div id="edit-deck-component-container">

    <div id="add-deck-title">
      <form>
        <label id="label-for-title" for="deckTitle">Title: </label>
        <input type="text" v-model="currentDeck.title" id="deckTitle" />
      </form>
    </div>

    <div id="save-card-btn-editDeck-ele">
      <div id="save-btn-inner-div">
        <b-button id="save-card-btn" v-on:click.prevent="createDeck">Save Deck</b-button>
      <p v-show="errorMsg !== ''">{{ errorMsg }}</p>
      
      </div>
      
    </div>

    <div id="add-card-ele-editDeck">
      <div v-for="i in newCards" v-bind:key="i.name">
        <form class="new-card-form">
          <textarea placeholder="Question" v-model="i.question" type="text" class="edit-form-question" />
          <textarea placeholder="Answer" v-model="i.answer" type="text" class="edit-form-answer" />
          <input 
            v-on:change="updateTags($event,i)"
            placeholder="keyword1 keyword2 keyword3 etc"
            class="tags"
          />
        </form>
      </div>
       <b-button id="add-extra-card-btn" v-on:click.prevent="createAnotherCard">Add Another Card</b-button>
    </div>

    <div class="all-cards-editDeck-ele">
      <div id="editDeck-search-and-goat">
        <img src="../assets/goat.png" alt="goat pic">
        <b-input id="search-bar" type="text" v-model="search" placeholder="search cards by Tag" />

      </div>
      
      <!--replaced this.$store.state.cards with filteredCards for search capabilities-->
      <div
        v-bind:class="{first: cardsInDeck.includes(card.cardId)}"
        class="single-card-editDeck-ele"
        v-for="card in filteredCards"
        v-bind:key="card.cardId"
      >
        <div class="edit-deck-card-info-ele">
          <p><span>Question:</span>{{ ` ${card.question}` }}</p>
          <p><span>Answer:</span>{{ ` ${card.answer}` }}</p>
          <p><span>Tags:</span>{{ ` ${card.tags}` }}</p>
          <div class="checkbox-in-editDeck">
            <b-form-checkbox  v-model="cardsInDeck" v-bind:value="card.cardId" >In This Deck</b-form-checkbox>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DeckServices from "@/services/DeckServices.js";
import CardServices from "@/services/CardServices.js";

export default {
  components: {},

  data() {
    return {
      errorMsg: '',
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


    updateTags(event, info){
     
      let tags = event.target.value.toLowerCase().replaceAll(/[^a-z ]/g,'');
      tags = tags.replaceAll(/ +/g, ' ');
      info.tags = tags;
      event.target.value = tags;

    },

    handleCreateCardDTOs() {
      let cardObjectList = [];
      for (let cardInfo of this.newCards) {
        
        if (
          cardInfo.question !== "" &&
          cardInfo.answer !== "" &&
          cardInfo.tags !== "" 
        ) {
          let cardDTO = {
            question: cardInfo.question,
            answer: cardInfo.answer,
            tags: cardInfo.tags.toLowerCase(),
          };

          cardObjectList.push(cardDTO);
        } else {
          this.errorMsg = 'Make sure your cards have questions, answers, and tags that are keywords separated by spaces.'

        }
      }
      return cardObjectList;
    },

    checkForNewCard() {
      let counter = 0;
      this.newCards.forEach(element => {
        if (element.question !== '' && element.answer !== '' && element.tags !== '') {
          counter++;
          console.log(counter);
        }
      });
       return counter;
    },

    createAnotherCard() {
      this.newCards.push({
         name: `card${this.newCards.length + 1}`,
          question: '',
          answer: '',
          tags: ''
      });
    },

    createDeck() {
        if (this.cardsInDeck.length > 0 || this.checkForNewCard() > 0) {
        if (this.currentDeckId === 0) {
           //language for creating new deck
           DeckServices.addDeck(this.currentDeck, this.currentDeck).then(
          (response) => {
            if (response.status === 201) {
              DeckServices.addMultipleCardsToDeck(
                response.data.deckId,
                this.cardsInDeck
              ).then(() => {
                let cardArray = this.handleCreateCardDTOs();
                DeckServices.addNewCardsToDeck(
                  response.data.deckId,
                  cardArray
                ).then(() => {
                  DeckServices.getAllDecks().then(() => {
                    CardServices.getAllCards().then(() => {
                      this.$router.push({ name: "home" });
                    });
                  });
                });
              });
            }
          }
        );
      } else {
        //language for updating deck
        let updatedDeck = {
          title: this.currentDeck.title,
          deckId: this.currentDeckId,
          username: this.currentDeck.username,
          allCardIds: this.cardsInDeck,
        };
        DeckServices.updateDeck(updatedDeck).then((response5) => {
          if (response5.status === 200) {
            let updatedArray = this.handleCreateCardDTOs();
            DeckServices.addNewCardsToDeck(
              this.currentDeckId,
              updatedArray
            ).then(() => {
              DeckServices.getAllDecks().then(() => {
                CardServices.getAllCards().then(() => {
                  this.$router.push({ name: "currentDeck" });
                });
              });
            });
          }
        });
      } 
      } else {
        this.errorMsg = 'You must have at least one card in your deck!'
      }
      
     
    },
  },
  computed: {
    filteredCards: function () {
      return this.$store.state.cards.filter((card) => {
        if (card.tags.toLowerCase().match(this.search.toLowerCase())) {
          return card;
        }
      });
    },


  },

  created() {
    if (this.currentDeckId !== 0) {
      DeckServices.getDeck(this.currentDeckId).then((response) => {
        this.currentDeck.title = response.data.title;
        this.cardsInDeck = response.data.allCardIds;
      });
    }

    //api call to figur eout what cards are in this deck (get deck by id)
  },
};
</script >

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
#editDeck-search-and-goat {
  display: flex;
  order: -9;

}

.checkbox-in-editDeck {
  margin: 0 0 0 2em;
  align-self: flex-start;

}
#add-extra-card-btn {
  width: 40%;
  align-self: center;

}

#editDeck-search-and-goat img {
  width: 10%;
  margin: 0.3em;
  
}

.edit-form-question {
  flex-grow: 1;
}

.edit-form-answer {
  flex-grow: 1;
}

.tags {
  flex-grow: 2;
}

.edit-deck-card-info-ele {
  display: flex;
  flex-direction: column;
}

.edit-deck-card-info-ele p {
  margin: 0.5em;
}

.edit-deck-card-info-ele span {
  font-weight: bold;
}


#search-bar {
  width: 80%;
  margin: 1em;
  border: 1px solid black;
  
}

#save-card-btn{
  margin: 0.2em;
  background-color:  #B68C71;
  color: black;
  padding: 1em;
  font-weight: bold;
  border: 1px solid black;
  
}

#label-for-title {
  margin: 0.5em;
  font-weight: bold;
  font-size: large;
}

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
  border-radius: 3px;
  display: flex;
  flex-direction: column;
}

#add-deck-title {
  grid-area: title;
  display: flex;
  flex-direction: column;
  border: solid black 1px;
  background-color: #95B0B6;
  border-radius: 3px;
  margin: 1em;
   
}

#add-deck-title form {
  align-self: center;
  margin: 1em;
}


.all-cards-editDeck-ele {
  grid-area: addExisting;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color:  #95b0b6;
  margin: 1em;
  border-radius: 3px;
  border: 1px solid black;
}

.single-card-editDeck-ele{
  display: flex;
  flex-direction: column;
  border: solid black 1px;
  margin: 1em;
  background-color: white;
  border-radius: 3px;
  width: 99%;
}

.first {
  order: -1;
}


#save-card-btn-editDeck-ele {
  grid-area: submit;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 1em;
  
}

#save-btn-inner-div {
  background-color: #95B0B6;
  border-radius: 3px;
  align-self: center;
}

.new-card-form {
  display: flex;
  border: solid 1px black;
  padding: 20px;
  border-radius: 10px;
  background-color: #95b0b6;
  align-content: space-between;
  margin: 1em;
}

#add-extra-card-btn {
  width: 20%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid black;
  padding: 7px;
  color:white;
  font-weight: bold;
}



@media screen and (max-width: 768px) {
  #edit-deck-component-container {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "title"
    "submit"
    "addNewCards" 
    "addExisting";
    
}
.new-card-form {
  display: flex;
  flex-direction: column;
  border: solid 1px black;
  padding: 3px;
  border-radius: 3px;
  background-color: #95b0b6;
  align-content: space-between;
  margin: 1em 0 1em 0;
}
#editDeck-search-and-goat img {
  width: 50px;
  margin: 0.3em;
  
}


}
</style>