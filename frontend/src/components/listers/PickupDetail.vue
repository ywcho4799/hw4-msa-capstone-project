<template>
    <v-card outlined>
        <v-card-title>
            Pickup # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="PickupStatus" v-model="item.pickupStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="OrderId" v-model="item.orderId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CustomerAddr" v-model="item.customerAddr" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CustomerTel" v-model="item.customerTel" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CustomerId" v-model="item.customerId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PickupId" v-model="item.pickupId" :editMode="editMode" @change="change" />
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
        name: 'PickupDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/pickups/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
        },
    };
</script>
