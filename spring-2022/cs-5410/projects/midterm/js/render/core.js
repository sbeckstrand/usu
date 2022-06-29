// Used to render a full image or a sub portion of an image
MyGame.graphics = (function() {
    'use strict';
    let canvas = document.getElementById('game-canvas');
    let context = canvas.getContext('2d');

    function clear() {
        context.clearRect(0, 0, canvas.width, canvas.height);

        context.fillStyle = "rgb(48, 50, 54)";
        context.fillRect(0, 0, canvas.width, canvas.height);
    }

    function drawRectangle(rect) {
        context.save();
        context.translate(rect.center.x, rect.center.y );
        context.rotate(rect.rotation);
        context.translate(-rect.center.x, -rect.center.y);
        
        context.fillStyle = rect.fill;
        context.fillRect(rect.center.x - rect.size.x / 2, rect.center.y - rect.size.y / 2, rect.size.x, rect.size.y);
        
        context.strokeStyle = rect.stroke;
        context.strokeRect(rect.center.x - rect.size.x / 2, rect.center.y - rect.size.y / 2, rect.size.x, rect.size.y);

        context.restore();
    }

    function drawText(spec) {
        context.save();

        context.font = spec.font;
        context.fillStyle = spec.fillStyle;
        context.strokeStyle = spec.strokeStyle;
        context.textBaseline = 'top';

        context.translate(spec.position.x, spec.position.y);
        context.rotate(spec.rotation);
        context.translate(-spec.position.x, -spec.position.y);


        context.fillText(spec.text, spec.position.x, spec.position.y);
        context.strokeText(spec.text, spec.position.x, spec.position.y);

        context.restore();
    }

    let api = {
        get canvas() { return canvas; },
        clear: clear,
        drawRectangle: drawRectangle,
        drawText: drawText
    };

    return api;
}());