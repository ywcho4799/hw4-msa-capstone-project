

<template>
    <v-app id="inspire">
        <div>
            <v-app-bar app clipped-left flat>
                <v-toolbar-title>
                    <span class="second-word font uppercase"
                        style="font-weight:700;"
                    >
                        <v-app-bar-nav-icon
                            @click="openSideBar()"
                            style="z-index:1;
                            height:56px;
                            width:30px;
                            margin-right:10px;
                            font-weight:300;
                            font-size:55px;"
                        >
                            <div style="line-height:100%;">≡</div>
                        </v-app-bar-nav-icon>
                        sirenorder
                    </span>
                </v-toolbar-title>
                <v-spacer></v-spacer>
            </v-app-bar>

            <v-navigation-drawer app clipped flat v-model="sideBar">
                <v-list>


                    <v-list-item
                        class="px-2"
                        key="orders"
                        to="/orders"
                        color="deep-purple lighten-2"
                        style="font-weight:700;"
                    >
                        Order
                    </v-list-item>



                    <v-list-item
                        class="px-2"
                        key="pickups"
                        to="/pickups"
                        color="deep-purple lighten-2"
                        style="font-weight:700;"
                    >
                        Pickup
                    </v-list-item>



                    <v-list-item
                        class="px-2"
                        key="payments"
                        to="/payments"
                        color="deep-purple lighten-2"
                        style="font-weight:700;"
                    >
                        Payment
                    </v-list-item>



                    <v-list-item
                        class="px-2"
                        key="orderDetails"
                        to="orderDetails"
                        color="deep-purple lighten-2"
                        style="font-weight:700;"
                    >
                        OrderDetails
                    </v-list-item>


                </v-list>
            </v-navigation-drawer>
        </div>

        <v-main>
            <v-container class="py-8 px-6" fluid>
                <router-view></router-view>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
export default {
    name: "App",

    components: {},

    data: () => ({
        useComponent: "",
        drawer: true,
        components: [],
        sideBar: true,
    }),

    mounted() {
        var me = this;
        me.components = this.$ManagerLists;
    },

    methods: {
        openSideBar(){
            this.sideBar = !this.sideBar
        }
    }
};
</script>
<style>
</style>
