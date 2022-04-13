<template>
  <div id="dashboard">
    <div class="side-menu">
      <div class="brand-logo">
        <img src="../assets/images/pothole_tracker_logo.png" alt="" />
      </div>
      <ul>
        <li>
          <font-awesome-icon icon="fa-solid fa-house" />
          <span>dashboard</span>
        </li>
        <li>
          <font-awesome-icon icon="fa-solid fa-house" /><span>dashboard</span>
        </li>
        <li>
          <font-awesome-icon icon="fa-solid fa-house" /><span>dashboard</span>
        </li>
        <li>
          <font-awesome-icon icon="fa-solid fa-house" /><span>dashboard</span>
        </li>
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
            <a href="#" class=""></a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "dashboard",
};
</script>

<style scoped>
#dashboard {
  height: 100vh;
}
img {
  width: 60%;
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
  text-transform: uppercase;
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
</style>
