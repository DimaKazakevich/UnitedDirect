<template>
    <header>
        <div class="header-navbar">
            <ul class="header-navbar__list">
                <li class="header-navbar__nav-item" v-if="isLoggedIn">
                    <a href="/" class="header-navbar__nav-link">{{userName}}</a>
                </li>
                <li class="header-navbar__nav-item" v-if="isLoggedIn">
                    <a class="header-navbar__nav-link" @click="logout">Logout</a>
                </li>
                <li class="header-navbar__nav-item" v-else>
                    <a href="/login" class="header-navbar__nav-link">Login</a>
                </li>
                <li class="header-navbar__nav-item">
                    <a href="/register" class="header-navbar__nav-link">Register</a>
                </li>
            </ul>
        </div>
        <div class="header-images">
            <div class="header-images__item">
                <img class="header-images__img" src="/img/adidas.png" alt="img" />
                <img class="header-images__img" src="/img/aon.png" alt="img" />
            </div>
            <div class="header-images__item">
                <a href="/">
                    <img src="/img/uniteddirectheaderlogo.png" alt="img"/>
                </a>
            </div>
            <div class="header-images__item">
                <img class="header-images__img" src="/img/chevrolet.png" alt="img" />
                <img class="header-images__img" src="/img/manutd.png" alt="img" />
            </div>
        </div>
    </header>
</template>

<script>
    import { mapGetters } from 'vuex'
    export default {
        name: "v-header",
        data() {
            return {}
        },
        computed: {
            ...mapGetters(['isLoggedIn', 'userName'])
        },
        methods: {
            logout() {
                this.$store.dispatch('logout')
                    .then(() => {
                        this.$router.push('/login')
                    })
            }
        }
    }
</script>

<style lang="scss">
    .header-navbar {
        &__nav-item {
            cursor: pointer;
        }
        &__nav-item:hover {
            cursor: pointer;
        }
        &__nav-link {
            @import url('https://fonts.googleapis.com/css?family=Open+Sans');
            font-family: "Open Sans", sans-serif;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.5;
            list-style: none;
            -webkit-box-direction: normal;
            box-sizing: border-box;
            text-decoration: none;
            background-color: transparent;
            display: block;
            padding: .5rem;
            color: hsla(0,0%,100%,.5);
        }
        &__nav-link:hover {
            color: hsla(0,0%,100%,.75);
            text-decoration: none;
        }
        &__list {
            display: flex;
            list-style-type: none;
        }
    }
    .header-images {
        display: flex;
        justify-content: space-between;
        &__img {
            height: 60px;
            width: 60px;
        }
    }
</style>