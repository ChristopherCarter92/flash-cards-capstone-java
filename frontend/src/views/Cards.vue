<template>
<div id="card-container-card-view">
  <card-details id="card-details-card-view" />
</div>
  
</template>

<script>
import CardDetails from '../components/CardDetails.vue'
export default {
    components: {
        CardDetails
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
  "card";
  height: 100vh;
}

#card-details-card-view {
  grid-area: card;
  justify-self: center;
  height: 50vh;
  width: auto;
}

</style>