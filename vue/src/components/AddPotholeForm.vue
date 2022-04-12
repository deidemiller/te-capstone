<template>
  <form v-on:submit.prevent="submitForm">
    <!-- v-show keeps it in the DOM, but adds display: none. This is ALWAYS a good idea for forms -->
    <div class="form-element">
      <label for="crossStreet1">Street:</label>
      <input id="crossStreet1" type="text" v-model="newPothole.crossStreet1" />
      <label for="crossStreet2">Nearest Cross Street:</label>
      <input id="crossStreet2" type="text" v-model="newPothole.crossStreet2" />
    </div>
    <div class="form-element">
      <label for ="latitude">Latitude</label>
      <input id="latitude" type="decimal" v-model="newPothole.latitude" />
      <label for="longitude">Longitude</label>
      <input id="longitude" type="decimal" v-model="newPothole.longitude" />
    </div>
    <div class="form-element">
      <label for="date">Date Reported:</label>
      <input id="date" type="date" v-model="newPothole.dateReported" />
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
    <input type="submit" value="Save" />
    <!-- v-on:click.prevent="handleSave" -->
    <input type="button" value="Cancel" />
  </form>
</template>

<script>
import PotholeService from "../services/PotholeService";
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
  computed: {},
  methods: {
    submitForm() {
      PotholeService.add(this.newPothole).then((response) => {
        if (response.status === 201 || response.status === 200) {
          this.$router.push("/potholes");
        }
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