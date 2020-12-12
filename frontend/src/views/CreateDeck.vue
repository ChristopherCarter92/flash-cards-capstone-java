<template>
<div>
    <h1>Create New Deck</h1>
    <edit-deck v-bind:currentDeckId="getDeckId" />
</div>

  
</template>

<script>
import EditDeck from '@/components/EditDeck.vue';

export default {

    data() {
        return {
            
        }
    },

    components: {
        EditDeck,
        
    },

    computed: {
        getDeckId(){
           return this.$route.params.deckId;
        }
    },

}
</script>

<style>


</style>