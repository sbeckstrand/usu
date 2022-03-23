// Create flea object
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

    // Create a copy of the mushroom grid, which is used to track which tiles we have already calculated if a mushroom should be placed. 
    let processed = []

    for (const row in MyGame.mushroomGrid) {
        const rowCopy = [...MyGame.mushroomGrid[row]];
        processed.push(rowCopy);
    }

    image.onload = function() {
        imageReady = true;
    };

    // Update flea object with every frame
    function update(elapsedTime) {

        // Update animation stage and starting position in image to pull sprite
        animationTime += elapsedTime;


        if (animationTime >= spec.spriteTime[animationStage]) {
            animationTime -= spec.spriteTime[animationStage];
            animationStage = animationStage + 1;
            
            if (animationStage == spec.spriteCount ) {
                animationStage = 0;
            }
        }

        start = animationStarts[animationStage];

        // Move flea
        spec.center.y += spec.moveRate * elapsedTime;

        // Remove the flea once it is off screen
        if (spec.center.y - (spec.size.height / 2 ) > MyGame.graphics.canvas.height) {
            delete MyGame.flea;
        }

        const currY = Math.floor((spec.center.y - 12.5) / spec.size.height) + 1;
        const currX = Math.floor((spec.center.x - 12.5) / spec.size.width) + 1;

        // if (currX == MyGame.mushrooms[0].x && currY == MyGame.mushrooms[0].y) {
        //     console.log(`x: ${currX}, y: ${currY}, mushX: ${MyGame.mushrooms[0].x}, y: ${MyGame.mushrooms[0].y}}`);
        // }
        // console.log(MyGame.mushroomGrid[currX - 1][currY - 1]);

        
        

        if (processed[currX][currY] == 0) {
            const randomChance = Math.random()
            if (randomChance < 0.25 && currY < 35 && currY > 4) {
                let placed = false;
                if (Math.abs(MyGame.player.center.y - spec.center.y) >=  MyGame.player.size.height * 2) {
                    let attempts = 0;
                    while (!placed) {
                        if (attempts >= 100) {
                            placed = true;
                        }
                        let mushroom = MyGame.objects.Mushroom({
                            imageSrc: 'assets/sprites.png',
                            center: { x: (25 * currX) - 12.5, y: (25 * currY) - 12.5},
                            size: { width: 25, height: 25},
                            startX: 64,
                            startY: 8,
                            x: currX,
                            y: currY
                        })
                        
                        const distance = Math.sqrt(Math.pow(Math.abs(MyGame.player.center.x - mushroom.center.x), 2) + Math.pow(Math.abs(MyGame.player.center.y - mushroom.center.y), 2))
        
                        if (MyGame.mushroomGrid[currX][currY] == 0 && distance > 2 * mushroom.size.width) {
                            MyGame.mushrooms.push(mushroom);
                            MyGame.mushroomGrid[mushroom.x][mushroom.y] = 1;
    
                            placed = true;
                        }
        
                    }
                }
                
            }
            processed[currX][currY] = 1;

        }

    }

    // function updateProcessedMap(x, y) {
    //     processed[x][y] = 0;
    // }
    image.src = spec.imageSrc;

    let api = {
        update: update,
        // updateProcessedMap: updateProcessedMap,
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