<template>
<div>
    <h1>Create New Deck</h1>
    <edit-deck v-bind:currentDeckId="defaultDeckId" />
    
</div>

  
</template>

<script>
import EditDeck from '@/components/EditDeck.vue';

export default {

    data() {
        return {
            defaultDeckId: 0  
        }
    },

    components: {
        EditDeck,
        
    },

    computed: {
        
    },

}
</script>

<style>


</style>