MyGame.screens['main-menu'] = (function(game) {
    'use strict';
    
    function initialize() {
        // Setup each of menu events for the screens
        document.getElementById('new-game-btn').addEventListener(
            'click',
            function() {
                game.showScreen('game-play'); 
            });
        
        document.getElementById('high-scores-btn').addEventListener(
            'click',
            function() { game.showScreen('high-scores'); });
        
        document.getElementById('controls-btn').addEventListener(
            'click',
            function() { game.showScreen('controls'); });
        
        document.getElementById('credits-btn').addEventListener(
            'click',
            function() { game.showScreen('credits'); });
    }
    
    function run() {
        //
        // I know this is empty, there isn't anything to do.
    }
    
    return {
        initialize : initialize,
        run : run
    };
}(MyGame.game));
