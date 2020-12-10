<template>
  <div>
      <h1>Decks</h1>
      <deck-list></deck-list>
      <card-list v-bind:currentDeckId=currentDeckId></card-list>

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
      currentDeckId: ''
    }
  },
  created() {
    this.currentDeckId = this.$route.params.deckId;

  }
}
</script>

<style>

</style>