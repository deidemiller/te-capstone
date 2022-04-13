<template>
  <div class="step-container">
    <div class="img-left"><img src="../assets/images/map.png" alt="" /></div>
    <div class="content content-right">
      <h3>Check pothole</h3>
      <p>
        Vitae natoque dictum etiam semper magnis enim feugiat amet curabitur
        tempor orci penatibus. Tellus erat mauris ipsum fermentum etiam vivamus
        eget. Nunc nibh morbi quis fusce lacus.
      </p>
    </div>

    <div class="content content-left">
      <h3>Fix pothole</h3>
      <p>
        Vitae natoque dictum etiam semper magnis enim feugiat amet curabitur
        tempor orci penatibus. Tellus erat mauris ipsum fermentum etiam vivamus
        eget. Nunc nibh morbi quis fusce lacus.
      </p>
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
</style>
