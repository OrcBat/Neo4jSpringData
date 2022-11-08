# Neo4jSpringData
This project uses a Spring application to store and retrieve data from the graph-based NoSQL database Neo4j

## Setup
- Start the Application via bootRun
- Run this Docker Image Command for the Docker Container (Docker Desktop required):
    docker run \
    --publish=7474:7474 --publish=7687:7687 \
    --volume=$HOME/neo4j/data:/data \
    neo4j

- Go to the localhost URL on the port 7474 (http://localhost:7474)
- Login with a user (preferably ADMIN)
- Done

## Users
| Username | Password | Privileges |
|----------|----------|------------|
| neo4j    | secret   | ADMIN      |
| luca     | secret   | USER       |
| aravinth | secret   | USER       |
