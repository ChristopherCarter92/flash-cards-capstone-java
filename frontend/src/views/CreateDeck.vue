<template>
<div>
    <h1>Create New Deck</h1>
    <b-button v-on:click.prevent="wantToAdd = true">Add Card</b-button>
    <!-- fix routing of add card button press in the component -->
    <add-card v-show="wantToAdd"></add-card>
    <edit-deck />
</div>

  
</template>

<script>
import EditDeck from '@/components/EditDeck.vue';
import AddCard from '../components/AddCard.vue';

export default {

    data() {
        return {
            wantToAdd: false
        }
    },

    components: {
        EditDeck,
        AddCard
    }
}
</script>

<style>

</style>