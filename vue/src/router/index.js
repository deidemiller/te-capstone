import Vue from "vue";
import Router from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Logout from "../views/Logout.vue";
import Register from "../views/Register.vue";
import store from "../store/index";
import PotholeMap from "../views/PotholeMapView.vue";
import DashMain from "../components/DashboardMain.vue";
import DashReview from "../components/DashboardReview.vue";
import DashSchedule from "../components/DashboardSchedule.vue";
import DashBoard from "../views/DashBoardView.vue";
import DashboardReviewView from "../views/DashboardReviewView";
import DashboardMainView from "../views/DashboardMainView";

Vue.use(Router);

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/potholes",
      name: "potholes",
      component: PotholeMap,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: DashBoard,
      children: [
        {
          path: "",
          component: DashMain,
        },
        {
          path: "review",
          component: DashReview,
        },
        {
          path: "schedule",
          component: DashSchedule,
        },
      ],
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/dashboard/main",
      name: "dashboardMain",
      component: DashboardMainView,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/dashboard/review",
      name: "dashboardReview",
      component: DashboardReviewView,
      meta: {
        requiresAuth: false,
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === "") {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
