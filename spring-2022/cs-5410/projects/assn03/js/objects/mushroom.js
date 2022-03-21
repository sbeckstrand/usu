MyGame.objects.Mushroom = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "mushroom";
    let image = new Image();
    let state = 1;
    let start = {x: spec.startX, y: spec.startY};
    let poisoned = false;
    
    image.onload = function() {
        imageReady = true;
    };

    function updateState() {
        state += 1;
    }

    function updateStart(updatedStart) {
        start = updatedStart;
    }

    function poison() {
        if (!poisoned) {
            poisoned = true;
            start = {x: 64, y: 0};
        }
    }

    image.src = spec.imageSrc;

    let api = {
        updateState: updateState,
        updateStart: updateStart,
        poison: poison,
        get type() { return type },
        get imageReady() { return imageReady; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; },
        get state() { return state; },
        get start() { return start; },
        get poisoned() { return poisoned; },
        get x() { return spec.x; },
        get y() { return spec.y; }
    }

    return api;
}