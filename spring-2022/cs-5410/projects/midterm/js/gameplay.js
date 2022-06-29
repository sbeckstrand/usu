MyGame.screens['game-play'] = (function(game, objects, renderer, graphics, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;

    let myKeyboard = input.Keyboard();

    let myText = objects.Text({
        text: `Score: ${MyGame.score}  Multiplier: ${MyGame.multiplier}`,
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
        position: { x: MyGame.graphics.canvas.width / 3, y: MyGame.graphics.canvas.height / 2 }
    })

    MyGame.bar = objects.Rect({
        center: { x: 500, y: 400 },
        size: { x: 500, y: 100 },
        fill: 'rgb(0, 0, 255)',
        stroke: 'rgb(0, 0, 0)'
    });

    MyGame.zone = objects.Rect({
        center: { x: MyGame.bar.center.x, y: MyGame.bar.center.y },
        size: { x: 400, y: MyGame.bar.size.y },
        fill: 'rgb(0, 255, 0)',
        stroke: 'rgb(0, 0, 0)'
    })

    MyGame.ticker = objects.Ticker({
        center: { x: MyGame.bar.center.x, y: MyGame.bar.center.y},
        size: { x: 8, y: MyGame.bar.size.y },
        fill: 'rgb(255, 255, 0)',
        stroke: 'rbg(0, 0, 0)',
        moveRate: 500 / 1000
    })

    let gameOverTimer = 5000;
    let scoreSet = false;
    

    function processInput(elapsedTime) {
        myKeyboard.update(elapsedTime);
        // myMouse.update(elapsedTime);
    }

    function update(elapsedTime) {
        // Update Multiplier: 
        MyGame.multiplier = 1 + ((MyGame.stage - 1) / 10) + MyGame.bonus
        // Update Score
        myText.setText(`Score: ${MyGame.score}  Multiplier: ${Math.round(MyGame.multiplier * 100) / 100}`);
        
        // Update Zone
        MyGame.zone.update();

        // update Ticker
        MyGame.ticker.update(elapsedTime);

        // Update High Scores
        if (MyGame.game_over) {
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

        // Player Collisions

        if (MyGame.game_over) {
            gameOverTimer -= elapsedTime;
        }

    }

    function render() {
        graphics.clear();

        // Render Text
        renderer.Text.render(myText);

        // Render Bar
        renderer.Rect.render(MyGame.bar);

        // Render Zone
        renderer.Rect.render(MyGame.zone);

        // Render Ticket
        renderer.Rect.render(MyGame.ticker);
        
        if (MyGame.game_over) {
            renderer.Text.render(gameOverText);
        }

        

        if (gameOverTimer <= 0) {
            cancelNextRequest = true;
            game.showScreen('main-menu');
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

        myKeyboard.register('Space', MyGame.ticker.trigger);
        
        myKeyboard.register('Escape', function() {
            //
            // Stop the game loop by canceling the request for the next animation frame
            cancelNextRequest = true;
            //
            // Then, return to the main menu
            game.showScreen('main-menu');
        });

        gameOverTimer = 3000;
        scoreSet = false;
        MyGame.game_over = false;
        MyGame.score = 0;
        MyGame.stage = 1;
        MyGame.bonus = 0;

        input.Keyboard = myKeyboard;
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
