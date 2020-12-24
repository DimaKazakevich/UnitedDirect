<template>
    <div style="display:flex; flex-direction:column; justify-content: center; align-self: center; padding: 20px 0 20px 0">
      <section style="max-width:450px; border:1px solid #d9d9d9; padding:0 20px; margin-bottom:20px">
        <textarea v-model="product.title" style="width: -webkit-fill-available; margin-top: 15px">{{product.title}}</textarea>
        <p>
          <textarea rows="1" v-model="product.price" style="color:#c00; font-size:20px; font-weight:700; width:-webkit-fill-available">
              {{product.price}}
          </textarea>
        </p>
        <p style="margin-top:10px">
          <textarea v-model="product.description" style="font-weight:400; font-family: cursive; width: -webkit-fill-available;" rows="10">
              {{product.description}}
          </textarea>
        </p>
      </section>
      <button id="addtobasket" @click="updateProduct(product.id)">Save</button>
    </div>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";

export default {
  name: "v-edit-product",
  data() {
    return {
      product: {},
      prevRoute: null
    }
  },
  computed: {
    ...mapGetters([
      'catalogItems',
      'isAdmin',
      'cartItems'
    ])
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.prevRoute = from
    })
  },
  created() {
    this.product = this.catalogItems.content.find(item => item.id === this.$route.params.id);
  },
  methods: {
    updateProduct(productId) {
      axios.put(`/api/admin/products/${productId}`,
    {
          title: this.product.title,
          price: this.product.price,
          description: this.product.description
        }).then((product) => {
          let itemToUpdate = this.cartItems.find(item => item.id === product.data.id)
          itemToUpdate.title = product.data.title
          itemToUpdate.price = product.data.price
          itemToUpdate.description = product.data.description
      });
      this.$router.replace(this.prevRoute.path)
    }
  }
}
</script>

<style>

</style>