MyGame.graphics = (function() {
    'use strict';
    let canvas = document.getElementById('game-canvas');
    let context = canvas.getContext('2d');

    function clear() {
        context.clearRect(0, 0, canvas.width, canvas.height);

        context.fillStyle = "black";
        context.fillRect(0, 0, canvas.width, canvas.height);
    }

    function drawTexture(image, center, rotation, size) {
        context.save();

        context.translate(center.x, center.y);
        context.rotate(rotation);
        context.translate(-center.x, -center.y);

        context.drawImage(
            image,
            center.x - size.width / 2,
            center.y - size.height / 2,
            size.width, size.height);

        context.restore();
    }

    function drawSubTexture(image, center, rotation, start, subSize, size) {
        context.save();

        context.translate(center.x, center.y);
        context.rotate(rotation);
        context.translate(-center.x, -center.y);

        context.drawImage(
            image,
            start.x,
            start.y,
            subSize.width,
            subSize.height,
            center.x - size.width / 2,
            center.y - size.height / 2,
            size.width, size.height);

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

    function drawShot(spec) {
        context.save();

        context.lineWidth = 5;

        context.beginPath();
        context.moveTo(spec.x, spec.y);
        context.lineTo(spec.x, spec.y + 15)
        context.strokeStyle = "red";
        
        context.stroke();

        context.restore();
    }

    let api = {
        get canvas() { return canvas; },
        clear: clear,
        drawTexture: drawTexture,
        drawSubTexture: drawSubTexture,
        drawText: drawText,
        drawShot: drawShot
    };

    return api;
}());