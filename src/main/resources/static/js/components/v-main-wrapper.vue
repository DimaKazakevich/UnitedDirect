<template>
    <div class="v-main-wrapper" style="height: 100%">
        <v-header />
        <v-menu />
        <v-carousel v-if="isCategoryPage" />
        <router-view class="content" style="height: 100%"></router-view>
        <v-footer></v-footer>
    </div>
</template>

<script>
    import vMenu from "./v-menu.vue";
    import vCarousel from "./v-carousel.vue"
    import vCatalog from "./v-catalog.vue";
    import vHeader from "./v-header.vue";
    import vFooter from "./v-footer.vue";
    import axios from 'axios'
    import {mapGetters} from "vuex";

    export default {
        components: {
            vFooter,
            vHeader,
            vMenu,
            vCarousel,
            vCatalog
        },
        computed: {
            isCategoryPage() {
                return this.$route.name === 'categoryPage'
            },
          ...mapGetters([
            'catalogItems'
          ]),
        }
    }
</script>

<style lang="scss">
.v-main-wrapper {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}
</style>