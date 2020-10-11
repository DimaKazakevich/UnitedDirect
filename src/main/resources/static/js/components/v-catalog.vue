<template>
    <div class="v-catalog">
<!--        <div class="menu">-->
<!--            <ul class="topLevelMenu">-->
<!--                <li v-on:click="example('kits')" class="menuHeader">Kits</li>-->
<!--                <li class="menuHeader">Fashion</li>-->
<!--                <li class="menuHeader">Souvenirs</li>-->
<!--                <li v-on:click="example('training')" class="menuHeader">Training</li>-->
<!--                <li class="menuHeader">Home</li>-->
<!--            </ul>-->
<!--        </div>-->
        <div class="v-catalog__filters col-xs-3"></div>
        <div class="v-catalog__list">
            <v-catalog-item v-for="product in products" :key="product.id" v-bind:product-data="product" />
        </div>
    </div>
</template>

<script>
    import vCatalogItem from './v-catalog-item.vue'
    import axios from 'axios'

    export default {
        name: 'v-catalog',
        components: {vCatalogItem},
        component: {
            vCatalogItem
        },
        data() {
            return {
                products: []
            }
        },
        computed: {},
        created() {
            axios.get('/products').then(response =>
                response.data.forEach(product => this.products.push(product))
            );
        },
        methods: {
            example(category) {
                this.products = [];
                axios.get(`/products/${category}`).then(response =>
                    response.data.forEach(product => this.products.push(product))
                );
            }
        }
    }
</script>

<style lang="css">
.v-catalog {
    display: flex;
    justify-content: center;
    margin-top: 40px;
}
div.v-catalog div.v-catalog__list {
    display: flex;
    flex-wrap: wrap;
}
</style>