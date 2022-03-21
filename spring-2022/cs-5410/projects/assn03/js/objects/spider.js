MyGame.objects.Spider = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "spider";
    let image = new Image();
    let start = {x: spec.startX, y: spec.startY}
    let animationTime = 0;
    let animationStage = 0;

    let animationStarts = {
        0: {x: 0,  y: 32},
        1: {x: 0,  y: 40},
        2: {x: 16, y: 32},
        3: {x: 16, y: 40},
        4: {x: 32, y: 32},
        5: {x: 32, y: 40},
        6: {x: 48, y: 32},
        7: {x: 32, y: 40}
    }
    
    image.onload = function() {
        imageReady = true;
    };

    function update(elapsedTime) {
        animationTime += elapsedTime;

        if (animationTime >= spec.spriteTime[animationStage]) {
            animationTime -= spec.spriteTime[animationStage];
            animationStage = animationStage +1;

            if (animationStage == spec.spriteCount) {
                animationStage = 0;
            }
        }

        start = animationStarts[animationStage];

        let moveDistance = spec.moveRate * elapsedTime;
        
        if (spec.center.x - (spec.size.width / 2) < 0) {
            spec.direction[0] = "right";
        }

        if (spec.center.x + (spec.size.width / 2) > MyGame.graphics.canvas.width) {
            spec.direction[0] = "left";
        }

        if (spec.center.y > MyGame.graphics.canvas.height * 0.9) {
            spec.direction[1] = "up";
            const randDirection = Math.round(Math.random())
            spec.tempDirection = (randDirection == 1 ? spec.direction[0] : "none");
        } 

        if (spec.center.y < MyGame.graphics.canvas.height * 0.7) {
            spec.direction[1] = "down";
            const randDirection = Math.round(Math.random())
            spec.tempDirection = (randDirection == 1 ? spec.direction[0] : "none");
        }

        if (spec.tempDirection == "left") {
            spec.center.x -= spec.moveRate * elapsedTime;
        } else if (spec.tempDirection == "right") {
            spec.center.x += spec.moveRate * elapsedTime;
        }


        if (spec.direction[1] == "up") {
            spec.center.y -= moveDistance;
        } 

        if (spec.direction[1] == "down") {
            spec.center.y += moveDistance;
        }

    }

    image.src = spec.imageSrc;

    let api = {
        update: update,
        get type() { return type },
        get imageReady() { return imageReady; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; },
        get start() { return start; },
        get animationTime() { return animationTime; },
        get animationStage() { return animationStage; }
    }

    return api;
}