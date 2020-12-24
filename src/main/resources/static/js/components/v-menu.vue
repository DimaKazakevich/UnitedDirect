<template>
    <div class="menu">
        <ul class="menu__list">
            <li @click="loadProducts" v-for="category in categories" :key="category.id" class="menu__item">
                <router-link :to="{ name: 'categoryPage', params: {category: category.category.toLowerCase()}}">{{category.category}}</router-link>
            </li>
        </ul>
    </div>
</template>

<script>
    import axios from "axios";
    import {mapActions, mapGetters} from "vuex";
    export default {
        name: "v-menu",
        data() {
            return {
                categories: []
            }
        },

      computed: {
        ...mapGetters([
          'catalogItems'
        ])
      },
      methods: {
        ...mapActions(['getProductFromApi', 'getPagesCount']),
        loadProducts() {
          this.getProductFromApi({page: 0, category:this.$route.params.category})
          this.getPagesCount(this.$route.params.category)
        }
      },
      created() {
          this.categories = [];
          axios.get('/api/products/categories').then(response =>
              response.data.forEach(product => this.categories.push(product))
          );
      }
    }
</script>

<style lang="scss">
    .menu {
        background-color:#b61c1c;
        height: 40px;
        &__list {
            margin: 0px;
            padding: 0px;
            display:flex;
            justify-content:space-around;
            font-size: 16pt;
        }
        &__item {
            @import url('https://fonts.googleapis.com/css?family=Open+Sans');
            font-family: "Open Sans", sans-serif;
            text-transform: uppercase;
            font-weight: 600;
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