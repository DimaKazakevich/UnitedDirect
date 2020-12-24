import Vue from 'vue'
import Vuex from 'vuex'
import actions from "./actions";
import mutations from "./mutations";
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)
export default new Vuex.Store({
    state: {
        status: '',
        token: localStorage.getItem('token') || '',
        email: '',
        roles: [],

        items: [],
        checkoutStatus: null,

        catalog_items: [],

        pagesCount: null
    },
    actions,
    mutations,
    getters: {
        isLoggedIn: state => !!state.token,
        authStatus: state => state.status,
        userEmail: state => state.email,
        cartTotalPrice: (state, getters) => {
            return getters.cartProducts.reduce((total, product) => {
                return total + product.price * product.quantity
            }, 0)
        },
        cartItems: state => state.items,
        totalCost(state) {
            return state.items.reduce((total, item) => total += item.price * item.quantity, 0);
        },
        isAdmin: state => state.roles.some(role => role === 'ROLE_ADMIN'),
        catalogItems: state => state.catalog_items,
        countCartItem: state => state.items.length,
        pagesCount: state => state.pagesCount
    },
    plugins: [createPersistedState()]
})
