<template>
  <div id="login">
    <div class="container">
      <div class="header">
        <h1 class="h3 mb-3 font-weight-normal">Login</h1>
      </div>
      <div class="main">
        <form class="form-signin" @submit.prevent="login">
          <span>
            <font-awesome-icon icon="fa-solid fa-user" />

            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            /> </span
          ><br />
          <span>
            <font-awesome-icon icon="fa-solid fa-lock" />
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            />
          </span>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="invalidCredentials"
          >
            Invalid username and password!
          </div>
          <!-- <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
        </div> -->
          <!-- <label for="username" class="sr-only">Username</label> -->

          <!-- <label for="password" class="sr-only">Password</label>

        <router-link :to="{ name: 'register' }">Need an account?</router-link> -->
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style scoped>
#login {
  width: 100%;
  height: 100vh;
  background-color: #83acf1;
  position: relative;
}
.container {
  position: absolute;
  width: 380px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 25px;
  background-color: rgba(255, 255, 255, 0.354);
}
.header {
  text-align: center;
  padding-top: 75px;
}
.header h1 {
  color: black;
  font-size: 45px;
  margin-bottom: 80px;
}
.main {
  text-align: center;
}
.main input,
button {
  width: 300px;
  height: 40px;

  outline: none;
  padding-left: 40px;
  box-sizing: border-box;
  font-size: 15px;
  color: #333;
  margin-bottom: 40px;
  margin-left: 20px;
}
::placeholder {
  color: #666;
  text-align: left;
}
.main button {
  padding-left: 0;
  background-color: #83acf1;
  letter-spacing: 1px;
  font-weight: 700;
}
.main button:hover,
input:hover {
  box-shadow: 2px 2px 5px #555;
}
</style>
