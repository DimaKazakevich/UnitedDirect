<template>
    <div class="v-catalog-item">
        <p class="v-catalog-item__title">{{productData.name}}</p>
        <span class="v-catalog-item__price">{{productData.price}}$</span>
        <img src="/../img/defaultProductImage.jpg" style="height: 250px" alt="img"/>
    </div>
</template>

<script>
    export default {
        name: 'v-catalog-item',
        props: {
            productData: {
                type: Object,
                default() {
                    return {};
                }
            }
        },
        data() {
            return {}
        },
        computed: {}
    }
</script>

<style lang="scss">
.v-catalog-item {
    display:flex;
    padding-left:25px;
    max-width: 275px;
    flex-direction:column;
    margin-bottom:15px;
    margin-right:15px;
    border-bottom: 1px solid lightgray;
    &__title {
        margin-left:-25px;
        font-weight:700;
        font-size:12px;
        min-height: 34px;
    }
    &__price {
        color:#b61c1c;
        align-self:flex-start;
        position:absolute;
        margin-top:35px;
        font-weight:700;
        font-size:14px;
        margin-left: -25px;
    }
}
</style>