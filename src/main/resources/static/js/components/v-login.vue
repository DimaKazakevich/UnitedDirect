<template>
    <div>
    <h1>Login</h1>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' v-model="input.username" required="required"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' v-model="input.password"  required="required"/></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" @click="login" value="login" /></td>
            </tr>
        </table>
    </div>
</template>

<script>
    import axios from "axios";
    import {prodComponent} from "./data";
    let token = '';
    export default {
        name: "v-login",
        data() {
            return {
                input: {
                    username: "",
                    password: ""
                }
            }
        },
        methods: {
            login() {
                    let axiosConfig = {
                        headers: {
                            'Content-Type': 'application/json;charset=UTF-8',
                            "Access-Control-Allow-Origin": "*",
                        }
                    };
                    let body = {
                        username: this.input.username,
                        password: this.input.password
                    };

                    axios.post(`/api/auth/login`,body,axiosConfig).then(response => {
                            token = response.data
                            this.$router.replace({name: "mainPage"})
            })
            }
        }
    }
</script>

<style>

</style>