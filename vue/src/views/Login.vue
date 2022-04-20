<template>
  <div id="login">
    <div class="container-log">
      <div class="screen">
        <div class="screen_content">
          <form class="login" @submit.prevent="login">
            <div class="login_field">
              <font-awesome-icon icon="fa-solid fa-user" class="login_icon" />
              <input
                type="text"
                id="username"
                class="form-control"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
              />
            </div>
            <div class="login_field">
              <font-awesome-icon icon="fa-solid fa-lock" class="login_icon" />
              <input
                type="password"
                id="password"
                class="form-control"
                placeholder="Password"
                v-model="user.password"
                required
              />
            </div>

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
            <button type="submit" class="button-log login_submit">
              <span class="button_text">Lon In Now</span>
              <font-awesome-icon
                icon="fa-solid fa-chevron-right"
                class="button__icon"
              />
            </button>
          </form>
          <div class="screen__background">
            <span
              class="screen__background__shape screen__background__shape4"
            ></span>
            <span
              class="screen__background__shape screen__background__shape3"
            ></span>
            <span
              class="screen__background__shape screen__background__shape2"
            ></span>
            <span
              class="screen__background__shape screen__background__shape1"
            ></span>
          </div>
        </div>
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
            this.$router.push("/dashboard");
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
  background: linear-gradient(90deg, #c7c5f4, #776bcc);
  height: 100vh;
}
.container-log {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
}
::placeholder {
  color: #888;
  font-size: 0.8em;
}
.screen {
  background: linear-gradient(90deg, #5d54a4, #7c78b8);
  position: relative;
  height: 600px;
  width: 360px;
  box-shadow: 0px 0px 24px #5c5696;
}
.screen_content {
  z-index: 1;
  position: relative;
  height: 100%;
}
.screen__background {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: -1;
  clip-path: inset(0 0 0 0);
}
.screen__background__shape {
  transform: rotate(45deg);
  position: absolute;
}
.screen__background__shape1 {
  height: 520px;
  width: 520px;
  background: #fff;
  top: -50px;
  right: 120px;
  border-radius: 0 72px 0 0;
}
.screen__background__shape2 {
  height: 220px;
  width: 220px;
  background: #6c63ac;
  top: -172px;
  right: 0;
  border-radius: 32px;
}
.screen__background__shape3 {
  height: 540px;
  width: 190px;
  background: linear-gradient(270deg, #5d54a4, #6a679e);
  top: -24px;
  right: 0;
  border-radius: 32px;
}
.screen__background__shape4 {
  height: 400px;
  width: 200px;
  background: #7e7bb9;
  top: 420px;
  right: 50px;
  border-radius: 60px;
}
.login {
  width: 320px;
  padding: 30px;
  padding-top: 156px;
}

.login_field {
  padding: 20px 0px;
  position: relative;
}
.login_icon {
  position: absolute;
  top: 30px;
  color: #7875b5;
}
.login_input {
  border: none;
  border-bottom: 2px solid #d1d1d4;
  background: none;
  padding: 10px;
  padding-left: 24px;
  font-weight: 700;
  width: 75%;
  transition: 0.2s;
}
.login_input:active,
.login_input:focus,
.login_input:hover {
  outline: none;
  border-bottom-color: #6a679e;
}

.login_submit {
  background: rgb(255, 255, 255);
  font-size: 14px;
  margin-top: 45px;
  margin-left: 55px;
  padding: 16px 20px;
  border-radius: 26px;
  border: 1px solid #d4d3e8;
  text-transform: uppercase;
  font-weight: 700;
  display: flex;
  align-items: baseline;
  width: 80%;
  color: #4c489d;
  box-shadow: 0px 2px 2px #5c5696;
  cursor: pointer;
  transition: 0.2s;
  justify-content: space-between;
}

.login_submit:active,
.login_submit:focus,
.login_submit:hover {
  border-color: #6a679e;
  outline: none;
}

.button_icon {
  font-size: 24px;
  margin-left: auto;
  color: #7875b5;
}
.button_log {
  padding: 0;
  border: none;
}
</style>
