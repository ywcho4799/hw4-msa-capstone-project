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
        name: 'OrderDetailsView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "orderId", value: "orderId" },
                { text: "orderStatus", value: "orderStatus" },
                { text: "payStatus", value: "payStatus" },
                { text: "menu", value: "menu" },
                { text: "paymentId", value: "paymentId" },
                { text: "pickupStatus", value: "pickupStatus" },
            ],
            orderDetails : [],
        }),
          async created() {
            var temp = await axios.get(axios.backend + '/orderdetails')

            this.orderDetails = temp.data._embedded.orderdetails;
        },
        methods: {
        }
    }
</script>

