<template>
    <div>
        <h4>Register</h4>
        <form @submit.prevent="register">
            <label for="email" >E-Mail Address</label>
            <div>
                <input id="email" type="email" v-model="email" >
            </div>
            <label for="password">Password</label>
            <div>
                <input id="password" type="password" v-model="password" required>
            </div>
            <label for="password-confirm">Confirm Password</label>
            <div>
                <input id="password-confirm" v-model="password_confirmed" type="password" required>
            </div>
            <hr />
            <div>
                <button type="submit">Register</button>
            </div>
        </form>
    </div>
</template>

<script>
    export default {
        name: "v-register",
        data(){
            return {
                email : '',
                password : '',
                password_confirmed : '',
                prevRoute: null
            }
        },
        methods: {
            register() {
                let data = {
                    email: this.email,
                    password: this.password,
                }
                this.$store.dispatch('register', data)
                    .catch(err => console.log(err))

                this.$route.replace(this.prevRoute.path)
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