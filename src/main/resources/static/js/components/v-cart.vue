<template>
  <div style="display:flex; flex-direction:column; padding-top: 40px" v-if="cartItems.length !== 0">
    <div style="display: flex; justify-content: center">
      <table class="table" id="basketTable" style="width:650px; background-color: white">
        <thead>
        <tr>
          <th>Product Description</th>
          <th class="text-center">Quantity</th>
          <th class="text-right">Item Cost</th>
          <th class="text-right">Total cost</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="product in this.cartItems" :key="product.id" style="font-weight:700; font-size:12px">
          <td class="text-left">
            <router-link :to="{ name: 'product_details', params: {id: product.id}}">
              <img :src="product.images[0].image" style="height:90px; width:90px" />
            </router-link>
            <br /><span>{{product.title}}</span>
            <br /><span>Product code: {{product.id}}</span>
            <br /><span>Size: {{ product.size }}</span>
          </td>
          <td class="quantity" style="font-size:14px">
            <div class="mancont" style="display:flex; flex-direction:column; align-items:center">
              <span>{{ product.quantity }}</span>
            </div>
          </td>
          <td class="text-right" style="font-size:14px">{{ product.price + '$' }}</td>
          <td class="text-right cost" data-id="@line.Product.Article" data-content="@line.Size" style="font-size:14px">
            {{ product.price * product.quantity + '$'}}
          </td>
        </tr>
        </tbody>
      </table>
      <aside class="basket-side-panel" style="position: relative; margin-left:10px">
        <div class="order-summary" style="background-color:#fff; width:306px">
          <h3 class="order-summary__header">Order Summary</h3>
          <div class="order-summary__content" style="padding:20px 26px">
            <div class="total-cost">
              <span>Total:</span>
              <span class="price" style="float:right">{{ totalCost + '$'}}</span>
            </div>
            <div class="payment">
              <router-link :to="{ name: 'order'}" v-if="isLoggedIn">
              <button type="submit" class="btn btn-success" id="checkoutBtn" style="width:100%; border-radius:0; background-color:#00a92c; width:250px; height:50px; font-size:16px; font-weight:600; font-family: 'Open Sans', sans-serif;">
                CHECKOUT SECURELY
              </button>
              </router-link>
              <router-link :to="{ name: 'login'}" v-else>
                <button type="submit" class="btn btn-success" id="checkoutBtn" style="width:100%; border-radius:0; background-color:#00a92c; width:250px; height:50px; font-size:16px; font-weight:600; font-family: 'Open Sans', sans-serif;">
                  CHECKOUT SECURELY
                </button>
              </router-link>
            </div>
          </div>
        </div>
      </aside>
    </div>
  </div>
  <div v-else style="display: flex; flex-direction: column; justify-content: center; align-items: center">
    <h1>Cart is wait to be filled.</h1>
    <h2> Have a good shopping!</h2>
    <div class="continueShoppingBtn">
      <button @click="replaceBack" class="btn btn-primary" style="background-color: #b61c1c; border-color: #b61c1c; width: 300px; height: 50px;border-radius: 0;text-align: center;vertical-align: middle;text-transform: uppercase;font-size: 22px; justify-content:flex-start; font-weight:600;">Continue Shopping</button>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "v-cart",
  data() {
    return {
      products: [],
      prevRoute: null
    }
  },
  computed: {
    ...mapGetters([
      'cartItems',
      'totalCost',
      'isLoggedIn'
    ])
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.prevRoute = from
    })
  },
  methods: {
    replaceBack() {
      this.$router.replace(this.prevRoute.path)
    }
  }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Open+Sans');
.content {
  background-color: #f1f1f1;
}
.order-summary__header {
  border-bottom: 1px solid #d9d9d9;
  padding: 20px 10px;
  margin: 0 15px;
  font-size: 20px;
  font-weight: 600;

  font-family: "Open Sans", sans-serif;
  text-transform: uppercase;
}
.total-cost {
  font-weight: 700;
  font-size: 16px;
  margin-bottom: 15px;
  font-family: "Open Sans", sans-serif;
}
span, th, td {
  font-family: "Open Sans", sans-serif;
}
</style>