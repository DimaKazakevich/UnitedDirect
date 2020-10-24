<template>
    <div class="v-main-wrapper">
        <v-header />
        <v-menu />
        <v-carousel v-if="isCategoryPage" />
        <router-view></router-view>
    </div>
</template>

<script>
    import vMenu from "./v-menu.vue";
    import vCarousel from "./v-carousel.vue"
    import vCatalog from "./v-catalog.vue";
    import vHeader from "./v-header.vue";

    export default {
        components: {
            vHeader,
            vMenu,
            vCarousel,
            vCatalog
        },
        computed: {
            isCategoryPage() {
                return this.$route.name === 'categoryPage'
            }
        }
    }
</script>

<style lang="scss">
.v-main-wrapper {
    min-height: 100%;
}
</style>