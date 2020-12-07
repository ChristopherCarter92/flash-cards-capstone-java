<template>
  <div>
      <card v-for="card in cards" v-bind:key="card.id">
      </card>
  </div>
</template>

<script>
import card from '@/views/Card.vue';
export default {

    data() {
        return {
            cards:''
        }
    },
    components: {
        card
    },
    created() {
        this.cards = this.$store.state.cards;
    }
}
</script>

<style>

</style>