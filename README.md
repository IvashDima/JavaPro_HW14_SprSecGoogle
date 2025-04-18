# JavaPro Homework 14: Spring Security with Google OAuth2

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen?style=for-the-badge&logo=spring-boot)
![Build](https://img.shields.io/badge/Build-Maven-blue?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-Educational-lightgrey?style=for-the-badge)

🔒 **A Java Spring Boot application demonstrating Google OAuth2 authentication integration.**

---

## 📋 Project Description

This project implements user authentication via Google accounts using OAuth2 in a Spring Boot application.  
After a successful login, users can access protected resources within the app.

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3.2
- Spring Security
- Spring Security OAuth2 Client
- Maven
- Google OAuth2 API

---

## 🚀 Quick Start

1. **Clone the repository**

    ```bash
    git clone https://github.com/IvashDima/JavaPro_HW14_SprSecGoogle.git 

2. **Navigate to the project directory:**
   ```bash
   cd JavaPro_HW14_SprSecGoogle

3. **Configure OAuth2 credentials**
   Create a project in Google Cloud Console, configure OAuth2 Client credentials, and update src/main/resources/application.properties:

    ```properties
   spring.security.oauth2.client.registration.google.client-id=your-google-client-id
   spring.security.oauth2.client.registration.google.client-secret=your-google-client-secret
   spring.security.oauth2.client.registration.google.scope=openid,email,profile
⚠️ Important: Set your Authorized redirect URIs in Google Cloud Console correctly. 
Example: http://localhost:8080/login/oauth2/code/google

4. **Build and Run**
   ```bash
   mvn spring-boot:run 

5. **Access the application**
Open your browser and navigate to:

   ```arduino
   http://localhost:8886/

You will be redirected to the Google login page.


## 📂 Project Structure

      src/
      └── main/
         ├── java/
         │    └── org/example/springsecurity/
         │         ├── controllers/       # Controllers handling HTTP requests
         │         ├── config/            # OAuth2 security configuration
         │         └── SpringSecurityApplication.java  # Main application class
         ├── resources/
         │     └── application.properties  # Application configuration
         └── webapp/WEB-INF/pages          # JSTL templates


## 📌 Important Notes
Google might require OAuth2 consent screen verification for apps requesting sensitive scopes.

Always protect sensitive information (like client secret) in production (use environment variables, vaults, etc.).

This project is educational and focuses on OAuth2 integration basics.

## 👨‍💻 Author

Name: Dmytro Ivashchenko

Email: dnytsyk@gmail.com


## 📝 License

This project is provided for educational purposes only and does not have a specific license.
Feel free to use and adapt it for your learning and personal projects!