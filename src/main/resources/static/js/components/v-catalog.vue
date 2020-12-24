<template>
    <div class="v-catalog" >
        <div class="v-catalog__filters col-xs-3">
        </div>
        <div>
          <v-sorting-drop-down />
          <div class="v-catalog__list">
              <v-catalog-item v-for="product in catalogItems.content" :key="product.id" v-bind:product-data="product" />
          </div>
          <button @click="loadNextItems">Next</button>
          <button @click="loadPrevItems">Prev</button>
        </div>
    </div>
</template>

<script>
    import vCatalogItem from './v-catalog-item.vue'
    import vMenu from './v-menu.vue'
    import axios from 'axios'
    import vSortingDropDown from "./v-sorting-drop-down.vue";
    import {mapGetters, mapActions} from "vuex";

    export default {
        name: 'v-catalog',
        components: {
            vSortingDropDown,
            vCatalogItem,
            vMenu
        },
        computed: {
          ...mapGetters([
            'catalogItems',
              'pagesCount'
          ]),
        },
        methods: {
          ...mapActions(['getProductFromApi']),
          loadNextItems() {
            if(this.currentPage < this.pagesCount - 1) {
              this.getProductFromApi({page: ++this.currentPage, category:this.$route.params.category})
            }
          },
          loadPrevItems() {
            if(this.currentPage > 0) {
              this.getProductFromApi({page: --this.currentPage, category: this.$route.params.category});
            }
          },
        },
        created() {
        },
      mounted() {
      },
      data() {
          return {
            sizes: [],
            currentPage: 0,
          }
        }
    }
</script>

<style lang="scss">
.v-catalog {
    display: flex;
    justify-content: center;
    margin-top: 40px;
    &__list {
         display: flex;
         flex-wrap: wrap;
         border-top: 1px solid lightgray;
         padding-top: 10px;
     }
}
label.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 14px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
  /* background-color: #2196F3; */
  background-color: #b61c1c;

}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}

#logoutForm {
  height: 16px;
}

.sortingDropDown {
  flex: 0 0 auto;
  margin: auto 0;
  font-weight: 700;
}

.drop-down {
  height: 35px;
  font-size: 14px;
  color: #000;
  height: 42px;
  border: 1px solid #000;
  border-radius: 0;
  width: 100%;
  padding: 5px 10px;
  box-shadow: none;
  font-family: Arial,Helvetica,sans-serif;
}

.layout-row > * {
  flex: 1 1;
  min-width: 0;
}
.layout-row {
  display: flex;
  flex-wrap: wrap;
  max-width: 100em;
  margin: 0 auto;
}

.i-filters .show {
  display: block;
}

.i-filters .filterblock {
  position: relative;
  display: none;
  margin-bottom: 24px;
}
.i-filters .filter-title {
  word-wrap: break-word;
  overflow-wrap: break-word;
  word-break: break-word;
  position: relative;
  padding-right: 15px;
  cursor: pointer;
}
.i-filters .filterblock.price .selectorsblock {
  margin-top: 12px;
}
.i-filters .filter-title-text {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  color: #000;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
  font-weight: 700;
  margin-right: 12px;
  vertical-align: middle;
}
div.filterblock.wbsize.show.filter-active {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  margin: 0px 40px 0px 0px;
  padding: 0;
  position: relative;
  margin-bottom: 24px;
  display: block;
}
div.filter-title {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  display:flex;
  margin: 0;
  padding: 0;
  overflow-wrap: break-word;
  word-break: break-word;
  position: relative;
  padding-right: 15px;
  cursor: pointer;
}
div.filter-title > span.filter-title-text {
  overflow-wrap: break-word;
  word-break: break-word;
  cursor: pointer;
  outline: 0;
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  color: #000;
  font-size: 16px;
  line-height: 22px;
  font-weight: 700;
  margin-right: 12px;
  vertical-align: middle;
}
div.i-scrollbar-in-filter {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  margin: 0;
  padding: 0;
}
div.scroll-wrapper.selectorsblock.custom-scroll.scrollbar-inner {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  margin: 0;
  overflow: hidden !important;
  padding: 0 !important;
  border: 0;
  max-height: 238px;
  width: auto;
  margin-top: 12px;
  display: block;
  position: relative;
}
div.selectorsblock.custom-scroll.scrollbar-inner.scroll-content {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  border: none !important;
  box-sizing: content-box !important;
  left: 0;
  margin: 0;
  max-width: none !important;
  overflow: auto !important;
  padding: 0;
  position: relative !important;
  top: 0;
  width: auto !important;
  padding-right: 20px;
  display: block;
  /*height: auto;*/
  margin-bottom: 0px;
  margin-right: 0px;
  /*max-height: 55px;*/
}
#wbsize_list_left {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  margin: 0;
  padding: 0;
  border: 0;
  max-height:200px;
  overflow-y:auto;
}
label.j-list-item.filters-item.c-checkbox-withText-v1.selected:after {
  content: '';
  border-right: 2px solid #fff;
  border-bottom: 2px solid #fff;
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
  display: none;
  width: 4px;
  height: 10px;
  top: 3px;
  left: 8px;
  display: block;
}
label.j-list-item.filters-item.c-checkbox-withText-v1::before {
  position: absolute;
  content: '';
  left: 0;
  top: 0;
  width: 22px;
  height: 22px;
  border-radius: 6px;
  border: 1px solid #d5d5d5;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  background: #fff;
}

label.j-list-item.filters-item.c-checkbox-withText-v1 {
  outline: 0;
  border: none;
  vertical-align: middle;
  display: block;
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  color: #000;
  font-weight: 400;
  font-size: 16px;
  line-height: 22px;
  overflow-wrap: break-word;
  word-break: break-word;
  position: relative;
  padding-left: 34px;
  cursor: pointer;
  margin-top: 12px;
}
span.reset {
  display:none;
  overflow-wrap: break-word;
  word-break: break-word;
  outline: 0;
  text-decoration: none;
  cursor: pointer;
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;
  border-bottom: 1px dashed #8b8b8b;
  color: #8b8b8b;
  position: relative;
  vertical-align: middle;
}

div.filterblock.price.show.filter-active {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  margin: 0;
  padding: 0;
  position: relative;
  margin-bottom: 24px;
  margin-right:40px;
  display: block;
}

div.filter-title {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  margin: 0;
  padding: 0;
  overflow-wrap: break-word;
  word-break: break-word;
  position: relative;
  padding-right: 15px;
  cursor: pointer;
}

div.selectorsblock.range {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 12px;
  outline: 0;
  margin: 0;
  padding: 0;
  margin-top: 12px;
  display: block;
}

div.range-text {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  outline: 0;
  margin: 0;
  padding: 0;
  font-size: 0;
}

input[type='number'] {
  outline: 0;
  margin: 0;
  vertical-align: middle;
  width: 100%;
  box-sizing: border-box;
  border: 1px solid #d5d5d5;
  border-radius: 4px;
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-weight: 400;
  font-size: 16px;
  line-height: 1;
  color: #000;
  padding: 6px 12px;
  height: 36px;
  margin-top: 6px;
  border-bottom-right-radius: 0;
  border-top-right-radius: 0;
}

input.c-input-base-sm:focus {
  border-right: 1px solid #cb11ab;

  box-shadow: inset 1px 0 0 #cb11ab, inset 0 1px 0 #cb11ab, inset -1px 0 0 #cb11ab, inset 0 -1px 0 #cb11ab;
}

span.end-text {
  outline: 0;
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;
  color: #999;
}

span.start-text {
  outline: 0;
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;
  color: #999;
}

div.end-n {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 0;
  outline: 0;
  margin: 0;
  padding: 0;
  display: inline-block;
  width: 50%;
}

div.start-n {
  font-family: system,-apple-system,BlinkMacSystemFont,'Segoe UI','Segoe WP',Roboto,Ubuntu,Oxygen,Cantarell,'Fira Sans','Helvetica Neue',Helvetica,'Lucida Grande','Droid Sans',Tahoma,'Microsoft Sans Serif',sans-serif;
  font-size: 0;
  outline: 0;
  margin: 0;
  padding: 0;
  display: inline-block;
  width: 50%;
}
</style>