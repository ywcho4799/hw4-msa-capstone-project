<template>

<v-data-table
    :headers="headers"
    :items="orderDetails"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'OrderDetails',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
           { text: "menu", value: "menu" },
            { text: "orderId", value: "orderId" },
           
            { text: "orderStatus", value: "orderStatus" },
            { text: "payStatus", value: "payStatus" },
            { text: "paymentId", value: "paymentId" },
            { text: "pickupStatus", value: "pickupStatus" },
            
        ],
        orderDetails : [],
    }),
    async created() {
      var temp = await axios.get(axios.fixUrl('/orderDetails'))

      this.orderDetails = temp.data._embedded.orderDetails;

    },
    methods: {
    }
  }
</script>