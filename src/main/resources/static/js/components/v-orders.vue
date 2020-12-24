<template>
  <div style="display:flex; flex-direction:column; padding-top: 40px">
    <div style="display: flex; justify-content: center">
      <table class="table" id="basketTable" style="width:650px; background-color: white">
        <thead>
        <tr>
          <th>Order Id</th>
          <th class="text-center">User Id</th>
          <th class="text-center">DateTime</th>
          <th class="text-center">Total Cost</th>
          <th class="text-center">Status</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="order in orders" :key="order.id" style="font-weight:700; font-size:12px">
          <td class="text-left">
            {{order.id}}
          </td>
          <td class="quantity" style="font-size:14px">
            <div class="mancont" style="display:flex; flex-direction:column; align-items:center">
              <span>{{order.user.email}}</span>
            </div>
          </td>
          <td class="quantity" style="font-size:14px">
            <div class="mancont" style="display:flex; flex-direction:column; align-items:center">
              <span>{{order.datetime}}</span>
            </div>
          </td>
          <td class="quantity" style="font-size:14px">
            <div class="mancont" style="display:flex; flex-direction:column; align-items:center">
              <span>{{order.totalCost}}</span>
            </div>
          </td>
          <td class="quantity" style="font-size:14px">
            <div class="mancont" style="display:flex; flex-direction:column; align-items:center">
              <span>{{order.status}}</span>
            </div>
          </td>
          <td v-if="order.status === 'In processing'" class="quantity" style="font-size:14px">
            <div class="mancont" style="display:flex; flex-direction:column; align-items:center">
              <button @click="sendEmail(order.user.email, order.id)">Send</button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "v-users",
  mounted() {
    this.getAllUsers();
  },
  data() {
    return {
      orders: []
    }
  },
  methods: {
    getAllUsers() {
      axios.get('/api/admin/orders').then((orders) => {
        this.orders = orders.data;
      });
    },
    sendEmail(email, orderId) {
      axios.get('/api/admin/send-email', {
        params: {
          email: email,
          orderId: orderId
        }
      });
    }
  }
}
</script>

<style>

</style>