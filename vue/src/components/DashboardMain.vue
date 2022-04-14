<template>
  <div class="main">
    <div class="content">
      <div class="cards">
        <div class="card">
          <div class="box">
            <h1>200</h1>
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
            <h1>150</h1>
            <h3>Ready for Repair</h3>
          </div>
          <div class="icon-case">
            <font-awesome-icon
              icon="fa-solid fa-screwdriver-wrench"
              size="3x"
              :style="{ color: 'orange' }"
            />
          </div>
        </div>
      </div>
      <div class="content-2">
        <div class="recent">
          <div class="title">
            <h2>Recent Projects</h2>
            <button class="button-64" role="button">
              <span class="text">View All</span>
            </button>
          </div>

          <table>
            <tr>
              <th>Date</th>
              <th>Street</th>
              <th>Verify</th>
              <th>Delete</th>
            </tr>

            <tr>
              <td>2022-4-12</td>
              <td>This is street name</td>
              <td>
                <button class="button-35" role="button">Verify</button>
              </td>
              <td>
                <button class="button-35" role="button">Delete</button>
              </td>
            </tr>
          </table>
        </div>
        <div class="recent-2">
          <div class="title">
            <h2>Employees</h2>
            <button class="button-64" role="button">
              <span class="text">View All</span>
            </button>
          </div>
          <table>
            <tr>
              <th>Profile</th>
              <th>Name</th>
              <th>Availability</th>
            </tr>
            <tr>
              <td><img src="../assets/images/e1.jpg" alt="" /></td>
              <td>John Doe</td>
              <td>
                <font-awesome-icon
                  icon="fa-solid fa-calendar-check"
                  size="2x"
                  :style="{ color: 'green' }"
                />
              </td>
            </tr>
            <tr>
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
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script scoped>
export default {
  name: "dashboard-main",
};
</script>

<style>
.main {
  position: relative;
  margin-top: 10vh;
  min-height: 90vh;
}
#dashboard {
  height: 100vh;
}
img {
  width: 60%;
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
.side-menu {
  position: fixed;
  background-color: #cddfe3;
  width: 20vw;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.side-menu .brand-logo {
  display: flex;
  align-items: center;
  justify-content: center;
}
ul {
  padding: 0;
}
.side-menu li {
  font-size: 1.5em;
  padding: 1em 4em;
  display: flex;
  align-items: center;

  font-weight: 700;
}
.side-menu li:hover {
  background-color: #888;
  color: white;
}
.side-menu li span {
  padding-left: 1em;
}
.container-dashboard {
  position: fixed;
  right: 0;
  width: 80vw;
  height: 100vh;
  background-color: #f1f1f1;
}
.container-dashboard .header {
  position: fixed;
  top: 0;
  right: 0;
  width: 80vw;
  height: 10vh;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}
.container-dashboard .header .nav {
  width: 90%;
  display: flex;
  align-items: center;
}
.container-dashboard .header .search {
  flex: 3;
}
.container-dashboard .header .search button {
  width: 4em;
  height: 4em;
  border: none;
  background: transparent;
  transform: translateX(-40px);
}
.container-dashboard .header .user {
  flex: 1;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.container-dashboard .header input[type="text"] {
  border: none;
  background: #f1f1f1;
  padding: 10px;
  width: 50%;
  border-radius: 50px;
}
.container-dashboard .header .user .user-pic {
  position: relative;
  width: 3.5em;
  height: 3.5em;
}
.container-dashboard .header .user .user-pic img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-radius: 50%;
}
.container-dashboard .header .user .notification {
  width: 20%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.container-dashboard .content {
  position: relative;
  margin-top: 10vh;
  min-height: 90vh;
}
.container-dashboard .content .cards {
  padding: 2em 1.5em;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
.container-dashboard .content .cards .card {
  width: 30%;
  height: 15em;
  background-color: rgba(255, 255, 255, 0.739);
  margin: 2em 1em;
  display: flex;
  align-items: center;
  justify-content: space-around;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
}
.container-dashboard .content .content-2 {
  min-height: 60vh;
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  flex-wrap: wrap;
}
.container-dashboard .content .content-2 .recent {
  min-height: 50vh;
  flex: 5;
  background: rgba(255, 255, 255, 0.739);
  margin: 0 2.5em 2.5em 2.5em;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
}
.container-dashboard .content .content-2 .recent-2 {
  flex: 2;
  min-height: 50vh;
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

.container-dashboard .content .content-2 .recent-2 table td:nth-child(1) img {
  height: 4em;
  width: 4em;
  border-radius: 50%;
}
</style>
