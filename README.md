# Quiz Application
Command-line quiz application that allows users to create, take, and manage quizzes.
## Features
- Create custom quizzes with multiple-choice and true/false questions
- Take quizzes and receive immediate feedback on answers
- View a list of all available quizzes
- Delete quizzes
- Persistent storage of quizzes in JSON format
## Question Types
The application supports two types of questions:
1. **Multiple Choice Questions**: Questions with multiple options where only one is correct
2. **True/False Questions**: Simple questions with either true or false as the answer
## Getting Started
### Prerequisites
- Java 17 or higher
- Maven (for building the project)
### Running the Application
1. Clone the repository
2. Build the project using Maven:
   ```
   mvn clean package
   ```
3. Run the application:
   ```
   java -jar target/quiz-app.jar
   ```
## Usage
The application presents a simple menu interface:
1. **Create a new quiz**: Add a new quiz with custom questions
2. **List all quizzes**: View all available quizzes
3. **Take a quiz**: Select and take a quiz, receiving immediate feedback
4. **Delete a quiz**: Remove a quiz from the system
5. **Exit**: Close the application
### Creating a Quiz
When creating a quiz, you'll need to provide:
- Quiz title
- Description
- Time limit (in minutes)
- Questions (multiple-choice or true/false)
For each question, you'll specify:
- Question text
- Points value
- Answer options (for multiple-choice)
- Correct answer
### Taking a Quiz
When taking a quiz:
1. Select the quiz from the list
2. Answer each question as prompted
3. Receive immediate feedback on each answer
4. View your final score at the end
## Data Storage
Quizzes are stored in a JSON file (`src/main/resources/quizzes.json`) and loaded when the application starts. Sample quizzes are automatically created if no quizzes file exists.
## Project Structure
- `model`: Contains the data models (Quiz, Question, etc.)
- `service`: Contains business logic
- `repository`: Handles data access
- `cli`: Contains command-line interface components
- `config`: Contains configuration and initialization code
- `exception`: Contains custom exceptions