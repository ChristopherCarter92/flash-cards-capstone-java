<template>
<div>
    <ul v-for="decks in $store.state.decks" v-bind:key="decks.deckId">
        <li>{{decks}}</li>
        
    </ul>
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
    }

}
</script>

<style>

</style>