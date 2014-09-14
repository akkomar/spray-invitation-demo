## Spray invitation service demo
Invitee repository can be configured to use embedded H2 database or immutable stub containing one invitee.
To configure, set `invitee.repository.impl` parameter to `H2DB` or `immutableList`.

### To compile and run tests:
    $ sbt test
    
### To start application:
    $ sbt run
or
    $ ./run.sh
    
### Example requests:
#### GET /invitation
    $ curl -i http://localhost:8080/invitation
#### POST /invitation
    $ curl -i -H "Content-Type: application/json" -d '{"invitee": "John Smith","email": "john@smith.mx"}' http://localhost:8080/invitation
    

[![Build Status](https://travis-ci.org/akkomar/spray-invitation-demo.svg?branch=master)](https://travis-ci.org/akkomar/spray-invitation-demo)