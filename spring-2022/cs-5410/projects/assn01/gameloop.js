// *** Initialization *** 

// Event Constructor
class GameLoopEvent {
    constructor(name, interval, executionCount ) {
        this.name = name
        this.interval = interval
        this.remainingTime = interval
        this.executionCount = executionCount
    }
}

// Event Creation Handler
function eventFormHandler(event) {
    // Prevent page from reloading on submit. 
    // Submitting instead of using generic button type allows for easierfrontend validation
    event.preventDefault();

    // Get the form field values
    const name = document.getElementById("name").value
    const interval = document.getElementById("interval").value
    const executionCount = document.getElementById("executionCount").value

    // Create the new event
    const newEvent = new GameLoopEvent(name, interval, executionCount)
    events.push(newEvent)
    
    // Clear the form
    document.getElementById("name").value = ""
    document.getElementById("interval").value = ""
    document.getElementById("executionCount").value = ""
}



// Add Event Listener
const form = document.getElementById("eventForm")
form.addEventListener('submit', eventFormHandler)
let events = []

// Start GameLoop
const stream = document.getElementById('eventStream')
let prevTime = performance.now()
gameLoop(prevTime)







// *** Game Loop ***

// Primary Game Loop Function. Runs in a loop once the page loads. 
function gameLoop(timeStamp) {
    const elapsedTime = timeStamp - prevTime;
    update(elapsedTime);
    render();
    prevTime = timeStamp
    requestAnimationFrame(gameLoop);

}

// Update function. Checks if there are any events and if so, it updates 
// them to reduce the amount of time running before the event should be fired. It also checks which events should have their counter decreased or be removed entirely
function update(elapsedTime) {
    // This is where active events are updated
    events.forEach(function (e) {
        
        
        if (e.remainingTime <= 0) {
            e.executionCount -= 1
            e.remainingTime = e.interval
        }

        if (e.executionCount <= 0) {
            events = events.filter(function (obj) {
                return obj.name !== e.name;
            })
        }

        e.remainingTime = e.remainingTime - elapsedTime
        
    })
    
}

// Render function. This function checks if there are any events ready to be triggered. 
// If so, it outputs the event name and how many remaining times the event should be triggered.
function render() {
    
    events.forEach(function (e) {
        if (e.remainingTime <= 0 && e.executionCount >= 0) {
            // 
            const node = document.createElement("div")
            const text = document.createTextNode(`Event: ${e.name} (${e.executionCount - 1} remaining)\n`)
            node.appendChild(text)
            stream.appendChild(node)

            stream.scrollTop = stream.scrollHeight
        }

        

        
    })
}


