// --------------------------------------------------------------
//
// Renders a Logo object.
//
// spec = {
//    image: ,
//    center: { x: , y: },
//    size: { width: , height: }
// }
//
// --------------------------------------------------------------

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
