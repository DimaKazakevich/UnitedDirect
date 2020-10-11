<template>
    <div id="app">
       <v-main-wrapper></v-main-wrapper>
    </div>
</template>

<script>
    import VMainWrapper from "../components/v-main-wrapper.vue";

export default {
    name: 'app',
    components: {VMainWrapper}
}
</script>

<style lang="css">
    #app {
        display: flex;
    }
</style>

