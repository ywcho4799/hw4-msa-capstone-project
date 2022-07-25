<template>

    <v-card style="width:450px;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="deep-purple"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            Order # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
        </v-card-title >
        <v-card-title v-else>
            Order
        </v-card-title >

        <v-card-text>
            <div>
                <String label="Menu" v-model="value.menu" :editMode="editMode"/>
            </div>
            <div>
                <Number label="Price" v-model="value.price" :editMode="editMode"/>
            </div>
            <div>
                <String label="CustomerId" v-model="value.customerId" :editMode="editMode"/>
            </div>
            <div>
                <String label="CustomerName" v-model="value.customerName" :editMode="editMode"/>
            </div>
            <div>
                <String label="CustomerTel" v-model="value.customerTel" :editMode="editMode"/>
            </div>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
            >
                Cancel
            </v-btn>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>                        
        </v-card-actions>
    </v-card>

</template>

<script>
    const axios = require('axios').default;
    
    export default {
        name: 'Order',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean
        },
        data: () => ({
        }),
        created(){
            if(!this.value) {
                this.value = {};
            }

            if(typeof this.value === 'object') {
                if(!('menu' in this.value)) {
                    this.value.menu = null;
                }
                if(!('price' in this.value)) {
                    this.value.price = null;
                }
                if(!('customerId' in this.value)) {
                    this.value.customerId = null;
                }
                if(!('customerName' in this.value)) {
                    this.value.customerName = null;
                }
                if(!('customerTel' in this.value)) {
                    this.value.customerTel = null;
                }
            }            
        },

        methods: {
            selectFile(){
            if(this.editMode == false) {
                return false;
            }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/orders'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                } catch(e) {
                    alert(e.message)
                }
                location.reload()
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    alert(e.message)
                }
            },
            change(){
                this.$emit('input', this.value);
            },
        },
    }
</script>

