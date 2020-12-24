<template>
  <div style="display:flex; flex-direction:column; justify-content: center; align-self: center">
    <section style="max-width:450px; border:1px solid #d9d9d9; padding:0 20px; margin-bottom:20px">
      <label class="col-md-2 control-label" for="Shipping_Country">Title</label>
      <textarea v-model="product.title" style="width: -webkit-fill-available; margin-top: 15px"></textarea>
      <label class="col-md-2 control-label" for="Shipping_Country">Price</label>
      <p>
        <textarea v-model="product.price" rows="1" style="color:#c00; font-size:20px; font-weight:700; width:-webkit-fill-available">

        </textarea>
      </p>
      <label class="col-md-2 control-label" for="Shipping_Country">Description</label>
      <p style="margin-top:10px">
        <textarea v-model="product.description" style="font-weight:400; font-family: cursive; width: -webkit-fill-available;" rows="10">

        </textarea>
      </p>

      <label class="col-md-2 control-label" for="Shipping_Country">Image</label>
      <p style="margin-top:10px">
        <textarea v-model="product.images" style="font-weight:400; font-family: cursive; width: -webkit-fill-available;" rows="10">

        </textarea>
      </p>
      <div class="form-group">
        <label class="col-md-2 control-label" for="Shipping_Country">Category</label>
        <div class="col-md-10">
          <select v-model="product.category" class="form-control" id="Shipping_Country" name="Shipping.Country">
            <option value="1">Kits</option>
            <option value="2">Training</option>
            <option value="3">Kids</option>
            <option value="4">Fashion</option>
          </select>
        </div>
      </div>
      <label class="col-md-2 control-label" for="Shipping_Country">Sizes</label>
      <input type="checkbox" id="S" value="1" v-model="checkedSizes">
      <label for="S">S</label>
      <input type="checkbox" id="M" value="2" v-model="checkedSizes">
      <label for="M">M</label>
      <input type="checkbox" id="L" value="3" v-model="checkedSizes">
      <label for="L">L</label>
      <input type="checkbox" id="XL" value="4" v-model="checkedSizes">
      <label for="XL">XL</label>
    </section>
    <button id="addtobasket" @click="addNewProduct">Add</button>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "v-add-item",
  data() {
    return {
      product: {
        images: ''
      },
      prevRoute: null,
      checkedSizes: []
    }
  },
  methods: {
    addNewProduct() {
      axios.post('/api/admin/products', {
        title: this.product.title,
        price: this.product.price,
        description: this.product.description,
        images: this.product.images.split('\n\n'),
        category: this.product.category,
        sizes: this.checkedSizes
      });
      this.$router.replace(this.prevRoute.path)
    }
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.prevRoute = from
    })
  },
}
</script>

<style scoped>
.content {
  padding: 30px;
}
</style>