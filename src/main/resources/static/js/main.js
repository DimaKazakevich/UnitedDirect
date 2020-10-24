import Vue from 'vue'
import App from './pages/App.vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter);

let v_catalog = require('./components/v-catalog.vue');
let v_main_content = require('./components/v-main-content.vue');
let v_login = require('./components/v-login.vue')

const routes = [
    {path: '/', name: 'mainPage', component: v_main_content.default},
    {path: '/products/:category', name: 'categoryPage' , component: v_catalog.default},
    {path: '/login', component: v_login.default}
];

const router = new VueRouter
({
    mode: 'history',
    routes
})


new Vue({
    render: h => h(App),
    router: router
}).$mount('#app');
