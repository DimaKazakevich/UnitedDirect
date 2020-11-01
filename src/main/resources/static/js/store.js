import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)
export default new Vuex.Store({
    state: {
        status: '',
        token: localStorage.getItem('token') || '',
        email: ''
    },
    mutations: {
        auth_request(state){
            state.status = 'loading'
        },
        auth_success(state, token){
            state.status = 'success'
            state.token = token.token
            state.email = token.email
        },
        auth_error(state){
            state.status = 'error'
        },
        logout(state){
            state.status = ''
            state.token = ''
            state.email = ''
        },
        register_success(state) {
            state.status = 'success'
        },
        register_failure(state) {
            state.status = 'failure'
        }
    },
    getters: {
        isLoggedIn: state => !!state.token,
        authStatus: state => state.status,
        userEmail: state => state.email
    },
    actions: {
        async login({commit}, user){
            commit('auth_request')
            try {
                let resp = await axios({url: '/api/auth/login', data: user, method: 'POST'})
                const token = resp.data.token
                const email = resp.data.email
                localStorage.setItem('token', token)
                axios.defaults.headers.common['Authorization'] = token
                commit('auth_success', {token: token, email: email})
            } catch(err) {
                commit('auth_error')
                localStorage.removeItem('token')
                console.log(err.message)
            }
        },
        async logout({commit}){
            commit('logout')
            localStorage.removeItem('token')
            delete axios.defaults.headers.common['Authorization']
        },
        register({commit}, user) {
            try {
                axios({url: '/api/auth/register', data: user, method: 'POST'})
                commit('register_success')
            } catch (err) {
                commit('register_failure')
                console.log(err.message)
            }
        }
    },
    plugins: [createPersistedState()]
})