flight(slc,lax).
flight(slc,jfk).
flight(lax,sfo).
flight(sfo,syd).
% Capture the airports visited on a path
tc(X,Y,L,[X|[Y|L]]) :- flight(X,Y).
tc(X,Y,L,NL) :- flight(X,W), tc(W, Y, [X|L], NL).