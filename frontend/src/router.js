
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderManager from "./components/listers/OrderCards"
import OrderDetail from "./components/listers/OrderDetail"

import PickupManager from "./components/listers/PickupCards"
import PickupDetail from "./components/listers/PickupDetail"

import PaymentManager from "./components/listers/PaymentCards"
import PaymentDetail from "./components/listers/PaymentDetail"


import OrderDetailsView from "./components/OrderDetailsView"
import OrderDetailsViewDetail from "./components/OrderDetailsViewDetail"

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
                path: '/orders/:id',
                name: 'OrderDetail',
                component: OrderDetail
            },

            {
                path: '/pickups',
                name: 'PickupManager',
                component: PickupManager
            },
            {
                path: '/pickups/:id',
                name: 'PickupDetail',
                component: PickupDetail
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },
            {
                path: '/payments/:id',
                name: 'PaymentDetail',
                component: PaymentDetail
            },


            {
                path: '/orderDetails',
                name: 'OrderDetailsView',
                component: OrderDetailsView
            },
            {
                path: '/orderDetails/:id',
                name: 'OrderDetailsViewDetail',
                component: OrderDetailsViewDetail
            },


    ]
})
