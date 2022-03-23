// Build Centipede object
MyGame.objects.Centipede = function(spec) {
    'use strict';

    let imageReady = false;
    let type = "centipede";
    let image = new Image();
    let start = {x: spec.startX, y: spec.startY}
    let start_x = spec.startX;
    let start_y = spec.startY;
    let head = spec.head;
    let direction = spec.direction;
    let pathY = spec.pathY;
    let pathX = spec.pathX
    let xCollide = false;
    let yCollide = false;
    let collideY = 0;
    let id = spec.id;

    let animationTime = 0;
    let animationStage = 0;
    let offset = 0;
    if (head) {
        offset = 16;
    }

    // Define sprite locations based on movement
    let left = {
        0: {x: 0,   y: 16},
        1: {x: 8,   y: 16},
        2: {x: 16,  y: 16},
        3: {x: 24,  y: 16},
        4: {x: 0,   y: 24},
        5: {x: 8,   y: 24},
        6: {x: 16,  y: 24},
        7: {x: 24,  y: 24}
    }

    let right = {
        0: {x: 32,      y: 16},
        1: {x: 40,      y: 16},
        2: {x: 48,      y: 16},
        3: {x: 56,      y: 16},
        4: {x: 32,      y: 24},
        5: {x: 40,      y: 24},
        6: {x: 48,      y: 24},
        7: {x: 56,      y: 24}
    }

    let down = {
        0: {x: 64,      y: 16},
        1: {x: 72,      y: 16},
        2: {x: 64,      y: 24},
        3: {x: 72,      y: 24},
        4: {x: 64,      y: 16},
        5: {x: 72,      y: 16},
        6: {x: 64,      y: 24},
        7: {x: 72,      y: 24}
    }

    let up = {
        0: {x: 80,      y: 16},
        1: {x: 88,      y: 16},
        2: {x: 80,      y: 24},
        3: {x: 88,      y: 24},
        4: {x: 80,      y: 16},
        5: {x: 88,      y: 16},
        6: {x: 80,      y: 24},
        7: {x: 88,      y: 24}
    }



    
    image.onload = function() {
        imageReady = true;
    };

    // Update Centipede object with each cycle
    function update(elapsedTime) {
        animationTime += elapsedTime;

        // Set origin X/Y locations. Used to revert location if there is a collision to help avoid segments detatching too far from each other. 
        let originX = spec.center.x;
        let originY = spec.center.y;

        // Move and set sprites
        if (animationTime >= spec.spriteTime[animationStage]) {
            animationTime -= spec.spriteTime[animationStage];
            animationStage = animationStage + 1;
            
            if (animationStage == spec.spriteCount ) {
                animationStage = 0;
            }
        }
        if (direction == "left") {
            spec.center.x -= spec.moveRate * elapsedTime;
            start_x = left[animationStage].x;
            start_y = left[animationStage].y;

            if (head) {
                start_y = left[animationStage].y - 16;
            }
        } 
        if (direction == "right") {
            spec.center.x += spec.moveRate * elapsedTime;
            start_x = right[animationStage].x;
            start_y = right[animationStage].y;

            if (head) {
                start_y = right[animationStage].y - 16;
            }
        }

        if (direction == "up") {
            spec.center.y -= spec.moveRate * elapsedTime;
            start_x = up[animationStage].x;
            start_y = up[animationStage].y;

            if (head) {
                start_y = up[animationStage].y - 16;
            }
        }

        if (direction == "down") {
            spec.center.y += spec.moveRate * elapsedTime;
            start_x = down[animationStage].x;
            start_y = down[animationStage].y;

            if (head) {
                start_y = down[animationStage].y - 16;
            }
        }
        
        // Check for collision with mushroom. 
        let mushroomCollision = checkForCollision();

        // Move straight down if mushroom is poisoned
        if (mushroomCollision.length > 0) {
            if (mushroomCollision[0].poisoned) {
                direction = "down";
                // spec.center.x = 
            } 
        }
        
        // If you hit a mushroom while going left
        if (mushroomCollision.length > 0 && !xCollide && direction == "left") {
            xCollide = true;
            spec.center.x = originX;

            const mushroomIndex = { x: mushroomCollision[0].x, y: mushroomCollision[0].y };
            pathX = "right"
            collideY = spec.center.y;

            if (pathY == "down") {
                spec.center.x = originX;
                direction = "down"
            } else {
                direction = "up";
            }

        }

        // If you hit a mushroom while going right
        if (mushroomCollision.length > 0 && !yCollide && direction == "right") {
            xCollide = true;
            spec.center.x = originX
            spec.center.y = originY

            pathX = "left";
            collideY = spec.center.y;

            if (pathY == "down") {
                spec.center.x = originX;
                direction = "down";
            } else {
                direction = "up";
            }
        }

        // If you hit a mushroom while going down
        if (mushroomCollision.length > 0 && !xCollide && direction == "down") {

            if (!mushroomCollision[0].poisoned) {
                if (pathX == "left") {
                    direction = "left";
                } else {
                    direction = "right";
                }
            }
            
        }

        // If you hit a mushroom while going up
        if (mushroomCollision.length > 0 && !xCollide && direction == "up") {
            if (pathX == "left") {
                direction = "left";
            } else {
                direction = "right";
            }
        }



        // If you hit the left wall
        if (spec.center.x <= spec.size.width / 2 && direction == "left") {
            pathX = "right"
            collideY = spec.center.y;
            xCollide = true;
            if (pathY == "down") {
                spec.center.x = (spec.size.width / 2);
                direction = "down";
            } else {
                direction = "up";
            }
        }

        // If you hit the right wall
        if (spec.center.x >= MyGame.graphics.canvas.width - (spec.size.width / 2) && direction == "right") {
            pathX = "left"
            collideY = spec.center.y;
            xCollide = true
            if (pathY == "down") {
                spec.center.x = MyGame.graphics.canvas.width - (spec.size.width / 2);
                direction = "down";
            } else {
                direction = "up";
            }
        }

        // Turning corner going down
        if (direction == "down" && xCollide) {
            if (spec.center.y >= collideY + (spec.size.height)) {
                spec.center.y = collideY + (spec.size.height);
                if (pathX == "left") {
                    direction = "left";
                } else {
                    direction = "right";
                }
                xCollide = false;
                yCollide = false;
            }
        // turning corner going up
        } else if (direction == "up" && xCollide) {
            if (spec.center.y <= collideY - (spec.size.height)) {
                spec.center.y = collideY - (spec.size.height);
                if (pathX == "left") {
                    direction = "left";
                } else {
                    direction = "right";
                }
                xCollide = false;
                yCollide = false;
            }
            
        }

        // If you hit the bottom
        if (spec.center.y >= (MyGame.graphics.canvas.height * 0.9) - (spec.size.height / 2)) {
            if (!yCollide) {
                if (pathX == "left") {
                    direction = "left";
                } else {
                    direction = "right";
                }
            }
            pathY = "up";
            yCollide = true;
        }

        // If you hit the top
        if (spec.center.y <= spec.size.height / 2) {
            if (!yCollide) {
                if (pathX == "left") {
                    direction = "left";
                } else {
                    direction = "right";
                }
            }
            pathY = "down";
            yCollide = true;
        }
    }

    function getStart() {
        return {x: start_x, y: start_y}
    }
    
    function checkForCollision() {
        let collision = [];

        for (const mushroom in MyGame.mushrooms) {
            const distance = Math.sqrt(Math.pow(Math.abs(spec.center.x - MyGame.mushrooms[mushroom].center.x), 2) + Math.pow(Math.abs(spec.center.y - MyGame.mushrooms[mushroom].center.y), 2))
            if (distance < (spec.size.width / 2) + (MyGame.mushrooms[mushroom].size.width / 2)) {
                collision.push(MyGame.mushrooms[mushroom]);
            }
        }

        return collision;
    }

    image.src = spec.imageSrc;

    let api = {
        update: update,
        getStart: getStart,
        get type() { return type },
        get imageReady() { return imageReady; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; },
        get start() { return start; },
        get animationTime() { return animationTime },
        get animationStage() { return animationStage },
        get direction() { return direction; },
        get pathY() { return pathY; },
        get id() { return id; }
    }

    return api;
}