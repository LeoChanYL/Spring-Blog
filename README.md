# Photo Sharing Web Application

## Project Overview
This web application allows users to share and interact with photos. It includes the following key features:

1. **Index Page**: The landing page of the web application.
2. **Profile Page**: Displays the user's profile information, including their uploaded photos.
3. **Photo Page**: Displays details of a specific photo, such as the image, caption, and comments.
4. **User Registration and Login**: Allows users to create accounts and log in to the application.
5. **Admin Functionality**: Provides additional features and management capabilities for administrators.
6. **Photo Upload History**: Displays the upload history of all registered users.
7. **Comment History**: Displays the comment history for a registered user, including comments made by both admins and normal users.

## Pre-defined User Accounts
The following pre-defined user accounts are available for testing:

| Username | Password | Role |
| --- | --- | --- |
| admin | admin | Admin |
| qwe | 1234 | Admin/User |

## Database Setup
The database will be initiated automatically when the application is built.

## Getting Started
1. Clone the repository.
2. Install the necessary dependencies.
3. Run the application.
4. Access the web application in your browser.

## Folder Structure
```
├── Data
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── Config
│   │   │   ├── Controller
│   │   │   ├── Model
│   │   │   └── Service
│   │   │   └── AsdApplication.java
│   │   ├── resources
│   │   └── webapp/WEB-INF/
│   │       └── jsp
│   └── test
│       └── java
├── .gitignore
└── README.md

```

## Technologies Used
- Frontend: HTML, CSS, JavaScript,BootStrap
- Backend: Spring Boot
- Database: H2 Database
