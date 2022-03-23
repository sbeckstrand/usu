function buildCentipede() {
    MyGame.centipede = [];
    const randDirection = Math.round(Math.random());
    let direction = (randDirection == 0 ? "left" : "right");
    for (let i = 0; i < 15; i++) {

        let start_pos_X = 0;
        let startX = 0;
        let startY = 16;
        let increment = 0
        if (direction == "left") {
            start_pos_X = 37;
            increment = -1;
        } else {
            start_pos_X = 3
            increment = 1;
            startX = 32;
        }

        let centSeg = {};
        let head = false;
        if (i == 14) {
            head = true;
        }
        centSeg = MyGame.objects.Centipede({
            imageSrc: 'assets/centipede.png',
            center: { x: (25 * start_pos_X) + (i * increment * 25) - 12.5, y: (25 * 3) - 12.5},
            size: {width: 25, height: 25},
            moveRate: 300 / 1000,
            head: head,
            direction: direction,
            pathX: direction,
            pathY: "down",
            id: i,
            spriteCount: 8,
            spriteTime: [250, 250, 250, 250, 250, 250, 250, 250],
            startX: startX,
            startY: startY
        })

        MyGame.centipede.push(centSeg);
        
    }
}
                