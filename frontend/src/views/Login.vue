<template>
<div id="login-goat">
  <form class="form-signin" @submit.prevent="login">
      <div id="login-form-sub-container">
         <h1 id="login-title-txt" class="h3 mb-3 font-weight-normal">Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username or password. Please try again or click below to create an account.</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
       <b-button type="submit" id="login-form-sign-in-btn">Sign In</b-button>
      </div>
     
      <router-link id="need-act-link" :to="{ name: 'register' }">Need an account?</router-link>
     
    </form>
</div>
    
</template>

<script>
import authService from "../services/AuthService";
import CardServices from "@/services/CardServices.js";
import DeckServices from '@/services/DeckServices.js';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit('SET_AUTH_TOKEN', response.data.token);
            this.$store.commit('SET_USER', response.data.user);
            this.$router.push('/home');
            CardServices.getAllCards();
            DeckServices.getAllDecks();
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

* {
 box-sizing: border-box; 
 margin: 0;
 padding: 0;

}
#login-goat {
  background-image: linear-gradient(#569FAD, white);

}


.form-signin {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-image: url("../assets/goat.png");
background-repeat: no-repeat;
background-size: 10%;
background-attachment: local;
background-position: 50% 10%;
}

#login-title-txt {
  margin: 1em 0 0 0;
}
#login-form-sign-in-btn {
  width: 70%; 
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  border: 1px solid transparent;
  padding: 7px;
  color: #324B50;

}

.form-control {
  text-align: center;
  margin: 0.5em 0 0.5em 0 ;
  width: 97%;
  
}

#login-form-sub-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #95B0B6;
  margin: 10% 0 0 0;
  border-radius: 5px;
  border: solid transparent 10px;
}


#need-act-link {
  color: #324B50;
  font-size: 1.2em;
  margin: 1% 0% 7% 0%;
  text-decoration: underline;
  
}

.form-container {
  border: #324B50 solid 1px;
  margin: 0% 20% 0% 20%;
  border-radius: 10px;
  background-color: rgb(201, 220, 224);
}

@media screen and (max-width: 992px) {
  .form-signin {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-image: url("../assets/goat.png");
background-repeat: no-repeat;
background-size: 15%;
background-attachment: local;
background-position: 50% 1%;
}
}


</style>
