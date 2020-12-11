<template>
  <div>
      <h1>Decks</h1>
      <b-button>Edit Deck</b-button>
      <deck-list></deck-list>
      <card-list v-bind:currentDeckId="$route.params.deckId" v-bind:key="$route.params.deckId"></card-list>
  </div>
</template>

<script>
import CardList from '@/components/CardList.vue';
import DeckList from '@/components/DeckList.vue';

export default {

  components: {
    CardList,
    DeckList
  },

  data () {
    return {
    
    }
  }
}
</script>

<style>

</style>