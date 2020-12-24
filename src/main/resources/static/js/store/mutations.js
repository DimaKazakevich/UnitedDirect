let auth_request = (state) => {
    state.status = 'loading'
}

let auth_success = (state, token) => {
    state.status = 'success'
    state.token = token.token
    state.email = token.email
    state.roles = token.roles
}

let auth_error = (state) => {
    state.status = 'error'
}

let logout = (state) => {
    state.status = ''
    state.token = ''
    state.email = ''
    state.roles = []
}

let register_success = (state) => {
    state.status = 'success'
}

let register_failure = (state) => {
    state.status = 'failure'
}

let pushProductToCart = (state, { product }) => {
    state.items.push(product.product)
}

let incrementItemQuantity = (state, { id }) => {
    const cartItem = state.items.find(item => item.id === id)
    cartItem.quantity++
}

let setCartItems = (state, { items }) => {
    state.items = items
}

let setCheckoutStatus = (state, status) => {
    state.checkoutStatus = status
}

let setProductsToState = (state, products) => {
    state.catalog_items = products
}

let setPagesCount = (state, pages) => {
    state.pagesCount = pages;
}

export default {
    auth_request,
    auth_success,
    auth_error,
    logout,
    register_success,
    register_failure,
    setCartItems,
    pushProductToCart,
    setCheckoutStatus,
    incrementItemQuantity,
    setProductsToState,
    setPagesCount
};