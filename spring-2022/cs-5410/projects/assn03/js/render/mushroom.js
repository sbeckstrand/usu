MyGame.render.Mushroom = (function(graphics) {
    'use strict';

    function render(spec) {
        if (spec.imageReady) {
            graphics.drawTexture(spec.image, spec.center, 0,  spec.size);
        }
    }

    return {
        render: render
    };
}(MyGame.graphics));