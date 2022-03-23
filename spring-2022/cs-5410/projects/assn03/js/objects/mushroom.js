// Create Mushroom object
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

    // Function used to update state when mushroom is hit with shot
    function updateState() {
        state += 1;
    }

    // Update the position of image to grab sprite
    function updateStart(updatedStart) {
        start = updatedStart;
    }

    // Mark if mushroom is poisonous
    function poison() {
        if (!poisoned) {
            poisoned = true;
            start = {x: start.x, y: 0};
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