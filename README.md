# Quiz Application
Command-line quiz application that allows users to create and take quizzes.
## Features
- Create custom quizzes with multiple-choice and true/false questions
- Take quizzes and receive immediate feedback on answers
- View a list of all available quizzes
- Delete quizzes
- Persistent storage of quizzes in JSON format
## Question Types
The application supports two types of questions:
1. **Multiple Choice Questions**
2. **True/False Questions**
## Getting Started
### Prerequisites
- Java
- Maven (for building the project)
### Running the Application
1. Build the project using Maven:
   ```
   mvn clean package
   ```
2. Run the application:
   ```
   java -jar target/quiz320-1.0-SNAPSHOT.jar
   ```
   
or simple run the main class
## Usage
The application presents a simple menu interface:
1. **Create a new quiz**: Add a new quiz with custom questions
2. **List all quizzes**: View all available quizzes
3. **Take a quiz**: Select and take a quiz, receiving immediate feedback
4. **Delete a quiz**: Remove a quiz from the system
5. **Exit**: Close the application
## Data Storage
Quizzes are stored in a JSON file (`quizzes.json`) and loaded when the application starts. Sample quizzes are automatically created if file don't exist.
## Project Structure
- `cli`: Contains command-line interface components
- `config`: Contains configuration and initialization code
- `exception`: Contains custom exceptions
- `model`: Contains the data models (Quiz, Question, etc.)
- `repository`: Handles data access
- `service`: Contains business logic
