import Vue from 'vue'
import '@babel/polyfill'
import App from './pages/App.vue'
import VueRouter from 'vue-router'
import store from './store'
Vue.use(VueRouter);

const token = localStorage.getItem('token')
if (token) {
    Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}

let v_catalog = require('./components/v-catalog.vue');
let v_main_content = require('./components/v-main-content.vue');
let v_login = require('./components/v-login.vue')

const routes = [
    {path: '/', name: 'mainPage', component: v_main_content.default},
    {path: '/products/:category', name: 'categoryPage' , component: v_catalog.default},
    {path: '/login', name: 'login', component: v_login.default}
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
}).$mount('#app');
