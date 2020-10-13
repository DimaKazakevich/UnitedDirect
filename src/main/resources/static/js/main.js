import Vue from 'vue'
import App from './pages/App.vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter);

let v_catalog = require('./components/v-catalog.vue');
const routes = [
    {path: '/', component: v_catalog.default},
    {path: '/products/:category', name: 'categoryPage' , component: v_catalog.default}
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
