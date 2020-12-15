<template>
<div >
    <div class="deck-display" v-for="decks in $store.state.decks" v-bind:key="decks.deckId">
        <b-button v-on:click="$router.push({name: 'currentDeck', params: {deckId: decks.deckId}})">
            {{decks.title}}
        </b-button>
    </div>
</div>
  
</template>

<script>


export default {
    data() {
        return {
        deckId: '',
        username: '',
        title: ''
    }
    },


}
</script>

<style scoped>

/* @media screen and (max-width: 680px) { */

#deck-list {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
  "deck deck";
}

.deck-display {
  display: flex;
  justify-content: center;
  grid-area: "deck";
  margin-bottom: 20px;
  
  
}
  
/* } */

</style>