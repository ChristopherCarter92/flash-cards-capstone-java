<template>
  <div id="main-landing-container">
    <div id="landing-title">
      <h1>Notes With Goats</h1>

    </div>
    <div id="landing-content">
      <div id="landing-gif">
        <img src="../assets/walkthrough.gif" alt="product demo">
        <b-button id="landing-get-started" v-on:click.prevent="$router.push({name: 'login'})"> Get Started</b-button>
      </div>
      <div id = "goat-pic-landing">
        <img id = "goat-pic-landing" src="../assets/goat.png" alt="goat picture">
      </div>
    </div>

  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

h1 {
  color: #324B50;
  font-size: 7em;;
}

#main-landing-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-image: linear-gradient(#569FAD, white);
}

#landing-title {
  margin: 3em 3em 5em 3em;
}

#landing-content {
  margin: 1em;
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-template-areas: 
  "gif goat" ;
}

#goat-pic-landing {
  
  grid-area: goat;
  justify-self: left;
  width: 20em;
  
}

#landing-gif {
  width: auto;
  height: auto;
  grid-area: gif;
  justify-self: right;
  display: flex;
  flex-direction: column;
}

#landing-gif img {
  margin: 1em 5em 1em 1em;
  align-self: center;
}


#landing-get-started {
  margin: 1em;
  width: 50%;
  align-self: center;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
}

@media screen and (max-width: 768px) {

}


  


</style>