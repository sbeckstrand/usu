// Render the Player object and extra lives
MyGame.render.Player = (function(graphics) {
    'use strict';

    function render(spec) {
        if (spec.imageReady) {
            graphics.drawSubTexture(spec.image, spec.center, spec.rotation, spec.start, {width: 8, height: 8}, spec.size);
        }
    }

    return {
        render: render
    };
}(MyGame.graphics));
