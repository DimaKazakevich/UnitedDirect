<template>
    <div class="v-catalog-item">
        <v-size-modal v-if="isSizeModalVisible" @closeModalWindow="closeModalWindow">
            <label v-for="size in sizes" class="sizeItem" style="background-color:whitesmoke">
                <span @click="addToCart(2)">{{size}}</span><input class="size" name="size" type="radio">
            </label>
        </v-size-modal>
        <p class="v-catalog-item__title">{{productData.name}}</p>
        <span class="v-catalog-item__price">{{productData.price}}$</span>
        <div @click="openModalWindow(productData.id)" data-toggle="tooltip" title="Add to basket" data-placement="bottom" class="addtobasket" style="display:flex; flex-direction:column; margin-top:55px; position:absolute; margin-left:-25px">
            <img src="/../img/shopping_cart.png" style="width:24px; height:24px; outline:none;margin-top:5px; cursor:pointer" alt="img"/>
        </div>
        <div>
            <img src="/../img/default.jpg" style="max-height: 220px; max-width: 250px" alt="img"/>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import vSizeModal from './v-size-modal.vue'

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
                sizes: []
            }
        },
        computed: {},
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
            addToCart(productId) {
                axios.get(`/api/cart/addtocart/${productId}`);
                console.log('yes');
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
</style>