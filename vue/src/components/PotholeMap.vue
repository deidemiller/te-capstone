<template>
  <div class="map">
    <div class="top-bar">
      <div class="logo-top">
        <img src="../assets/images/pothole_tracker_logo.png" alt="" />
      </div>
      <div class="top-form">
        <h5 v-if="!showForm">Click on the Map to Report a Pothole</h5>
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
              <select
                name="severity"
                id="severity"
                v-model="newPothole.severity"
              >
                <option value="low">"hmm"</option>
                <option value="moderate">
                  "I should avoid that next time"
                </option>

                <option value="high">"$#%*!"</option>
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
            <button
              class="button-80"
              type="submit"
              v-on:click="showAlert = true"
            >
              Save
            </button>
            <button class="button-80" v-on:click.prevent="clearForm">
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>

    <div id="container">
      <div class="sideBar">
        <div class="logo">
          <img src="../assets/images/pothole_tracker_logo.png" alt="Logo" />
        </div>
        <h3 v-if="!showForm">Click on the Map to Report a Pothole</h3>
        <div>
          <div class="pending-button">
            <h4>Show Pending Reports:</h4>
            <input type="checkbox" v-on:click="toggleShowPending()" />
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
                <select
                  name="severity"
                  id="severity"
                  v-model="newPothole.severity"
                >
                  <option value="low">"hmm"</option>
                  <option value="moderate">
                    "I should avoid that next time"
                  </option>

                  <option value="high">"Oh @#&%!"</option>
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
              <button
                class="button-80"
                type="submit"
                v-on:click="showAlert = true"
              >
                Save
              </button>
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
          <h1>Nearest Intersection:</h1>
          <!--<h1>🔍Nearest Intersection:</h1>-->
          <h1>{{ pothole.crossStreet1 }} & {{ pothole.crossStreet2 }}</h1>
        </div>
      </div>
      <div id="map">
        <div class="alert" id = "alert" v-if="showAlert">
          <h6>
            This has been successfully added! Thank you for helping make our
            roads safer!
          </h6>
          <button class="close" v-on:click="showAlert = false">
            <font-awesome-icon icon="fa-solid fa-xmark" />
          </button>
        </div>
        <button v-on:click="getCurrentLocation" class="geolocation" v-if="showForm === false">
          <font-awesome-icon
            icon="fa-solid fa-location-arrow"
            class="location"
          />
        </button>
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
      located: false,
      showAlert: false,
      potholes: [],
      showForm: false,
      showPending: false,
      lat: "",
      lng: "",
      newPothole: {
        pending: true,
        repairStatus: "unscheduled",
      },
    };
  },
  methods: {
    getCurrentLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(this.showPosition);
      } else {
        alert("Geolocation is not supported by this browser.");
      }
    },
    showPosition(position) {
      if (this.located == false) {
        this.center = [position.coords.latitude, position.coords.longitude];
        this.located = true;
      } else {
        this.center = [39.961178, -82.998795];
        this.located = false;
      }
    },
    getPotholes() {
      PotholeService.list().then((response) => {
        this.potholes = response.data;
      });
    },
    getReportedPotholes() {
      PotholeService.getReportedPotholes().then((response) => {
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
      this.newPothole.repairStatus = "unscheduled";
      this.newPothole.inspected = "false";
    },
    submitForm() {
      PotholeService.add(this.newPothole).then((response) => {
        if (response.status === 201 || response.status === 200) {
          
          if (!this.showPending) {
            this.getPotholes();
          } else {
            this.getReportedPotholes();
          }

          // alert(
          //   "This has been successfully added! Thank you for helping make our roads safer!"
          // );
          this.newPothole = {
            pending: true,
            repairStatus: "unscheduled",
          };
        }
      });
      this.showForm = false;
      this.add = true;
      
      
      setTimeout(() => this.showAlert = false, 3000)
    },
    clearForm() {
      this.newPothole = {};
      this.showForm = !this.showForm;
    },
    toggleShowPending() {
      if (this.showPending === false) {
        this.getReportedPotholes();
        this.showPending = true;
      } else {
        this.getPotholes();
        this.showPending = false;
      }
    },
  },
  created() {
    this.getPotholes();
  },
};
</script>

<style scoped>
.geolocation {
  position: absolute;
  height: 40px;
  width: 40px;
  top: 10%;
  left: 27%;
  z-index: 1000;
  background-color: white;
  cursor: pointer;
}
.location {
  font-size: 2em;
}
.alert {
  background-color: #f55353;
  height: 3%;
  display: flex;
  justify-content: space-between;
}
.close {
  border: none;
  background-color: #f55353;
  cursor: pointer;
}
h6 {
  margin: 0;
  font-size: 1em;
  text-align: center;
  padding-top: 0.5em;
  padding-left: 2em;
}
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
h3 {
  display: none;
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
.top-bar {
  display: none;
}

@media screen and (max-width: 426px) {
  img {
    width: 5em;
  }
  #container {
    flex-direction: column;
    flex-flow: column;
    flex-wrap: wrap;
    overflow: auto;
  }

  .sideBar {
    display: none;
  }
  #map {
    height: 57.3vh;
    width: auto;
    flex: auto;
    overflow-y: hidden;
    z-index: 10;
  }
  .top-bar {
    background-color: #cddfe3;
    height: 10%;
    display: block;
  }
  .logo-top {
    width: 20%;
    margin: 0 auto;
  }

  #map {
    width: 100%;
    height: 100vh;
  }
  .logo {
    display: none;
  }
  h5 {
    text-align: center;
    margin: 0;
    padding: 0;
    font-weight: 700;
  }
  .top-form form {
    background-color: rgba(0, 0, 0, 0.05);
    padding: 3em;
    z-index: 2000;
  }
  .pothole-form {
    margin-bottom: 0;
  }
  .geolocation {
    position: absolute;
    height: 40px;
    width: 40px;
    top: 20%;
    left: 2%;
    z-index: 1000;
    background-color: white;
    cursor: pointer;
  }
}
</style>
