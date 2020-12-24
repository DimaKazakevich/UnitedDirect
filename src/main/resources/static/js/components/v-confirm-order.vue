<template>
  <form class="form-horizontal" @submit.prevent="addNewOrder">
    <div style="display:flex; justify-content: center; padding: 40px 0 40px 0; align-items: center; flex-direction: column">
<!--    <div style="width:250px">
      <div class="form-group">
        <label class="col-md-2 control-label" for="Shipping_Country">Country</label>
        <div class="col-md-10">
          <select class="form-control" id="Shipping_Country" name="Shipping.Country"><option>Belarus</option>
            <option>Ukraine</option>
            <option>USA</option>
            <option>United Kingdom</option>
          </select>
        </div>
      </div>
      <div style="display:flex; flex-direction:column">
        <div class="form-group">
          <label class="col-md-2 control-label" for="Shipping_FirstName">Fisrt Name</label>
          <div class="col-md-10">
            <input class="form-control" id="Shipping_FirstName" name="Shipping.FirstName" required="required" type="text" value="">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-2 control-label" for="Shipping_LastName">Last Name</label>
          <div class="col-md-10">
            <input class="form-control" id="Shipping_LastName" name="Shipping.LastName" required="required" type="text" value="">
          </div>
        </div>

        <div class="form-group">
          <label class="col-md-2 control-label" for="Shipping_Town">Town</label>
          <div class="col-md-10">
            <input class="form-control" id="Shipping_Town" name="Shipping.Town" required="required" type="text" value="">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-2 control-label" for="Shipping_AddresLine">Addres Line</label>
          <div class="col-md-10">
            <input class="form-control" id="Shipping_AddresLine" name="Shipping.AddresLine" required="required" type="text" value="">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-2 control-label" for="Shipping_Postcode">Postcode</label>
          <div class="col-md-10">
            <input class="form-control" id="Shipping_Postcode" name="Shipping.Postcode" required="required" type="text" value="">
          </div>
        </div>
      </div>
    </div>-->

    <aside id="checkoutSidePanel" style="position: relative; margin-left:10px">
      <section id="orderSummaryContainer">
        <div id="orderSummaryHeader">
          <h3 class="summaryLineLeft">Order Summary</h3>
        </div>
        <section class="scrollableContentContainer">
          <div class="scrollableContent">
            <div class="orderPart">
              <div class="copy" style="font-size:12px">
                Delivery Estimate: 6 - 7 Business Days
              </div>
              <br>
              <div v-for="product in cartItems" :key="product.id">
                  <div class="container-fluid">
                    <order-summary-item item="item">
                      <div class="itemInfo" style="display:flex">
                        <div class="itemImage">
                          <img :src="product.images[0].image" width="70" height="70" />
                        </div>
                        <div class="aboutItem" style="margin-left:10px">
                          <div class="itemDetails">
                            <div class="itemDescription">{{ product.title }}</div>
                            <div class="itemSize">
                              <label class="itemVariationLabel">Size:</label>
                              <span style="font-size:12px">{{ product.size }}</span>
                            </div>
                            <div class="itemQuantity">
                              <label class="itemQuantityLabel">Qty: </label>
                              <span style="font-size:12px">{{ product.quantity }}</span>
                            </div>
                          </div>
                          <div class="itemPricing">
                            <div class="nowPrice">
                              <span class="showPriceLabel">Price:</span>
                              <span style="font-size:12px">{{ product.price * product.quantity + '$'}}</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </order-summary-item>
                </div>
              </div>
              <hr>
            </div>
          </div>
        </section>
        <p class="summaryTotals" style="display:flex; justify-content:space-between">
          <span class="summaryLineLeft" style="text-transform: uppercase; font-family: Arial, Helvetica, sans-serif">Total:</span>
          <span class="summaryLineRight" style="font-size:24px; font-weight:600">{{totalCost + '$'}}</span>
        </p>
      </section>
    </aside>

    <button type="submit" class="btn btn-success" id="checkoutBtn" style="width:100%; border-radius:0; background-color:#00a92c; width:200px; height:50px; font-size:16px; font-weight:600; border-radius:4px">
      CONFIRM ORDER
    </button>
    </div>
  </form>
</template>

<script>
import { mapGetters } from 'vuex';
import axios from "axios";
export default {
  name: "v-confirm-order",
  computed: {
    ...mapGetters([
      'cartItems',
      'totalCost'
    ])
  },
  data() {
    return {
      isSizeModalVisible: false,
      sizes: []
    }
  },
  methods: {
    addNewOrder() {
      axios.post('/api/cart/order', this.cartItems).then(() => {
        this.$store.state.items = []
        this.$router.push('/')
      });
    }
  }
}
</script>

<style scoped>
/*.combobox {
  display: inline-block;
  position: relative;
}*/
.combobox select {
  display: none;
  position: absolute;
  overflow-y: auto;
}
#orderSummaryContainer {
  width: 392px;
  padding: 0 14px 25px 14px;
  background: #fff;
  position: relative;
  margin-bottom: 14px;
}
#orderSummaryHeader {
  display: flex;
  margin: 0;
  align-items: center;
  height: 53px;
}
.scrollableContentContainer {
  overflow: hidden;
  border-top: 1px solid silver;
  border-bottom: 1px solid silver;
  margin-bottom: 30px;
}
.scrollableContent {
  height: 300px;
  overflow-y: auto;
}
.summaryTotals {
  display: flex;
  font-family: montserratsemibold;
  font-size: 18px;
  margin-bottom: 7px;
}
.container-fluid {
  margin-right: auto;
  margin-left: auto;
  padding-left: 15px;
  padding-right: 15px;
  display: flex;
}
.itemDescription {
  font-size: 14px;
  font-weight: 600;
}
.itemVariationLabel {
  display: inline-block;
  max-width: 100%;
  margin-bottom: 5px;
  font-weight: bold;
  font-size: 12px;
}
.itemQuantityLabel {
  display: inline-block;
  max-width: 100%;
  margin-bottom: 5px;
  font-weight: bold;
  font-size: 12px;
}
.showPriceLabel {
  display: inline-block;
  max-width: 100%;
  margin-bottom: 5px;
  font-weight: bold;
  font-size: 12px;
}
.content {
  background-color: #f1f1f1;
}
.summaryLineLeft {
  font-size: 24px;
  text-transform: uppercase;
  font-weight: 600;
}
#CheckoutProgressSection .line.part1 {
  right: 50%;
}
#CheckoutProgressSection .line {
  border-top: 1px solid #000;
  left: 17%;
  right: 17%;
  top: 39px;
  z-index: 1;
}
#CheckoutProgressSection .line {
  border-top: 1px solid #d7d7d7;
  left: 18%;
  right: 18%;
  top: 22px;
  position: absolute;
}
#CheckoutProgressSection .line.part2 {
  left: 50%;
}
#CheckoutProgressSection .line {
  border-top: 1px solid #000;
  left: 17%;
  right: 17%;
  top: 39px;
  z-index: 1;
}
#CheckoutProgressSection .line {
  border-top: 1px solid #d7d7d7;
  left: 18%;
  right: 18%;
  top: 22px;
  position: absolute;
}
.form-group {
  display: grid;
}
.col-md-2.control-label {
  white-space: nowrap;
}
</style>