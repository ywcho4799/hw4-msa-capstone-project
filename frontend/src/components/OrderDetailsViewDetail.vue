<template>

    <v-card outlined>
        <v-card-title>
            OrderDetails # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="OrderId" v-model="item.orderId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="OrderStatus" v-model="item.orderStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PayStatus" v-model="item.payStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Menu" v-model="item.menu" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="PaymentId" v-model="item.paymentId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PickupStatus" v-model="item.pickupStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'OrderDetailsViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        orderDetails : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.backend + '/orderdetails/' + params.id)

      this.orderDetails = temp.data._embedded.orderdetails;

    },
    methods: {
    }
  }
</script>

