<template>
  <form v-on:submit.prevent="handleSave">
    <!-- v-show keeps it in the DOM, but adds display: none. This is ALWAYS a good idea for forms -->
    <div class="form-element">
      <label for="crossStreet1">Street:</label>
      <input id="crossStreet1" type="text" v-model="newPothole.crossStreet1" />
      <label for="crossStreet2">Nearest Cross Street:</label>
      <input id="crossStreet2" type="text" v-model="newPothole.crossStreet2" />
    </div>
    <div class="form-element">
      <label for="date">Date Reported:</label>
      <input id="date" type="text" v-model="newPothole.dateReported" />
      <!-- input as Date -->
    </div>
    <div class="form-element">
      <label for="contactName">Contact Name:</label>
      <textarea id="contactName" v-model="newPothole.contactName"></textarea>
      <label for="contactEmail">Contact Email:</label>
      <textarea
        id="contactEmail"
        type="email"
        v-model="newPothole.contactEmail"
      ></textarea>
      <label for="contactPhone">Contact Phone:</label>
      <textarea id="contactPhone" v-model="newPothole.contactPhone"></textarea>
    </div>
    <input type="submit" value="Save" v-bind:disabled="isSaveDisabled" />
    <!-- v-on:click.prevent="handleSave" -->
    <input
      type="button"
      value="Cancel"
      v-on:click="$store.commit('TOGGLE_ADD_FORM_VISIBLE')"
    />
  </form>
</template>

<script>
export default {
  data() {
    return {
      newPothole: {
        dateReported: "", // v-model="newReview.title"
        contactName: "",
        contactEmail: "",
        contactPhone: "",
        crossStreet1: "",
        crossStreet2: "",
        latitude: "",
        longitude: "",
      },
    };
  },
  computed: {
    isSaveDisabled() {
      return this.newReview.title === "";
    },
  },
  methods: {
    handleSave(event) {
      console.log("Save was clicked!", event);

      // Identify an object representing the new review
      let reviewToAdd = this.newReview;

      // Add the new review to the reviews array (at the beginning)
      this.$store.commit("ADD_REVIEW", reviewToAdd);

      // Clear the form for the next addition (and prevents odd bugs in adding data multiple times)
      this.newReview = {
        rating: 1,
        title: "",
        review: "",
        reviewer: "",
      };

      // Hide the form
      this.$store.commit("TOGGLE_ADD_FORM_VISIBLE");

      // Navigate to the new review
      this.$router.push({
        name: "ReviewDetails",
        params: { reviewer: reviewToAdd.reviewer },
      });
    },
  },
};
</script>

<style>
div.form-element {
  margin-top: 10px;
}
div.form-element > label {
  display: block;
}
div.form-element > input,
div.form-element > select {
  height: 30px;
  width: 300px;
}
div.form-element > textarea {
  height: 60px;
  width: 300px;
}
form > input[type="button"] {
  width: 100px;
}
form > input[type="submit"] {
  width: 100px;
  margin-right: 10px;
}
</style>