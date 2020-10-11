import Vue from 'vue'
import App from './pages/App.vue'
/*
var productApi = Vue.resource('/products{/id}');

Vue.component('products-list', {
    props: ['products'],
    template: '<div id="content" style="display:flex; flex-wrap:wrap; justify-content:end; width:1020px; border-top: 1px solid lightgray; padding: 30px;"><product-row v-for="product in products" :key="product.id" :product="product" /></div>',
    created: function () {
        productApi.get().then(result =>
            result.json().then(data =>
                data.forEach(product => this.products.push(product))
            )
        )
    }
});

});*/


new Vue({
    render: h => h(App)
}).$mount('#app');
