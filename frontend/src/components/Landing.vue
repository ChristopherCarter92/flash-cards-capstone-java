<template>
  <div id="main-landing-container">
    <div id="landing-login">
        <p>Already have an account?</p>
        <b-button id="landing-login-btn" v-on:click.prevent="$router.push({name: 'login'})">Login</b-button>
      </div>

    <div id="landing-title">
      <h1>Notes With Goats</h1>
      <p>Need some practice with vocabulary? Wish you could access your flashcards from any device? Now you can! Introducing "Notes with Goats." A fun, animal themed environment where Mr. Griffy the watchful goat can keep an eye on your study session.</p>
    </div>
    
    <div id="landing-content">
      <div id="landing-gif">
        <img src="../assets/walkthrough.gif" alt="product demo">
        <b-button id="landing-get-started" v-on:click.prevent="$router.push({name: 'register'})"> Get Started</b-button>
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
#landing-title {
  width: 100vw;
   display: flex;
   flex-direction: column;
  
}

#landing-title p {
  width: 50%;
  align-self: center;
  text-align: center;
  font-size: 1.5em;
}
#landing-login {
  display: flex;
  align-self: flex-end;
  margin: 1em;
}
#landing-login p {
  font-size: 1.5em;
}

#landing-login-btn {
  margin: 1em;
  width: 50%;
  align-self: center;
  background-color: #B68C71;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;
  border: solid black 1px;
  font-weight: bold;
}

h1 {
  color: #324B50;
  font-size: 7em;
  text-align: center;
}

#main-landing-container {
  display: flex;
  flex-direction: column;
  background-image: linear-gradient(#569FAD, white);
  height: 100vh;
}

#landing-title {
  margin: 1em 3em 3em 3em;
}

#landing-content {
  margin: 1em;
  display: grid;
  grid-template-columns: 2fr 1fr;
  grid-template-areas: 
  "gif goat" ;
  align-self: center;
  
  
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
  color:white;
  font-weight: bold;
  font-size: 2em;
  border: solid black 1px;
  
}

@media screen and (max-width: 992px) {

  * {
    margin: 0;
    padding: 0;
  }
  #landing-title {
  margin: 1em 0 1em 0;
}

#landing-gif img {
  margin: 0;
  align-self: center;
  object-fit: contain;
  width: 20em;
  
}

  #landing-content {
  display: flex;
  flex-direction: column;
  margin: 0;
  }

h1 {
  text-align: center;
  font-size: 3em;
}
#landing-gif {
  object-fit: contain;
  align-self: center;
  display: flex;
  flex-direction: column;
  margin: 0;

}
#goat-pic-landing {
  
  align-self: center;
  object-fit: contain;
  justify-self: center;
  
  
}
#landing-title p {
  width: 90%;
  align-self: center;
  text-align: center;
  font-size: 1em;
  margin: 1em;
}


}



  


</style>