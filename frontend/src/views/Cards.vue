<template>
<div id="card-container-card-view">
  <card-details id="card-details-card-view" />
</div>
  
</template>

<script>
import CardDetails from '../components/CardDetails.vue';
import CardServices from '@/services/CardServices.js';

export default {
    components: {
        CardDetails
    },

    data () {
      return {
        cards: [],
        counter: 0,
        currentCard: ''
      }
    },

    created() {
      CardServices.getAllCards().then(response => {
        this.cards = response.data;
        this.$store.commit('SET_CARDS', this.cards);
      })


    },

    methods: {
      handleGetStarted () {
        this.currentCard = 0;
      }
    }

}
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

#card-container-card-view {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: 
  "card"
  "button";
  height: 100vh;
}

#card-details-card-view {
  grid-area: card;
  justify-self: center;
  height: 50vh;
  width: auto;
}

</style>