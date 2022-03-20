// Render player object

MyGame.objects.Player = function(spec) {
    'use strict';

    let rotation = 0;
    let imageReady = false;
    let image = new Image();
    let readyToShoot = true;
    let shotTimer = 5;
    let previousShotTime = 0;
    let start = {x: spec.startX, y: spec.startY};

    image.onload = function() {
        imageReady = true;
    };
    image.src = spec.imageSrc; 

    function updateRotation(diff) {
        rotation += diff;
    }

    function updateShots(elapsedTime) {
        // Update shot timer
        if (shotTimer > 0) {
            shotTimer -= elapsedTime;
        } else {
            readyToShoot = true;
        }

        // Update shot position
        for (let shot in MyGame.shots) {
            const curr_y = MyGame.shots[shot].y
            MyGame.shots[shot].y = curr_y - 10;
        }
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
            let all_objects = [];
            // Check for mushroom collisions
            for (let mushroom in MyGame.mushrooms) {
                all_objects.push(MyGame.mushrooms[mushroom]);

            }

            if (typeof MyGame.spider != 'undefined') {
                all_objects.push(MyGame.spider);
            }

            if (typeof MyGame.scorpion != 'undefined') {
                all_objects.push(MyGame.scorpion);
            }

            if (typeof MyGame.flea != 'undefined') {
                all_objects.push(MyGame.flea);
            }

            for (const obj in all_objects) {
                let inXBound = false;
                let inYBound = false;
                const objectCenter = all_objects[obj].center;
                const objectWidth = all_objects[obj].size.width;
                const objectHeight = all_objects[obj].size.height;


                if (objectCenter.x - (objectWidth / 2) <= MyGame.shots[shot].x &&  MyGame.shots[shot].x <= objectCenter.x + (objectWidth / 2)) {
                    inXBound = true;
                }

                if (objectCenter.y - (objectHeight / 2) <= MyGame.shots[shot].y && MyGame.shots[shot].y <= objectCenter.y + (objectHeight / 2)) {
                    inYBound = true;
                }

                if (inXBound && inYBound) {
                    const collision = {
                        "shot": MyGame.shots[shot],
                        "object": all_objects[obj]
                    }
                    shotCollisions.push(collision);
                }
            }

        }

        return shotCollisions;
    }

    function handleShotCollisions() {
        const collisions = checkForShotCollision()
        if (Object.keys(collisions).length > 0) {
            for (const collision in collisions) {
                MyGame.shots = MyGame.shots.filter(function( obj ) {
                    return obj != collisions[collision]["shot"];
                });

                const collision_object = collisions[collision]["object"];

                if (collision_object.type == "mushroom") {
                    let mushroomIndex = MyGame.mushrooms.findIndex(element => element == collision_object)
                    let mushroom = MyGame.mushrooms[mushroomIndex];
                    
                    mushroom.updateState();
                    
                    const mushroomState = mushroom.state;
                    
                    if (mushroomState == 2) {
                        mushroom.updateStart({x: 72, y: 8});
                    } else if (mushroomState == 3) {
                        mushroom.updateStart({x: 80, y: 8});
                    } else if (mushroomState == 4) {
                        mushroom.updateStart({x: 88, y: 8});
                    } else if (mushroomState > 4) {

                        MyGame.mushrooms = MyGame.mushrooms.filter(element => element != mushroom);
                    }
                    MyGame.score += 1;
                }

                if (collision_object.type == "spider") {
                    delete MyGame.spider;
                    MyGame.score += 300;
                }

                if (collision_object.type == "scorpion") {
                    delete MyGame.scorpion;
                    MyGame.score += 500;
                }

                if (collision_object.type == "flea") {
                    delete MyGame.flea;
                    MyGame.score += 300;
                }
                

            }
        }
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
            shotTimer = 100;

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

    let api = {
        updateRotation: updateRotation,
        moveLeft: moveLeft,
        moveRight: moveRight,
        moveUp: moveUp,
        moveDown: moveDown,
        moveTo: moveTo,
        shoot: shoot,
        checkForShotCollision: checkForShotCollision,
        updateShots: updateShots,
        handleShotCollisions: handleShotCollisions,
        get imageReady() { return imageReady; },
        get rotation() { return rotation; },
        get image() { return image; },
        get center() { return spec.center; },
        get size() { return spec.size; },
        get start() { return start; }
    };

    return api;

}