MyGame.objects.Mushroom = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "mushroom";
    let image = new Image();
    let stage = 1;
    
    image.onload = function() {
        imageReady = true;
    };

    function updateState() {
        stage += 1;
    }

    function updateImage(imageSrc) {
        imageReady = false;
        image = new Image();

        image.onload = function() {
            imageReady = true;
        };

        image.src = imageSrc;
    }

    image.src = spec.imageSrc;

    let api = {
        updateState: updateState,
        get type() { return type },
        get imageReady() { return imageReady; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; }
    }

    return api;
}