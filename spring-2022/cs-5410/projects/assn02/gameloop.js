// *** Initialization *** 

// Maze Constructor
class Maze {

    constructor(width, height) {
        this.width = width;
        this.height = height;
        this.cells = null;
        this.start = null;
        this.end = null;

        this.visited = 0;

        this.createCells()
    }

    // Create grid of cells
    createCells() {
        let gridCells = [];
        
        // Create a Grid of unvisted Cells
        for (let row = 0; row < this.height; row++) {
            // Create array for row
            let rowCells = []

            // Create Cells and fill row
            for (let column = 0; column < this.width; column++) {
                let cell = {
                    row: row,
                    column: column,
                    index: [row, column],
                    walls: [1,1,1,1], // UP/RIGHT/DOWN/LEFT
                    connections: [],
                    checked: false,
                    visited: false
                }

                rowCells.push(cell);    
            }

            // Append Row to grid
            gridCells.push(rowCells);
        }
        this.cells = gridCells;
        this.start = this.chooseStart();
        this.end = this.chooseEnd();
        this.frontier();
    }

    // Randomly choose start point
    chooseStart() {

        // Choose a starting point
        const randomTBorLR = Math.round(Math.random()); // 0 = top/bottom, 1 = left/right
        let startRow, startColumn;

        // Randomly chooses between top and bottom and any possible column
        if (randomTBorLR == 0) {
            startColumn = Math.floor(Math.random() * this.width);
            const rowChoices = [0, this.height -1];
            startRow = rowChoices[Math.round(Math.random())]; // 0 = top, 1 = bottom
        } 

        // Randomly chooese between left and right and any possible row. 
        else {
            startRow = Math.floor(Math.random() * this.height);
            const columnChoices = [0, this.width -1];
            startColumn = columnChoices[Math.round(Math.random())];
        }
        let startCell = this.cells[startRow][startColumn];
        
        return startCell;
    }

    // Method used to randomly choose an end cell that is on the oposite side of the start
    chooseEnd() {
        let endRow = -1;
        let endColumn = -1;
        if (this.start.row == 0) {
            endRow = this.height - 1;
        } else if (this.start.row == this.height - 1) {
            endRow = 0;
        } else if (this.start.column == 0) {
            endColumn = this.width - 1;
        } else if (this.start.column == this.width - 1) {
            endColumn = 0;
        }


        if (endRow == -1) {
            endRow = Math.floor(Math.random() * this.height);
        }

        if (endColumn == -1) {
            endColumn = Math.floor(Math.random() * this.width);
        }

        let endCell = this.cells[endRow][endColumn];
        return endCell;
    }

    // Frontier method used to queue unexplored neighboring cells to generate maze
    frontier() {
        this.start.checked = true;
        let frontierCells = [this.start];
        while (this.visited < this.width * this.height) {
            const currentCell = frontierCells[Math.floor(Math.random() * frontierCells.length)];
            const currentIndex = frontierCells.indexOf(currentCell);
            currentCell.visited = true;
            this.visited += 1;
            

            const neighbors = [0,0,0,0];
            // up
            if (currentCell.row != 0) {
                neighbors[0] = this.cells[currentCell.row - 1][currentCell.column];
            }

            // right
            if (currentCell.column != this.width -1) {
                neighbors[1] = this.cells[currentCell.row][currentCell.column + 1];
            }

            // down
            if (currentCell.row != this.height - 1) {
                neighbors[2] = this.cells[currentCell.row + 1][currentCell.column];
            }

            // left
            if (currentCell.column != 0) {
                neighbors[3] = this.cells[currentCell.row][currentCell.column - 1];
            }


             
            let possibleConnections = [];
            for (const neighbor in neighbors) {
                if (neighbors[neighbor] != 0) {
                    const currentNeighbor = neighbors[neighbor];
                if (currentNeighbor.checked == false) {

                    currentNeighbor.checked = true;
                    frontierCells.push(currentNeighbor);
                }

                if (currentNeighbor.visited) {
                    possibleConnections.push(currentNeighbor);
                }
                }
            }

            if (possibleConnections.length > 0) {
                const randomConnection = Math.floor(Math.random() * possibleConnections.length);
                const connectionCell = possibleConnections[randomConnection];
                const neighborIndex = neighbors.indexOf(connectionCell);

                
                connectionCell.connections.push(currentCell);
                currentCell.connections.push(connectionCell);

                
                if (neighborIndex == 0) {
                    currentCell.walls[0] = 0;
                    connectionCell.walls[2] = 0;
                } 
                else if (neighborIndex == 1) {
                    currentCell.walls[1] = 0;
                    connectionCell.walls[3] = 0;
                } 
                else if (neighborIndex == 2) {
                    currentCell.walls[2] = 0;
                    connectionCell.walls[0] = 0
                } 
                else if (neighborIndex == 3) {
                    currentCell.walls[3] = 0;
                    connectionCell.walls[1] = 0;
                }


            }
            
            frontierCells.splice(currentIndex, 1);

        }
    }

    

    // Get the shortest path from given node to the end. Uses breath first search
    getPath(start) {
        let queue = [];
        let explored = [];
        explored.push({
            parent: null, 
            row: start.row,
            col: start.column
        })
        queue.push({
            row: start.row, 
            col: start.column
        });

        while (queue.length > 0) {
            let current = queue.shift()
            
            if (current.row == this.end.row && current.col == this.end.column) {
                // Found end, is already in explored, no need to continue. 
                queue = [];
            }

            for (let i = 0; i < 4; i++) {
                if (this.cells[current.row][current.col].walls[i] == 0) {
                    let nRow = 0;
                    let nCol = 0;
                    let nParrent = current;
                    if (i == 0) {
                        nRow = current.row - 1;
                        nCol = current.col;
                    } 
                    else if (i == 1) {
                        nRow = current.row;
                        nCol = current.col + 1;
                    }
                    else if (i == 2) {
                        nRow = current.row + 1;
                        nCol = current.col;
                    } 
                    else if (i == 3) {
                        nRow = current.row;
                        nCol = current.col - 1;
                    }

                    if (explored.findIndex(cell => cell.row == nRow && cell.col == nCol) < 0) {
                        const neighbor = { parent: nParrent, row: nRow, col: nCol }
                        explored.push(neighbor);
                        queue.push(neighbor);
                    }
                }
            }
        }

        let path = [];
        let endCell = explored.find(cell => cell.row == this.end.row && cell.col == this.end.column)
        path.unshift(endCell);
        let currentParent = endCell.parent;
        while (currentParent != null) {
            path.unshift(currentParent);
            currentParent = currentParent.parent;
        }

        return path;
    }


}

// Game object used to track data and settings for the game
class Game {
    constructor(width, height) {
        // Primary Data Structures
        this.maze = new Maze(width, height);
        this.position = this.maze.start;
        this.visited = [this.position];
        this.mainPath = this.maze.getPath(this.position);
        this.currentPath = this.mainPath;
        this.events = [];
        
        // Scoring
        this.currentScore = 0;
        this.scores = [];
        this.scoreMultiplier = 0.0;
        this.noScore = false;
        this.remainingCheckpoints = this.mainPath.length - 1
        this.timeBous = 0;
        this.applyMultipliers();
        
        // Toggles
        this.complete = false;
        this.breadcrumbs = true;
        this.hints = false;
        this.displayPath = false;
        

        // Game Loop Data
        this.prevTime = 0;
        this.latestInput = null;
        this.keyLetGo = true;
        this.currentTime = 0;
        this.completeTime = 0;

        // Canvas
        this.canvas = document.getElementById('id-canvas');
        this.context = this.canvas.getContext('2d');
        
        // Assets
        this.assets = [];
        this.generateAssets();

        // Credits
        this.showCredits = false;
    }

    // Reset the game 
    resetValues() {
        this.position = this.maze.start;
        this.mainPath = this.maze.getPath(this.position);
        this.visited = [this.position];
        this.mainPath = this.maze.getPath(this.position);
        this.currentPath = this.mainPath;


        this.complete = false;
        this.breadcrumbs = true;
        this.hints = false;
        this.displayPath = false;

        this.currentTime = 0;
        this.completeTime = 0;

        this.currentScore = 0;
        this.noScore = false;
        this.scoreMultiplier = 0.0;
        this.remainingCheckpoints = this.mainPath.length - 1
        this.timeBous = 0;
        this.applyMultipliers();
        

    }

    // Apply multipliers to score based on toggled settings
    applyMultipliers() {
        this.scoreMultiplier += this.maze.width / 10;

        if (!breadcrumbs) {
            this.scoreMultiplier += 0.2;
        }

        if (this.hints) {
            this.scoreMultiplier -= 0.5
        }
    
    }

    // Load images
    generateAssets() {
        this.assets = [];
        const assetNames = ['background', 'person', 'trophy', 'dot', 'x'];

        for (let asset = 0; asset < assetNames.length; asset++) {
            let size = 0;
            let extention = '';
            
            if (assetNames[asset] == 'background') {
                size = 1000;
                extention = 'jpg';
            } else {
                size = 1000 / this.maze.width;
                extention = 'png';
            }

            let asset_image = {
                src: `assets/${assetNames[asset]}.${extention}`,
                center: { x: size / 2, y: size / 2},
                width: size,
                height: size,
                rotation: 0,
            }

            asset_image.image = new Image();
            asset_image.image.src = asset_image.src;

            this.assets.push(asset_image);
        }
    }

}




// Initialize Data
let game = new Game(10, 10);


// Input event listeners and handling
document.addEventListener('keydown', recordInput);
document.addEventListener('keyup', readyInput);


function readyInput(input) {
    game.keyLetGo = true;
}

// Start GameLoop
game.prevTime = performance.now();
gameLoop(game.prevTime);

// *** Game Loop ***
function gameLoop(timeStamp) {
    const elapsedTime = timeStamp - game.prevTime;
    processInput(elapsedTime);
    update(elapsedTime);
    render();
    game.prevTime = timeStamp
    requestAnimationFrame(gameLoop);

}

// Record Input
function recordInput(input) {
    const scrollingKeys = ["Space","ArrowUp","ArrowDown","ArrowLeft","ArrowRight"];
    if(scrollingKeys.indexOf(input.code) >= 0) {
        input.preventDefault();
    }
    if (game.keyLetGo) {
        if (game.latestInput == null) {
            game.latestInput = input;
            game.keyLetGo = false;
        }
    }
}

// Handle keyboard input
function processInput(elaspedTime) {
    if (game.latestInput) {
        const input = game.latestInput;
        
        game.latestInput = null;

        if (input.code == 'ArrowUp' || input.code == 'KeyW' || input.code == 'KeyI') {
            game.events.push({name: 'move', params: 'up'});
        }

        if (input.code == 'ArrowLeft' || input.code == 'KeyA' || input.code == 'KeyJ') {
            game.events.push({name: 'move', params: 'left'})
        }

        if (input.code == 'ArrowDown' || input.code == 'KeyS' || input.code == 'KeyK') {
            game.events.push({name: 'move', params: 'down'})
        }

        if (input.code == 'ArrowRight' || input.code == 'KeyD' || input.code == 'KeyL') {
            game.events.push({name: 'move', params: 'right'})
        }

        if (input.code == 'KeyB') {
            game.events.push({name: 'toggle', params: 'breadcrumbs'});
        }

        if (input.code == 'KeyH') {
            game.events.push({name: 'toggle', params: 'hints'});
        }

        if (input.code == 'KeyP') {
            game.events.push({name: 'toggle', params: 'path'})
        }
    }   
}

function update(elapsedTime) {
    
    // Mark maze as complete if it is
    game.currentTime += performance.now() - game.prevTime;
    
    if (game.position.index == game.maze.end.index && !game.complete) {
        game.complete = true;
        
        game.completeTime = game.currentTime;

        game.speedBonus = (game.maze.width * 1000) - Math.floor(game.currentTime);
        if (game.speedBonus > 0) {
            game.currentScore += game.speedBonus;
        }

        game.scores.push({ score: game.currentScore, time: (game.currentTime / 1000).toFixed(3), size: `${game.maze.width} x ${game.maze.height}`})
        game.scores.sort((a,b) => (a.score < b.score) ? 1 : -1);

    }

    // Set score to 0 if path is enabled
    if (game.noScore) {
        game.scoreMultiplier *= 0;
    }

    // Event Handling
    if (game.events.length > 0) {
        const evt = game.events.pop();

        // Reset the page with the same maze
        if (evt.name == "reset_maze") {
            game.resetValues();
        }

        // Reset the page with a new maze
        if (evt.name == "new_maze") {
            game.maze = new Maze(evt.params[0],evt.params[1]);
            game.generateAssets();
            game.resetValues();
        }

        // Move Character based on keyboard input
        if (evt.name == "move") {
            if (!game.complete) {
                if (evt.params == "up") {
                    if (game.position.walls[0] == 0) {
                        game.position = game.maze.cells[game.position.row - 1][game.position.column];
                    }
                } 
                else if (evt.params == "right") {
                    if (game.position.walls[1] == 0) {
                        game.position = game.maze.cells[game.position.row][game.position.column + 1];
                    }
                }
                else if (evt.params == "down") {
                    if (game.position.walls[2] == 0) {
                        game.position = game.maze.cells[game.position.row + 1][game.position.column];
                    }
                }
                else if (evt.params == "left") {
                    if (game.position.walls[3] == 0) {
                        game.position = game.maze.cells[game.position.row][game.position.column -1];
                    }
                }

                game.currentPath = game.maze.getPath(game.position);
            }
        }

        // Add current position to visited if not already there.
        const currentCell = {row: game.position.row, column: game.position.column}
        if (game.visited.findIndex(coords => coords.row == currentCell.row && coords.column == currentCell.column) < 0 ) {
            game.visited.push(currentCell);
        }

        // Update Data based on keyboard input 
        if (evt.name == "toggle") {
            if (evt.params == "breadcrumbs") {
                game.breadcrumbs = !game.breadcrumbs;
                if (game.breadcrumbs) {
                    game.scoreMultiplier -= 0.2;
                } else {
                    game.scoreMultiplier = game.scoreMultiplier + 0.2;
                }
            }

            if (evt.params == "hints") {
                game.hints = !game.hints;
                if (game.hints) {
                    game.scoreMultiplier -= 0.5;
                } else {
                    game.scoreMultiplier += 0.5;
                }
            }

            if (evt.params == 'path') {
                game.displayPath = !game.displayPath;
                if (game.displayPath) {
                    game.noScore = true;
                }
            }
        }
    }

    // Update Scoring
    if (game.currentPath.length -1 < game.remainingCheckpoints ) {
        game.currentScore += 1000 * game.scoreMultiplier;
        game.remainingCheckpoints = game.currentPath.length - 1;
    }


}

function render() {

    const cellWidth = 1000 / game.maze.width;
    const cellHeight = 1000 / game.maze.height;

    // Update Score Details
    document.getElementById("scoreScale").innerHTML = `Score Scale:  ${game.scoreMultiplier.toFixed(1)}`;
    document.getElementById("gameSize").innerHTML = `* Size: <b>${game.maze.width} x ${game.maze.height}</b> : +${(game.maze.width / 10).toFixed(1)}`;
    document.getElementById("breadcrumbs").innerHTML = `* Breadcrumbs ${game.breadcrumbs ? '<b>enabled</b>: +0.0' : '<b>disabled</b>: +0.2'}`
    document.getElementById('hints').innerHTML = `* Hints ${game.hints ? '<b>enabled</b>: -0.5' : '<b>disabled</b>: +0.0'}`
    document.getElementById('fullPath').innerHTML =`* Full Path Used? ${game.noScore ? '<b>Yes</b>: *0.0' : '<b>No</b>: +0.0'}`
    
    // Update Duration
    if (game.complete) {
        document.getElementById("gameDuration").innerHTML = `<b>Duration:</b> ${(game.completeTime / 1000).toFixed(3)} (Completed!)`;
        document.getElementById("speedBonus").innerHTML = `<b>Speed Bonus:</b> ${game.speedBonus > 0 ? game.speedBonus : 0 }`;
        document.getElementById("totalScore").innerHTML = `<b>Total Score:</b> ${game.currentScore}`;

        // Update High Scores:
        const topScoreCount = (game.scores.length >=5 ? 5 : game.scores.length );
        
        const scoreTable = document.getElementById('scoreTable');

        while (scoreTable.firstChild) {
            scoreTable.removeChild(scoreTable.firstChild);
        }

        for (let i = 0; i < topScoreCount; i++) {
            const tableRow = document.createElement("tr");
            const scoreData = document.createElement("td");
            const timeData = document.createElement("td");
            const sizeData = document.createElement("td");
        
            scoreData.innerHTML = game.scores[i].score;
            timeData.innerHTML = game.scores[i].time;
            sizeData.innerHTML = game.scores[i].size;

            tableRow.appendChild(scoreData);
            tableRow.appendChild(timeData);
            tableRow.appendChild(sizeData);

            scoreTable.appendChild(tableRow);

        }
    } 
    else {
        document.getElementById("gameDuration").innerHTML = `<b>Duration:</b> ${(game.currentTime / 1000).toFixed(3)}`;
        document.getElementById("speedBonus").innerHTML = `<b>Speed Bonus:</b> 0`;
        document.getElementById("totalScore").innerHTML = `<b>Total Score:</b> 0`;
    }


    game.context.clearRect(0, 0, game.canvas.width, game.canvas.height);
    game.context.beginPath();
    
    // Draw Background
    const background = game.assets[0];
    game.context.drawImage(
        background.image,
        background.center.x - background.width / 2,
        background.center.y - background.height / 2,
        background.width, background.height
    );

    // Draw Player
    const player = game.assets[1];
    game.context.drawImage(
        player.image,
        (player.center.x - player.width / 2) + (player.width * game.position.column) + ((player.width / 10) / 2),
        (player.center.y - player.height / 2) + (player.height * game.position.row) + ((player.height / 10) / 2) ,
        player.width - (player.width / 10), player.height - (player.height / 10)
    )

    // Draw Trophy
    if (!game.complete) {
        const trophy = game.assets[2];
        game.context.drawImage(
            trophy.image,
            (trophy.center.x - trophy.width / 2) + (trophy.width * game.maze.end.column) + ((trophy.width / 5) / 2),
            (trophy.center.y - trophy.height / 2) + (trophy.height * game.maze.end.row) + ((trophy.height / 5) / 2),
            trophy.width - (trophy.width / 5), trophy.height - (trophy.height / 5)
        )
    }

    

    // Draw Hint
    if (game.hints) {
        if (game.position.index != game.maze.end.index) {
            const nextCell = game.currentPath[1];
            if (!(nextCell.row == game.maze.end.row && nextCell.col == game.maze.end.column)) {
                game.context.beginPath()
                game.context.arc(
                    (cellWidth * nextCell.col) + (cellWidth / 2),
                    (cellHeight * nextCell.row) + (cellHeight / 2),
                    (cellWidth / 5),
                    0, 
                    2 * Math.PI
                    );
                game.context.fill();
                game.context.fillStyle = "blue";
                
                game.context.stroke();
            }
        }
    }

    // Draw Path to Trophy
    if (game.displayPath) {
        if (game.position.index != game.maze.end.index) {
            for (let i = 1; i < game.currentPath.length - 1; i++) {
                const nextCell = game.currentPath[i];
                game.context.beginPath()
                game.context.strokeStyle = "yellow";
                game.context.arc(
                    (cellWidth * nextCell.col) + (cellWidth / 2),
                    (cellHeight * nextCell.row) + (cellHeight / 2),
                    (cellWidth / 5),
                    0, 
                    2 * Math.PI
                    );
                game.context.fill();
                game.context.fillStyle = "blue";
                
                game.context.stroke();
            }
        }
    }

    // Draw BreadCrumbs
    if (game.breadcrumbs) {
        const breadcrumb = game.assets[4];
        for (let i = 0; i < game.visited.length; i++) {
            const cell = game.visited[i];
            if (!(cell.row == game.position.row && cell.column == game.position.column)) {
                
                game.context.drawImage(
                    breadcrumb.image,
                    (breadcrumb.center.x - breadcrumb.width / 2) + (breadcrumb.width * cell.column) + ((breadcrumb.width / 1.5) / 2),
                    (breadcrumb.center.y - breadcrumb.height / 2) + (breadcrumb.height * cell.row) + ((breadcrumb.height / 1.5) / 2),
                    breadcrumb.width - (breadcrumb.width / 1.5), breadcrumb.height - (breadcrumb.height / 1.5)
                )
            }
        }
    }
    
    
    // Draw Maze
    for (let row = 0; row < game.maze.height; row++) {
        for (let column = 0; column < game.maze.width; column++) {

            const cell = game.maze.cells[row][column];
            game.context.lineWidth = 2;
            
            
            for (let i = 0; i < 5; i++) {
                game.context.moveTo(cellWidth * column, cellHeight * row);

                // Top
                if (cell.row != 0) {
                    if (cell.walls[0] == 1) {
                        game.context.lineTo(cellWidth * (column + 1), cellHeight * row);
                    } else {
                        game.context.moveTo(cellWidth * (column + 1), cellHeight * row);
                    } 
                }
                else {
                    game.context.moveTo(cellWidth * (column + 1), cellHeight * row);
                }
                
                // Right
                if (cell.column != game.maze.width - 1) {
                    if (cell.walls[1] == 1) {
                        game.context.lineTo(cellWidth * (column + 1), cellHeight * (row + 1));
                    } else {
                        game.context.moveTo(cellWidth * (column + 1), cellHeight * (row + 1));
                    }
                }
                else {
                    game.context.moveTo(cellWidth * (column + 1), cellHeight * (row + 1));
                }
            }
            
            
        }
    
    }
    
    // Draw line Strokes 
    game.context.strokeStyle = "black";
    
    game.context.stroke();


    // Show Credits if Toggled
    creditParent = document.getElementById("creditRow");

    while (creditParent.firstChild) {
        creditParent.removeChild(creditParent.firstChild);
    }

    if (game.showCredits) {
        const colDiv = document.createElement("div");
        colDiv.classList.add("col-auto")

        const creditHeader = document.createElement("h2");
        creditHeader.innerHTML = "Credits";

        const creditP = document.createElement("p")
        creditP.innerHTML = "Created By: Stephen Beckstrand"

        colDiv.appendChild(creditHeader);
        colDiv.appendChild(creditP);

        creditParent.appendChild(colDiv);
    }
  
}

