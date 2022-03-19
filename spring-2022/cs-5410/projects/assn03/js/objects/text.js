// --------------------------------------------------------------
//
// Creates a Text object, with functions for managing state.
//
// spec = {
//    text: ,
//    font: ,
//    fillStyle: ,
//    strokeStyle: ,
//    position: { x: , y: }
// }
//
// --------------------------------------------------------------
MyGame.objects.Text = function(spec) {
    'use strict';

    let rotation = 0;
    let text = spec.text;

    function updateRotation(howMuch) {
        rotation += howMuch;
    }

    function setText(new_text) {
        text = new_text;
    }

    let api = {
        updateRotation: updateRotation,
        setText: setText,
        get rotation() { return rotation; },
        get position() { return spec.position; },
        get text() { return text; },
        get font() { return spec.font; },
        get fillStyle() { return spec.fillStyle; },
        get strokeStyle() { return spec.strokeStyle; }
    };

    return api;
}
