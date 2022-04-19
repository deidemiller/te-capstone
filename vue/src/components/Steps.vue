<template>
  <div class="step-container">
    <div class="img-left"><img src="../assets/images/map.png" alt="" /></div>
    <div class="content content-right">
      <h3>Check potholes</h3>
      <p>
        Easy and accessible pothole reporting and visualization with the public map feature.
      </p>
      <router-link
        tag="button"
        v-bind:to="{ name: 'potholes' }"
        class="head_button"
        ><button class="button-59" role="button">View</button></router-link
      >
    </div>

    <div class="content content-left">
      <h3>Fix potholes</h3>
      <p>
        Built-in project management for municipal administrators to easily schedule and dispatch repair crews.
      </p>
      <router-link tag="button" to="/dashboard" class="head_button"
        ><button class="button-59" role="button">View</button></router-link
      >
    </div>
    <div class="img-right"><img src="../assets/images/fix.jpg" alt="" /></div>
  </div>
</template>

<script>
export default {
  name: "steps",
};
</script>

<style scoped>
.step-container {
  display: grid;
  width: 60%;
  grid-template-areas:
    "img-left content-right"
    "content-left img-right";
  margin: 0 auto;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
}
img {
  width: 100%;
}

.content {
  padding: 3.5em;
  vertical-align: middle;
  width: 48%;
}
.content h3 {
  font-size: 1.75em;
  text-transform: uppercase;
  letter-spacing: 0.125em;
  line-height: 1.75em;
}
.content p {
  font-size: 1em;
  color: #666;
}
.img-left {
  grid-area: img-left;
}
.content-right {
  grid-area: content-right;
}
.img-right {
  grid-area: img-right;
}
.content-left {
  grid-area: content-left;
  text-align: right;
}
.button-59 {
  align-items: center;
  background-color: #fff;
  border: 2px solid #000;
  box-sizing: border-box;
  color: #000;
  cursor: pointer;
  display: inline-flex;
  fill: #000;
  font-family: Inter, sans-serif;
  font-size: 16px;
  font-weight: 600;
  height: 48px;
  justify-content: center;
  letter-spacing: -0.8px;
  line-height: 24px;
  min-width: 140px;
  outline: 0;
  padding: 0 17px;
  text-align: center;
  text-decoration: none;
  transition: all 0.3s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-59:focus {
  color: #171e29;
}

.button-59:hover {
  border-color: #06f;
  color: white;
  fill: white;
  background-color: #06f;
}

.button-59:active {
  border-color: #06f;
  color: white;
  fill: white;
  background-color: #06f;
}

@media (min-width: 768px) {
  .button-59 {
    min-width: 170px;
  }
}
.head_button {
  border: none;
}
</style>
