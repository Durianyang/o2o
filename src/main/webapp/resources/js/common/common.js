function changeImage(img) {
    // 生成四位随机数
    img.src = "../kaptcha?" + Math.floor(Math.random() * 100);
}