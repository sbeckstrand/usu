MyGame.objects.Spider = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "spider";
    let image = new Image();
    let start = {x: spec.startX, y: spec.startY}
    
    image.onload = function() {
        imageReady = true;
    };

    image.src = spec.imageSrc;

    let api = {
        get type() { return type },
        get imageReady() { return imageReady; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; },
        get start() { return start; }
    }

    return api;
}