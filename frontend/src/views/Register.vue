<template>
  <div id="register" >
    <form class="form-register" @submit.prevent="register">
      <div id="register-form-container">
        <h1 id="header" class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div
          id="error-msg"
          class="alert alert-danger"
          role="alert"
          v-if="registrationErrors"
        >
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
        <b-button type="submit" id="register-button"> Create </b-button>
      </div>
      <router-link id="have-account-link" :to="{ name: 'login' }"
        >Have an account?</router-link
      >
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
#register {
  background-image: linear-gradient(#569FAD, white);
}

#header {
  color: #324b50;
  margin: 1em 0 0 0;
}
.form-register {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100vh;
  margin: 0 0 0 0;
  background-image: url("../assets/goat.png");
background-repeat: no-repeat;
background-size: 10%;
background-attachment: local;
background-position: 50% 6%;
}

#register-button {
  width: 70%;
  margin: 1em 0 1em 0;
  background-color: #569fad;
  color: #324b50;
  border: 1px solid transparent;
  font-size: 100%;
  padding: 7px;
}

/* #register-button:hover {
  box-shadow: 0 0.5em 0.5em -0.4em var(--hover);
  transform: translateY(-0.25em);
}   */

.form-control {
  width: 97%;
  margin: 0.5em 0 0.5em 0;
  text-align: center;
}

#register-form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #95b0b6;
  border-radius: 5px;
  border: 10px solid transparent;
  margin: 10% 0 0 0;
}

#have-account-link {
  color: #324b50;
  font-size: 1.2em;
  text-decoration: underline;
  text-decoration-color: #324b50;
  margin: 1% 0 0 0;
}

@media screen and (max-width: 992px) {
  .form-register  {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-image: url("../assets/goat.png");
background-repeat: no-repeat;
background-size: 13%;
background-attachment: local;
background-position: 50% 1%;
}
}
</style>
