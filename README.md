## Spray invitation service demo

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
    
    