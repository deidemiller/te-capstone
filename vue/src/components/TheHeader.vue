<template>
  <div id="header">
    <img
      src="../assets/images/pothole_tracker_logo.png"
      alt="Logo"
      class="logo"
    />
    <h1>Pothole Patrol.</h1>
    <p>
      Generate Lorem Ipsum placeholder text
      <br />
      Select the number of characters, words, sentences or paragraphs, and hit
      generate!
    </p>

    <router-link
      tag="button"
      class="header_button"
      v-bind:to="{ name: 'potholes' }"
      >Report Pothole</router-link
    >
  </div>
</template>

<script>
export default {
  name: "the-header",
};
</script>

<style scoped>
#header {
  background-attachment: scroll, scroll, fixed;
  background-image: url("../assets/images/top-3200.svg"),
    url("../assets/images/overlay.png"), url("../assets/images/header.jpg");
  background-position: bottom center, top left, center center;
  background-repeat: repeat-x, repeat, no-repeat;
  background-size: 3200px 460px, auto, cover;
  color: rgba(255, 255, 255, 0.8);
  padding: 10em 0 20em 0;
  position: relative;
  text-align: center;
  height: 70vh;
}
#header:before,
#header:after {
  background: rgba(224, 224, 224, 0.75);
  content: "";
  left: 50%;
  position: absolute;
  width: 1px;
  z-index: 0;
}

#header:before {
  height: calc(100% - 50px);
  opacity: 0.15;
  top: 0;
}

#header:after {
  bottom: 0;
  height: 50px;
}
#header .logo {
  cursor: default;
  display: inline-block;
  font-size: 3.25em;
  background-color: rgba(255, 255, 255, 0.244);
  line-height: 2.35em;
  margin: 2em 0 0.75em 0;
  text-align: center;
  width: 5.35em;
}

#header h1 {
  font-size: 3em;
  margin: 0;
  font-weight: 700;
  letter-spacing: 0.125em;
  line-height: 1.75em;
  margin-bottom: 0.75em;
  text-transform: uppercase;
  text-align: center;
}

#header p {
  font-size: 1.75em;
  line-height: 1.75em;
  margin: 0 0 2em 0;
  font-weight: 400;
}

button a {
  text-decoration: none;
  color: #fff;
}
button {
  background-color: #595260;
  border: 0 solid #e5e7eb;
  box-sizing: border-box;
  display: inline-block;
  font-size: 1.75em;
  font-weight: 700;
  letter-spacing: 0.125em;
  justify-content: center;
  line-height: 1.75rem;
  padding: 1em 2em;
  position: relative;
  text-align: center;
  text-decoration: none #ffffff solid;
  text-decoration-thickness: auto;
  width: 100%;
  max-width: 400px;
  position: relative;
  cursor: pointer;
  transform: rotate(-2deg);
  user-select: none;
  touch-action: manipulation;
}

button:focus {
  outline: 0;
}

button:after {
  content: "";
  position: absolute;
  border: 2.5px solid #000000;
  bottom: 4px;
  left: 4px;
  width: calc(100% - 1px);
  height: calc(100% - 1px);
}

button:hover:after {
  bottom: 2px;
  left: 2px;
}
</style>
