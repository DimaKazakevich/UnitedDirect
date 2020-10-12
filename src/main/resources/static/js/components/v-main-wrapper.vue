<template>
    <div class="v-main-wrapper">
        <v-menu />
        <v-carousel />
        <v-catalog />
    </div>
</template>

<script>
    import vMenu from "./v-menu.vue";
    import vCarousel from "./v-carousel.vue"
    import vCatalog from "./v-catalog.vue";
    export default {
        components: {
            vMenu,
            vCarousel,
            vCatalog
        }
    }
</script>

<style lang="css">
.v-main-wrapper {
    /*display: flex;*/
    /*justify-content: center;*/
    /*width: max-content;*/
}
</style>