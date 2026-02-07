# Selenium Automation Testing Project

This project is a **Java + Selenium automation framework** created for practicing
real-world UI test automation concepts.

The framework follows **Page Object Model (POM)** and uses **JSON files**
for test data handling.

---

## 🚀 Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- JSON (org.json)
- Page Object Model (POM)

---

## 🧪 Test Scenarios Automated

- Login functionalities
- Cart functionalities
- Home functionalities 
- Details functionalities
- Checkout functionalities
- Sorting validation:
    - Price (Low to High)
    - Price (High to Low)
    - Name (A to Z)
    - Name (Z to A)
- Data-driven testing using JSON files

---

## 🌐 Website Tested

- **Sauce Demo**
    - https://www.saucedemo.com/

---

## 📂 Project Structure

```text
Demo_Blaze_Testing
├── src
│   ├── main
│   │   ├── java
│   │   │   └── Utils
│   │   │       └── JsonFileReaderUtil.java
│   │   └── resources
│   │  
│   ├── test
│       └── java
│           ├── BaseTest        
│           ├── Data_Driven    
│           ├── Pages          
│           └── Tests         
│
├── Test_Data
│   └── login.json             
│
├── Test_Outputs
│   ├── Test_Report.html
│   ├── Test_Report_02.html
│   ├── Test_Report_03.html
│   ├── Test_Report_DD.html
│   └── ...
│
├── pom.xml
├── .gitignore

```
## ▶️ How to Run the Project

Clone the repository : 
```bash 
git clone https://github.com/Parth-Chikalkar/Selenium-Testing

````
Open the project in IntelliJ IDEA / Eclipse

Select a test user from the following list:

Usernames :

```bash                                      
standard_user 
locked_out_user
problem_user
performance_glitch_user
error_user
visual_user
```
Password : 
```bash

secret_sauce
```

Replace the username and password and run the tests 

Run tests using:

TestNG XML file
OR

Run test classes directly


##  Author : Parth Chikalkar

