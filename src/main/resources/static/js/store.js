import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)
export default new Vuex.Store({
    state: {
        status: '',
        token: localStorage.getItem('token') || '',
        username: ''
    },
    mutations: {
        auth_request(state){
            state.status = 'loading'
        },
        auth_success(state, token){
            state.status = 'success'
            state.token = token.token
            state.username = token.username
        },
        auth_error(state){
            state.status = 'error'
        },
        logout(state){
            state.status = ''
            state.token = ''
            state.username = ''
        }
    },
    getters: {
        isLoggedIn: state => !!state.token,
        authStatus: state => state.status,
        userName: state => state.username
    },
    actions: {
        async login({commit}, user){
            commit('auth_request')
            try {
                let resp = await axios({url: '/api/auth/login', data: user, method: 'POST'})
                const token = resp.data.token
                const username = resp.data.user
                localStorage.setItem('token', token)
                axios.defaults.headers.common['Authorization'] = token
                commit('auth_success', {token: token, username: username})
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
        }
    },
    plugins: [createPersistedState()]
})