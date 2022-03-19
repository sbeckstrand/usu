// Render player object

MyGame.objects.Player = function(spec) {
    'use strict';

    let rotation = 0;
    let imageReady = false;
    let image = new Image();
    let readyToShoot = true;
    let shotTimer = 5;
    let previousShotTime = 0;

    image.onload = function() {
        imageReady = true;
    };
    image.src = spec.imageSrc; 

    function updateRotation(diff) {
        rotation += diff;
    }

    function checkForPlayerCollision() {
        let collision = false;
        let fatal = false;
        for (const mushroom in MyGame.mushrooms) {
            const distance = Math.sqrt(Math.pow(Math.abs(spec.center.x - MyGame.mushrooms[mushroom].center.x), 2) + Math.pow(Math.abs(spec.center.y - MyGame.mushrooms[mushroom].center.y), 2))
            if (distance < (spec.size.width / 2) + (MyGame.mushrooms[mushroom].size.width / 2)) {
                collision = true
            }
        }

        return [collision, fatal]
        
    }

    function checkForShotCollision() {
        let shotCollisions = [];
        
        for (let shot in MyGame.shots) {
            for (let mushroom in MyGame.mushrooms) {
                let inXBound = false;
                let inYBound = false;
                const mushroomCenter = MyGame.mushrooms[mushroom].center;
                const mushroomWidth = MyGame.mushrooms[mushroom].size.width;
                const mushroomHeight = MyGame.mushrooms[mushroom].size.height;


                if (mushroomCenter.x - (mushroomWidth / 2) <= MyGame.shots[shot].x &&  MyGame.shots[shot].x <= mushroomCenter.x + (mushroomWidth / 2)) {
                    inXBound = true;
                }

                if (mushroomCenter.y - (mushroomHeight / 2) <= MyGame.shots[shot].y && MyGame.shots[shot].y <= mushroomCenter.y + (mushroomHeight / 2)) {
                    inYBound = true;
                }

                if (inXBound && inYBound) {
                    const collision = {
                        "shot": MyGame.shots[shot],
                        "object": MyGame.mushrooms[mushroom]
                    }
                    shotCollisions.push(collision);
                }
            }
        }

        return shotCollisions;
    }

    function moveLeft(elapsedTime) {
        const origin = [spec.center.x, spec.center.y]

        if (spec.center.x - (spec.size.width / 2) >= 0 + (spec.size.width / 2)  ) {
            spec.center.x -= (spec.moveRate * elapsedTime);
        } else if (spec.center.x != 0 + (spec.size.width / 2)) {
            spec.center.x = 0 + (spec.size.width / 2);
        }

        if (checkForPlayerCollision()[0]) {

            spec.center.x = origin[0];
            spec.center.y = origin[1];
        }
        
    }

    function moveRight(elapsedTime) {
        const origin = [spec.center.x, spec.center.y]

        // console.log(`${spec.center.x}, ${spec.center.y}`)
        if (spec.center.x + (spec.size.width / 2) <= MyGame.graphics.canvas.width - (spec.size.width / 2)  ) {
            spec.center.x += (spec.moveRate * elapsedTime);
        } else if (spec.center.x != MyGame.graphics.canvas.width - (spec.size.width / 2)) {
            spec.center.x = MyGame.graphics.canvas.width - (spec.size.width / 2);
        }

        if (checkForPlayerCollision()[0]) {
            spec.center.x = origin[0];
            spec.center.y = origin[1];
        }
    }

    function moveUp(elapsedTime) {
        const origin = [spec.center.x, spec.center.y]

        // console.log(`${spec.center.x}, ${spec.center.y}`)
        if (spec.center.y - (spec.size.height / 2) >= (MyGame.graphics.canvas.width * 0.75) + (spec.size.height / 2)) {
            spec.center.y -= (spec.moveRate * elapsedTime);
        } else if (spec.center.y != (MyGame.graphics.canvas.width * 0.75) + (spec.size.height / 2)) {
            spec.center.y = (MyGame.graphics.canvas.width * 0.75) + (spec.size.height / 2)
        }
        
        if (checkForPlayerCollision()[0]) {
            spec.center.x = origin[0];
            spec.center.y = origin[1];
        }
    }

    function moveDown(elapsedTime) {
        const origin = [spec.center.x, spec.center.y]

        // console.log(`${spec.center.x}, ${spec.center.y}`)
        if (spec.center.y + (spec.size.height / 2) <= (MyGame.graphics.canvas.height * 0.90) - (spec.size.height / 2)) {
            spec.center.y += (spec.moveRate * elapsedTime);
        } else if (spec.center.y != MyGame.graphics.canvas.height - (spec.size.height / 2)) {
            spec.center.y = (MyGame.graphics.canvas.height * 0.90) - (spec.size.height / 2);
        }

        if (checkForPlayerCollision()[0]) {
            spec.center.x = origin[0];
            spec.center.y = origin[1];
        }
    }

    function shoot(elapsedTime) {
        if (readyToShoot) {
            
            readyToShoot = false; 
            shotTimer = 250;

            console.log("pew pew");
            MyGame.shots.push({
                x: spec.center.x,
                y: spec.center.y
            })
            MyGame.in
        }
        
    }

    function moveTo(pos) {
        spec.center.x = pos.x;
        spec.center.y = pos.y;
    }

    function updateShotStatus(elapsedTime) {
        if (shotTimer > 0) {
            shotTimer -= elapsedTime;
        } else {
            readyToShoot = true;
        }
    }
    let api = {
        updateRotation: updateRotation,
        moveLeft: moveLeft,
        moveRight: moveRight,
        moveUp: moveUp,
        moveDown: moveDown,
        moveTo: moveTo,
        shoot: shoot,
        updateShotStatus: updateShotStatus,
        checkForShotCollision: checkForShotCollision,
        get imageReady() { return imageReady; },
        get rotation() { return rotation; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; }
    };

    return api;

}