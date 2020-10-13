<template>
    <div class="v-catalog">
        <div class="v-catalog__filters col-xs-3"></div>
        <div>
            <v-sorting-drop-down />
            <div class="v-catalog__list">
                <v-catalog-item v-for="product in products" :key="product.id" v-bind:product-data="product" />
            </div>
        </div>
    </div>
</template>

<script>
    import vCatalogItem from './v-catalog-item.vue'
    import vMenu from './v-menu.vue'
    import axios from 'axios'
    import { prodComponent } from './data.json'
    import vSortingDropDown from "./v-sorting-drop-down.vue";

    export default {
        name: 'v-catalog',
        components: {
            vSortingDropDown,
            vCatalogItem,
            vMenu
        },
        data() {
            return prodComponent;
        },
        computed: {},
        created() {
            axios.get(`/api/products/${this.$route.params.category}`).then(response =>
                response.data.forEach(product => prodComponent.products.push(product))
            );
        },
        watch: {
            '$route' (to, from) {
                if (this.$route.params.category) {
                    vMenu.methods.getAllByCategory(this.$route.params.category);
                }
            }
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