<template>
  <div>
    <div class="review">
      <div class="filter">
        <div class="status-button">
          <h4>Pending</h4>
          <input type="checkbox" />
        </div>
        <div class="status-button">
          <h4>Complete</h4>
          <input type="checkbox" />
        </div>
        <div class="status-button">
          <h4>Confirm</h4>
          <input type="checkbox" />
        </div>
      </div>
      <section>
        <div class="list">
          <table>
            <thead>
              <tr>
                <th>Date</th>
                <th>Street</th>
                <th>Status</th>
                <th>View</th>
              </tr>
            </thead>

            <tbody>
              <tr>
                <td>2022-4-12</td>
                <td>This is street name</td>
                <td>
                  Pending
                </td>
                <td>
                  <button class="button-35" role="button" v-on:click="show">
                    View
                  </button>
                </td>
              </tr>
              <tr class="details" v-if="showDetails">
                <td colspan="4">
                  <div class="info"><h1>This is details</h1></div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div id="map">
          <l-map style="height: 1000px" :zoom="zoom" :center="center">
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
      </section>
    </div>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LIcon, LPopup } from "vue2-leaflet";
import PotholeService from "../services/PotholeService.js";

export default {
  name: "dashboard-review",
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
      showDetails: false,
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
    show() {
      if (this.showDetails == true) {
        this.showDetails = false;
      } else {
        this.showDetails = true;
      }
    },
  },
  created() {
    this.getPotholes();
  },
};
</script>

<style scoped>
.review {
  position: relative;
  margin-top: 10vh;
  min-height: 90vh;
}
.status-button {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  border-left: 1px solid rgba(136, 136, 136, 0.276);
  padding: 0 1em;
}
h4 {
  font-size: 1em;
  color: #666;
}
input[type="checkbox"] {
  width: 2.75em;
  height: 1.5em;
  appearance: none;
  outline: none;
  border-radius: 20px;
  position: relative;
  transform: translateY(10px);
  background: red;
}
input:checked[type="checkbox"] {
  background: #2978b5;
}
input[type="checkbox"]:before {
  content: "";
  position: absolute;
  width: 1.5em;
  height: 1.5em;
  border-radius: 50%;
  top: 0;
  left: 0;
  background-color: #fff;
  transform: scale(1.1);
}
input:checked[type="checkbox"]:before {
  left: 1.5em;
}
.filter {
  display: flex;
  flex-direction: row;
  justify-content: end;
  align-items: baseline;
  align-content: center;
  width: 100%;
  float: right;
  border-bottom: 1px solid rgba(136, 136, 136, 0.276);
}
.content {
  min-height: 100vh;
}
.list {
  width: 70%;
  display: flex;
  flex-direction: column;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  overflow-y: scroll;
}
table {
  padding: 1.5em;
}
th,
td {
  padding: 1em;
  border-bottom: 1px solid #888;
}
section {
  width: 100%;
  display: flex;
}
#map {
  width: 30%;
}
.details {
  width: 80%;
  height: 200px;
}
.info {
  height: 150px;
  border: 1px solid rgba(136, 136, 136, 0.276);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
}
.button-35 {
  align-items: center;
  background-color: #fff;
  border-radius: 12px;
  box-shadow: transparent 0 0 0 3px, rgba(18, 18, 18, 0.1) 0 6px 20px;
  box-sizing: border-box;
  color: #121212;
  cursor: pointer;
  display: inline-flex;
  flex: 1 1 auto;
  font-family: Inter, sans-serif;
  font-size: 0.8rem;
  font-weight: 700;
  justify-content: center;
  line-height: 1;
  margin: 0;
  outline: none;
  padding: 0.8rem 1rem;
  text-align: center;
  text-decoration: none;
  transition: box-shadow 0.2s, -webkit-box-shadow 0.2s;
  white-space: nowrap;
  border: 0;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-35:hover {
  box-shadow: #121212 0 0 0 3px, transparent 0 0 0 0;
}
</style>
