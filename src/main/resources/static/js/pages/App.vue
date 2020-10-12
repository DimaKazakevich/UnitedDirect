<template>
    <div id="app">
       <v-main-wrapper></v-main-wrapper>
    </div>
</template>

<script>
    import vMainWrapper from "../components/v-main-wrapper.vue";

export default {
    name: 'app',
    components: {vMainWrapper: vMainWrapper}
}
</script>

<style lang="css">
    #app {
    }
</style>

