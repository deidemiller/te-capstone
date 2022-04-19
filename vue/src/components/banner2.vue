<template>
  <div class="major container">
    <h3>Expand our program</h3>
    <p>
      We're always looking to better serve Central Ohio. If you're a city administrator looking to bring our program to your city. Reach out to us here.
    </p>
    <ul class="actions special">
      <li><a href="#" class="button">Get in Touch</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "banner-2",
};
</script>

<style scoped>
p {
  margin: 0;
  color: #5b5b5b;
  font-weight: 400;
  letter-spacing: 0.125em;
  line-height: 1.75em;
  margin-bottom: 0.3em;
  text-transform: uppercase;
  text-align: center;
  font-size: 1em;
}
ul.actions {
    all: unset;
  display: flex;
}
ul.actions.special {

  justify-content: center;
}
li {
  list-style: none;
}
ul a {
  text-decoration: none;
}
.button {
  appearance: none;
  transition: color 0.2s ease-in-out, background-color 0.2s ease-in-out;
  background: #544d55;
  border-radius: 4px;
  border: 0;
  color: #ffffff;
  cursor: pointer;
  display: inline-block;
  font-size: 0.9em;
  font-weight: 700;
  letter-spacing: 0.125em;
  line-height: 3.25em;
  min-width: 15em;
  padding: 0 1.5em;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
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
</style>
