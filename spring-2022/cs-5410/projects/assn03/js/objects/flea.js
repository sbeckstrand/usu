MyGame.objects.Flea = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "flea";
    let image = new Image();
    let start = {x: spec.startX, y: spec.startY}
    let animationTime = 0;
    let animationStage = 0;
    
    image.onload = function() {
        imageReady = true;
    };

    function update(elapsedTime) {
        animationTime += elapsedTime;
        console.log(animationStage);


        if (animationTime >= spec.spriteTime[animationStage]) {
            animationTime -= spec.spriteTime[animationStage];
            animationStage = animationStage + 1;
            
            if (animationStage == spec.spriteCount ) {
                animationStage = 0;
            }
        }

        if (animationStage == 0) {
            start = {x: 64, y: 32};
        } else if (animationStage == 1) {
            start = {x: 80, y: 32};
        } else if (animationStage == 2) {
            start = {x: 64, y: 40};
        } else if (animationStage == 3) {
            start = {x: 80, y: 40};
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
        get animationTime() { return animationTime },
        get animationStage() { return animationStage }
    }

    return api;
}