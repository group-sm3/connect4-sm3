=============================================================================
REMOTE PLAY MODULE
=============================================================================

Group: SM3 - CS 4398
Author: Anne Leach - AL1404
Date: 8/7/19

=============================================================================
ABOUT
=============================================================================

This module was designed in conjunction with the Connect 4 boardgame.  Having
experience writing socket programs for previous projects, I was the designated
remote play module writer.

Contains two projects: Client and Server.  Each launch a GUI from their main
in Client.view.CLientView and Server.view.ServerView.  From the menu the user
inputs a port number and ip address (client only).

A successful connection is noted in the message area in the client menu.

Our goal was to merge our code modules, Connect 4 and RemotePlay, to allow 
players to game each other remotely.  However, we mismanaged our time and 
were unable to merge the code.  The authors of both modules needed to work
together to determine how the Clients would communicate with the Server
(probably objects from a Request class).  

Please see the Docs file in RemotePlay to view diagrams of the projected
merge design, and the docs file of each respective Client and Server to 
see automatically generated UML files.  Finally the Server and Client have
been documented with javadoc.

