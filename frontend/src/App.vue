<template>
  <div id="app">
    <div id="nav">
      <div id="logo">
        <img src="@/assets/logo.png" alt="Flopped">
      </div>
      <div id="nav-btns">
        <b-button class="nav-btn" v-on:click="$router.push({name: 'home'})">Home</b-button>
        <b-button class="nav-btn"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          v-on:click="$router.push({name: 'logout'})"
          >Logout</b-button>
          <b-button class="nav-btn"
          v-bind:to="{ name: 'login' }" 
          v-else
          v-on:click="$router.push({name: 'login'})"
          >Login</b-button>
      </div>
    </div>
    <div id="display-user-top">
          <p v-if="$store.state.token != ''"> {{ `Welcome ${$store.state.user.username.toUpperCase()}` }} </p>
        </div>
    <router-view />
  </div>
</template>
<script>
export default {};
</script>
<style>

* {
 box-sizing: border-box; 

}
#nav {
  display: flex;
  background-color: #B68C71;
  align-items: center;
}
.nav-btn {
  margin: 0 0.5em 0 0;;
}

#logo img {
  width: 55%;
  margin: 0.5em 0 0.5em 0.5em;
}
#nav-btns {
  display: flex;
}
.nav-btn {
  opacity: 0.7;
}
.nav-btn:hover {
  opacity: 0.5;
}

#display-user-top {
  display: flex;
  justify-content: flex-end;
  margin: 0 0.5em 0 0;
}
</style>
