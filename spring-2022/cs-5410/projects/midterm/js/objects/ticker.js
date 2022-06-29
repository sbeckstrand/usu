MyGame.objects.Ticker = function(spec) {
    'use strict';
    let direction = 'left';
    let canTrigger = true;

    function update(diff) {
        let distance = spec.moveRate * diff;
        if (direction == 'left') {
            if (spec.center.x + distance + (spec.size.x / 2) >= MyGame.bar.center.x + (MyGame.bar.size.x / 2)) {
                spec.center.x = MyGame.bar.center.x + (MyGame.bar.size.x / 2) - (spec.size.x / 2)
                direction = 'right';
            } else {
                spec.center.x += distance;
            }
        } else {
            if (spec.center.x - distance - (spec.size.x / 2) <= MyGame.bar.center.x - (MyGame.bar.size.x / 2)) {
                spec.center.x = MyGame.bar.center.x - (MyGame.bar.size.x / 2) + (spec.size.x / 2)
                direction = 'left';
            } else {
                spec.center.x -= distance;
            }
        }

        if (Object.keys(MyGame.input.Keyboard.keys).length == 0) {
            canTrigger = true;
        }

    }

    function trigger() {
        if (!MyGame.game_over) {
            if (canTrigger) {
                checkForZone();
                canTrigger = false;
            }
        }
    }

    // Check if ticker is in zone.
    function checkForZone() {
        if (spec.center.x > MyGame.zone.center.x - (MyGame.zone.size.x / 2) && spec.center.x < MyGame.zone.center.x + (MyGame.zone.size.x / 2)) {
            MyGame.score += 100 * MyGame.multiplier;
            if (MyGame.stage < 5) {
                MyGame.stage += 1;
            } else {
                if (MyGame.bonus < 2) {
                    MyGame.bonus += 0.01;
                } 
            }
        } else {
            MyGame.game_over = true;
        }
    }

    let api = {
        update: update,
        trigger: trigger,
        get center() { return spec.center },
        get size() { return spec.size },
        get fill() { return spec.fill },
        get stroke() { return spec.stroke }
    }

    return api;
}