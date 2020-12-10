<template>
<div>
    <div v-for="decks in $store.state.decks" v-bind:key="decks.deckId">
        <b-button v-on:click="$router.push({name: 'currentDeck', params: {deckId: decks.deckId}})">{{decks.title}}</b-button>
       
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

</style>