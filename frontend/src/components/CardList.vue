<template>
  <div>

  </div>
</template>

<script>
import Cards from '@/views/Cards.vue';
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
        retrieveCards() {
            cardServices
            .getAllCards()
            .then(response => {
                this.id = response.data.id;
                this.$store.commit('GET_CARDS', response.data.cards);
            })
            .catch(error => {
                if (error.response && error.response.status === 404) {
                    alert(
                        'Not loading cards-- could be deleted or not found'
                    );
                    this.$router.push({name: 'home'})
                }
            })
        }
    }



}
</script>

<style>

</style>