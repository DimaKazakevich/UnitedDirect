<template>
    <div>
        <form class="login" @submit.prevent="login">
            <h1>Log in</h1>
            <label>Email</label>
            <input required v-model="email" type="email" placeholder="Name"/>
            <br />
            <label>Password</label>
            <input required v-model="password" type="password" placeholder="Password"/>
            <hr/>
            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script>
    export default {
        name: "v-login",
        data() {
            return {
                email: '',
                password: '',
                prevRoute: null
            }
        },
        methods: {
            login() {
                let email = this.email
                let password = this.password
                this.$store.dispatch('login', { email:email, password:password })
                    .then(() =>  this.$router.replace(this.prevRoute.path))
                    .catch(err => console.log(err))
            }
        },
      beforeRouteEnter(to, from, next) {
        next(vm => {
          vm.prevRoute = from
        })
      },
    }
</script>

<style>

</style>