# AI-ChatBot
A simple AI chatbot that answers questions regarding immigration for potential immigrants.

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Configuration](#configuration)
6. [API Reference](#api-reference)
7. [Contributing](#contributing)
8. [License](#license)
9. [Acknowledgements](#acknowledgements)

## Introduction
The Java Chatbot Program is designed to provide automated responses to user queries. It uses natural language processing to understand and respond to user inputs. The chatbot can be integrated into various platforms and provides interactive and engaging user experiences.

## Features
- Natural language processing using Stanford CoreNLP
- Interactive graphical user interface (GUI) with Swing
- Customizable responses loaded from a file
- Logging of user interactions
- Basic error handling

## Installation
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/java-chatbot.git
2. Navigate to the project directory:
   cd java-chatbot
3. Install dependencies and build the project using Maven:
   mvn clean install
Run the application:
   java -cp target/java-chatbot-1.0-SNAPSHOT.jar com.aaron.nlp.Bot

# Java Chatbot Program

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Configuration](#configuration)
6. [API Reference](#api-reference)
7. [Contributing](#contributing)
8. [License](#license)
9. [Acknowledgements](#acknowledgements)

## Introduction
The Java Chatbot Program is designed to provide automated responses to user queries. It uses natural language processing to understand and respond to user inputs. The chatbot can be integrated into various platforms and provides interactive and engaging user experiences.

## Features
- Natural language processing using Stanford CoreNLP
- Interactive graphical user interface (GUI) with Swing
- Customizable responses loaded from a file
- Logging of user interactions
- Basic error handling

## Installation
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/java-chatbot.git
2. Navigate to the project directory:
   cd java-chatbot
3. Install dependencies and build the project using Maven:
   mvn clean install
4. Run the application:
   java -cp target/java-chatbot-1.0-SNAPSHOT.jar com.aaron.nlp.Bot
5. Usage
   Running the Chatbot

To start the chatbot, run the following command:
java -cp target/java-chatbot-1.0-SNAPSHOT.jar com.aaron.nlp.Bot

Example Interaction
User: Hello, bot!
Bot: Hello! How can I assist you today?

**Configuration**
Data File
The chatbot responses are loaded from a data.txt file. Each line in the file should have the format:
keyword1,...~response : Where there can be infinitely many keywords.
For example:
hello,hi~Hello! How can I assist you today?

**Stanford CoreNLP Configuration**
Ensure that the Stanford CoreNLP models are available in your classpath. You can download the models from the Stanford NLP website.

**API Reference**
Bot Class
The main class that initializes and runs the chatbot.

**Methods**
public Bot(): Constructor that sets up the GUI and initializes the chatbot.
private static void makeHashMap(String fileName): Loads responses from the specified file.
private static String addKeyword(String words): Processes and normalizes keywords.
private static void addToHashmap(String line): Adds keywords and responses to the hashmap.
private static boolean isGreeting(String message): Checks if the user message is a greeting.
private static void getAppropriateResponse(String gtext): Determines and displays the appropriate response to the user message.
private static String generateAppropriateResponse(String text): Generates a response based on the processed user input.
private static boolean isAppropriateEntry(String text): Validates the user input.
private static String breakDownSentence(String text): Breaks down the input sentence into tokens.
private static String[] lemmatizeList(String words): Lemmatizes the input words.
private static String findKeywords(String sentence): Finds matching keywords in the user input.
private static String matchKeyWords(String sentence, List<String> words): Matches keywords and generates the appropriate response.
private static boolean contains(String[] sentence, String message): Checks if a sentence contains a specific word.
private static void bot(String message): Displays the bot's response in the chat area.
Pipeline Class
Provides the Stanford CoreNLP pipeline instance.

**Methods**
public static StanfordCoreNLP getPipeLine(): Returns the Stanford CoreNLP pipeline instance.

**Contributing**
We welcome contributions from the community! To contribute, please follow these steps:

Fork the repository.
Create a new branch:
git checkout -b feature-branch
Make your changes and commit them:
git commit -m 'Add new feature'
Push to the branch:
git push origin feature-branch
Submit a pull request.
Please ensure your code follows our coding standards and includes appropriate tests.

**License**
This project is licensed under the MIT License. See the LICENSE file for details.
