// Render Centipede Object
MyGame.render.Centipede = (function(graphics) {
    'use strict';

    function render(spec) {
        if (spec.imageReady) {
            graphics.drawSubTexture(spec.image, spec.center, 0, spec.getStart(), {width: 8, height: 8}, spec.size);
        }
    }

    return {
        render: render
    };
}(MyGame.graphics));