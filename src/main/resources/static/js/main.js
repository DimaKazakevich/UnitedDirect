import Vue from 'vue'
import '@babel/polyfill'
import App from './pages/App.vue'
import VueRouter from 'vue-router'
import store from './store/store'
import axios from 'axios'
Vue.use(VueRouter);

const token = localStorage.getItem('token')
if (token) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
}

let v_catalog = require('./components/v-catalog.vue');
let v_main_content = require('./components/v-main-content.vue');
let v_login = require('./components/v-login.vue');
let v_register = require('./components/v-register.vue')
let v_cart = require('./components/v-cart.vue')
let v_confirm_order = require('./components/v-confirm-order.vue')
let v_item_details = require('./components/v-item-details.vue')
let v_edit_products = require('./components/v-edit-product.vue')
let v_add_product = require('./components/v-add-item.vue')
let v_users = require('./components/v-orders.vue')

const routes = [
    {path: '/', name: 'mainPage', component: v_main_content.default},
    {path: '/product/:id', name: 'product_details', component: v_item_details.default},
    {path: '/products/:category', name: 'categoryPage' , component: v_catalog.default},
    {path: '/login', name: 'login', component: v_login.default},
    {path: '/register', name: 'register', component: v_register.default},
    {path: '/cart', name: 'cart', component: v_cart.default},
    {path: '/order', name: 'order', component: v_confirm_order.default},
    {path: '/edit/:id', name: 'edit', component: v_edit_products.default},
    {path: '/add-product/', name: 'add_product', component: v_add_product.default},
    {path: '/orders/', name: 'users', component: v_users.default},
    {path: '*', component: v_main_content.default}
];

const router = new VueRouter
({
    mode: 'history',
    routes
})

new Vue({
    render: h => h(App),
    store,
    router
}).$mount('.app');
