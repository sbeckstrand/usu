MyGame.objects.Mushroom = function(spec) {
    'use strict';

    let imageReady = false;
    let image = new Image();
    let stage = 1
    
    image.onload = function() {
        imageReady = true;
    };

    image.src = spec.imageSrc;

    let api = {
        get imageReady() { return imageReady; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; }
    }

    return api;
}