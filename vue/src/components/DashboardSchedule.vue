<template>
  <div>
    <div class="schedule">
      <table>
        <thead>
          <tr>
            <th>Pothole#</th>
            <th>Employee Profile</th>
            <th>Employee Name</th>
            <th>Schedule Date</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td><img src="../assets/images/e1.jpg" alt="" /></td>
            <td>John Doe</td>
            <td>2020-04-18</td>
            <td>
              <button class="button-35" role="button">Print Report</button>
            </td>
          </tr>
          <tr>
            <td>2</td>
            <td><img src="../assets/images/e2.jpg" alt="" /></td>
            <td>John Doe</td>
            <td>2020-04-18</td>
            <td>
              <button class="button-35" role="button">Print Report</button>
            </td>
          </tr>
          <tr>
            <td>3</td>
            <td><img src="../assets/images/e3.jpg" alt="" /></td>
            <td>John Doe</td>
            <td>2020-04-18</td>
            <td>
              <button class="button-35" role="button">Print Report</button>
            </td>
          </tr>
          <tr>
            <td>4</td>
            <td><img src="../assets/images/e4.jpg" alt="" /></td>
            <td>John Doe</td>
            <td>2020-04-18</td>
            <td>
              <button class="button-35" role="button">Print Report</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "dashboard-schedule",
};
</script>

<style scoped>
.schedule {
  position: relative;
  margin-top: 10vh;
  min-height: 90vh;
  display: flex;
  flex-direction: column;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  overflow-y: scroll;
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
}
thead {
  color: #999;
  border-bottom: 2px solid rgb(221, 217, 217);
  height: 100px;
}
th,
td {
  padding: 1em;
  font-size: 1.2em;
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
  font-size: 1.2rem;
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
