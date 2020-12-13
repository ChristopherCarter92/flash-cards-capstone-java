<template>
<div id="deck-list">
    <div id="deck-title" v-for="decks in $store.state.decks" v-bind:key="decks.deckId">
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

<style>

@media screen and (max-width: 680px) {

#deck-list {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
  "deck deck";
}

#deck-title {
  display: flex;
  justify-content: center;
  grid-area: "deck";
}
  
}

</style>