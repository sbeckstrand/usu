MyGame.objects.Rect = function(spec) {
    'use strict';


    let size = spec.size;
    const stages = [
        {x: 400, y: spec.size.y},
        {x: 300, y: spec.size.y},
        {x: 200, y: spec.size.y},
        {x: 100, y: spec.size.y},
        {x: 50, y: spec.size.y}
    ]

    function update() {
        size = stages[MyGame.stage - 1]
    }

    let api = {
        update: update,
        get center() { return spec.center },
        get size() { return size },
        get fill() { return spec.fill },
        get stroke() { return spec.stroke }
    }

    return api;
}