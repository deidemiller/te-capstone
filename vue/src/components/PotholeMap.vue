<template>
  <div id="container">
    <div class="sideBar">
      <div class="logo">
        <img src="../assets/images/pothole_tracker_logo.png" alt="Logo" />
      </div>
      <div>
        <form class="pothole-form" v-on:submit.prevent="submitForm">
          <!-- v-show keeps it in the DOM, but adds display: none. This is ALWAYS a good idea for forms -->
          <div class="form-element">
            <input
              id="crossStreet1"
              type="text"
              v-model="newPothole.crossStreet1"
            />
            <label for="crossStreet1">Street:</label>
            <input
              id="crossStreet2"
              type="text"
              v-model="newPothole.crossStreet2"
            />
            <label for="crossStreet2">Nearest Cross Street:</label>
          </div>
          
          <div class="form-element">
            <input id="date" type="date" v-model="newPothole.dateReported" />
            <label for="date">Date Reported:</label>
          </div>
          <div class="form-element">
            <input
              type="text"
              id="contactName"
              v-model="newPothole.contactName"
            />
            <label for="contactName">Contact Name:</label>
            <input
              id="contactEmail"
              type="email"
              v-model="newPothole.contactEmail"
            />
            <label for="contactEmail">Contact Email:</label>
            <input
              type="text"
              id="contactPhone"
              v-model="newPothole.contactPhone"
            />
            <label for="contactPhone">Contact Phone:</label>
          </div>
          <input type="submit" value="Save" />
          <!-- v-on:click.prevent="handleSave" -->
          <input type="button" value="Cancel" />
        </form>
      </div>
      <div
        class="pothole-list"
        v-for="pothole in potholes"
        v-bind:key="pothole.potholeId"
      >
        <h1>🔍{{ pothole.dateReported }}</h1>
      </div>
      <div class="option-2">
        <div
          class="pothole-list2"
          v-for="pothole in potholes"
          v-bind:key="pothole.potholeId"
        >
          <h1>🔍{{ pothole.dateReported }}</h1>
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
      console.log(e.latlng.lat);
      console.log(e.latlng.lng);
      this.showForm = true;
      this.lat = e.latlng.lat;
      this.lng = e.latlng.lng;
      this.newPothole.latitude = e.latlng.lat;
      this.newPothole.longitude = e.latlng.lng;
    },
    submitForm() {
      PotholeService.add(this.newPothole).then((response) => {
        if (response.status === 201 || response.status === 200) {
          console.log('success');
        }
      });
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
  padding: 1.5em 2.25em;
  margin-bottom: 1.75em;
  font-size: 0.5em;
  letter-spacing: 0.125em;
  background-color: #42484d51;
  color: #030303;
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
form > input[type="button"] {
  width: 100px;
}
form > input[type="submit"] {
  width: 100px;
  margin-right: 10px;
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
