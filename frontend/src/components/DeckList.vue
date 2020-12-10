<template>
<div>
    <div v-for="decks in $store.state.decks" v-bind:key="decks.deckId">
        <b-button v-on:click="$router.push('/decks/:id')">{{decks.title}}</b-button>
       
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

    methods: {
        getDeckId(title) {
            
            
        }
    }

}
</script>

<style>

</style>