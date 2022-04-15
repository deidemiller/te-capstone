<template>
  <div id="dashboard">
    <div class="side-menu">
      <div class="brand-logo">
        <img src="../assets/images/pothole_tracker_logo.png" alt="" />
      </div>
      <ul>
        <!-- <li>
          <font-awesome-icon icon="fa-solid fa-house" />
          <span>Dashboard</span>
        </li>
        <li> -->
        <router-link tag="li" to="/dashboard"
          ><font-awesome-icon icon="fa-solid fa-house" />DashBoard</router-link
        >
        <!-- <font-awesome-icon icon="fa-solid fa-list-check" /><span>Review</span> -->
        <!-- </li> -->
        <router-link tag="li" to="/dashboard/review"
          ><font-awesome-icon
            icon="fa-solid fa-list-check"
          />Review</router-link
        >
      </ul>
    </div>
    <div class="container-dashboard">
      <div class="header">
        <div class="nav">
          <div class="search">
            <input type="text" placeholder="Search.." />
            <button type="submit">🔍</button>
          </div>
          <div class="user">
            <div class="welcome">
              <h4>Welcome back, John!</h4>
            </div>
            <div class="notification">
              <div><font-awesome-icon icon="fa-solid fa-bell" /></div>
              <div><font-awesome-icon icon="fa-solid fa-comments" /></div>
            </div>
            <div class="user-pic">
              <img src="../assets/images/user.jpg" alt="user-pic" />
            </div>
          </div>
        </div>
      </div>
      <router-view />
    </div>
  </div>
</template>

<script scoped>
export default {
  name: "dashboard",
};
</script>

<style scoped>
#dashboard {
  height: 100vh;
}
img {
  width: 50%;
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
  padding: 1em;
}
.side-menu li {
  font-size: 1.2em;
  padding: 2em 4em;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
  font-weight: 700;
}
.side-menu li:hover {
  background-color: #22577e;
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
li.router-link-exact-active {
  background-color: #22577e;
  color: white;
}
</style>
