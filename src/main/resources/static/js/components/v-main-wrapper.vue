<template>
    <div class="v-main-wrapper">
        <v-catalog></v-catalog>
    </div>
</template>

<script>
    import VCatalog from "./v-catalog.vue";
    export default {
        components: {VCatalog}
    }
</script>

<style lang="css">
.v-main-wrapper {
    display: flex;
    justify-content: center;
}
</style>