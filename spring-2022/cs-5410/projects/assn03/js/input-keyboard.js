MyGame.input.Keyboard = function () {
    let that = {
        keys: {},
        handlers: {}
    };

    function keyPress(e) {
        if (e.keyCode == 32 && e.target == document.body) {
            e.preventDefault();
        }
        that.keys[e.code] = e.timeStamp;
    }

    function keyRelease(e) {
        delete that.keys[e.code];
    }

    that.update = function (elapsedTime) {
        for (let key in that.keys) {
            if (that.keys.hasOwnProperty(key)) {
                if (that.handlers[key]) {
                    that.handlers[key](elapsedTime);
                }
            }
        }
    };

    that.register = function (key, handler) {
        that.handlers[key] = handler;
    };

    that.deregisterAll = function() {
        const escHandler = that.handlers['Escape']
        that.handlers = {}
        that.handlers['Escape'] = escHandler
    }

    window.addEventListener('keydown', keyPress);
    window.addEventListener('keyup', keyRelease);

    return that;
};
