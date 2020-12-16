<template>
  <div id="landing">
    <div class="container-fluid">
      <div class="col-sm body">
        <h1>Notes With Goats</h1>
        <div class="animation-box">
          <img src="../assets/walkthrough.gif" alt="">
           
        </div>
        <div id="get-started-btn-container">
          <b-button id="get-started-btn" v-on:click.prevent="$router.push({name: 'login'})">Get Started</b-button>
        </div>
       
      </div>
      <div class="col-sm body-w-goat">
        <img src="@/assets/goat.png" alt="zenGoat" class="theGoat" />
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#landing {
  background-color: #7f5a41;
}

#get-started-btn {
  height: 100%;
}

#get-started-btn-container {
  margin: 1em;
  
}

.container-fluid {
  display: grid;
  grid-template: 1fr 1fr;
  grid-template-areas: "body body-w-goat";
  padding: 5px;
}

.body {
  grid-area: body;
}

.body-w-goat {
  grid-area: body-w-goat;
}

.theGoat {
  display: flex;
  max-width: 100vh;
  max-height: 100vh;
  flex-direction: column;
}

.animation-box {
    border: black solid 5px;
    
    
}
.animation-box img {
  box-sizing: border-box;
  width: 100%;
}
</style>