<template>
  <div>
    <input type="text" v-model="newDeck.title"/>
  </div>
</template>

<script>
import DeckService from '@/services/DeckService.js';

export default {
    data() {
      return {
        newDeck: {
          title: '',
          cards: []
        }
      }
    },
    methods: {
      getDecks(){
      DeckService.getAllDecks().then(response => {
        this.$router.push({name: 'home'});
      });
       }
    }
}
</script>

<style>

</style>