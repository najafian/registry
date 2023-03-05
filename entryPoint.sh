. .env
mvn clean package

docker-compose -f docker-compose.yml down
docker rmi registry_registry
docker-compose -f docker-compose.yml up --build -d

mvn clean

