# tictactoe_server

Example Clojure web application that implements Tic Tac Toe on a RESTful API.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Messages

### Create a new game

    curl -i -XPOST http://localhost:3000/games

    HTTP/1.1 201 Created
    Date: Tue, 28 May 2013 01:40:57 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 55
    Server: Jetty(7.6.1.v20120215)

    {:id 0, :game-state [[:b :b :b] [:b :b :b] [:b :b :b]]}

The ```:b``` keywords stand for blank spaces.

### Mark a position

Post an EDN map containing ```:row``` and ```:column``` from a 0 based index to place an X. The server will play an O. Once a winner has been determined the ```:winner``` field will be set.

    curl -i -XPOST  -H "Content-Type: application/edn" http://localhost:3000/games/0/mark -d '{:row 0 :column 0}'

    HTTP/1.1 200 OK
    Date: Tue, 28 May 2013 01:41:59 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 68
    Server: Jetty(7.6.1.v20120215)

    {:winner nil, :id 0, :game-state [[:x :o :b] [:b :b :b] [:b :b :b]]}

### List Games

    curl -i -XGET http://localhost:3000/games

    HTTP/1.1 200 OK
    Date: Tue, 28 May 2013 01:41:10 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 57
    Server: Jetty(7.6.1.v20120215)

    ({:id 0, :game-state [[:b :b :b] [:b :b :b] [:b :b :b]]})

### Get Game

    curl -i -XGET http://localhost:3000/games/0

    HTTP/1.1 200 OK
    Date: Tue, 28 May 2013 01:41:24 GMT
    Content-Type: application/edn;charset=ISO-8859-1
    Content-Length: 55
    Server: Jetty(7.6.1.v20120215)

    {:id 0, :game-state [[:b :b :b] [:b :b :b] [:b :b :b]]}


