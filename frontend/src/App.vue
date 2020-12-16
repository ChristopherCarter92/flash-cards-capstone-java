<template>
  <div id="app">
    <div id="nav">
      <div >
        <b-button class="stylized-btn white"
        v-on:click="$router.push({name: 'landing'})">Notes With Goats</b-button>
      </div>
      <div id="nav-btns">
        <b-button class="nav-btn stylized-btn"
         v-show="$store.state.token != ''"
         v-on:click="$router.push({name: 'home'})"
         >Home</b-button>
        <b-button class="nav-btn stylized-btn"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          v-on:click="$router.push({name: 'logout'})"
          >Logout</b-button>
          <b-button class="nav-btn stylized-btn"
          v-show="$store.state.token != ''"
          v-bind:to="{ name: 'login' }" 
          v-else
          v-on:click="$router.push({name: 'login'})"
          >Login</b-button>
      </div>
    </div>
    <div id="display-user-top">
          <p id="welcome-message" v-if="$store.state.token != ''"> {{ `Welcome ${$store.state.user.username.toUpperCase()}` }} </p>
        </div>
    <router-view />
  </div>
</template>
<script>
import CardServices from '@/services/CardServices.js';
import DeckServices from '@/services/DeckServices.js';

export default {
  created() {
    
    if (this.$store.state.token !== '') {
      CardServices.getAllCards();
      DeckServices.getAllDecks();
      
    }
  }


};
</script>
<style>

* {
 box-sizing: border-box; 

}
#welcome-message {
  font-size: 120%;
  width: auto;
  margin: 0 0.5em 0 0;
}

#display-user-top {
  background-color: #569FAD;
  width: auto;
}

#nav {
  display: flex;
  /* background-color: #9d57b9; */

  background-color: #B68C71;
  align-items: center;
  justify-content: space-between;
  height: 55px;
  }
.nav-btn {
  margin: 0 0.5em 0 0;;
}

#logo {
  display: flex;
  flex-flow: row nowrap;
  align-items: flex-center;

}
#logo img{
  max-width: 20vh;
  max-height: 20vh;
  margin: 0.5em 0 0.5em 0.5em;
  display: block;
}
#nav-btns {
  display: flex;
}

#display-user-top {
  display: flex;
  justify-content: flex-end;
  margin: 0 0 0 0;
  height: 40px;
}

@media screen and (max-width: 680px) {
  #display-user-top {
    justify-content: center;
  }
}
</style>
