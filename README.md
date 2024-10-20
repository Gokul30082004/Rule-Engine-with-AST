# Rule Engine API

Overview
This project is a Rule Engine API built using Spring Boot and MongoDB. It provides functionality to create, combine, and evaluate rules through a user-friendly interface. The API supports operations for managing rules and evaluating conditions based on user-defined criteria.

Features
Create Rule: Users can define new rules with various parameters.
Combine Rules: Combine multiple rules into a single logical expression using AND/OR operations.
Evaluate Rules: Evaluate combined rules against provided data to determine if conditions are met.

Technologies Used
Spring Boot: Framework for building the API.
MongoDB: NoSQL database for storing rules.
Java: Programming language used for backend logic.
HTML/CSS/JavaScript: Frontend technologies for the user interface.


Getting Started

Prerequisites
Java 21 or higher
Maven
MongoDB - https://fastdl.mongodb.org/windows/mongodb-windows-x86_64-8.0.1-signed.msi
Postman (To test the api)

IDE
This project is best developed using IntelliJ IDEA. It provides excellent support for Spring Boot and Maven, making development and debugging easier.

Installation

Clone the repository:
git clone https://github.com/Gokul30082004/Rule-Engine-with-AST.git
cd Rule-Engine-with-AST

Ensure MongoDB is running and connected.


API Endpoints

1. Create Rule
Endpoint: /create-rule
Method: POST
Parameter : String rule
Example:
((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)

3. Combine Rules
Endpoint: /combine-rule
Method: POST
Parameter: List<AST>
Example:
list of tree constructed

5. Evaluate Rules
Endpoint: /evaluate-rule
Method: POST
Request Parameters: id (the ID of the combined rule)
Request Body: JSON object with data to evaluate against.
Example:
json
Copy code
{
    "age": 40,
    "department": "Sales",
    "salary": 100000,
    "experience": 10
}


Frontend Interface
Access the UI at http://localhost:8080 to interact with the rule engine. The interface allows users to create rules, combine them.

Usage
Create a Rule: Fill in the form to create a new rule.
Combine Rules: Enter multiple rules to combine them logically.
Evaluate Rules: Provide data and evaluate the combined rules.
