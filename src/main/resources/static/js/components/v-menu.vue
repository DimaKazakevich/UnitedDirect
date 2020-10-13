<template>
    <div class="menu">
        <ul class="menu__list">
            <li v-for="category in categories" :key="category.id" class="menu__item">
                <router-link :to="{ name: 'categoryPage', params: {category: category.category.toLocaleLowerCase()}}">{{category.category}}</router-link>
            </li>
        </ul>
    </div>
</template>

<script>
    import axios from "axios";
    import { prodComponent } from './data.json'
    export default {
        name: "v-menu",
        data() {
            return {
                products: prodComponent,
                categories: []
            }
        },
        methods: {
            getAllByCategory(category) {
                prodComponent.products = [];
                axios.get(`/api/products/${category}`).then(response =>
                    response.data.forEach(product => prodComponent.products.push(product))
                );
            }
        },
        created() {
            axios.get('/api/products/categories').then(response =>
                response.data.forEach(product => this.categories.push(product))
            );
        }
    }
</script>

<style lang="scss">
    .menu {
        background-color:black;
        height: 40px;
        &__list {
            margin: 0px;
            padding: 0px;
            display:flex;
            justify-content:space-around;
            font-size: 16pt;
        }
        &__item {
            color: white;
            height: 32px;
            margin: 4px auto;
            list-style-type: none;
        }
        &__item:hover {
            background: white;
            cursor: pointer;
        }
        &__item a {
            color: white;
            margin: 0px auto;
            text-decoration: none;
            height: 32px;
            padding-left: 30px;
            padding-right: 30px;
        }
        &__item:hover a {
            color:black;
        }
    }
</style>