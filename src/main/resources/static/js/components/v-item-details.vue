<template>
  <div style="display: flex; margin-top: 30px; justify-content: center; padding: 40px 0 40px 0">
    <div style="margin-right:20px">
      <div class="fotorama" data-width="500"
           data-maxwidth="100%"
           data-allowfullscreen="true"
           data-nav="thumbs">
          <img v-for="image in product.images" :src="image.image" width="500" height="500" alt="" />
      </div>
    </div>
    <div style="display:flex; flex-direction:column">
      <section style="max-width:450px; border:1px solid #d9d9d9; padding:0 20px; margin-bottom:20px">
        <h1>{{product.title}}</h1>
        <p>
          <span style="color:#c00; font-size:20px; font-weight:700">
              {{product.price}}$
          </span>
        </p>
        <section id="productDetailsSize" class="productDetailsInformationSections structuredControlSection" style="border-top:1px solid #d9d9d9; border-bottom:1px solid #d9d9d9;display:flex">
          <span class="controlDescription ng-scope" style="align-self:center">Size</span>
          <div class="controlContainer" style="display:flex">
            <ul class="donate-now" style="display:flex; margin-top:10px; padding-left:0; flex-wrap:wrap" v-for="size in sizes">
              <li class="productSize" style="margin-bottom:10px">
                <input :checked="toggleChecked" :id="product.id" type="radio" :value="size.size" name="size">
                <label style="display:flex;justify-content:center; align-items:center;">{{size.size}}</label>
              </li>
            </ul>
          </div>
        </section>
        <p style="margin-top:10px">
          <span style="font-weight:400; font-family: cursive">
              {{product.description}}
          </span>
        </p>
      </section>
      <button id="addtobasket" v-bind:disabled="toggleChecked">Add To Basket</button>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import axios from "axios";
import fotorama from "fotorama/fotorama.css"

export default {
  name: "v-item-details",
  data() {
    return {
      product: {},
      sizes: [],
      toggleChecked: false,
    };
  },
  created() {
    this.loadJquery()
    this.loadFotorama()
    this.loadCss()
/*    this.product = this.catalogItems.find(item => item.id === this.$route.params.id);*/
    this.sizes = [];
    axios.get(`/api/products/${this.$route.params.id}/sizes`).then(response =>
        response.data.forEach(size => this.sizes.push(size))
    );
  },
  mounted() {
    this.getProduct();
  },
  methods: {
    getProduct() {
      return this.product = this.catalogItems.content.find(item => item.id === this.$route.params.id);
    },
    loadFotorama() {
      let script = document.createElement('script');
      script.src = 'https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js';
      document.documentElement.firstChild.appendChild(script);
    },
    loadJquery() {
      let script = document.createElement('script');
      script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js';
      document.documentElement.firstChild.appendChild(script);
    },
    loadCss() {
      let script = document.createElement('link');
      script.src = 'https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css';
      script.rel = 'stylesheet';
      document.documentElement.firstChild.appendChild(script);
    },
    getAllSizesByProductId(productId) {
      this.sizes = [];
      axios.get(`/api/products/${productId}/sizes`).then(response =>
          response.data.forEach(size => this.sizes.push(size))
      );
    },
    addToCart(product) {
      this.$store.dispatch("addProductToCart", {product: product});
      this.isSizeModalVisible = false;
    },
    onToggle() {
      this.toggleChecked = !this.toggleChecked;
      return this.toggleChecked;
    },
  },
  computed: {
    ...mapGetters([
      'catalogItems',
      'isAdmin'
    ]),
  },
}
</script>

<style>
.fotorama__thumb-border {
  border-color: #ccc;
}
#image {
  border: 1px solid #d9d9d9;
  height: 550px;
  width: 550px;
}
.thumb:visited {
  border: 1px solid red;
}
.addtobasket:focus {
  outline: none;
}
.controlDescription {
  flex: 0 0 auto;
  width: 150px;
  font-weight: 700;
  font-size: 16px;
  display: block;
}
.donate-now li {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 20px;
  text-align: center;
  font-size: 12px;
  height: 40px;
  width: 44px;
  border: 1px solid #000;
  font-size: 14px;
}
#addtobasket {
  border-radius: 0;
  width: 450px;
  border: 0;
  cursor: pointer;
  height: 80px;
  text-wrap: none;
  font-size: 28px;
  color: white;
  background-color:green;
  outline:none
}
#addtobasket:disabled {
  background-color: #999;
}
.donate-now li {
  position: relative;
}
ul {
  margin-bottom: 0px;
}
.donate-now label,
.donate-now input {
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin-bottom: 0;
  padding: 0;
}
.donate-now input[type="radio"] {
  opacity: 0.01;
  z-index: 100;
  width: inherit;
  height: inherit;
}
.donate-now input[type="radio"]:checked + label,
.Checked + label {
  background: black;
  color: white;
  border: 1px solid white;
}
.donate-now label {
  cursor: pointer;
  z-index: 90;
}
.donate-now label:hover {
  background: black;
  color: white;
}
h1 {
  font-weight: 600;
  text-transform: uppercase;
}
</style>