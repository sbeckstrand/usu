MyGame.screens['game-play'] = (function(game, objects, renderer, graphics, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;

    let myKeyboard = input.Keyboard();
    // let myMouse = input.Mouse();

    let myText = objects.Text({
        text: `Score: ${MyGame.score}`,
        font: '24pt "Hubballi"',
        fillStyle: 'rgba(255, 255, 255, 1)',
        strokeStyle: 'rgba(0, 0, 0, 1)',
        position: { x: 20, y: 0 }
    });

    let player = objects.Player({
        imageSrc: 'assets/blob.png',
        center: { x: graphics.canvas.width / 2, y: graphics.canvas.height * 0.85 },
        size: { width: 25, height: 25 },
        moveRate: 500 / 1000    // pixels per millisecond
    });

    let PlayerIcon = {
        imageSrc: 'assets/blob.png',
        center: { x: graphics.canvas.width / 2, y:  12.5 },
        size: { width: 25, height: 25 },
        rotation: 0
    }

    PlayerIcon.image = new Image();
    PlayerIcon.image.src = PlayerIcon.imageSrc;


    let mushroom = objects.Mushroom({
        imageSrc: 'assets/mushroom1.png',
        center: { x: 100, y: 100 },
        size: { width: 25, height: 25}
    })

    function processInput(elapsedTime) {
        myKeyboard.update(elapsedTime);
        // myMouse.update(elapsedTime);
    }

    function update(elapsedTime) {
        
        // Update Score
        myText.setText(`Score: ${MyGame.score}`);
        
        // Shot Updates
        MyGame.player.updateShotStatus(elapsedTime); // Update Shot Timer
        for (let shot in MyGame.shots) {
            const curr_y = MyGame.shots[shot].y
            MyGame.shots[shot].y = curr_y - 10;
        }
        const collisions = MyGame.player.checkForShotCollision()
        if (Object.keys(collisions).length > 0) {
            for (const collision in collisions) {
                MyGame.shots = MyGame.shots.filter(function( obj ) {
                    return obj != collisions[collision]["shot"];
                });

                const collision_object = collisions[collision]["object"];

                if (collision_object.type == "mushroom") {
                    collision_object.updateState();

                    
                }
                console.log(collisions[collision]["object"].type)
                

            }
        }

        // Update Object state

        // Update Lives
    }

    function render() {
        graphics.clear();
        renderer.Player.render(MyGame.player);

        for (mushroom in MyGame.mushrooms) {
            renderer.Mushroom.render(MyGame.mushrooms[mushroom]);
        }
        
        
        renderer.Text.render(myText);
        renderer.Player.render(MyGame.PlayerIcon)

        
        for (let i = 0; i < MyGame.lives; i++) {
            MyGame.graphics.drawTexture(PlayerIcon.image, {x: PlayerIcon.center.x + (i * (PlayerIcon.size.width + 10)), y: PlayerIcon.center.y}, PlayerIcon.rotation, PlayerIcon.size);
        }

        for (let shot in MyGame.shots) {
            MyGame.graphics.drawShot(MyGame.shots[shot]);
        }
    }

    function gameLoop(time) {
        let elapsedTime = time - lastTimeStamp;
        lastTimeStamp = time;

        processInput(elapsedTime);
        update(elapsedTime);
        render();

        if (!cancelNextRequest) {
            requestAnimationFrame(gameLoop);
        }
    }

    function initialize() {
        myKeyboard.register(83, player.moveDown);
        myKeyboard.register(87, player.moveUp);
        myKeyboard.register(65, player.moveLeft);
        myKeyboard.register(68, player.moveRight);
        myKeyboard.register(32, player.shoot);
        myKeyboard.register(27, function() {
            //
            // Stop the game loop by canceling the request for the next animation frame
            cancelNextRequest = true;
            //
            // Then, return to the main menu
            game.showScreen('main-menu');
        });

        MyGame.lives = 3
        MyGame.shots = []
        
        
        MyGame.render.push(MyGame.PlayerIcon)
        // Build Mushroom Grid
        MyGame.mushroomGrid = [];
        for (let i = 0; i < 40; i++) {
            MyGame.mushroomGrid.push([]);
            for (let j = 0; j < 40; j++) {
                MyGame.mushroomGrid[i].push(0)
            }
        }



        // Generate initial 20 Mushrooms
        MyGame.mushrooms = []
        for (let i = 0; i < 20; i++) {

            let placed = false
            while (!placed) {
                const rand_x = Math.floor(Math.random() * 39) + 1;
                let rand_y = 0;
                while (rand_y < 4) {
                    rand_y = Math.floor(Math.random() * 34) + 1;
                }
                
                
                let mushroom = objects.Mushroom({
                    imageSrc: 'assets/mushroom1.png',
                    center: { x: (25 * rand_x) - 12.5, y: (25 * rand_y) - 12.5},
                    size: { width: 25, height: 25}
                })
                
                const distance = Math.sqrt(Math.pow(Math.abs(player.center.x - mushroom.center.x), 2) + Math.pow(Math.abs(player.center.y - mushroom.center.y), 2))

                if (MyGame.mushroomGrid[rand_y][rand_x] == 0 && distance > 2 * mushroom.size.width) {
                    MyGame.mushrooms.push(mushroom);
                    MyGame.mushroomGrid[rand_y][rand_x] = mushroom;
                    placed = true;
                }

            }
            
        }


        input.Keyboard = myKeyboard;
        MyGame.player = player;
        MyGame.PlayerIcon = PlayerIcon;
    }

    function run() {
        lastTimeStamp = performance.now();
        cancelNextRequest = false;
        requestAnimationFrame(gameLoop);
    }

    return {
        initialize : initialize,
        run : run
    };

}(MyGame.game, MyGame.objects, MyGame.render, MyGame.graphics, MyGame.input));
