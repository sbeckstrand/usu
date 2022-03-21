MyGame.screens['game-play'] = (function(game, objects, renderer, graphics, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;

    let myKeyboard = input.Keyboard();

    let myText = objects.Text({
        text: `Score: ${MyGame.score}`,
        font: '24pt "Hubballi"',
        fillStyle: 'rgba(255, 255, 255, 1)',
        strokeStyle: 'rgba(0, 0, 0, 1)',
        position: { x: 20, y: 0 }
    });

    let gameOverText = objects.Text({
        text: "Game Over",
        font: '48pt "Hubballi"',
        fillStyle: 'rgba(255, 255, 255, 1)',
        strokeStyle: 'rgba(0, 0, 0, 1)',
        position: { x: MyGame.graphics.canvas.width / 3, y: MyGame.graphics.canvas.height / 3 }
    })

    let player = objects.Player({
        imageSrc: 'assets/sprites.png',
        center: { x: graphics.canvas.width / 2, y: graphics.canvas.height * 0.85 },
        startX: 0,
        startY: 80,
        size: { width: 25, height: 25 },
        moveRate: 300 / 1000,    // pixels per millisecond
        dead: false
    });

    let PlayerIcon = {
        imageSrc: 'assets/sprites.png',
        center: { x: graphics.canvas.width / 2, y:  12.5 },
        size: { width: 25, height: 25 },
        rotation: 0,
        startX: 0,
        startY: 80
    }

    PlayerIcon.image = new Image();
    PlayerIcon.image.src = PlayerIcon.imageSrc;

    let playerTimer = 3000;
    let fleaTimer = 1000;
    let spiderTimer = 1000;
    let scorpionTimer = 1000;
    let gameOverTimer = 5000;
    let scoreSet = false;

    function processInput(elapsedTime) {
        myKeyboard.update(elapsedTime);
        // myMouse.update(elapsedTime);
    }

    function update(elapsedTime) {
        
        // Update Score
        myText.setText(`Score: ${MyGame.score}`);
        
        // Shot Updates
        MyGame.player.updateShots(elapsedTime);
        MyGame.player.handleShotCollisions();

        // Player Collisions
        const collisions = MyGame.player.checkForPlayerCollision();

        if (!MyGame.player.dead) {
            if (collisions[1]) {
                MyGame.player.deathStatus(true);  

                let death = new Audio('assets/death.wav')
                death.play();

            }
        } else {
            playerTimer -= elapsedTime;
        }

        // Revive
        if (playerTimer <= 0) {
            if (MyGame.lives != 0) {
                MyGame.player.deathStatus(false);
                playerTimer = 5000;
                MyGame.lives -= 1;
                MyGame.player.moveTo({x: graphics.canvas.width / 2, y: graphics.canvas.height * 0.85});
                delete MyGame.spider;
                delete MyGame.flea;
            } else {
                MyGame.game_over = true;

               // Set High Score
               if (!scoreSet) {
                    if (typeof localStorage.highScores == "undefined" ) {
                        let scores = [];
                        scores.push(MyGame.score);
                        localStorage.highScores = JSON.stringify(scores);

                    } else if (JSON.parse(localStorage.highScores).length < 5) {
                        let scores = JSON.parse(localStorage.highScores);
                        scores.push(MyGame.score);
                        scores = scores.sort((a,b) => b -a); // Sort by score value, descending
                        localStorage.highScores = JSON.stringify(scores);
                    } else {
                        let scores = JSON.parse(localStorage.highScores);
                        scores.push(MyGame.score)
                        scores = scores.sort((a,b) => b -a);
                        scores.pop();
                        localStorage.highScores = JSON.stringify(scores);
                    }

                    scoreSet = true;
               }
            }
        }


        if (MyGame.game_over) {
            gameOverTimer -= elapsedTime;
        }

        // Flea
        if (fleaTimer > 0 && typeof MyGame.flea == "undefined") {
            fleaTimer -= elapsedTime;
        } else {
            MyGame.flea.update(elapsedTime);
        }

        if (fleaTimer <= 0) {
            const rand_x = Math.floor(Math.random() * 39) + 1;

            let flea = objects.Flea({
                imageSrc: 'assets/sprites.png',
                center: { x: (25 * rand_x) + 12.5, y: 50 + 12.5},
                size: { width: 25, height: 25},
                startX: 64,
                startY: 32,
                spriteCount: 4,
                spriteTime: [250, 250, 250, 250],
                moveRate: 300 / 1000
            })

            MyGame.flea = flea;
            fleaTimer = 1000;
        }

        // Scorpion
        if (scorpionTimer > 0 && typeof MyGame.scorpion == "undefined") {
            scorpionTimer -= elapsedTime;
        } else {
            MyGame.scorpion.update(elapsedTime);
        }

        if (scorpionTimer <= 0) {
            let rand_y = 0;
            const rand_x = Math.round(Math.random());
            let position_x = (rand_x == 0) ? 0 + 25 : graphics.canvas.width - 25;
            const direction = (rand_x == 0) ? "right" : "left";
            while (rand_y < 6) {
                rand_y = Math.floor(Math.random() * 30) + 1;
            }

            let scorpion = objects.Scorpion({
                imageSrc: 'assets/sprites.png',
                center: { x: position_x + 12.5, y: (25 * rand_y) + 25 + 12.5},
                size: { width: 50, height: 25},
                startX: 0,
                startY: 56,
                spriteCount: 4,
                spriteTime: [250, 250, 250, 250],
                moveRate: 150 / 1000,
                direction: direction
            })

            MyGame.scorpion = scorpion;
            scorpionTimer = 1000;
        }

        // Spider
        if (spiderTimer > 0 && typeof MyGame.spider == 'undefined') {
            spiderTimer -= elapsedTime;
        } else {
            MyGame.spider.update(elapsedTime);
        }

        if (spiderTimer <= 0) {
            let rand_y = 0;
            const rand_x = Math.round(Math.random());
            let position_x = (rand_x == 0) ? 0 + 25 : graphics.canvas.width - 25;
            const direction = (rand_x == 0) ? "right" : "left";
            while (rand_y < 26) {
                rand_y = Math.floor(Math.random() * 30) + 1;
            }

            let spider = objects.Spider({
                imageSrc: 'assets/sprites.png',
                center: { x: position_x, y: (25 * rand_y) + 25},
                size: {width: 50, height: 25},
                startX: 0,
                startY: 40,
                spriteCount: 8,
                spriteTime: [250, 250, 250, 250, 250, 250, 250, 250],
                moveRate: 200 / 1000,
                direction: [direction, "down"],
                tempDirection: direction

            })

            MyGame.spider = spider;
            spiderTimer = 1000;
        }
    }

    function render() {
        graphics.clear();

        // Render Player
        if (!MyGame.player.dead) {
            renderer.Player.render(MyGame.player);
        }
        

        // Render Mushrooms
        for (const mushroom in MyGame.mushrooms) {
            renderer.Mushroom.render(MyGame.mushrooms[mushroom]);
        }
        
        // Render Text
        renderer.Text.render(myText);

        if (MyGame.game_over) {
            renderer.Text.render(gameOverText);
        }

        if (gameOverTimer <= 0) {
            cancelNextRequest = true;
            game.showScreen('main-menu');
        }

        // Render Remaining Lives Indicator
        for (let i = 0; i < MyGame.lives; i++) {
            MyGame.graphics.drawSubTexture(PlayerIcon.image, {x: PlayerIcon.center.x + (i * (PlayerIcon.size.width + 10)), y: PlayerIcon.center.y}, PlayerIcon.rotation, {x: PlayerIcon.startX, y: PlayerIcon.startY}, {width: 8, height: 8}, PlayerIcon.size);
        }

        // Render Shots
        for (let shot in MyGame.shots) {
            MyGame.graphics.drawShot(MyGame.shots[shot]);
        }

        // Render Flea (if ready)
        if (typeof MyGame.flea != 'undefined') {
            renderer.Flea.render(MyGame.flea);
        }

        // Render Scorpion (if ready)
        if (typeof MyGame.scorpion != 'undefined') {
            renderer.Scorpion.render(MyGame.scorpion);
        }

        // Render Spider(if ready)
        if (typeof MyGame.spider != 'undefined') {
            renderer.Spider.render(MyGame.spider);
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
        // Register Controls
        if (typeof localStorage.controls == "undefined") {
            myKeyboard.register('KeyS', player.moveDown);
            myKeyboard.register('KeyW', player.moveUp);
            myKeyboard.register('KeyA', player.moveLeft);
            myKeyboard.register('KeyD', player.moveRight);
            myKeyboard.register('Space', player.shoot);
        } else {
            const controls = JSON.parse(localStorage.controls);

            for (const control in controls) {
                if (controls[control] == "moveUp") {
                    myKeyboard.register(control, player.moveUp);
                } else if (controls[control] == "moveLeft") {
                    myKeyboard.register(control, player.moveLeft);
                } else if (controls[control] == "moveDown") {
                    myKeyboard.register(control, player.moveDown);
                } else if (controls[control] == "moveRight") {
                    myKeyboard.register(control, player.moveRight);
                } else if (controls[control] == "shoot") {
                    myKeyboard.register(control, player.shoot);
                }
            }
        }
        
        myKeyboard.register('Escape', function() {
            //
            // Stop the game loop by canceling the request for the next animation frame
            cancelNextRequest = true;
            //
            // Then, return to the main menu
            game.showScreen('main-menu');
        });

        gameOverTimer = 5000;
        scoreSet = false;
        MyGame.game_over = false;
        MyGame.score = 0;
        MyGame.lives = 3;
        MyGame.shots = [];

        if (typeof MyGame.flea != "undefined") {
            delete MyGame.flea;
        }

        if (typeof MyGame.spider != "undefined") {
            delete MyGame.spider;
        }

        if (typeof MyGame.scorpion != "undefined") {
            delete MyGame.scorpion;
        }

        
        
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
                    imageSrc: 'assets/sprites.png',
                    center: { x: (25 * rand_x) - 12.5, y: (25 * rand_y) - 12.5},
                    size: { width: 25, height: 25},
                    startX: 64,
                    startY: 8,
                    x: rand_x,
                    y: rand_y
                })
                
                const distance = Math.sqrt(Math.pow(Math.abs(player.center.x - mushroom.center.x), 2) + Math.pow(Math.abs(player.center.y - mushroom.center.y), 2))

                if (MyGame.mushroomGrid[rand_y][rand_x] == 0 && distance > 2 * mushroom.size.width) {
                    MyGame.mushrooms.push(mushroom);
                    MyGame.mushroomGrid[rand_y][rand_x] = 1;
                    placed = true;
                }

            }
            
        }


        input.Keyboard = myKeyboard;
        console.log(player.dead);
        MyGame.player = player;
        console.log(MyGame.player.dead);
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
