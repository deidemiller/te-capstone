<template>
  <div id="container">
    <div class="sideBar">
      <div class="logo">
        <img src="../assets/images/pothole_tracker_logo.png" alt="Logo" />
      </div>
      <add-pothole v-if="showForm" />

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
import AddPothole from "../components/AddPotholeForm.vue";
export default {
  name: "pothole-map",
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LIcon,
    LPopup,
    AddPothole,
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
    },
  },
  created() {
    this.getPotholes();
  },
};
</script>

<style scoped>
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
