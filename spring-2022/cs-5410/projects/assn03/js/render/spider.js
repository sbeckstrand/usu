// Render Spider Object
MyGame.render.Spider = (function(graphics) {
    'use strict';

    function render(spec) {
        if (spec.imageReady) {
            graphics.drawSubTexture(spec.image, spec.center, 0, spec.start, {width: 16, height: 8}, spec.size);
        }
    }

    return {
        render: render
    };
}(MyGame.graphics));