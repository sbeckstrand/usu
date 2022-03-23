// Render Flea Object
MyGame.render.Flea = (function(graphics) {
    'use strict';

    function render(spec) {
        if (spec.imageReady) {
            graphics.drawSubTexture(spec.image, spec.center, 0, spec.start, {width: 9, height: 8}, spec.size);
        }
    }

    return {
        render: render
    };
}(MyGame.graphics));