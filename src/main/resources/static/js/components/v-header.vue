<template>
    <header>
        <div id="navbarTop" class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto"></ul><ul class="nav navbar-nav">
<!--            '<div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>' +-->
<!--            '<div v-else>' +-->
<!--                '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>' +-->
<!--                '<messages-list :messages="messages" />' +-->
<!--                '</div>' +-->
            <li class="nav-item" v-if="!profile">
                <a href="/login" class="nav-link">Login</a>
            </li>
            <li class="nav-item" v-else>
                <a href="/logout" class="nav-link">Logout</a>
            </li>
            <li class="nav-item">
                <a href="/register" class="nav-link">Register</a>
            </li>
            <li>
                <button class="btn btn-success navbar-btn"> Checkout <span class="badge badge-light">0 ($ 0)</span></button>
            </li>
        </ul>
        </div>
        <div style="display: flex; justify-content: space-between">
            <div>
                <img src="/img/adidas.png" alt="img" height="60" width="60"/>
                <img src="/img/aon.png" alt="img" height="60" width="60"/>
            </div>
            <div>
                <a href="/">
                    <img src="/img/uniteddirectheaderlogo.png" alt="img"/>
                </a>
            </div>
            <div>
                <img src="/img/chevrolet.png" alt="img" height="60" width="60"/>
                <img src="/img/manutd.png" alt="img" height="60" width="60"/>
            </div>
        </div>
    </header>
</template>

<script>
    export default {
        name: "v-header",
        data() {
            return {
                profile: frontendData.profile
            }
        },
    }
</script>

<style scoped>

</style>