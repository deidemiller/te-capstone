<template>
  <div>
    <div class="main">
      <div class="content">
        <div class="cards">
          <div class="card">
            <div class="box">
              <h1>{{ pendingPotholes.length }}</h1>
              <h3>Ready for Review</h3>
            </div>
            <div class="icon-case">
              <font-awesome-icon
                icon="fa-solid fa-list-check"
                size="3x"
                :style="{ color: 'red' }"
              />
            </div>
          </div>
          <div class="card">
            <div class="box">
              <h1>{{ scheduled }}</h1>
              <h3>Scheduled</h3>
              <h1>{{ unScheduled }}</h1>
              <h3>Need to be Scheduled</h3>
            </div>
            <div class="icon-case">
              <font-awesome-icon
                icon="fa-solid fa-screwdriver-wrench"
                size="3x"
                :style="{ color: 'orange' }"
              />
            </div>
          </div>
          <div class="card">
            <div class="box">
              <h1>{{ completed }}</h1>
              <h3>Completed</h3>
            </div>
            <div class="icon-case">
              <font-awesome-icon
                icon="fa-solid fa-thumbs-up"
                size="3x"
                :style="{ color: '#6BCB77' }"
              />
            </div>
          </div>
        </div>
        <div class="content-2">
          <div class="recent">
            <div class="title">
              <h2>Pending Verification</h2>
              <button class="button-64" role="button">
                <span class="text">View All</span>
              </button>
            </div>
            <div class="table1">
              <table>
                <tr>
                  <th>Date</th>
                  <th>Street</th>
                  <th>Verify</th>
                  <th>Delete</th>
                </tr>

                <tr
                  v-for="pothole in pendingPotholes"
                  v-bind:key="pothole.potholeId"
                >
                  <td>{{ pothole.dateReported }}</td>
                  <td>
                    Street: {{ pothole.crossStreet1 }}, Nearest Intersection
                    {{ pothole.crossStreet2 }}
                  </td>
                  <td>
                    <button
                      class="button-35"
                      role="button"
                      v-on:click="verifyPothole(pothole.potholeId)"
                    >
                      Verify
                    </button>
                  </td>
                  <td>
                    <button
                      class="button-35"
                      role="button"
                      v-on:click="deletePothole(pothole.potholeId)"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              </table>
            </div>
          </div>
          <div class="recent-2">
            <div class="title">
              <h2>Employees</h2>
              <button class="button-64" role="button">
                <span class="text">View All</span>
              </button>
            </div>
            <div class="table">
              <table>
                <tr>
                  <!-- <th>Profile</th> -->
                  <th>Title</th>
                  <th>Name</th>
                  <!-- <th>Availability</th> -->
                  <th>Availability</th>
                </tr>
                <tr v-for="employee in employees" v-bind:key = "employee.employee_id">
                  <!-- <td><img src="../assets/images/e1.jpg" alt="" /></td> -->
                  <td>{{employee.title}}</td>
                  <td>{{employee.firstName}} {{employee.lastName}}</td>
                  <td>
                    <font-awesome-icon
                      icon="fa-solid fa-calendar-check"
                      size="2x"
                      :style="{ color: 'green' }"
                    />
                  </td>
                </tr>
                <!-- <tr>
                  <td><img src="../assets/images/e4.jpg" alt="" /></td>
                  <td>Jane Doe</td>
                  <td>
                    <font-awesome-icon
                      icon="fa-solid fa-calendar-xmark"
                      size="2x"
                      :style="{ color: 'red' }"
                    />
                  </td>
                </tr>
                <tr>
                  <td><img src="../assets/images/e3.jpg" alt="" /></td>
                  <td>John Doe</td>
                  <td>
                    <font-awesome-icon
                      icon="fa-solid fa-calendar-check"
                      size="2x"
                      :style="{ color: 'green' }"
                    />
                  </td>
                </tr> -->
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script scoped>
import PotholeService from "../services/PotholeService";
import EmployeeService from "../services/EmployeeService";
export default {
  name: "dashboard-main",
  data() {
    return {
      pothole: {},
      pendingPotholes: [],
      completed: 0,
      scheduled: 0,
      unScheduled: 0,
      employees: [],
    };
  },
  methods: {
    getPendingPotholes() {
      PotholeService.listPending().then((response) => {
        this.pendingPotholes = response.data;
      });
    },
    getEmployees() {
      EmployeeService.listEmployees().then(response => {
        this.employees = response.data;
      });
    },
    deletePothole(id) {
      PotholeService.delete(id).then((response) => {
        if (response.status === 200) {
          this.getPendingPotholes();
        }
      });
    },
    verifyPothole(id) {
      for (let i = 0; i < this.pendingPotholes.length; i++) {
        if (id === this.pendingPotholes[i].potholeId) {
          this.pothole = this.pendingPotholes[i];
          break;
        }
      }
      PotholeService.updatePending(this.pothole).then((response) => {
        if (response.status === 200) {
          this.pothole = {};
          console.log("cool");
        }
      });
      this.getPendingPotholes();
      this.unScheduledPotholes();
    },
    completedPotholes() {
      PotholeService.countRepaired().then((response) => {
        this.completed = response.data;
      });
    },
    scheduledPotholes() {
      PotholeService.countScheduled().then((response) => {
        this.scheduled = response.data;
      });
    },
    unScheduledPotholes() {
      PotholeService.countUnscheduled().then((response) => {
        this.unScheduled = response.data;
      });
    },
  },
  created() {
    this.getPendingPotholes();
    this.completedPotholes();
    this.scheduledPotholes();
    this.unScheduledPotholes();
    this.getEmployees();
  },
};
</script>

<style scoped>
.main {
  position: relative;
  margin-top: 10vh;
  min-height: 90vh;
}

h2 {
  font-size: 1.5em;
  letter-spacing: 0;
}
h3 {
  color: #888;
}

.button-64 {
  align-items: center;
  background-image: linear-gradient(144deg, #af40ff, #5b42f3 50%, #00ddeb);
  border: 0;
  border-radius: 8px;
  box-shadow: rgba(151, 65, 252, 0.2) 0 15px 30px -5px;
  box-sizing: border-box;
  color: #ffffff;
  display: flex;
  justify-content: center;
  line-height: 1em;
  width: 5%;
  padding: 3px;
  text-decoration: none;
  user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  cursor: pointer;
}
.button-64:active,
.button-64:hover {
  outline: 0;
}

.button-64 span {
  background-color: rgb(5, 6, 45);
  padding: 0.3em;
  border-radius: 6px;
  width: 100%;
  height: 100%;
  transition: 300ms;
  font-size: 0.5em;
}

.button-64:hover span {
  background: none;
}

@media (min-width: 768px) {
  .button-64 {
    font-size: 2em;
    min-width: 100px;
  }
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
.title {
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding: 1.5em 1em;
  border-bottom: 2px solid rgba(153, 153, 153, 0.205);
}

.content {
  padding-bottom: 10px;
  position: relative;
  margin-top: 10vh;
  min-height: 90vh;
}
.content .cards {
  padding: 2em 1.5em;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
.content .cards .card {
  width: 30%;
  height: 15em;
  background-color: rgba(255, 255, 255, 0.739);
  margin: 2em 1em;
  display: flex;
  align-items: center;
  justify-content: space-around;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
}
.content .content-2 {
  min-height: 60vh;
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  flex-wrap: wrap;
}
.content .content-2 .recent {
  height: 50vh;
  flex: 5;
  background: rgba(255, 255, 255, 0.739);
  margin: 0 2.5em 2.5rem 2.5em;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: column;
}
.table1 {
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
  height: 80%;
}
.content .content-2 .recent-2 {
  flex: 2;
  height: 50vh;
  background: rgba(255, 255, 255, 0.739);
  margin: 0 2.5em;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: column;
}

table {
  padding: 1.5em;
}
th,
td {
  text-align: center;
  padding: 1em;
}

.content .content-2 .recent-2 table td:nth-child(1) img {
  height: 4em;
  width: 4em;
  border-radius: 50%;
}
.table {
  overflow-y: scroll;
}
</style>
