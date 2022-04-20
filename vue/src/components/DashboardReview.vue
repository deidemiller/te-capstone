<template>
  <div>
    <div class="review">
      <div class="alert" v-if="showAlert">
        <h6>
          This pothole has been scheduled for repair.
        </h6>
        <button class="close" v-on:click="showAlert = false">
          <font-awesome-icon icon="fa-solid fa-xmark" />
        </button>
      </div>
      <div class="alert" v-if="showMark">
        <h6>
          This pothole has been marked repaired.
        </h6>
        <button class="close" v-on:click="showMark = false">
          <font-awesome-icon icon="fa-solid fa-xmark" />
        </button>
      </div>
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
        <transition name="fade" appear>
          <div class="overlay" v-if="showS">
            <transition name="slide" appear>
              <div class="schedule-container">
                <form
                  class="schedule-form"
                  v-on:submit.prevent="scheduleRepair()"
                >
                  <h1>Schedule Form</h1>
                  <div>
                    <div class="id">
                      <label for="id">Pothole ID</label>
                      <input id="id" type="text" v-model="potholeIdForRepair" />
                    </div>
                    <div class="date">
                      <label for="date">Date</label>
                      <input id="date" type="date" v-model="dateForRepair" />
                    </div>
                    <!-- <div class="time">
                      <label for="time">Time</label>
                      <input id="time" type="time" />
                    </div> -->
                    <div class="employee">
                      <label for="severity">Employee</label>
                      <select
                        name="employee"
                        id="employee"
                        v-model="selectedEmployee"
                      >
                        <option
                          v-for="employee in employees"
                          v-bind:value="employee.employeeId"
                          v-bind:key="employee.employeeId"
                        >
                          {{ employee.firstName }} {{ employee.lastName }}
                        </option>
                      </select>
                    </div>
                    <div class="schedule-button">
                      <button
                        class="button-28 button-submit"
                        role="button"
                        type="submit"
                      >
                        Submit
                      </button>
                      <button
                        class="button-28 button-cancel"
                        role="button"
                        v-on:click.prevent="showS = false"
                      >
                        Cancel
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </transition>
          </div>
        </transition>
        <div class="list">
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
                <div class="info" v-show="pothole.showDetails === true">
                  <div class="text">
                    <div class="pothole-detail">
                      <h3>Pothole Details</h3>
                      <h4>Pothole ID: {{ pothole.potholeId }}</h4>
                      <h4>Reported Date: {{ pothole.dateReported }}</h4>
                      <h4>
                        Nearest Intersection: {{ pothole.crossStreet1 }} &
                        {{ pothole.crossStreet2 }}
                      </h4>
                      <h4>Severity: {{ pothole.severity }}</h4>
                      <h4 v-if="pothole.repairDate != undefined">
                        Repair Date: {{ pothole.repairDate }}
                      </h4>
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
                    <button
                      class="button-80"
                      role="button"
                      v-if="pothole.repairStatus != 'completed'"
                      v-on:click="showS = true"
                    >
                      Schedule for Repair
                    </button>
                    <button
                      class="button-80"
                      role="button"
                      v-on:click="completePothole()"
                      v-if="pothole.repairStatus != 'completed'"
                    >
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
import EmployeeService from "../services/EmployeeService";

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
      potholeIdForRepair: -1,
      dateForRepair: "",
      pothole: {
        potholeId: "",
        dateReported: "",
        latitude: "",
        longitude: "",
        imageUrl: "",
        showDetails: "",
      },
      showAlert: false,
      showMark: false,
      potholes: [],
      showS: false,
      lat: "",
      lng: "",
      scheduled: false,
      unscheduled: false,
      completed: false,
      details: false,
      employees: [],
      selectedEmployee: 0,
    };
  },
  methods: {
    getPotholes() {
      this.details = false;
      PotholeService.getVerifiedPotholes().then((response) => {
        this.potholes = response.data;
      });
    },
    getEmployees() {
      EmployeeService.listEmployees().then((response) => {
        this.employees = response.data;
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
      this.details = false;
      if (!this.scheduled) {
        PotholeService.listScheduled().then((response) => {
          this.unscheduled = false;
          this.completed = false;
          this.potholes = response.data;
        });
      } else {
        this.scheduled = false;
        PotholeService.getVerifiedPotholes(id).then((response) => {
          this.potholes = response.data;
        });
      }
    },
    getUnscheduledPotholes(id) {
      this.details = false;
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
      this.details = false;
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
            if (this.details) {
              pothole.showDetails = false;
              this.details = !this.details;
            } else {
              pothole.showDetails = true;
              this.details = !this.details;
            }

            // pothole.showDetails = !pothole.showDetails;
            // if (pothole.showDetails === false) {
            //   pothole.showDetails = true;
            //   }
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
            if (this.details) {
              pothole.showDetails = false;
              this.details = !this.details;
            } else {
              pothole.showDetails = true;
              this.details = !this.details;
            }
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
            if (this.details) {
              pothole.showDetails = false;
              this.details = !this.details;
            } else {
              pothole.showDetails = true;
              this.details = !this.details;
            }
          }
        });
      });
    },
    showDetailsAll(id) {
      PotholeService.getVerifiedPotholes().then((response) => {
        this.scheduled = false;
        this.unscheduled = false;
        this.completed = false;
        this.potholes = response.data;
        this.potholes.forEach((pothole) => {
          if (pothole.potholeId === id) {
            if (this.details) {
              pothole.showDetails = false;
              this.details = !this.details;
            } else {
              pothole.showDetails = true;
              this.details = !this.details;
            }
          }
        });
      });
    },
    showDetails(id) {
      this.potholeIdForRepair = id;
      if (!this.scheduled && !this.unscheduled && !this.completed) {
        this.showDetailsAll(id);
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
    scheduleRepair() {
      let potholeToSchedule = {};
      for (let i = 0; i < this.potholes.length; i++) {
        if (this.potholes[i].potholeId === this.potholeIdForRepair) {
          potholeToSchedule = this.potholes[i];
          break;
        }
      }
      potholeToSchedule.repairDate = this.dateForRepair;
      potholeToSchedule.repairStatus = "scheduled";
      potholeToSchedule.employeeId = this.selectedEmployee;
      PotholeService.updateRepairDate(potholeToSchedule).then((response) => {
        if (response.status === 200) {
          PotholeService.updateRepairStatus(potholeToSchedule).then(
            (response) => {
              if (response.status === 200) {
                console.log("Success!");
              }
            }
          );
        }
      });
      this.showAlert = true;
      this.showS = false;
      this.details = false;
      setTimeout(() => this.showAlert = false, 3000);
    },
    completePothole() {
      let potholeToRepair = {};
      for (let i = 0; i < this.potholes.length; i++) {
        if (this.potholes[i].potholeId === this.potholeIdForRepair) {
          potholeToRepair = this.potholes[i];
          break;
        }
      }
      PotholeService.repairedPothole(potholeToRepair).then((response) => {
        if (response.status === 200) {
          console.log("Success!");
        }
      });
      this.showMark = true;
      this.details = false;
      potholeToRepair.repairStatus = "completed";
      setTimeout(() => this.showMark = false, 3000);
    },
  },
  created() {
    this.getPotholes();
    this.getEmployees();
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
  position: relative;
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
.overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  height: 100%;
  z-index: 100;
  background-color: rgba(0, 0, 0, 0.6);
}
.schedule-container {
  border: 1px solid rgba(136, 136, 136, 0.276);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  background-color: white;
  height: 65%;
  width: 65%;
  margin: 5%;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.slide-enter-active,
.slide-leave-active {
  transition: transform 0.5s;
}
.slide-enter,
.slide-leave-to {
  transform: translateY(50%);
}
.schedule-form {
  border-radius: 5px;
  padding: 2.5em 7.25em 2.5em 4em;
  margin-bottom: 1.75em;
  font-size: 1em;
  letter-spacing: 0.125em;
  text-align: center;
}
.schedule-form h1 {
  margin-bottom: 2em;
  font-size: 1.8em;
}
.date,
.time,
.employee,
.id {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  justify-items: center;
  align-items: baseline;
  margin: 1.8em;
}
.schedule-form label {
  font-size: 1.2em;
  font-weight: 700;
  width: 30%;
}
.schedule-form input,
.schedule-form select {
  width: 50%;
  border: none;
  box-shadow: 0 1rem 2rem rgba(0, 0, 0, 0.1);
}
#employee {
  font-size: 1rem;
  padding: 1rem 2.5rem;
  border-radius: 2px;
  background-color: rgba(255, 255, 255, 0.5);
  border: none;
  border-bottom: 3px solid transparent;
  width: 50%;
  display: block;
}
.schedule-button {
  display: flex;
  flex-direction: row;
  justify-content: s;
}
.button-28 {
  appearance: none;
  background-color: transparent;
  border: 2px solid #1a1a1a;
  border-radius: 15px;
  box-sizing: border-box;
  color: #3b3b3b;
  cursor: pointer;
  display: inline-block;

  font-size: 16px;
  font-weight: 600;
  line-height: normal;
  margin: 2.5em 1.5em;
  height: 60px;

  outline: none;
  padding: 16px 24px;
  text-align: center;
  text-decoration: none;
  transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  width: 100%;
  will-change: transform;
}

.button-28:disabled {
  pointer-events: none;
}

.button-submit:hover {
  color: #fff;
  background-color: #2978b5;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}
.button-cancel:hover {
  color: #fff;
  background-color: red;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}
.button-28:active {
  box-shadow: none;
  transform: translateY(0);
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
</style>
