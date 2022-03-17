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
        if (spec.center.x - (spec.size.width / 2) >= 0 + (spec.size.width / 2)  ) {
            spec.center.x -= (spec.moveRate * elapsedTime);
        } else if (spec.center.x != 0 + (spec.size.width / 2)) {
            spec.center.x = 0 + (spec.size.width / 2);
        }
        
    }

    function moveRight(elapsedTime) {
        // console.log(`${spec.center.x}, ${spec.center.y}`)
        if (spec.center.x + (spec.size.width / 2) <= MyGame.graphics.canvas.width - (spec.size.width / 2)  ) {
            spec.center.x += (spec.moveRate * elapsedTime);
        } else if (spec.center.x != MyGame.graphics.canvas.width - (spec.size.width / 2)) {
            spec.center.x = MyGame.graphics.canvas.width - (spec.size.width / 2);
        }
    }

    function moveUp(elapsedTime) {
        console.log(`${spec.center.x}, ${spec.center.y}`)
        if (spec.center.y - (spec.size.height / 2) >= (MyGame.graphics.canvas.width * 0.75) + (spec.size.height / 2)) {
            spec.center.y -= (spec.moveRate * elapsedTime);
        } else if (spec.center.y != (MyGame.graphics.canvas.width * 0.75) + (spec.size.height / 2)) {
            spec.center.y = (MyGame.graphics.canvas.width * 0.75) + (spec.size.height / 2)
        }
        
    }

    function moveDown(elapsedTime) {
        // console.log(`${spec.center.x}, ${spec.center.y}`)
        if (spec.center.y + (spec.size.height / 2) <= MyGame.graphics.canvas.height - (spec.size.height / 2)) {
            spec.center.y += (spec.moveRate * elapsedTime);
        } else if (spec.center.y != MyGame.graphics.canvas.height - (spec.size.height / 2)) {
            spec.center.y = MyGame.graphics.canvas.height - (spec.size.height / 2);
        }
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