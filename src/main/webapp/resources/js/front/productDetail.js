$(function () {
    var productId = getQueryParam('productId');
    var productUrl = '/o2o/front/getProductDetailInfo?productId='
        + productId;

    $.getJSON(productUrl, function (data) {
        if (data.success) {
            var product = data.row;
            $('#product-img').attr('src', product.imgAddr);
            $('#product-time').text(new Date(product.lastEditTime).Format("yyyy-MM-dd"));
            $('#product-name').text(product.productName);
            $('#product-desc').append(product.productDesc);
            if (product.normalPrice == null) {
                product.normalPrice = 0;
            }
            $('#normal-price').append(product.normalPrice + "元");
            if (product.promotionPrice == null) {
                product.promotionPrice = 0;
            }
            $('#promotion-price').append(product.promotionPrice + "元");

            var imgListHtml = '';
            product.productImgList.map(function (item, index) {
                imgListHtml += '<div> <img src="'
                    + item.imgAddr + '"/></div>';
            });
            // // 生成购买商品的二维码供商家扫描
            // imgListHtml += '<div> <img src="/o2o/front/generateqrcode4product?productId='
            //     + product.productId + '"/></div>';
            // $('#imgList').html(imgListHtml);
        } else {
            $.toast("商品信息加载失败！", 1000);
        }
    });
    $('#me').click(function () {
        $.openPanel('#panel-right-demo');
    });
    $.init();
});
