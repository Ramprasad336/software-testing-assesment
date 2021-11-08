# software-testing-assesment
## SQL
* Prepare a docker file with all the artifacts to add postgress setup with necessary DDL'S
* Build the image
* Run the image and deploy in container(spin of image)
* Verify the container is up and running using shell script attcahed in the sql folder
* Console in to the container and observe the logs
* docker build --no-cache -t my-postgres ./
* docker run -d --name my-postgresdb-container -p 5432:5432 my-postgres
* docker exec -it my-postgresdb-container psql -U docker
