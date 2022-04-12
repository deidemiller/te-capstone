<template>
  <form class="pothole-form" v-on:submit.prevent="submitForm">
    <!-- v-show keeps it in the DOM, but adds display: none. This is ALWAYS a good idea for forms -->
    <div class="form-element">
      <input id="crossStreet1" type="text" v-model="newPothole.crossStreet1" />
      <label for="crossStreet1">Street:</label>
      <input id="crossStreet2" type="text" v-model="newPothole.crossStreet2" />
      <label for="crossStreet2">Nearest Cross Street:</label>
    </div>
    <div class="form-element">
      <input id="latitude" type="decimal" v-model="newPothole.latitude" />
      <label for="latitude">Latitude</label>
      <input id="longitude" type="decimal" v-model="newPothole.longitude" />
      <label for="longitude">Longitude</label>
    </div>
    <div class="form-element">
      <input id="date" type="date" v-model="newPothole.dateReported" />
      <label for="date">Date Reported:</label>
      <!-- input as Date -->
    </div>
    <div class="form-element">
      <input type="text" id="contactName" v-model="newPothole.contactName" />
      <label for="contactName">Contact Name:</label>
      <input id="contactEmail" type="email" v-model="newPothole.contactEmail" />
      <label for="contactEmail">Contact Email:</label>
      <input type="text" id="contactPhone" v-model="newPothole.contactPhone" />
      <label for="contactPhone">Contact Phone:</label>
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
.pothole-form {
  border-radius: 25px;
  padding: 1.5em 2.25em;
  margin-bottom: 1.75em;
  font-size: 0.5em;
  letter-spacing: 0.125em;
  background-color: #42484d51;
  color: #030303 ;
}
div.form-element {
  margin-top: 10px;
  
}
div.form-element > label {
  font-size: 10pt;
  display: block;
}
div.form-element > input,
div.form-element > select {
  height: 15px;
  width: 300px;
}
div.form-element > textarea {
  height: 25px;
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