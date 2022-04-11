<template>
  <div class="home">
    <the-header />
    <banner-1 />
    <steps />
    <banner-2 />
    <the-footer />
  </div>
</template>

<script>
import TheHeader from "../components/TheHeader.vue";
import Banner1 from "../components/Banner1.vue";
import Banner2 from "../components/banner2.vue";
import Steps from "../components/Steps.vue";
import TheFooter from "../components/TheFooter.vue";
export default {
  name: "home",
  components: {
    TheHeader,
    Banner1,
    Steps,
    Banner2,
    TheFooter,
  },
};
</script>
