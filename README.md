# Final-project
Year-end project for IRONHACK BACKEND AMZ MAR26

<img width="512" height="511" alt="image" src="https://github.com/user-attachments/assets/6bf34d13-5571-4ac5-b935-4daff136bb1b" />


"The project consists of being able to order a cake or a pastry specialty in a place near home, with the possibility of also requesting a customized cake. 
The idea came to me while talking about the final project with a friend during a birthday party. 
It is not always easy to find the cake you want, especially if you are away from home!"

# CAKE ME 🍰

## Description of the Project
**CAKE ME**  is a smart web application designed to help users find the perfect cake based on their specific dietary needs, flavor preferences, or special occasions. Instead of browsing through generic food delivery filters, users can express what they want in natural language (e.g., *"I want a vegan chocolate cake"* or *"A birthday cake with fresh cream"*). 

The backend application is a robust REST API built using **Java** and **Spring Boot**, backed by a **MySQL** database.
It manages user authentication, bakery profiles, and a dynamic cake catalog that separates standard ready-to-buy cakes from fully customizable ordered cakes using advanced JPA inheritance strategies.

---

## Class Diagram
This project implements a **JOINED** JPA Inheritance strategy for the `Cake` entity hierarchy to ensure clean data normalization and relational integrity in MySQL.

```mermaid
classDiagram
    direction TB
    class User {
        +Long id
        +String username
        +String password
        +String role
    }

    class PastryShop {
        +Long id
        +String name
        +String address
        +String city
    }

    class Cake {
        <<abstract>>
        +Long id
        +String name
        +String description
        +Double basePrice
        +String imageUrl
        +PastryShop pastryShop
    }

    class StandardCake {
        +Boolean isDietary
        +String allergens
        +Integer availableInStock
    }

    class CustomCake {
        +Integer maxTiers
        +Boolean customMessageAllowed
        +Integer productionDaysNeeded
    }

    User "*" --> "1" PastryShop : owns/manages
    PastryShop "1" --> "*" Cake : sells
    Cake <|-- StandardCake : Inherits (Joined)
    Cake <|-- CustomCake : Inherits (Joined)


## Extra Links
* **Task Management Board:** [https://github.com/claudiacri/Final-project/edit/main/README.md]
* **Presentation Slides:** [ https://docs.google.com/presentation/d/1rX2HalxOVseTLP6ho2NyIoC18LEy3l-O9cJ_b_njKI0/edit?usp=sharing ]
* **API Testing Collection:** [Link to Postman Public Workspace / JSON Export]

---

## Team Members
* **Claudia C.** - Full Stack Developer  [[GitHub Profile Link](https://github.com/claudiacri)]
