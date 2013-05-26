# tictactoe_server

FIXME

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Messages

### Create a new game

TODO the game state should be arrays

    curl -i -XPOST http://localhost:3000/games

    HTTP/1.1 201 Created
    Date: Sun, 26 May 2013 01:29:12 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 123
    Server: Jetty(7.6.1.v20120215)

    {:id 0, :game-state {1 {1 :blank, 2 :blank, 3 :blank}, 2 {1 :blank, 2 :blank, 3 :blank}, 3 {1 :blank, 2 :blank, 3 :blank}}}

### List Games

    curl -i -XGET http://localhost:3000/games

    HTTP/1.1 200 OK
    Date: Sun, 26 May 2013 01:29:42 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 125
    Server: Jetty(7.6.1.v20120215)

    ({:id 0, :game-state {1 {1 :blank, 2 :blank, 3 :blank}, 2 {1 :blank, 2 :blank, 3 :blank}, 3 {1 :blank, 2 :blank, 3 :blank}}})

### Get Game

    curl -i -XGET http://localhost:3000/games/0

    HTTP/1.1 200 OK
    Date: Sun, 26 May 2013 01:30:01 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 123
    Server: Jetty(7.6.1.v20120215)

    {:id 0, :game-state {1 {1 :blank, 2 :blank, 3 :blank}, 2 {1 :blank, 2 :blank, 3 :blank}, 3 {1 :blank, 2 :blank, 3 :blank}}}

## Mark a position

    TODO The response here should just be a the game. It's in another map.

    curl -i -XPOST  -H "Content-Type: application/edn" http://localhost:3000/games/0/mark -d '{:row 1 :column 1}'

    HTTP/1.1 200 OK
    Date: Sun, 26 May 2013 01:30:52 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 123
    Server: Jetty(7.6.1.v20120215)

    {0 {:id 0, :game-state {1 {1 :x, 2 :blank, 3 :blank}, 2 {1 :blank, 2 :blank, 3 :blank}, 3 {1 :blank, 2 :blank, 3 :blank}}}}

## License

Copyright © 2013 FIXME
