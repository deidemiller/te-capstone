<template>
  <div id="footer">
    <div class="container medium">
      <header class="major-footer last">
        <h2>Subscribe to our mailing list</h2>
        <p>
          Interested in learning more about our project. Subscribe to our mailing list to get updates on our project as we work hard to get those potholes patched. 
        </p>
      </header>
    </div>
    <form action="#" class="subscribe">
      <input
        type="email"
        placeholder="example@gmail.com"
        autocomplete="off"
        required
      />
      <ul class="actions special">
        <li><a href="#" class="button">Subscribe</a></li>
      </ul>
    </form>
  </div>
</template>

<script>
export default {
  name: "the-footer",
};
</script>

<style>
#footer {
  background-attachment: scroll;
  background-color: #23395B;
  background-image: url("../assets/images/bottom-3200.svg");
  background-position: top center;
  background-repeat: repeat-x;
  background-size: 3200px 460px;
  margin-top: -3em;
  color: rgba(255, 255, 255, 0.8);
  padding: calc(460px + 6em) 0 10em 0;
  position: relative;
  text-align: center;
}

#footer:before {
  background: rgba(224, 224, 224, 0.75);
  content: "";
  height: calc(460px + 9em);
  left: 50%;
  position: absolute;
  top: 0;
  width: 1px;
  z-index: 0;
}
.major-footer {
  border-bottom-width: 1px;
  border-color: rgba(224, 224, 224, 0.75);
  border-top: 1px solid rgba(224, 224, 224, 0.75);
  border-top-width: 1px;
  margin-bottom: 3em;
  padding: 4em 0;
  position: relative;
}
.major-footer:before {
  background: rgba(224, 224, 224, 0.75);
  content: "";
  display: block;
  height: 1px;
  left: 0;
  position: absolute;
  width: 100%;
}
.major-footer:before {
  top: 10px;
}

.major-footer:after {
  bottom: 10px;
}
h2 {
  font-weight: 700;
  letter-spacing: 0.125em;
  line-height: 1.75em;
  margin-bottom: 0.3em;
  text-transform: uppercase;
  text-align: center;
  font-size: 2em;
}
p {
  color: rgba(255, 255, 255, 0.5);
}
.subscribe {
  max-width: 50%;
  margin: auto;
}
form input {
  width: 100%;
  background: transparent;
  border: 0;
  border-bottom: 1px solid;
  padding: 1em 0 0.8em;
  text-align: center;
  font-size: 18px;
  font-family: inherit;
  font-weight: 300;
  line-height: 1.5;
  color: inherit;
  outline: none;
}
form input::placeholder {
  color: hsla(0, 0%, 100%, 0.5);
}
ul.actions {
    all: unset;
  display: flex;
}
ul.actions.special {

  justify-content: center;
}
.button {
  all: unset;
  margin-top: 2.4em;
  background: transparent;
  border: 2px solid white;
  padding: 1em 4em;
  border-radius: 50px;
  cursor: pointer;
  display: inline-block;
  font-weight: 700;
  position: relative;
  transition: all 300ms ease;
  justify-content: center;

}
li {
  list-style: none;
}
a:after {
  border: none;
}
a:hover {
  padding: 1.5em 6em;
}
</style>
