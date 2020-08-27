# mattos-financial-group
This is the parent repo containing all components from end to end payment platform and the correcponding actors apps

depends on rabbitMQ :

$ docker run -d --hostname rabbit --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management

and mongodb :

$ docker run -d --name mongo -p 27017:27017 mongo

and redis :

$ docker run -d --name redis -p 6379:6379 redis




with docker compose :

export DATAFLOW_VERSION=2.5.1.RELEASE
export SKIPPER_VERSION=2.4.1.RELEASE
docker-compose up