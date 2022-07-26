
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);



import OrderManager from "./components/OrderManager"

import PaymentManager from "./components/PaymentManager"


import OrderDetails from "./components/OrderDetails"
import PickupManage from "./components/PickupManage"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            
            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },


            {
                path: '/orderDetails',
                name: 'OrderDetails',
                component: OrderDetails
            },

            {
                path: '/pickups',
                name: 'PickupManage',
                component: PickupManage
            },


    ]
})
