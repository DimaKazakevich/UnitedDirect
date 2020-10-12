<template>
    <div class="v-catalog">
        <div class="v-catalog__filters col-xs-3"></div>
        <div class="v-catalog__list">
            <v-catalog-item v-for="product in products" :key="product.id" v-bind:product-data="product" />
        </div>
    </div>
</template>

<script>
    import vCatalogItem from './v-catalog-item.vue'
    import axios from 'axios'
    import { prodComponent } from './data.json'

    export default {
        name: 'v-catalog',
        components: {vCatalogItem},
        component: {
            vCatalogItem
        },
        data() {
            return prodComponent;
        },
        computed: {},
        created() {
            axios.get('/products').then(response =>
                response.data.forEach(product => prodComponent.products.push(product))
            );
        }
    }
</script>

<style lang="scss">
.v-catalog {
    display: flex;
    justify-content: center;
    margin-top: 40px;
    &__list {
             display: flex;
             flex-wrap: wrap;
     }
}
</style>