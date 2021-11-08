docker build --no-cache -t my-postgres ./
docker run -d --name my-postgresdb-container -p 5432:5432 my-postgres
docker exec -it my-postgresdb-container psql -U docker