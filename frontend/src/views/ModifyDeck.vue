<template>
<div>
    <h1>Modify Deck</h1>
    <edit-deck v-bind:currentDeckId="$route.params.deckId"></edit-deck>
</div>
  
</template>

<script>
import EditDeck from '../components/EditDeck.vue'

export default {
  components: { EditDeck },

}
</script>

<style scoped>

div h1 {
 color: #324B50;
  display: flex;
  justify-content: center;
}

</style>