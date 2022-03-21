MyGame.objects.Flea = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "flea";
    let image = new Image();
    let start = {x: spec.startX, y: spec.startY}
    let animationTime = 0;
    let animationStage = 0;
    let animationStarts = {
        0: {x: 64, y: 32},
        1: {x: 80, y: 32},
        2: {x: 64, y: 40},
        3: {x: 80, y: 40}
    }
    let previousY = 0;
    
    image.onload = function() {
        imageReady = true;
    };

    function update(elapsedTime) {
        animationTime += elapsedTime;


        if (animationTime >= spec.spriteTime[animationStage]) {
            animationTime -= spec.spriteTime[animationStage];
            animationStage = animationStage + 1;
            
            if (animationStage == spec.spriteCount ) {
                animationStage = 0;
            }
        }

        start = animationStarts[animationStage];

        spec.center.y += spec.moveRate * elapsedTime;

        if (spec.center.y - (spec.size.height / 2 ) > MyGame.graphics.canvas.height) {
            delete MyGame.flea;
        }

        const currY = Math.floor((spec.center.y - 12.5) / spec.size.height) + 1;
        const currX = Math.floor((spec.center.x - 12.5) / spec.size.width) + 1;

        // if (currX == MyGame.mushrooms[0].x && currY == MyGame.mushrooms[0].y) {
        //     console.log(`x: ${currX}, y: ${currY}, mushX: ${MyGame.mushrooms[0].x}, y: ${MyGame.mushrooms[0].y}}`);
        // }
        // console.log(MyGame.mushroomGrid[currX - 1][currY - 1]);
  
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