MyGame.render.Rect = (function(graphics) {
    'use script';

    function render(spec) {
        graphics.drawRectangle(spec);
    }

    return {
        render: render
    };
}(MyGame.graphics));