MyGame.screens['controls'] = (function(game, input, player) {
    'use strict';
    
    function initialize() {
        const inputFields = document.getElementById("input-form").getElementsByTagName('input')
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
                    if (temp_controls[actions[action]][i].substring(0,3) == "Key") {
                        document.getElementById(id_val).value=temp_controls[actions[action]][i][3];
                    } else {
                        document.getElementById(id_val).value=temp_controls[actions[action]][i];
                    }
                    
                } else {
                    document.getElementById(id_val).placeholder="N/A"
                }
            }
        }

        // Add Event Listeners to update field based on input
        for (let field in inputFields) {
            if (typeof inputFields[field] == 'object') {

                inputFields[field].addEventListener(
                    'keydown',
                    function(e) {
                        console.log(e)
                        e.preventDefault();

                        let input_val = "";
                        if (e.code.substring(0,3) == "Key") {
                            input_val = e.code[3];
                        } else {
                            input_val = e.code;
                        }

                        console.log(inputFields[field].value)

                        // Check for duplicates
                        for (let field in inputFields) {
                            if (typeof inputFields[field] == 'object') {
                                if (inputFields[field].value == input_val) {
                                    inputFields[field].value = ""
                                }
                            }
                        }

                        this.value = input_val;
                        
                        const saveButton = document.getElementById('controls-save');
                        saveButton.disabled = !(readyToSave());
                        console.log(readyToSave());

                        
                    }
                )
            }
        }

        function readyToSave() {
            let ready = true;

            if (document.getElementById('moveUp-p').value == "") {
                ready = false;
            }

            if (document.getElementById('moveDown-p').value == "") {
                ready = false;
            }

            if (document.getElementById('moveLeft-p').value == "") {
                ready = false;
            }

            if (document.getElementById('moveRight-p').value == "") {
                ready = false;
            }

            if (document.getElementById('shoot-p').value == "") {
                ready = false;
            }

            console.log(document.getElementById('moveUp-p').value);
            console.log(document.getElementById('moveDown-p').value);
            console.log(document.getElementById('moveLeft-p').value);
            console.log(document.getElementById('moveRight-p').value);
            console.log(document.getElementById('shoot-p').value);

            return ready;
        }

        document.getElementById('controls-back').addEventListener(
            'click',
            function() { game.showScreen('main-menu'); 
        });

        document.getElementById('controls-save').addEventListener(
            'click',
            function() { 
                input.Keyboard.deregisterAll()

                let controls = {}
                for (let field in inputFields) {
                    if (typeof inputFields[field] == 'object') {
                        const id_val = inputFields[field].id.split("-")[0];
                        let input_val = "";

                        if (inputFields[field].value.length == 1) {
                            input_val = "Key" + inputFields[field].value
                        } else {
                            input_val = inputFields[field].value;
                        }
                        
                        if (input_val != "") {
                            if (id_val == "moveUp") {
                                input.Keyboard.register(input_val, MyGame.player.moveUp);
                            } else if (id_val == "moveLeft") {
                                input.Keyboard.register(input_val, MyGame.player.moveLeft);
                            } else if (id_val == "moveDown") {
                                input.Keyboard.register(input_val, MyGame.player.moveDown);
                            } else if (id_val == "moveRight") {
                                input.Keyboard.register(input_val, MyGame.player.moveRight);
                            } else if (id_val == "shoot") {
                                input.Keyboard.register(input_val, MyGame.player.shoot);
                            }

                            controls[input_val] = id_val;
                        }

                        
                    }
                }
                localStorage.controls = JSON.stringify(controls);
                document.getElementById('controls-save').classList.remove('btn-outline-danger');
                document.getElementById('controls-save').classList.add('btn-outline-success');
                document.getElementById('controls-save').disabled = true;
            }
        );
    }
    
    function run() {
        //
        // I know this is empty, there isn't anything to do.
    }

    
    return {
        initialize : initialize,
        run : run
    };
}(MyGame.game, MyGame.input, MyGame.player));
