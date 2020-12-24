<template>
    <div class="v-catalog-item">
        <v-size-modal v-if="isSizeModalVisible" @closeModalWindow="closeModalWindow">
            <label v-for="size in sizes"  class="sizeItem" style="background-color:whitesmoke" @click="addToCart(Object.assign(productData, size, {quantity: 1}))">
                <span>{{size.size}}</span>
            </label>
        </v-size-modal>
      <router-link v-if="isAdmin" :to="{ name: 'edit', params: {id: productData.id}}" style="display:flex; flex-direction:column; margin-top:90px; position:absolute; margin-left:-25px">
        <span class="edit">Edit</span>
      </router-link>
        <p class="v-catalog-item__title">{{productData.title}}</p>
        <span class="v-catalog-item__price">{{productData.price}}$</span>
        <div @click="openModalWindow(productData.id)" data-toggle="tooltip" title="Add to basket" data-placement="bottom" class="addtobasket" style="display:flex; flex-direction:column; margin-top:55px; position:absolute; margin-left:-25px">
            <img src="/../img/shopping_cart.png" style="width:24px; height:24px; outline:none;margin-top:5px; cursor:pointer" alt="img"/>
        </div>
        <div>
          <router-link :to="{ name: 'product_details', params: {id: productData.id}}">
            <img :src="image" style="max-height: 220px; max-width: 250px" alt="img"/>
          </router-link>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import vSizeModal from './v-size-modal.vue'
    import {mapGetters} from "vuex";

    export default {
        name: 'v-catalog-item',
        components: {
            vSizeModal
        },
        props: {
            productData: {
                type: Object,
                default() {
                    return {};
                }
            }
        },
        data() {
            return {
                isSizeModalVisible: false,
                sizes: [],
                image: ''
            }
        },
        computed: {
          ...mapGetters(['isAdmin']),
        },
      created() {
          this.image = this.productData.images[0].image;
      },
      methods: {
            openModalWindow(productId) {
                this.isSizeModalVisible = true;
                this.getAllSizesByProductId(productId)
            },
            closeModalWindow() {
                this.isSizeModalVisible = false;
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
            }
        }
    }
</script>

<style lang="scss">
.v-catalog-item {
    display:flex;
    padding-left:25px;
    max-width: 265px;
    flex-direction:column;
    margin-bottom:15px;
    margin-right:15px;
    padding-bottom: 10px;
    border-bottom: 1px solid lightgray;
    &__title {
        @import url('https://fonts.googleapis.com/css?family=Open+Sans');
        font-family: "Open Sans", sans-serif;
        margin-left:-25px;
        font-weight:700;
        font-size:12px;
        min-height: 34px;
    }
    &__price {
        color:#b61c1c;
        align-self:flex-start;
        position:absolute;
        margin-top:35px;
        font-weight:700;
        font-size:14px;
        margin-left: -25px;
    }
}
.modal-fade-enter,
.modal-fade-leave-active {
    opacity: 0;
}
.modal-fade-enter-active,
.modal-fade-leave-active {
    transition: opacity .5s ease
}
.sizeItem {
    position: relative;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    float: left;
    line-height: 10px;
    border: 1px solid #d5d5d5;
    margin: 0 0 0 10px;
    padding: 9px;
    min-width: 42px;
    height: 30px;
    text-align: center;
    cursor: pointer;
    -webkit-box-shadow: inset 0 0 0 1px #fff;
    box-shadow: inset 0 0 0 1px #fff;
}
.size {
    position: absolute;
    top: 0;
    left: -9999px;
}
span.edit {
  overflow-wrap: break-word;
  word-break: break-word;
  outline: 0;
  text-decoration: none;
  cursor: pointer;
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;
  border-bottom: 1px dashed #8b8b8b;
  color: #8b8b8b;
  position: relative;
  vertical-align: middle;
}
a:hover {
  text-decoration: none;
}
</style>