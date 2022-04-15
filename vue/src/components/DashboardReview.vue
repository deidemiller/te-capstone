<template>
  <div>
    <div class="review">
      <div class="filter">
        <div class="status-button">
          <h4>Unscheduled</h4>
          <input
            type="checkbox"
            v-model="unscheduled"
            v-on:click="getUnscheduledPotholes()"
          />
        </div>
        <div class="status-button">
          <h4>Scheduled</h4>
          <input
            type="checkbox"
            v-model="scheduled"
            v-on:click="getScheduledPotholes()"
          />
        </div>
        <div class="status-button">
          <h4>Completed</h4>
          <input
            type="checkbox"
            v-model="completed"
            v-on:click="getCompletedPotholes()"
          />
        </div>
      </div>
      <section>
        <div class="list">
          <!-- <table>
            <thead>
              <tr>
                <th>Date Reported</th>
                <th>Street</th>
                <th>Status</th>
                <th>View</th>
              </tr>
            </thead> -->

          <!-- <tbody>
              <tr v-for="pothole in potholes" v-bind:key="pothole.potholeId">
                <td>{{ pothole.dateReported }}</td>
                <td>{{ pothole.crossStreet1 }} & {{ pothole.crossStreet2 }}</td>
                <td>{{ pothole.repairStatus }}</td>
                <td>
                  <button class="button-35" role="button" v-on:click="show">
                    View
                  </button>
                </td>
              </tr>
              <tr class="details" v-if="showDetails">
                <td colspan="4">
                  <div class="info">
                    <div class="text">
                      <div class="pothole-detail">
                        <h3>Pothole Details</h3>
                        <h4>Reported Date: {{ pothole.dateReported }}</h4>
                        <h4>
                          Nearest Intersection: {{ pothole.crossStreet1 }} &
                          {{ pothole.crossStreet2 }}
                        </h4>
                        <h4>Severity: {{ pothole.severity }}</h4>
                      </div>
                      <div class="contact">
                        <h3>Contact Info</h3>
                        <h4>{{ pothole.contactName }}</h4>
                        <h4>{{ pothole.contactEmail }}</h4>
                        <h4>{{ pothole.contactPhone }}</h4>
                      </div>
                    </div>
                    <div class="pothole-img">
                      <img
                        src="https://i.postimg.cc/Jn0hvp6f/matt-hoffman-OOi-Ay2l-BZc-unsplash.jpg"
                        alt=""
                      />
                    </div>
                    <div class="option">
                      <button class="button-80" role="button" v-on:click="show">
                        Schedule for Repair
                      </button>
                      <button class="button-80" role="button" v-on:click="show">
                        Mark Complete
                      </button>
                    </div>
                  </div>
                </td>
              </tr>
            </tbody> -->
          <!-- </table> -->
          <div class="listt">
            <div class="divTable">
              <div class="headRow">
                <div class="divCell">Date Reported</div>
                <div class="divCell">Street</div>
                <div class="divCell">Status</div>
                <div class="divCell">View</div>
              </div>
            </div>
            <div class="divTable">
              <div
                class="list-container"
                v-for="pothole in potholes"
                v-bind:key="pothole.potholeId"
              >
                <div class="row">
                  <div class="divCell">{{ pothole.dateReported }}</div>
                  <div class="divCell">
                    {{ pothole.crossStreet1 }} & {{ pothole.crossStreet2 }}
                  </div>
                  <div class="divCell">{{ pothole.repairStatus }}</div>
                  <div class="divCell">
                    <button
                      class="button-35"
                      role="button"
                      v-on:click="showDetails(pothole.potholeId)"
                    >
                      View
                    </button>
                  </div>
                </div>
                <div class="info" v-if="pothole.showDetails">
                  <div class="text">
                    <div class="pothole-detail">
                      <h3>Pothole Details</h3>
                      <h4>Reported Date: {{ pothole.dateReported }}</h4>
                      <h4>
                        Nearest Intersection: {{ pothole.crossStreet1 }} &
                        {{ pothole.crossStreet2 }}
                      </h4>
                      <h4>Severity: {{ pothole.severity }}</h4>
                    </div>
                    <div class="contact">
                      <h3>Contact Info</h3>
                      <h4>{{ pothole.contactName }}</h4>
                      <h4>{{ pothole.contactEmail }}</h4>
                      <h4>{{ pothole.contactPhone }}</h4>
                    </div>
                  </div>
                  <div class="pothole-img">
                    <img
                      src="https://i.postimg.cc/Jn0hvp6f/matt-hoffman-OOi-Ay2l-BZc-unsplash.jpg"
                      alt=""
                    />
                  </div>
                  <div class="option">
                    <button class="button-80" role="button" v-on:click="show">
                      Schedule for Repair
                    </button>
                    <button class="button-80" role="button" v-on:click="show">
                      Mark Complete
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
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
        showDetails: "",
      },
      potholes: [],

      lat: "",
      lng: "",
      scheduled: false,
      unscheduled: false,
      completed: false,
    };
  },
  methods: {
    getPotholes() {
      PotholeService.getVerifiedPotholes().then((response) => {
        this.potholes = response.data;
        });
    },
    show(pothole) {
      if (pothole == true) {
        pothole = false;
      } else {
        pothole = true;
      }
    },
    getScheduledPotholes(id) {
      if (!this.scheduled) {
        PotholeService.listScheduled().then((response) => {
          this.unscheduled = false;
          this.completed = false;
          this.potholes = response.data;
          this.potholes.forEach((pothole) => {
            if (pothole.potholeId === id) {
              pothole.showDetails = true;
            }
          });
        });
      } else {
        this.scheduled = false;
        PotholeService.getVerifiedPotholes(id).then((response) => {
          this.potholes = response.data;
        });
      }
    },
    getUnscheduledPotholes(id) {
      if (!this.unscheduled) {
        PotholeService.listUnscheduled().then((response) => {
          this.scheduled = false;
          this.completed = false;
          this.potholes = response.data;
          this.potholes.forEach((pothole) => {
            if (pothole.potholeId === id) {
              pothole.showDetails = true;
            }
          });
        });
      } else {
        this.unscheduled = false;
        PotholeService.getVerifiedPotholes(id).then((response) => {
          this.potholes = response.data;
        });
      }
    },
    getCompletedPotholes(id) {
      if (!this.completed) {
        PotholeService.listRepaired().then((response) => {
          this.unscheduled = false;
          this.scheduled = false;
          this.potholes = response.data;
          this.potholes.forEach((pothole) => {
            if (pothole.potholeId === id) {
              pothole.showDetails = true;
            }
          });
        });
      } else {
        this.completed = false;
        PotholeService.getVerifiedPotholes(id).then((response) => {
          this.potholes = response.data;
        });
      }
    },
    showDetailsScheduled(id) {
      PotholeService.listScheduled().then((response) => {
          this.unscheduled = false;
          this.completed = false;
          this.potholes = response.data;
          this.potholes.forEach((pothole) => {
            if (pothole.potholeId === id) {
              pothole.showDetails = true;
            }
          });
        });
    },
    showDetailUnscheduled(id) {
      PotholeService.listUnscheduled().then((response) => {
          this.scheduled = false;
          this.completed = false;
          this.potholes = response.data;
          this.potholes.forEach((pothole) => {
            if (pothole.potholeId === id) {
              pothole.showDetails = true;
            }
          });
        });
    },
    showDetailsCompleted(id) {
      PotholeService.listRepaired().then((response) => {
          this.unscheduled = false;
          this.scheduled = false;
          this.potholes = response.data;
          this.potholes.forEach((pothole) => {
            if (pothole.potholeId === id) {
              if (pothole.showDetails === true) {
                pothole.showDetails = false;
                PotholeService.updateShowStatus(pothole).then(response =>{
                  if (response.status === 200) {
                    console.log('cool');
                  }
                }
                )
              } 
            }
          });
        });
    },
    showDetails(id) {
      if (!this.scheduled && !this.unscheduled && !this.completed) {
        this.getPotholes(id);
      }
      if (this.scheduled) {
        this.showDetailsScheduled(id);
      }
      if (this.unscheduled) {
        this.showDetailUnscheduled(id);
      }
      if (this.completed) {
        this.showDetailsCompleted(id);
      }
    },
    updatePotholeShowDetails(pothole) {
      PotholeService.updateShowStatus(pothole).then(response => {
        if (response.status == 200) {
          console.log('cool');
        }
      })
    }
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
  width: 90%;
  height: 850px;
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
  flex-direction: row;
}
#map {
  width: 30%;
}
.details {
  width: 80%;
}
.info {
  border: 1px solid rgba(136, 136, 136, 0.276);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: row;
  padding: 2em 5em;
  align-items: center;
  justify-content: space-between;
}
.info h4 {
  border-bottom: 1px solid rgba(136, 136, 136, 0.276);
}
.text {
  display: flex;
  flex-direction: column;
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
.pothole-img {
  width: 30%;
  height: 30%;
}
.pothole-img img {
  width: 100%;
}
.option {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: stretch;
  column-gap: 2em;
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
  position: relative;
  text-align: center;
  text-decoration: none;
  transform: translateZ(0) scale(1);
  transition: transform 0.2s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  margin: 2em;
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
.headRow > .divCell {
  width: 20%;
  text-align: center;
  font-weight: 700;
}
.headRow {
  display: flex;
  justify-content: space-around;
  padding: 1em;
  margin: 1.5em 1.5em;
  border-bottom: 1px solid #888;
}
.row > .divCell {
  width: 20%;
}
.row {
  display: flex;
  justify-content: space-around;
  padding: 1em;
  margin: 1.5em 1.5em;
  border-bottom: 1px solid #888;
}
</style>
