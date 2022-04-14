<template>
  <div id="container">
    <div class="sideBar">
      <div class="logo">
        <img src="../assets/images/pothole_tracker_logo.png" alt="Logo" />
      </div>
      <h3 v-if="!showForm">Click on the Map to Report a Pothole</h3>
      <div>
        <div class="pending-button">
          <h4>Show Pending Potholes:</h4>
          <input type="checkbox" />
        </div>
        <form
          class="pothole-form"
          v-on:submit.prevent="submitForm"
          v-if="showForm"
        >
          <!-- v-show keeps it in the DOM, but adds display: none. This is ALWAYS a good idea for forms -->
          <h2>Report Form</h2>
          <div class="reportForm">
            <div>
              <input
                id="crossStreet1"
                type="text"
                v-model="newPothole.crossStreet1"
                placeholder="Street:"
              />
              <label for="crossStreet1">Street:</label>
              <input
                id="crossStreet2"
                type="text"
                v-model="newPothole.crossStreet2"
                placeholder="Nearest Cross Street:"
              />
              <label for="crossStreet2">Nearest Cross Street:</label>
            </div>
            <div class="severity">
              <h1>Choose Severity:</h1>
              <label for="severity">Choose Severity:</label>
              <select name="severity" id="severity" v-model="newPothole.severity">
                <option value="low">Low</option>
                <option value="moderate">Moderate</option>
                <option value="high">High</option>
              </select>
            </div>
            <div>
              <input
                type="text"
                id="contactName"
                v-model="newPothole.contactName"
                placeholder="Contact Name:"
              />
              <label for="contactName">Contact Name:</label>
              <input
                id="contactEmail"
                type="email"
                v-model="newPothole.contactEmail"
                placeholder="Contact Email:"
              />
              <label for="contactEmail">Contact Email:</label>
              <input
                type="text"
                id="contactPhone"
                v-model="newPothole.contactPhone"
                placeholder="Contact Phone:"
              />
              <label for="contactPhone">Contact Phone:</label>
            </div>
          </div>
          <div class="buttons">
            <button class="button-80" type="submit">Save</button>
            <button class="button-80" v-on:click.prevent="clearForm">
              Cancel
            </button>
          </div>
        </form>
      </div>
      <div
        class="pothole-list"
        v-for="pothole in potholes"
        v-bind:key="pothole.potholeId"
      >
        <h1>🔍Nearest Intersection:</h1>
        <h1>{{ pothole.crossStreet1 }} & {{ pothole.crossStreet2 }}</h1>
      </div>
    </div>
    <div id="map">
      <l-map
        v-on:click="onMapClick"
        style="width: 100%"
        :zoom="zoom"
        :center="center"
      >
        <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
        <l-marker
          v-for="pothole in potholes"
          v-bind:key="pothole.potholeId"
          :lat-lng="[pothole.latitude, pothole.longitude]"
          ><lIcon
            icon-url="https://i.postimg.cc/rmwdTRPL/imageedit-1-9960213275.png"
            :icon-size="[40, 40]"
          ></lIcon
          ><l-popup class="popup"
            >Reported on:<br />
            {{ pothole.dateReported }}</l-popup
          >
        </l-marker>
        <l-marker :lat-lng="[lat, lng]" v-if="showForm"
          ><lIcon
            icon-url="https://i.postimg.cc/rmwdTRPL/imageedit-1-9960213275.png"
            :icon-size="[40, 40]"
          ></lIcon
        ></l-marker>
      </l-map>
    </div>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LIcon, LPopup } from "vue2-leaflet";
import PotholeService from "../services/PotholeService.js";

export default {
  name: "pothole-map",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LIcon,
    LPopup,
  },
  data() {
    return {
      url: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
      attribution:
        '&copy; <a target="_blank" href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      zoom: 13,
      center: [39.961178, -82.998795],
      pothole: {
        potholeId: "",
        dateReported: "",
        latitude: "",
        longitude: "",
        imageUrl: "",
      },
      potholes: {},
      showForm: false,

      lat: "",
      lng: "",
      newPothole: {
        pending: true,
        repairStatus: 'unscheduled'
      },
    };
  },
  methods: {
    getPotholes() {
      PotholeService.list().then((response) => {
        this.potholes = response.data;
      });
    },
    onMapClick(e) {
      const today = new Date();
      const reportedDate =
        today.getFullYear() +
        "-0" +
        (today.getMonth() + 1) +
        "-" +
        today.getDate();
      console.log(e.latlng.lat);
      console.log(e.latlng.lng);
      this.showForm = true;
      this.lat = e.latlng.lat;
      this.lng = e.latlng.lng;
      this.newPothole.latitude = e.latlng.lat;
      this.newPothole.longitude = e.latlng.lng;
      this.newPothole.dateReported = reportedDate;
      this.newPothole.pending = true;
      this.newPothole.repairStatus = 'unscheduled';
      this.newPothole.inspected = 'false';
    },
    submitForm() {
      PotholeService.add(this.newPothole).then((response) => {
        if (response.status === 201 || response.status === 200) {
          console.log("success");
          this.getPotholes();
          alert(
            "This has been successfully added! Thank you for helping make our roads safer!"
          );
          this.newPothole = {
            pending: true,
            repairStatus: 'unscheduled'
          };
        }
      });
      this.showForm = false;
      this.add = true;
    },
    clearForm() {
      this.newPothole = {};
      this.showForm = !this.showForm;
    },
  },
  created() {
    this.getPotholes();
  },
};
</script>

<style scoped>
.pothole-form {
  border-radius: 5px;
  padding: 2.5em 7.25em 2.5em 4em;
  margin-bottom: 1.75em;
  font-size: 0.5em;
  letter-spacing: 0.125em;
  background-color: #42484d44;
  color: #030303;
}
/* div.form-element {
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
} */
input {
  font-size: 1rem;
  padding: 1rem 2.5rem;
  border-radius: 2px;
  background-color: rgba(255, 255, 255, 0.5);
  border: none;
  border-bottom: 3px solid transparent;
  width: 80%;
  display: block;
}

input:focus {
  outline: none;
  box-shadow: 0 1rem 2rem rgba(0, 0, 0, 0.1);
}
input:focus:invalid {
  border-bottom: 3px solid #fc5404;
}
input::-webkit-input-placeholder {
  color: #888;
  text-align: left;
  font-weight: 400;
}
input:placeholder-shown + label {
  font-size: 0.8rem;
  font-weight: 700;
  margin-left: 1.25rem;
  margin-top: 0.4rem;
  display: block;
  transform: translateY(-2.5rem);
  opacity: 0;
  visibility: hidden;
}
label {
  opacity: 0;
}

h2 {
  font-size: 2.5em;
  padding-bottom: 0.5em;
}
h3 {
  font-size: 1.5em;
  font-weight: 700;
  text-align: center;
  text-shadow: 2px 2px #888;
}
.severity {
  display: flex;
  margin-bottom: 1.5em;
}
.severity h1 {
  width: 70%;
}
#severity {
  font-size: 1rem;
  padding: 1rem 2.5rem;
  border-radius: 2px;
  background-color: rgba(255, 255, 255, 0.5);
  border: none;
  border-bottom: 3px solid transparent;
  width: 90%;
  display: block;
}
.buttons {
  display: flex;
  align-content: center;
  justify-content: space-evenly;
}
.button-80 {
  background: #fff;
  backface-visibility: hidden;
  border-radius: 0.375rem;
  border-style: solid;
  border-width: 0.125rem;
  box-sizing: border-box;
  color: #212121;
  cursor: pointer;
  display: inline-block;
  font-family: Circular, Helvetica, sans-serif;
  font-size: 1.125rem;
  font-weight: 700;
  letter-spacing: -0.01em;
  line-height: 1.3;
  padding: 0.875rem 1.125rem;

  text-align: center;
  text-decoration: none;
  transform: translateZ(0) scale(1);
  transition: transform 0.2s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-80:not(:disabled):hover {
  transform: scale(1.05);
}

.button-80:not(:disabled):hover:active {
  transform: scale(1.05) translateY(0.125rem);
}

.button-80:focus {
  outline: 0 solid transparent;
}

.button-80:focus:before {
  content: "";
  left: calc(-1 * 0.375rem);
  pointer-events: none;
  position: absolute;
  top: calc(-1 * 0.375rem);
  transition: border-radius;
  user-select: none;
}

.button-80:focus:not(:focus-visible) {
  outline: 0 solid transparent;
}

.button-80:focus:not(:focus-visible):before {
  border-width: 0;
}

.button-80:not(:disabled):active {
  transform: translateY(0.125rem);
}
.pending-button {
  display: flex;
  vertical-align: baseline;
  justify-content: space-evenly;
  align-content: center;
  align-items: baseline;
  padding: 0.5em 3em;
}
h4 {
  font-size: 1.2em;
}
input[type="checkbox"] {
  width: 2em;
  height: 1em;
  appearance: none;
  outline: none;
  border-radius: 20px;
  position: relative;
  transform: translateY(10px);
}
input:checked[type="checkbox"] {
  background: #2978b5;
}
input[type="checkbox"]:before {
  content: "";
  position: absolute;
  width: 2em;
  height: 2em;
  border-radius: 50%;
  top: 0;
  left: 0;
  background-color: #fff;
  transform: scale(1.1);
}
input:checked[type="checkbox"]:before {
  left: 3em;
}
#container {
  display: flex;
}
.sideBar {
  background-color: #cddfe3;
  height: 100vh;
  width: 25%;
  overflow-y: scroll;
  overflow-x: hidden;
  padding: 0.5em 1em 0.5em 1em;
}

.logo {
  margin: 0 auto;
  width: 10em;
  padding-top: 3em;
}
img {
  width: 10em;
}
#map {
  height: 100vh;
  flex: 1;
}
.pothole-list {
  border-radius: 5px;
  padding: 1.5em 2.25em;
  margin-bottom: 1.75em;
  font-size: 0.5em;
  letter-spacing: 0.125em;
  background-color: #42484d51;
}
</style>
