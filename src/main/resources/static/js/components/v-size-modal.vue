<template>
    <transition name="modal-fade">
        <div class="v-size-modal" ref="v-size-modal">
            <div class="v-size-modal__content">
                <div class="v-size-modal__header">
                    <span class="v-size-modal_close" @click="closeModalWindow">&times;</span>
                    <h2>Choose size</h2>
                </div>
                <div class="v-size-modal__body">
                    <slot></slot>
                </div>
                <div class="v-size-modal__footer">
                    <img src="/../img/modalFooterLogo.png" width="50" height="50" alt="img"/>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
    export default {
        name: "v-size-modal",
        data() {
            return {}
        },
        methods: {
            closeModalWindow() {
                this.$emit('closeModalWindow')
            }
        },
        mounted() {
            let self = this;
            document.addEventListener('click', function (event) {
                if(event.target === self.$refs['v-size-modal']) {
                    self.closeModalWindow();
                }
            })
        }
    }
</script>

<style lang="scss">
    .v-size-modal {
        display: block;
        position: fixed;
        z-index: 1;
        padding-top: 100px;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgb(0,0,0);
        background-color: rgba(0,0,0,0.4);
    &__content {
        position: relative;
        background-color: #fefefe;
        margin: auto;
        padding: 0;
        border: 1px solid #888;
        width: 400px;
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
        -webkit-animation-name: animatetop;
        -webkit-animation-duration: 0.4s;
        animation-name: animatetop;
        animation-duration: 0.4s
    }
    &__header {
        padding: 2px 16px;
        background-color: #333c;
        color: white;
    }
    &__body {
        padding: 2px 16px;
        height:90px;
        display:flex;
        justify-content:center;
        align-items:center;
        flex-wrap: wrap;
    }
    &__footer {
        padding: 2px 16px;
        background-color: #333c;
        color: white;
        display:flex;
        justify-content:center;
        align-items:center
    }
    &_close {
        color: white;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    &_close:hover, &_close:focus {
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }
    }
    .modal-fade-enter,
    .modal-fade-leave-active {
        opacity: 0;
    }

    .modal-fade-enter-active,
    .modal-fade-leave-active {
        transition: opacity .5s ease
    }
</style>