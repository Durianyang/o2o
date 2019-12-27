function changeImage(img) {
    // 生成四位随机数
    img.src = "../kaptcha?" + Math.floor(Math.random() * 100);
}

function getQueryParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*(&|$))");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}