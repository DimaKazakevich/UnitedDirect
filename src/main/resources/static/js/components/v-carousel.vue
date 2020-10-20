<template>
    <div id="carousel" class="carousel slide" data-ride="carousel" data-interval="5000">
        <ol class="carousel-indicators">
            <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-id" data-slide-to="1" class=""></li>
            <li data-target="#carousel-id" data-slide-to="2" class=""></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img :src="'../../img/carousel_kit.jpg'" alt="img">
            </div>
            <div class="item">
                <img :src="'../../img/carousel_bruno.jpg'" alt="img">
            </div>
            <div class="item">
                <img :src="'../../img/d_carousel_black_lives_matter.jpg'" alt="img">
            </div>
        </div>
        <a class="left carousel-control" href="#carousel" role="button" data-co data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" style="color:#333"></span>
        </a>
        <a class="right carousel-control" href="#carousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" style="color:#333"></span>
        </a>
    </div>
</template>

<script>
    export default {
        name: "v-carousel"
    }
</script>

<style>
.carousel-indicators {
    z-index: 1;
}
</style>