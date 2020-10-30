<template>
    <div>
        <form class="login" @submit.prevent="login">
            <h1>Log in</h1>
            <label>Email</label>
            <input required v-model="email" type="email" placeholder="Name"/>
            <label>Password</label>
            <input required v-model="password" type="password" placeholder="Password"/>
            <hr/>
            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script>
    let token = '';
    export default {
        name: "v-login",
        data() {
            return {
                email: '',
                password: ''
            }
        },
        methods: {
            login() {
                let email = this.email
                let password = this.password
                this.$store.dispatch('login', { username:email, password:password })
                    .then(() => this.$router.push('/'))
                    .catch(err => console.log(err))
            }
        }
    }
</script>

<style>

</style>