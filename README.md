# **Lodgify Server – REST API for Property Management**  

## Overview  
Lodgify Server is the **backend for a client-server property management system (PMS)**, built for a specific accommodation facility. It is developed in **Java with Spring Framework** and follows a **REST API architecture** with controllers, services, and repositories.  

## ⚠️ Disclaimer

This API is my very first REST API built with Spring, so it is **not perfect** and not fully aligned with best practices — and I’m aware of that.  
I have already applied improved design and coding standards in another project: [SyncCart](https://github.com/Greenn3/SyncCart).  

I plan to refactor this API to follow cleaner architecture and better conventions, and to rebuild the frontend as a modern web application.


The system handles the **core business logic**, including:  
- Managing **reservations** (adding, modifying, deleting)  
- **Price calculations** based on stay period & accommodation type  
- Managing **pricing lists & accommodation types**  
- Handling **data storage in a MySQL database**  

The server is **self-hosted on a VPS** and can be accessed at:  
🖥 **[80.211.200.112:8090](http://80.211.200.112:8090)**  

Since the API does not have a UI, it requires the **Lodgify Desktop Client** to interact with it:  
🔗 **[Lodgify Desktop App](https://github.com/Greenn3/demosystemFront)**  

---

## Core Features  

### Booking & Pricing Management  
- **CRUD operations** for reservations  
- **Real-time availability checks**  
- **Dynamic pricing calculations** based on accommodation type & period  
- **Pricelist & accommodation type management**  

### Architecture & Tech Stack  
- **Spring Boot** – Backend logic & API  
- **Hibernate & JPA** – ORM for database interaction  
- **MySQL** – Hosted database  
- **RESTful API** – Handles communication with the desktop client  

---

## Planned Improvements  

- 🖥 **Developing a web-based UI** for browser access  
- 📱 **Building a mobile app** for additional usability  
- ⚡ **Expanding API functionality** by adding new endpoints  
- 🔐 **Introducing a user login system**  
- 🛡️ **Enhancing security** with better validation & access control  

---
