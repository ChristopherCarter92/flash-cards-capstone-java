<template>
<ul>
  <li v-for="card in $store.state.cards" v-bind:key="card.cardId">
      {{card.question}}
      
      
  </li>
  </ul>
</template>

<script>


export default {
    data() {
        return {
            id: '',
            question: '',
            answer: '',
            tags: ''
        };
    },

    methods: {
        
    }



}
</script>

<style>

</style>