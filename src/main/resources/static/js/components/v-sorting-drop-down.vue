<template>
    <div class="v-sorting-drop-down">
        <select v-on:change="dropDownChanged()" class="v-sorting-drop-down__list">
            <option>Sort By</option>
            <option value="desc">Price High to Low</option>
            <option value="asc">Price Low to High</option>
        </select>
    </div>
</template>

<script>
    export default {
        name: "v-sorting-drop-down",
        methods: {
            dropDownChanged() {
                let selectedSortDirection = $('.v-sorting-drop-down__list').children("option:selected").val();
                switch (selectedSortDirection) {
                    case ('desc'): {
                        this.sort("desc");
                        break;
                    }
                    case ('asc'): {
                        this.sort("asc");
                        break;
                    }
                }
            },
            sort(sortOrder) {
                let content = document.querySelector('.v-catalog__list');
                let divsToSort = [...document.querySelectorAll('.v-catalog__list > div')];
                let desc;

                if (sortOrder === 'desc') {
                    desc = true;
                }

                divsToSort.sort(function (a, b) {
                    let na, nb;
                    na = parseInt(a.querySelector('.v-catalog-item__price').innerText.replace("$", "").trim());
                    nb = parseInt(b.querySelector('.v-catalog-item__price').innerText.replace("$", "").trim());
                    return desc ? (nb - na) : (na - nb);
                });

                divsToSort.forEach(el => content.appendChild(el));
            }
        }
    }
</script>

<style lang="scss">
.v-sorting-drop-down{
    flex: 0 0 auto;
    margin: 0 0 40px;
    font-weight: 700;
    width: 170px;
    &__list {
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
}
</style>