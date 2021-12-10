
% Print All items in a provided list
printList([H]) :- 
    write(H), 
    write('\n').
printList([H | T]) :- 
    write(H), write('\n'), 
    printList(T).

% Check if all items in one list are in another list. We use this to see if the list of provided rooms is in the list of rooms needed to reach the exit
inRoomList([H], L) :- 
    member(H,L).
inRoomList([H | T], L) :- 
    member(H, L), 
    inRoomList(T,L).

% Check if there is a valid path from one room to another. No cost requirement
findPath(Castle,FromRoom,ToRoom,RoomList,PathList) :- 
    room(Castle,FromRoom,ToRoom,_), 
    append(PathList, [FromRoom], UpdatedPathList),
    inRoomList(RoomList, UpdatedPathList),
    printList(UpdatedPathList).
findPath(Castle,FromRoom,ToRoom,RoomList,PathList) :- 
    room(Castle,FromRoom,PotentialRoom,_),
    append(PathList, [FromRoom], UpdatedPathList),
    findPath(Castle,PotentialRoom,ToRoom,RoomList,UpdatedPathList).

% Checks if there is a valid path from one room to another, but does have a cost requirement. 
findPath(Castle,FromRoom,ToRoom,MaxCost,TotalCost,PathList) :- 
    room(Castle,FromRoom,ToRoom,RoomCost), 
    append(PathList, [FromRoom], UpdatedPathList),
    UpdatedTotalCost is RoomCost + TotalCost,
    MaxCost >= UpdatedTotalCost,
    printList(UpdatedPathList).
findPath(Castle,FromRoom,ToRoom,MaxCost,TotalCost,PathList) :- 
    room(Castle,FromRoom,PotentialRoom,RoomCost),
    append(PathList, [FromRoom], UpdatedPathList),
    UpdatedTotalCost is RoomCost + TotalCost,
    findPath(Castle,PotentialRoom,ToRoom,MaxCost,UpdatedTotalCost,UpdatedPathList).


% This method takes a castle and a list of rooms as arguments. It checks if there is a path in the provided castle from the entrance to the exist that includes the rooms provided. 
solveRooms(Castle, L) :- 
    findPath(Castle,enter,exit,L,[]).

% This method is similar to the last, but instead of providing a list of rooms that have to be in the path, we check if the total cost of the rooms exceeds a limit that is provided. 
solveRoomsWithinCost(Castle, MaxCost) :- 
    findPath(Castle,enter,exit,MaxCost,0,[]).



