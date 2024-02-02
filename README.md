# MediLaboSolutions

Application to help detect type 2 diabetes. The application is based on a microservices architecture.

## Getting Started

### Prerequisites
You must have Docker installed on your machine.

### Run the application:

1. Clone the Git repository to your local machine.
    * git clone https://github.com/MichelCat/MediLaboSolutions.git
2. At the root of the project, execute the command.
   * docker compose up
3. Start the browser application
   *  http://localhost:9004/login
4. Login with user :
   * User : user
   * Password : user
5. The application comes with a test game.

## Endpoints

[Postman file containing the APIs](./doc/MediLaboSolutions.postman_collection.json)

* 9004 : Microservice Gateway
  * Login
  * Get Patients Information
  * Patient list
  * Get Patient by ID


* 9102 : Microservi Eureka
  * Page information serveur Eureka


* 8080 : Microservice frontend
  * Patient list


* 9001 : Microservice patients
  * Get Patients Information
  * Create New Patient
  * Delete Patient by ID
  * Update Patient by ID
  * Get Patient by ID
* 9005 : Microservice notes
  * Get notes for patient id
  * Create New Note
  * Delete note
  * Update note
  * Get Note by Note ID
  * Delete notes for patient id
* 9006 : Microservice diabetes risks
  * Get diabetes risk by Patient ID


## Architecture
> **Microservice Gateway** : Managing request routing to microservices.

> **Microservice Eureka** : Registry of all available instances of microservices.

> **Microservice frontend** : Web client.

> **Microservice patients** : Patient information management.

> **Microservice notes** : Management of patient observation notes.

> **Microservice diabetes risks** : Assessment of a patient's diabetes risks.


## Green Code

### Green Code issues
Prevent and reduce ecological impact.
* Digital energy consumption is growing by around 9% per year on average.
* In 2025, digital technology will emit as much as all road transport.
* The digital transformation of our economy has not yet triggered gains in GHG emissions.
* Resource depletion and pollution.

https://openclassrooms.com/fr/courses/6227476-appliquez-les-principes-du-green-it-dans-votre-entreprise/6699431-decouvrez-limpact-du-numerique-sur-lenvironnement

### Recommendations for “Green” improvement of the project
* Filter as much information as possible at the database level. In mongoDB, use of aggregation.
* Reduce the size of docker images using the ".dockerignore" file.
* Reduce the size of the repository using the ".gitignore" file.
* Use static contents for image files, "bootstrap.min.css", "bootstrap.bundle.min.js".
* Improve code quality with SonarQube.
* Remove unnecessary features.
* Code refactoring. Simplify the calculation of diabetes risk.
* Increase code performance with VisualVM.
* Measuring the environmental score of EcoIndex websites.

## Auteurs

* **OpenClassrooms student**