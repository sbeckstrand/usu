MyGame.objects.Scorpion = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "scorpion";
    let image = new Image();
    let start = {x: spec.startX, y: spec.startY}
    let animationTime = 0;
    let animationStage = 0;
    let animationStarts = {
        0: {x: 0, y: 56},
        1: {x: 16, y: 56},
        2: {x: 32, y: 56},
        3: {x: 48, y: 56}
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
        get animationTime() { return animationTime },
        get animationStage() { return animationStage }
    }

    return api;
}