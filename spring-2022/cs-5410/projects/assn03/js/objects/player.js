// Render player object

MyGame.objects.Player = function(spec) {
    'use strict';

    let rotation = 0;
    let imageReady = false;
    let image = new Image();
    let shots = []
    let ready_to_shoot = true;

    image.onload = function() {
        imageReady = true;
    };
    image.src = spec.imageSrc; 

    function updateRotation(diff) {
        rotation += diff;
    }

    function moveLeft(elapsedTime) {
        spec.center.x -= (spec.moveRate * elapsedTime);
    }

    function moveRight(elapsedTime) {
        spec.center.x += (spec.moveRate * elapsedTime);
    }

    function moveUp(elapsedTime) {
        spec.center.y -= (spec.moveRate * elapsedTime);
    }

    function moveDown(elapsedTime) {
        spec.center.y += (spec.moveRate * elapsedTime);
    }

    function shoot(elapsedTime) {
        shots.append({
            x: spec.center.x,
            y: spec.center.y
        })
    }

    function moveTo(pos) {
        spec.center.x = pos.x;
        spec.center.y = pos.y;
    }

    let api = {
        updateRotation: updateRotation,
        moveLeft: moveLeft,
        moveRight: moveRight,
        moveUp: moveUp,
        moveDown: moveDown,
        moveTo: moveTo,
        shoot: shoot,
        get imageReady() { return imageReady; },
        get rotation() { return rotation; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; }
    };

    return api;

}