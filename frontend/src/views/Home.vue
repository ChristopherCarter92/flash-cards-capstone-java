<template>
  <div class="home">
    <h1 id="home-title">Home</h1>
    <cards-list id="cards-list-in-home"></cards-list>
  </div>
</template>

<script>
import cardsList from '@/views/CardsList.vue';
export default {

  components: {
    cardsList
  }
};
</script>
<style scoped>
* {
  box-sizing: border-box;
}

.home {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: 
  "Title"
  "Card-List";
}

#home-title {
  grid-area: Title;
  justify-self: center;
}

#cards-list-in-home {
  grid-area: Card-List;
  justify-self: center;
}

</style>
