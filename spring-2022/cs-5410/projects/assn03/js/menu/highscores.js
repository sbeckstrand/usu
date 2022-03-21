MyGame.screens['high-scores'] = (function(game) {
    'use strict';
    
    function initialize() {

        document.getElementById('high-scores-back').addEventListener(
            'click',
            function() { game.showScreen('main-menu'); });
    }
    
    function updateScores() {
        let highScoreDiv = document.getElementById('high-scores');

        if (!(document.getElementById('noScoresId') == null)) {
            const noScoresP = document.getElementById('noScoresId');
            noScoresP.remove();
        } else {
            while(highScoreDiv.childNodes[3].firstChild) {
                highScoreDiv.childNodes[3].removeChild(highScoreDiv.childNodes[3].firstChild);
            }
        }

        if (typeof localStorage.highScores == 'undefined') {
            let noScoresPara = document.createElement('p');
            noScoresPara.appendChild(document.createTextNode("No Scores!"));
            noScoresPara.setAttribute('id', 'noScoresId')
            highScoreDiv.insertBefore(noScoresPara, highScoreDiv.childNodes[3]);
        } else {
            const scores = JSON.parse(localStorage.highScores);
            const list = highScoreDiv.childNodes[3];
            for (const score in scores) {
                let scoreItem = document.createElement('li');
                scoreItem.appendChild(document.createTextNode(scores[score]));
                list.appendChild(scoreItem);
            }
        }
    }

    function run() {
        //
        // I know this is empty, there isn't anything to do.
    }
    
    return {
        initialize : initialize,
        updateScores: updateScores,
        run : run
    };
}(MyGame.game));
