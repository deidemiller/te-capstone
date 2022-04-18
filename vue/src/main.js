import Vue from "vue";
import App from "./App.vue";
import router from "./router/index";
import store from "./store/index";
import axios from "axios";
import "leaflet/dist/leaflet.css";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faHouse } from "@fortawesome/free-solid-svg-icons";
import { faBell } from "@fortawesome/free-solid-svg-icons";
import { faComments } from "@fortawesome/free-solid-svg-icons";
import { faListCheck } from "@fortawesome/free-solid-svg-icons";
import { faCalendarCheck } from "@fortawesome/free-solid-svg-icons";
import { faCalendarXmark } from "@fortawesome/free-solid-svg-icons";
import { faScrewdriverWrench } from "@fortawesome/free-solid-svg-icons";
import { faCalendarDays } from "@fortawesome/free-solid-svg-icons";
import { faThumbsUp } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(faHouse);
library.add(faBell);
library.add(faComments);
library.add(faListCheck);
library.add(faCalendarCheck);
library.add(faCalendarXmark);
library.add(faScrewdriverWrench);
library.add(faCalendarDays);
library.add(faThumbsUp);
Vue.component("font-awesome-icon", FontAwesomeIcon);
Vue.config.productionTip = false;

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
