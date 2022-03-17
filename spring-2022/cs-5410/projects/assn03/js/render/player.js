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
            console.log("testing");
            graphics.drawTexture(spec.image, spec.center, spec.rotation, spec.size);
        }
    }

    return {
        render: render
    };
}(MyGame.graphics));
