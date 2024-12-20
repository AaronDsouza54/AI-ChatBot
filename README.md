# AI-ChatBot
A simple AI chatbot that answers questions regarding immigration for potential immigrants.

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Configuration](#configuration)
6. [API Reference](#api-reference)
7. [License](#license)
8. [Acknowledgements](#acknowledgements)

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
4. Install dependencies and build the project using Maven:
   mvn clean install
5. Run the application:
   java -cp target/java-chatbot-1.0-SNAPSHOT.jar com.aaron.nlp.Bot

To start the chatbot, run the following command:
java -cp target/java-chatbot-1.0-SNAPSHOT.jar com.aaron.nlp.Bot

Example Interaction
User: Hello!
Bot: Hello! How can I assist you today?

**Configuration**
Data File
The chatbot responses are loaded from a data.txt file. Each line in the file should have the format:
keyword1,...,keywordn~response

For example:
hello,hi ~ Hello! How can I assist you today?

**Stanford CoreNLP Configuration**
Ensure that the Stanford CoreNLP models are available in your classpath. You can download the models from the Stanford NLP website.

**API Reference**
Bot Class
The main class that initializes and runs the chatbot.

Methods
public Bot(): Constructor that sets up the GUI and initializes the chatbot.
public Bot(boolean forTestingOnly): Constructor that creates an instance of the Bot class without creating a GUI for unit testing.
private static void makeHashMap(String fileName): Loads responses from the specified file.
public static JTextArea getChatArea(): returns the constructor-level variale called ChatArea.
public static List<String> getKeywords(): returns the constructor-level variable called keywords that stores all the keywords to look for.
public static HashMap<String, String> getAnswers(): returns the constructor-level variable called answers that stores a response for each keyword. 
public static String normalizeAndLemmatizeWords(String words): Processes and normalizes keywords.
public static void addToHashmap(String line): Adds keywords and responses to the hashmap and adds keywords to the keywords variable.
public static void getAppropriateResponse(String gtext): Determines and displays the appropriate response to the user message.
public static String generateAppropriateResponse(String text): Generates a response based on the processed user input.
public static boolean isAppropriateEntry(String text): Validates the user input.
public static String breakDownSentence(String text): Breaks down the input sentence into tokens.
public static String[] lemmatizeList(String words): Lemmatizes the input words.
public static String findKeywords(String sentence): Finds matching keywords in the user input.
public static String matchKeyWords(String sentence, List<String> words): Matches keywords and generates the appropriate response.
public static boolean contains(String[] sentence, String message): Checks if a sentence contains a specific word.
public static void botResponse(String message): Displays the bot's response in the chat area.

Pipeline Class
Provides the Stanford CoreNLP pipeline instance.

Methods
public static StanfordCoreNLP getPipeLine(): Returns the Stanford CoreNLP pipeline instance.

BotTest Class
Tests individual functions in the Bot Class.

Methods
public void testBreakDownSentence(): tests the breakDownSentence method from the Bot class.
public void testLemmatizeList(): tests the lemmatizeList method from the Bot class.
public void testMakeHashMap(): tests the makeHashMap method from the Bot class.
public void testGenerateAppropriateResponseWithMatch(): tests the generateAppropriate response method from the Bot class when there is a match.
public void testGenerateAppropriateResponseNoMatch(): tests the generateAppropriate response method from the Bot class when there is no match.
public void testIsAppropriateEntryValid(): tests the isAppropriateEntry method from the Bot class when the entry is a valid string.
public void testIsAppropriateEntryInvalid(): tests the isAppropriateEntry method from the Bot class when the entry is not a valid string.
public void testAddKeyword(): tests the normalizeAndLemmatizeWords method from the Bot class.
public void testAddToHashmap(): tests the addToHashmap method from the Bot class.
public void testFindKeywordsWithMatch(): tests the findKeywordsWithMatch method from the Bot class when there is a match .
public void testFindKeywordsWithoutMatch(): tests the findKeywordsWithMatch method from the Bot class when there is no match .
public void testMatchKeyWords(): tests the matchKeywords method from the Bot class.
public void testContainsTrue(): tests the contains method from the Bot class if the word is in the array.
public void testContainsFalse(): tests the contains method from the Bot class if the word is not in the array.
public void testBotOutput(): tests whether the bot outputs the correct message. 
public void testEmptyString(): tests if isAppropriateEntry function returns false when an empty string is given to it.
public void testNonAlphabeticInput(): tests if isAppropriateEntry function returns false when a number is given to it.
public void testLemmatizeComplexSentence(): tests if normalizeAndLemmatizeWords function returns a correctly lemmatized string given a complex string.
public void testMultipleKeywordsMatch(): checks if generateAppropriateResponse returns the correct answer, that is, the first keyword correctly matched if the keywords are the same size.
public void testInvalidKeyword(): checks if generateAppropriateResponse returns the correct answer if there are no keywords in the sentence.
public void testLongInputSentence(): checks if generateAppropriateResponse returns the correct answer if there is a long sentence.
public void testCaseInsensitiveKeywordMatching(): checks if generateAppropriateResponse returns the correct answer if there is a keyword with a different case.
public void testMultipleMatchesSameResponse(): checks if generateAppropriateResponse returns the correct answer if there are multiple keywords which all have the same answer.

**License**
This project is licensed under the MIT License. See the LICENSE file for details.

