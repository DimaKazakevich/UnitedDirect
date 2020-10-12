import Vue from 'vue'
import App from './pages/App.vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

let v_catalog = require('./components/v-catalog.vue')
let v_menu = require('./components/v-menu.vue')
const routes = [
    {path: '/', component: v_catalog},
    {path: 'products/kits', component: v_menu}
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
