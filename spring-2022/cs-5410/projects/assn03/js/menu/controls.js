MyGame.screens['controls'] = (function(game, input) {
    'use strict';
    
    function initialize() {
        let curr_controls = input.Keyboard.handlers;
        let temp_controls = {
            'moveUp': [],
            'moveLeft': [],
            'moveDown': [],
            'moveRight': [],
            'shoot': []
        }

        const actions = ['moveUp', 'moveLeft', 'moveDown', 'moveRight', 'shoot']

        // Read current control settings
        for (let control in curr_controls) {
            for (const action in actions) {
                if (curr_controls[control].name == actions[action]) {
                    temp_controls[actions[action]].push(control);
                }
            }
        }

        // Render Control Settings
        for (const action in actions) {
            
            for (let i = 0; i <= 1; i++) {
                let id_val = actions[action];
                if (i == 0 ? id_val += "-p" : id_val += "-s" )

                if (typeof temp_controls[actions[action]][i] != 'undefined') {
                    console.log(actions[action])
                    document.getElementById(id_val).placeholder=temp_controls[actions[action]][i]
                } else {
                    document.getElementById(id_val).placeholder="N/A"
                }
            }
        }




        document.getElementById('controls-back').addEventListener(
            'click',
            function() { game.showScreen('main-menu'); 
        });

        document.getElementById('controls-save').addEventListener(
            'click',
            function() { saveControls(); 
        });
    }
    
    function run() {
        //
        // I know this is empty, there isn't anything to do.
    }

    function saveControls() {
        console.log("blah");
    }
    
    return {
        initialize : initialize,
        run : run
    };
}(MyGame.game, MyGame.input));
