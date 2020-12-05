<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <div id="register-form-container">
        <h1 id="header" class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div 
      id="error-msg"
      class="alert alert-danger" 
      role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
     
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="register-button">
        Create 
      </button>
      </div>
       <router-link id="have-account-link" :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>


#header {
  color: #324B50;
  margin: 1em 0 0 0 ;
}
.form-register {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
  margin: 10% 0 0 0;
 
}

#register-button {
  width: 70%;
  margin: 1em 0 1em 0;
  background-color: #569FAD;
  color: #324B50;
  border: 1px solid transparent;
  font-size: 100%;
  color: white;
  padding: 7px;
 
  }

#register-button:hover {
  box-shadow: 0 0.5em 0.5em -0.4em var(--hover);
  transform: translateY(-0.25em);
}  

.form-control {
  width: 97%;
  margin: .5em 0 .5em 0;
  text-align: center;
  
}

#register-form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #95B0B6;
  height: 40vh;
  border-radius: 5px;
  border: 10px solid transparent;
  margin: 1em 0 0 0 ;
}

#have-account-link {
  color: #324B50;
  font-size: 1.2em;
  margin: 1% 0 0 0;
  text-decoration: underline;
  text-decoration-color: #324B50;
}



</style>
