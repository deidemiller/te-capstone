<template>
  <div>
    <div class="schedule">
      <div class="employee">
        <table>
          <thead>
            <tr>
              <th>Employee Title</th>
              <th>Employee Profile</th>
              <th>Employee Name</th>
              <th>View Assigned Potholes</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="employee in employees" v-bind:key="employee.employeeId" >
              <td>{{ employee.title }}</td>
              <td><img v-bind:src="employee.imageUrl" alt="" v-if="employee.imageUrl != null" /></td>
              <td>{{ employee.firstName }} {{ employee.lastName }}</td>
              <td>
                <button
                  class="button-35"
                  role="button"
                  v-on:click="toggleDetails(employee.employeeId)"
                >
                  View Assigned
                </button>
              </td>
            </tr>
            <tr>
              <td colspan="4">
                <button
                  class="button-35"
                  role="button"
                  v-on:click="getAllScheduledPotholes()"
                  v-if="showAssigned === true"
                >
                  Reset Filter
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="pothole" id="schedule">
        <table>
          <thead id="header">
            <tr>
              <th>Employee Assigned</th>
              <th>Reported Date</th>
              <th>Scheduled Repair Date</th>
              <th>Location</th>
              <th>Severity</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="pothole in potholes" v-bind:key="pothole.potholeId">
              <td>
                {{ pothole.employeeFirstName }} {{ pothole.employeeLastName }}
              </td>
              <td>{{ pothole.dateReported }}</td>
              <td>{{ pothole.repairDate }}</td>
              <td>{{ pothole.crossStreet1 }} & {{ pothole.crossStreet2 }}</td>
              <td>{{ pothole.severity }}</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="5">
                <button
                  class="button-35"
                  role="button"
                  type="submit"
                  v-on:click="printSchedule()"
                >
                  Print Report
                </button>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import EmployeeService from "../services/EmployeeService";
import PotholeService from "../services/PotholeService";
export default {
  name: "dashboard-schedule",
  showAssigned: false,
  data() {
    return {
      employees: [],
      potholes: [],
      employeeID: 0,
      showButton: true,
    };
  },
  methods: {
    getEmployees() {
      EmployeeService.listEmployees().then((response) => {
        this.employees = response.data;
      });
    },
    toggleDetails(id) {
      if (this.employeeID === 0) {
        if (this.showAssigned === false) {
          PotholeService.getPotholesByEmployeeId(id).then((response) => {
            this.potholes = response.data;
            this.showAssigned = true;
            this.employeeID = id;
          });
        } else {
          this.getAllScheduledPotholes();
          this.employeeID = 0;
        }
      } else {
        if (this.employeeID === id) {
          this.getAllScheduledPotholes();
          this.employeeID = 0;
        } else {
          PotholeService.getPotholesByEmployeeId(id).then((response) => {
            this.potholes = response.data;
            this.showAssigned = true;
          });
          this.employeeID = id;
        }
      }
    },
    getAllScheduledPotholes() {
      PotholeService.getScheduledPotholesWithEmployeeInfo().then((response) => {
        this.potholes = response.data;
        this.showAssigned = false;
      });
    },
    printSchedule() {
      this.showButton = false;
      const header = document.getElementById("header").innerHTML;
      const tableBody = this.createScheduleRows();
      const schedule = window.open("", "", "height=500, width=500");
      schedule.document.write("<html>");
      schedule.document.write("<body > <h1>Schedule<br>");
      schedule.document.write("<table>");
      schedule.document.write(header);
      schedule.document.write("<tbody>");
      schedule.document.write(tableBody);
      schedule.document.write("</tbody>");
      schedule.document.write("</table>");
      schedule.document.write("</body></html>");
      schedule.document.close();
      schedule.print();
      this.showButton = true;
    },
    createScheduleRows() {
      let tableBody = "";
      this.potholes.forEach((pothole) => {
        tableBody += "<tr>";
        tableBody += `<td>${pothole.employeeFirstName} ${pothole.employeeLastName}</td>`;
        tableBody += `<td>${pothole.dateReported}</td>`;
        tableBody += `<td>${pothole.repairDate}</td>`;
        tableBody += `<td>${pothole.crossStreet1} & ${pothole.crossStreet2}</td>`;
        tableBody += `<td>${pothole.severity}</td>`;
        tableBody += "</tr>";
      });

      return tableBody;
    },
  },
  created() {
    this.getEmployees();
    this.getAllScheduledPotholes();
  },
};
</script>

<style scoped>
.schedule {
  position: relative;
  margin-top: 10vh;
  min-height: 90vh;
  display: flex;
  flex-direction: row;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
}
.employee {
  width: 50%;
  overflow-y: scroll;
}
.pothole {
  width: 50%;
  overflow-y: scroll;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
}
.employee,
.pothole {
  margin: 1em;
}
thead th {
  position: sticky;
}
td img {
  height: 50px;
  border-radius: 100%;
  border: 5px solid #e6e7e8;
}
table {
  padding: 1.5em;
  border-collapse: collapse;
  width: 100%;
}
thead {
  color: #999;
  border-bottom: 2px solid rgb(221, 217, 217);
  height: 100px;
}
th,
td {
  padding: 1em;
  font-size: 1.05em;
  font-weight: 700;
}
tr:nth-child(even) {
  background-color: #dddddd;
}
tr:nth-child(odd) {
  background-color: #edeef1;
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
  padding: 1rem 1.2rem;
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
