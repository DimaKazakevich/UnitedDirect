<template>
    <header>
        <div class="header-navbar" style="display:flex; justify-content: space-between">
            <ul class="header-navbar__list">
                <li class="header-navbar__nav-item nav-item" v-if="isLoggedIn" style="padding-left: 140px">
                    <img class="nav-item__icon" src="/img/user_profile.png" alt="img" />
                    <a href="/" class="header-navbar__nav-link">{{userEmail}}</a>
                </li>
                <li class="header-navbar__nav-item" v-if="isLoggedIn">
                    <a class="header-navbar__nav-link" @click="logout">Logout</a>
                </li>
                <li class="header-navbar__nav-item" v-if="!isLoggedIn" style="padding-left: 135px">
                  <router-link :to="{ name: 'login' }" class="header-navbar__nav-link">Login</router-link>
                </li>
                <li class="header-navbar__nav-item" v-if="!isLoggedIn">
                  <router-link :to="{ name: 'register' }" class="header-navbar__nav-link">Register</router-link>
                </li>
            </ul>
          <div class="top-bar-cart" role="complementary" style="height: 1px; margin: 0;order: 4;">
            <router-link to="/cart" data-talos="linkCartIcon" class="cart-icon" aria-label="You have 1 items in cart. The total is £62.95" data-trk-id="cart-icon">
              <!--<i class="icon icon-cart" role="presentation"></i>-->
              <i class="fas fa-shopping-cart"></i>
             <span class="cart-count" role="presentation">{{countCartItem}}</span>
            </router-link>
          </div>
          <div ref="sideNav" class="bm-menu">
            <nav class="bm-item-list">
              <!--<slot></slot>-->
              <router-link v-on:click.native="closeMenu" :to="{name: 'add_product'}">
                <i class="fa fa-fw fa-star-o"></i>
                <span>Add product</span>
              </router-link>
              <router-link v-on:click.native="closeMenu" :to="{name: 'users'}">
                <i class="fa fa-fw fa-star-o"></i>
                <span>Orders</span>
              </router-link>
            </nav>
            <span class="bm-cross-button cross-style" @click="closeMenu" :class="{ hidden: !crossIcon }">
                <span v-for="(x, index) in 2" :key="x" class="bm-cross" :style="{ position: 'absolute', width: '3px', height: '14px',transform: index === 1 ? 'rotate(45deg)' : 'rotate(-45deg)'}">
                </span>
            </span>
          </div>
        </div>
        <div class="header-images">
          <div ref="bmBurgerButton" v-if="isAdmin" class="bm-burger-button" @click="openMenu" :class="{ hidden: !burgerIcon }" style="margin-top: 17px">
            <span class="bm-burger-bars line-style" :style="{top:20 * (index * 2) + '%'}" v-for="(x, index) in 3" :key="index"></span>
          </div>
            <div class="header-images__item">
                <a href="/">
                    <img src="/img/uniteddirectheaderlogo.png" alt="img"/>
                </a>
            </div>
<!--          <div class="search-container"><div class="typeahead-container">
            <div class="typeahead-input-container">
              <form action="" class="typeahead-input-form">
                <label for="typeahead-input-desktop" class="hidden"></label>
                <input type="text" id="typeahead-input-desktop" class="typeahead-input" role="search" aria-label="Enter your search keyword" autocomplete="off" placeholder="Search" data-talos="navSearchInput" value="">
                &lt;!&ndash;<input type="text" aria-hidden="true" class="typeahead-input-hint" disabled="" value="">&ndash;&gt;
              </form>
            </div>
            <button aria-label="Search product" class="typeahead-go" data-talos="navSearchIcon">
              &lt;!&ndash;<i class="icon icon-search" role="presentation"></i>&ndash;&gt;
              <i class="fas fa-search"></i>
            </button>
          </div>
          </div>-->
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
            ...mapGetters(['isLoggedIn', 'userEmail', 'isAdmin', 'countCartItem'])
        },
        methods: {
            logout() {
                this.$store.dispatch('logout');
            },
          openMenu() {
            this.$emit('openMenu');
            this.isSideBarOpen = true;

            if (!this.noOverlay) {
              document.body.classList.add('bm-overlay');
            }
            if (this.right) {
              this.$refs.sideNav.style.left = 'auto';
              this.$refs.sideNav.style.right = '0px';
            }
            this.$nextTick(function() {
              this.$refs.sideNav.style.width = this.width
                  ? this.width + 'px'
                  : '300px';
            });
          },
          closeMenu() {
            this.$emit('closeMenu');
            this.isSideBarOpen = false;
            document.body.classList.remove('bm-overlay');
            this.$refs.sideNav.style.width = '0px';
          },
          closeMenuOnEsc(e) {
            e = e || window.event;
            if (e.key === 'Escape' || e.keyCode === 27) {
              this.closeMenu();
            }
          },
          documentClick(e) {
            let element = this.$refs.bmBurgerButton;
            let target = null;
            if (e && e.target) {
              target = e.target;
            }

            if (
                element &&
                element !== target &&
                !element.contains(target) &&
                !this.hasClass(target,'bm-menu') &&
                this.isSideBarOpen &&
                !this.disableOutsideClick
            ) {
              this.closeMenu();
            } else if (
                element &&
                this.hasClass(target,'bm-menu') &&
                this.isSideBarOpen &&
                this.closeOnNavigation
            ) {
              this.closeMenu();
            }
          },
          hasClass(element, className) {
            do {
              if (element.classList && element.classList.contains(className)) {
                return true;
              }
              element = element.parentNode;
            } while (element);
            return false;
          },
        },
      props: {
        isOpen: {
          type: Boolean,
          required: false
        },
        right: {
          type: Boolean,
          required: false
        },
        width: {
          type: [String],
          required: false,
          default: '300'
        },
        disableEsc: {
          type: Boolean,
          required: false
        },
        noOverlay: {
          type: Boolean,
          required: false
        },
        onStateChange: {
          type: Function,
          required: false
        },
        burgerIcon: {
          type: Boolean,
          required: false,
          default: true
        },
        crossIcon: {
          type: Boolean,
          required: false,
          default: true
        },
        disableOutsideClick: {
          type: Boolean,
          required: false,
          default: false
        },
        closeOnNavigation: {
          type: Boolean,
          required: false,
          default: false
        }
      },
      mounted() {
        if (!this.disableEsc) {
          document.addEventListener('keyup', this.closeMenuOnEsc);
        }
      },
      created: function() {
        document.addEventListener('click', this.documentClick);
      },
      destroyed: function() {
        document.removeEventListener('keyup', this.closeMenuOnEsc);
        document.removeEventListener('click', this.documentClick);
      },
      watch: {
        isOpen: {
          deep: true,
          immediate: true,
          handler(newValue, oldValue) {
            this.$nextTick(() => {
              if (!oldValue && newValue) {
                this.openMenu();
              }
              if (oldValue && !newValue) {
                this.closeMenu();
              }
            });
          }
        },
        right: {
          deep: true,
          immediate: true,
          handler(oldValue, newValue) {
            if (oldValue) {
              this.$nextTick(() => {
                this.$refs.bmBurgerButton.style.left = 'auto';
                this.$refs.bmBurgerButton.style.right = '36px';
                this.$refs.sideNav.style.left = 'auto';
                this.$refs.sideNav.style.right = '0px';
                document.querySelector('.bm-burger-button').style.left = 'auto';
                document.querySelector('.bm-burger-button').style.right = '36px';
                document.querySelector('.bm-menu').style.left = 'auto';
                document.querySelector('.bm-menu').style.right = '0px';
                document.querySelector('.cross-style').style.right='250px';
              });
            }
            if (newValue) {
              if (
                  this.$refs.bmBurgerButton.hasAttribute('style')
              ) {
                this.$refs.bmBurgerButton.removeAttribute('style');
                this.$refs.sideNav.style.right = 'auto';
                document
                    .querySelector('.bm-burger-button')
                    .removeAttribute('style');
                document.getElementById('sideNav').style.right = 'auto';
                document.querySelector('.cross-style').style.right='0px';
              }
            }
          }
        }
      }
    }
</script>

<style lang="scss">
    header {
      background-color:#333;
      height: 130px;
    }
    .header-navbar {
        &__nav-item {
            display: flex;
            cursor: pointer;
        }
        &__nav-item:hover {
            cursor: pointer;
        }
        &__nav-link {
            @import url('https://fonts.googleapis.com/css?family=Open+Sans');
            font-family: "Open Sans", sans-serif;
            font-size: 16px;
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
        justify-content: space-around;
        &__img {
            height: 60px;
            width: 60px;
        }
    }
    .search-container {
      font-size: 100%;
      cursor: auto;
      font-style: normal;
      font-weight: 400;
      line-height: 1.5;
      color: #242424;
      font-family: Open Sans,sans-serif;
      box-sizing: border-box;
      align-self: center;
      flex: 0 0 31.66667%;
      max-width: 31.66667%;
      padding-right: .9375rem;
      padding-left: .9375rem;
    }
    .typeahead-container {
      font-size: 100%;
      cursor: auto;
      font-style: normal;
      font-weight: 400;
      line-height: 1.5;
      color: #242424;
      position: relative;
      background-color: #fff;
      width: 100%;
      display: flex;
      font-family: Open Sans,sans-serif;
      height: 3.375rem;
      transition: all .1s linear;
      border-radius: 3px;
      border: 1px solid #b5b5b5;
      box-sizing: border-box;
    }
    .typeahead-input-container {
      font-size: 100%;
      cursor: auto;
      font-style: normal;
      font-weight: 400;
      line-height: 1.5;
      color: #242424;
      font-family: Open Sans,sans-serif;
      box-sizing: border-box;
      position: relative;
      flex: 1;
      border-radius: initial;
      border: none;
      border-right: 0;
      margin: 0 0 0 -1px;
    }
    .typeahead-input-form {
      cursor: auto;
      font-style: normal;
      font-weight: 400;
      line-height: 1.5;
      color: #242424;
      font-family: Open Sans,sans-serif;
      box-sizing: border-box;
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      margin: 0;
      background-color: transparent;
      border: none;
      padding: 0 .625rem;
      outline: none;
      font-size: .75rem;
      z-index: 0;
    }
    .typeahead-input {
      box-sizing: border-box;
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      margin: 0;
      background-color: transparent;
      border: none;
      padding: 0 .625rem;
      outline: none;
      font-size: 12px;
      z-index: 2;
    }
    .typeahead-go {
      box-sizing: border-box;
      background-color: transparent;
      border: none;
      border-radius: initial;
      width: 2.5rem;
      outline: none;
      /*margin: 0 -3px 0 0;*/
      cursor: pointer;
    }
    .fa-shopping-cart:before {
      /*content: '\F447';*/
    }
    .top-bar-cart .cart-icon {
      background-color: #1A1A1A;
      color: #ffffff;
    }
    .top-bar-cart .icon-cart {
      font-size: 1.5625rem;
      height: auto;
    }
    .cart-icon {
      font-style: normal;
      line-height: 1.5;
      font-family: Open Sans,sans-serif;
      box-sizing: border-box;
      display: inline-block;
/*      font-size: .875rem;*/
      font-weight: 600;
      text-decoration: none;
      border-radius: 0;
      border-left: 0;
      padding: .5rem 0 .4375rem;
      text-align: center;
      width: 3.75rem;
      background-color: #1A1A1A;
      color: #ffffff;
    }
    .cart-count {
      font-style: normal;
      line-height: 1.5;
      font-family: Open Sans,sans-serif;
      font-weight: 600;
      box-sizing: border-box;
      display: inline-block;
      padding: 0 2px;
      border-radius: 50%;
      min-width: 1.1875rem;
      text-align: center;
      font-size: .8125rem;
      vertical-align: middle;
      background-color: #DB1D24;
      color: #ffffff;
      margin-top: -.75rem;
      margin-left: -.6875rem;
    }
    .icon-cart {
      color: #ffffff;
      box-sizing: border-box;
      display: inline-block;
      text-decoration: none;
      font-weight: 400;
      font-style: normal;
      vertical-align: middle;
      -webkit-font-smoothing: antialiased;
      line-height: 1;
      text-align: center;
      font-family: Genericons;
      font-size: 1.5625rem;
      height: auto;
    }
    .top-bar-cart {
      font-size: 100%;
      cursor: auto;
      font-style: normal;
      font-weight: 400;
      line-height: 1.5;
      color: #242424;
      font-family: Open Sans,sans-serif;
      box-sizing: border-box;
      height: 1px;
      margin: 0;
      order: 4;
    }
    .nav-item {
        &__icon {
            align-self: center;
            margin-bottom: 2px;
            height: 20px;
            width: 20px;
        }
    }
    .icon-cart:before {
      content: "\F447";
    }
    .bm-burger-button {
      position: absolute;
      width: 36px;
      height: 30px;
      left: 36px;
      top: 36px;
      cursor: pointer;
    }
    .bm-burger-button.hidden {
      display: none;
    }
    .bm-burger-bars {
      background-color: lightgrey;
    }
    .line-style {
      position: absolute;
      height: 20%;
      left: 0;
      right: 0;
    }
    .cross-style {
      position: absolute;
      top: 12px;
      right: 2px;
      cursor: pointer;
    }
    .bm-cross {
      background: #bdc3c7;
    }
    .bm-cross-button {
      height: 24px;
      width: 24px;
    }
    .bm-cross-button.hidden {
      display: none;
    }
    .bm-menu {
      height: 100%; /* 100% Full-height */
      width: 0; /* 0 width - change this with JavaScript */
      position: fixed; /* Stay in place */
      z-index: 1000; /* Stay on top */
      top: 0;
      left: 0;
      background-color: rgb(63, 63, 65); /* Black*/
      overflow-x: hidden; /* Disable horizontal scroll */
      padding-top: 60px; /* Place content 60px from the top */
      transition: 0.5s; /*0.5 second transition effect to slide in the sidenav*/
    }

    .bm-overlay {
      //background: rgba(0, 0, 0, 0.3);
    }
    .bm-item-list {
      color: #b8b7ad;
      margin-left: 10%;
      font-size: 20px;
    }
    .bm-item-list > * {
      display: flex;
      text-decoration: none;
      padding: 0.7em;
    }
    .bm-item-list > * > span {
      margin-left: 10px;
      font-weight: 700;
      color: white;
    }
</style>