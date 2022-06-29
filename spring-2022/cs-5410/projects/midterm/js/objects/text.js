// Create text object. 
MyGame.objects.Text = function(spec) {
    'use strict';

    let rotation = 0;
    let text = spec.text;

    function setText(new_text) {
        text = new_text;
    }

    let api = {
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
