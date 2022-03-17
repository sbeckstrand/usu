MyGame.screens['game-play'] = (function(game, objects, renderer, graphics, input) {
    'use strict';

    let lastTimeStamp = performance.now();
    let cancelNextRequest = true;

    let myKeyboard = input.Keyboard();
    // let myMouse = input.Mouse();

    // let myText = objects.Text({
    //     text: 'This is a test',
    //     font: '32pt Arial',
    //     fillStyle: 'rgba(255, 0, 0, 1)',
    //     strokeStyle: 'rgba(0, 0, 0, 1)',
    //     position: { x: 50, y: 100 }
    // });

    let player = objects.Player({
        imageSrc: 'assets/blob.png',
        center: { x: graphics.canvas.width / 2, y: graphics.canvas.height / 2 },
        size: { width: 20, height: 20 },
        moveRate: 500 / 1000    // pixels per millisecond
    });

    function processInput(elapsedTime) {
        myKeyboard.update(elapsedTime);
        // myMouse.update(elapsedTime);
    }

    function update() {
        
    }

    function render() {
        graphics.clear();
        renderer.Player.render(MyGame.player);
        // renderer.Text.render(myText);
    }

    function gameLoop(time) {
        let elapsedTime = time - lastTimeStamp;
        lastTimeStamp = time;

        processInput(elapsedTime);
        update();
        render();

        if (!cancelNextRequest) {
            requestAnimationFrame(gameLoop);
        }
    }

    function initialize() {
        myKeyboard.register('s', player.moveDown);
        myKeyboard.register('w', player.moveUp);
        myKeyboard.register('a', player.moveLeft);
        myKeyboard.register('d', player.moveRight);
        myKeyboard.register('Space', player.shoot);
        myKeyboard.register('Escape', function() {
            //
            // Stop the game loop by canceling the request for the next animation frame
            cancelNextRequest = true;
            //
            // Then, return to the main menu
            game.showScreen('main-menu');
        });

        let canvas = document.getElementById('id-canvas');
        // let mouseCapture = false;
        // myMouse.register('mousedown', function(e, elapsedTime) {
        //     mouseCapture = true;
        //     player.moveTo({ x : e.clientX - canvas.offsetLeft, y : e.clientY - canvas.offsetTop });
        // });

        // myMouse.register('mouseup', function(e, elapsedTime) {
        //     mouseCapture = false;
        // });

        // myMouse.register('mousemove', function(e, elapsedTime) {
        //     if (mouseCapture) {
        //         player.moveTo({ x : e.clientX - canvas.offsetLeft, y : e.clientY - canvas.offsetTop });
        //     }
        // });

        input.Keyboard = myKeyboard;
        MyGame.player = player;
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
