<template>
  <div class="major container">
    <h3>
      We provide a platform
      <br />
      for citizens to report and 
      <br />
      cities to respond quickly 
      <br />
      to problem road conditions
    </h3>
  </div>
</template>

<script>
export default {
  name: "banner-1",
};
</script>

<style>
.container {
  margin: 3em auto;
  max-width: 100%;
  width: 51em;
}
.major {
  border-bottom-width: 1px;
  border-color: rgba(224, 224, 224, 0.75);
  border-top: 1px solid rgba(224, 224, 224, 0.75);
  border-bottom: 1px solid rgba(224, 224, 224, 0.75);
  border-top-width: 1px;
  margin-bottom: 3em;
  padding: 4em 0;
  position: relative;
}
.major:before,
.major:after {
  background: rgba(224, 224, 224, 0.75);
  content: "";
  display: block;
  height: 1px;
  left: 0;
  position: absolute;
  width: 100%;
}
.major:before {
  top: 10px;
}

.major:after {
  bottom: 10px;
}

.major h3 {
  margin: 0;
  color: #5b5b5b;
  font-weight: 700;
  letter-spacing: 0.125em;
  line-height: 1.75em;
  margin-bottom: 0.3em;
  text-transform: uppercase;
  text-align: center;
  font-size: 2em;
}
</style>
