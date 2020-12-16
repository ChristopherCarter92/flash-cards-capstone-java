<template>
  <div>

    <div class="card-list-cards" v-for="card in cardsInCurrentDeck" v-bind:key="card.cardId">

      <div>
        <p>{{ `Question: ${card.question}` }}</p>
        <p>{{ `Answer: ${card.answer}` }}</p>
        <p>{{ `Tag(s): ${card.tags}` }}</p>
      </div>
      <div class="card-list-edit-button-container" >
        <b-button class="card-list-edit-button" v-on:click="
        $router.push({name: 'update', params: {deckId: currentDeckId, cardId: card.cardId}})
      ">Edit Card</b-button>
      <b-button class="card-list-edit-button" v-on:click.prevent="deleteCard(card.cardId)">Delete Card</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import DeckServices from "@/services/DeckServices.js";
import CardServices from "@/services/CardServices.js";

export default {
  data() {
    return {
      currentDeck: "",
      cardsInCurrentDeck: [],
    };
  },

   props: ["currentDeckId"],

   methods: {
     deleteCard(id) {
       if(confirm('This will delete the card from every deck. Are you sure you want to continue? *If you want to delete a card from this deck only, click Edit Deck*')) {
         CardServices.deleteCard(id).then(() => {
           CardServices.getAllCards().then(() => {
             DeckServices.getAllDecks().then(() => {
               this.cardsInCurrentDeck = [];
               this.updateCardsList();
             });
           });

         });
         
         
       }

     },

     updateCardsList() {
       DeckServices.getDeck(parseInt(this.currentDeckId)).then(
      (response) => {
        if (response.status === 200) {
            this.currentDeck = response.data;
          for (let card of this.$store.state.cards) {
            if (this.currentDeck.allCardIds.includes(card.cardId)) {
              this.cardsInCurrentDeck.push(card);
              
            }

          }
        }
        
      }
    );

     }
   },

  created() {
    this.updateCardsList();
    
  },
 
};
</script>

<style scoped>

.card-list-edit-button-container {
  display: flex;
  justify-content: space-around;
  
  
  
}

.card-list-edit-button {
  width: 20%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  
}
.card-list-cards {
  border: solid 1px black;
  padding: 20px;
  border-radius: 10px;
  background: white;
  align-content: space-between;
  margin: 1em;
}

.card-face-card-list {
  display: flex;
  flex-direction: column;
  align-content: center;
  margin: 2em 2em 2em 2em;
  border-radius: 5px;
  border: solid #95b0b6 5px;
  background-color: rgb(220, 231, 241);
  width: 90%;

}

.card-face-card-list p {
  display: flex;
  justify-content: center;
  margin: 1em 1em 1em 1em;
  font-weight: bold;
}


</style>

