# software-testing-assesment
## SQL
* Prepare a docker file with all the artifacts to add postgress setup with necessary DDL'S
* Added all the SQL Files with quries in sequentail order where it will be executed
* Build the image
* Run the image and deploy in container(spin of image)
* Verify the container is up and running using shell script attcahed in the sql folder
* Console in to the container and observe the logs
* docker build --no-cache -t my-postgres ./
* docker run -d --name my-postgresdb-container -p 5432:5432 my-postgres
* docker exec -it my-postgresdb-container psql -U docker

## TestAutomation
* Uisng Selenium based BDD Test Automation framework
### Packages
* Factory : This package enables the building of driver object based on the config properties,Highlight the elements while execution
* features : Test Scenarios scripts
* locators : This package to identify the elements on screen
* Runner : This package is for Test execution triggering
* StepDefitions: This package defines the steps related to in feature file
* Util : Read configuration props & Generic element methods.
