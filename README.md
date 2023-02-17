![FarmWatch](https://github.com/C10-MIW-G/FarmWatch/blob/main/Docs/farmwatchbanner.png)

This web application boasts a dynamic front-end implemented using Angular and a robust backend powered by Spring Boot. The combination of these technologies results in a seamless user experience. The Angular front-end provides an interactive and responsive interface, while the Spring Boot backend efficiently handles and manages data.

The application utilizes a MySQL database for storage and retrieval of information, ensuring reliable and fast performance. Adhering to a microservices architecture, the application is broken down into smaller, more manageable components, making maintenance, testing, and deployment easier while improving overall performance.

Security is a top priority in this web application, with authentication and authorization methods in place to protect user data at all times. In conclusion, this web application combines the strengths of Angular and Spring Boot to deliver a feature-rich and high-performing solution to its users.

## How to install

To get up and running it is important to install the correct software and packages on your system. In this section you will read everything you need to know about what you need to install and how you need to install it!

### Prerequisites
-   npm (version 6.x or later)
-   Angular CLI (version 15.x or later)
-   Java (version 17 or later)
-   Maven (version 3.5 or later)
-   MySQL Server (version 8.x or later)

### Getting started
#### Installation
To start the installing clone the github repository, or download the to a local folder of your choosing.
1. Clone the repository to your local machine using the following command:
`git clone https://github.com/C10-MIW-G/FarmWatch.git`  
or  download and extract the repository .zip to the folder of your choosing.
2. Open MySQL and run the sql commands found in: [local_folder]/docs/farmwatch_startup_SQL.txt
3. The database tables and user has been created!

#### Backend
1.  Open the backend folder in your local folder using IntelliJ or a similar IDE. Open de pom.xml and download and install all missing dependencies.
2. Run the application.
3. The backend is now ready to use. 

#### Frontend
1.  Open the frontend folder in your local folder using Microsoft Visual Code or similar IDE.
2.  Open the terminal and run the following command to install the packages:
`npm install`
3.  Open the terminal and run the following command
`ng serve`
4. The frontend is now ready to use.

#### Postman
1. Open your Postman application and import the Seed.postman_collection.json found in [local_folder]/docs/postman/
2. Run the 'Seed Database' from the Seed section in the Postman workspace you have just imported.
3. Your database should now be seeded and you are able to login with the following credentials:
	- username: 		'user'
	- password: 		'password'

## Built With 
#### Frameworks
![Spring](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
 #### IDE's
![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ![Visual Studio Code](https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white)
#### Tools
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
#### Languages
![JAVA](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) ![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)


## Authors

- **Berend Boksma** - **Developer** - [ChiefValentine](https://github.com/chiefvalentine)
- **Dave Thijs** - **Developer** - [drownzz](https://github.com/drownzz)
- **Eelke Mulder** - **Developer** - [HamburgerSandwich](https://github.com/HamburgerSandwich)
- **Meent Sytze Pilat** - **Developer** - [mspbp](https://github.com/mspbp)
