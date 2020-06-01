# mattos-financial-group
This is the parent repo containing all components from end to end payment platform and the correcponding actors apps

depends on rabbitMQ :

$ docker run -d --hostname rabbit --name my-rabbit -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password -p 15672:15672 -p 5672:5672 rabbitmq:3-management

and mongodb :

$ docker run -d --name mongo -p 27017:27017 mongo
