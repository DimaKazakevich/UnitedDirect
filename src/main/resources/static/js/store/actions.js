import axios from "axios";

let login = async ({commit}, user) => {
    commit('auth_request')
    try {
        let resp = await axios({url: '/api/auth/login', data: user, method: 'POST'})
        const token = resp.data.token
        const email = resp.data.email
        const roles = resp.data.roles
        localStorage.setItem('token', token)
        axios.defaults.headers.common['Authorization'] = token
        commit('auth_success', {token, email, roles})
    } catch(err) {
        commit('auth_error')
        localStorage.removeItem('token')
        console.log(err.message)
    }
}

let logout = async ({commit}) => {
    commit('logout')
    localStorage.removeItem('token')
    delete axios.defaults.headers.common['Authorization']
}

let register = ({commit}, user) => {
    try {
        axios({url: '/api/auth/register', data: user, method: 'POST'})
        commit('register_success')
    } catch (err) {
        commit('register_failure')
        console.log(err.message)
    }
}

let addProductToCart = ({ state, commit }, product) => {
    commit('setCheckoutStatus', null)
    const cartItem = state.items.find(item => item.id === product.product.id && item.size === product.product.size)
    if (!cartItem) {
        commit('pushProductToCart', { product: product })
    } else {
        commit('incrementItemQuantity', cartItem)
    }
}

let getProductFromApi = ({commit}, {page, category}) => {
    return axios.get(`/api/products/${category}`, { params: {
        page: page,
        }}).then((products) => {
        commit('setProductsToState', products.data)
    })
}

let getPagesCount = ({commit}, category) => {
    axios.get(`/api/products/` + category + `/pages`).then((response) => {
        commit('setPagesCount', response.data.pagesCount)
    })
}

export default {
    login,
    logout,
    register,
    addProductToCart,
    getProductFromApi,
    getPagesCount
};