<template>
  <div id="container">
    <div class="sideBar">
      <div class="logo">
        <img src="../assets/images/pothole_tracker_logo.png" alt="Logo" />
      </div>
      <h1 v-if="!showForm">Click on the Map to Report a Pothole</h1>
      <div>
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

            <!-- <div >
              <input id="date" type="date" v-model="newPothole.dateReported" />
              <label for="date">Date Reported:</label>
            </div> -->
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
          <!-- <input type="submit" value="Save" /> -->
          <!-- v-on:click.prevent="handleSave" -->
          <!-- <input type="button" value="Cancel" /> -->
          <button class="button-73" type="submit">Save</button>
          <button class="button-73">Cancel</button>
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
      <div class="option-2">
        <div
          class="pothole-list2"
          v-for="pothole in potholes"
          v-bind:key="pothole.potholeId"
        >
          <h1>🔍 {{ pothole.dateReported }}</h1>
        </div>
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
      zoom: 10,
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
    },
    submitForm() {
      PotholeService.add(this.newPothole).then((response) => {
        if (response.status === 201 || response.status === 200) {
          console.log("success");
          this.getPotholes();
          alert("This has been successfully added!");
          this.newPothole = {};
        }
      });
      this.showForm = false;
      this.add = true;
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
  border-bottom: 3px solid #2978b5;
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
  font-size: 2em;
  font-weight: 400;
}
.button-73 {
  appearance: none;
  background-color: #ffffff;
  border-radius: 40em;
  border-style: none;
  box-shadow: #adcfff 0 -12px 6px inset;
  box-sizing: border-box;
  color: #000000;
  cursor: pointer;
  display: inline-block;
  font-family: -apple-system, sans-serif;
  font-size: 1.2rem;
  font-weight: 700;
  letter-spacing: -0.24px;
  margin: 1em;
  outline: none;
  padding: 1rem 1.3rem;
  quotes: auto;
  text-align: center;
  text-decoration: none;
  transition: all 0.15s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-73:hover {
  background-color: #ffc229;
  box-shadow: #ff6314 0 -6px 8px inset;
  transform: scale(1.125);
}

.button-73:active {
  transform: scale(1.025);
}

@media (min-width: 768px) {
  .button-73 {
    font-size: 1.5rem;
    padding: 0.75rem 2rem;
  }
}
/* form > input[type="button"] {
  width: 100px;
}
form > input[type="submit"] {
  width: 100px;
  margin-right: 10px;
} */

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
.option-2 {
  border-radius: 5px;
  padding: 1.5em 2.25em;
  margin-bottom: 1.75em;
  font-size: 0.5em;
  letter-spacing: 0.125em;
  background-color: #42484d51;
  overflow-y: scroll;
  overflow-x: hidden;
  height: 100px;
  width: 80%;
  margin: 0 auto;
}
</style>
