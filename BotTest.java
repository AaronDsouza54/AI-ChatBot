package com.aaron.nlp;

import java.util.List;

public class BotTest {

    public static void main(String[] args) {
        BotTest test = new BotTest();

        test.testBreakDownSentence();
        test.testLemmatizeList();
        test.testMakeHashMap();
        test.testGenerateAppropriateResponseWithMatch();
        test.testGenerateAppropriateResponseNoMatch();
        test.testIsAppropriateEntryValid();
        test.testIsAppropriateEntryInvalid();
        test.testNormalizeAndLemmatizeWords();
        test.testAddToHashmap();
        test.testFindKeywordsWithMatch();
        test.testFindKeywordsWithoutMatch();
        test.testMatchKeyWords();
        test.testContainsTrue();
        test.testContainsFalse();
        test.testBotOutput();
        test.testEmptyString();
        test.testNonAlphabeticInput();
        test.testLemmatizeComplexSentence();
        test.testMultipleKeywordsMatch();
        test.testInvalidKeyword();
        test.testLongInputSentence();
        test.testCaseInsensitiveKeywordMatching();
        test.testMultipleMatchesSameResponse();
    }

    public void testBreakDownSentence() {
        Bot bot = new Bot(true);
        String sentence = "I am running";
        String expected = "I be run";  // Expected lemmatization of the sentence.
        
        String result = bot.breakDownSentence(sentence);
        assert result.equals(expected) : "Test failed: expected " + expected + " but got " + result;
        
        System.out.println("testBreakDownSentence passed");
    }

    public void testLemmatizeList() {
        Bot bot = new Bot(true);
        String input = "running runners run";
        String[] expected = {"run", "runner", "run"};
        
        String[] result = bot.lemmatizeList(input);
        assert java.util.Arrays.equals(result, expected) : "Test failed: expected " + java.util.Arrays.toString(expected) + " but got " + java.util.Arrays.toString(result);
        System.out.println("testLemmatizeList passed");
    }

    public void testMakeHashMap() {
        Bot bot = new Bot(true);
        
        bot.makeHashMap("data.txt");  // Simulating the function that reads and populates the map
        
        assert Bot.getKeywords().contains("immigrate") : "Test failed: 'immigrate' keyword not found";
        assert Bot.getAnswers().containsKey("immigrate") : "Test failed: 'immigrate' answer not found";
        assert Bot.getAnswers().get("immigrate").equals("You should check out the immigration website.") : "Test failed: Incorrect answer for 'immigrate'";

        System.out.println("testMakeHashMap passed");
    }

    public void testGenerateAppropriateResponseWithMatch() {
        Bot bot = new Bot(true);
        Bot.getKeywords().add("immigrate");
        Bot.getAnswers().put("immigrate", "You should check out the immigration website.");

        String response = bot.generateAppropriateResponse("I want to immigrate to Canada");
        
        assert response.equals("You should check out the immigration website.") : "Test failed: expected response was not returned";
        System.out.println("testGenerateAppropriateResponseWithMatch passed");
    }

    public void testGenerateAppropriateResponseNoMatch() {
        Bot bot = new Bot(true);

        String response = bot.generateAppropriateResponse("I'm just asking about the weather");
        
        assert response.equals("I am having trouble understanding. Maybe you could rephrase it?") : "Test failed: unexpected response";
        System.out.println("testGenerateAppropriateResponseNoMatch passed");
    }

    public void testIsAppropriateEntryValid() {
        Bot bot = new Bot(true);
        boolean result = bot.isAppropriateEntry("immigrate");
        
        assert result : "Test failed: 'immigrate' should be valid";
        System.out.println("testIsAppropriateEntryValid passed");
    }

    public void testIsAppropriateEntryInvalid() {
        Bot bot = new Bot(true);
        boolean result = bot.isAppropriateEntry("!");
        
        assert !result : "Test failed: '!' should be invalid";
        System.out.println("testIsAppropriateEntryInvalid passed");
    }

    public void testNormalizeAndLemmatizeWords() {
        Bot bot = new Bot(true);
        String sentence = "I am running quickly";
        String expected = "run quickly";  // Expecting lemmatization result
        
        String result = bot.normalizeAndLemmatizeWords(sentence);
        
        assert result.equals(expected) : "Test failed: expected " + expected + " but got " + result;
        System.out.println("testNormalizeAndLemmatizeWords passed");
    }

    public void testAddToHashmap() {
        Bot bot = new Bot(true);
        String[] line = {"immigrate, immigrate canada", "You should check out the immigration website."};
        bot.addToHashmap(line);

        assert Bot.getKeywords().contains("immigrate") : "Test failed: 'immigrate' keyword not found";
        assert Bot.getAnswers().containsKey("immigrate") : "Test failed: 'immigrate' answer not found";
        assert Bot.getAnswers().get("immigrate").equals("You should check out the immigration website.") : "Test failed: Incorrect answer for 'immigrate'";

        System.out.println("testAddToHashmap passed");
    }

    public void testFindKeywordsWithMatch() {
        Bot bot = new Bot(true);
        Bot.getKeywords().add("immigrate");
        Bot.getAnswers().put("immigrate", "You should check out the immigration website.");

        String result = bot.findKeywords("I want to immigrate to Canada");

        assert result.equals("You should check out the immigration website.") : "Test failed: expected 'You should check out the immigration website.' but got " + result;
        System.out.println("testFindKeywordsWithMatch passed");
    }

    public void testFindKeywordsWithoutMatch() {
        Bot bot = new Bot(true);
        String result = bot.findKeywords("I am just asking about the weather.");
        
        assert result.equals("I am having trouble understanding. Maybe you could rephrase it?") : "Test failed: expected default response";
        System.out.println("testFindKeywordsWithoutMatch passed");
    }

    public void testMatchKeyWords() {
        Bot bot = new Bot(true);
        List<String> keywords = java.util.List.of("immigrate", "canada");
        String sentence = "I want to immigrate to Canada";
        
        String result = bot.matchKeyWords(sentence, keywords);

        assert result.equals("You should check out the immigration website.") : "Test failed: expected response was not returned";
        System.out.println("testMatchKeyWords passed");
    }

    public void testContainsTrue() {
        Bot bot = new Bot(true);
        String[] sentence = {"I", "want", "to", "immigrate"};
        String word = "immigrate";

        boolean result = bot.contains(sentence, word);

        assert result : "Test failed: 'immigrate' should be found";
        System.out.println("testContainsTrue passed");
    }

    public void testContainsFalse() {
        Bot bot = new Bot(true);
        String[] sentence = {"I", "want", "to", "live"};
        String word = "immigrate";

        boolean result = bot.contains(sentence, word);

        assert !result : "Test failed: 'immigrate' should not be found";
        System.out.println("testContainsFalse passed");
    }

    public void testBotOutput() {
        Bot bot = new Bot(true);
        Bot.getChatArea().setText("");  // Clear the existing text
        bot.botResponse("Hello, I am the bot!");
        
        String output = Bot.getChatArea().getText();
        assert output.contains("Bot -> Hello, I am the bot!") : "Test failed: output does not contain expected message";
        System.out.println("testBotOutput passed");
    }

    // **New rigorous test cases**

    public void testEmptyString() {
        Bot bot = new Bot(true);
        String sentence = "";
        boolean result = bot.isAppropriateEntry(sentence);
        
        assert !result : "Test failed: Empty string should be invalid.";
        System.out.println("testEmptyString passed");
    }

    public void testNonAlphabeticInput() {
        Bot bot = new Bot(true);
        String sentence = "1234!!!";
        boolean result = bot.isAppropriateEntry(sentence);
        
        assert !result : "Test failed: '1234!!!' should be invalid.";
        System.out.println("testNonAlphabeticInput passed");
    }

    public void testLemmatizeComplexSentence() {
        Bot bot = new Bot(true);
        String sentence = "The cats are running quickly across the fields.";
        String expected = "the cat be run quickly across the field";  // Expected lemmatized sentence.
        
        String result = bot.normalizeAndLemmatizeWords(sentence);
        assert result.equals(expected) : "Test failed: expected " + expected + " but got " + result;
        System.out.println("testLemmatizeComplexSentence passed");
    }

    public void testMultipleKeywordsMatch() {
        Bot bot = new Bot(true);
        Bot.getKeywords().add("immigrate");
        Bot.getKeywords().add("canada");
        Bot.getAnswers().put("immigrate", "You should check out the immigration website.");
        Bot.getAnswers().put("canada", "Canada is a beautiful country.");
        
        String sentence = "I want to immigrate to Canada next year.";
        
        String result = bot.generateAppropriateResponse(sentence);

        assert result.equals("You should check out the immigration website.") : "Test failed: expected response not returned";
        System.out.println("testMultipleKeywordsMatch passed");
    }

    public void testInvalidKeyword() {
        Bot bot = new Bot(true);
        Bot.getKeywords().add("immigrate");
        Bot.getAnswers().put("immigrate", "You should check out the immigration website.");
        
        String sentence = "I want to live in peace.";
        
        String result = bot.generateAppropriateResponse(sentence);
        
        assert result.equals("I am having trouble understanding. Maybe you could rephrase it?") : "Test failed: expected default response";
        System.out.println("testInvalidKeyword passed");
    }

    public void testLongInputSentence() {
        Bot bot = new Bot(true);
        String sentence = "I was wondering if there are any processes or steps involved in immigrating to Canada during the winter months, and how the process might differ for people from different countries.";
        
        String result = bot.generateAppropriateResponse(sentence);
        
        assert result.equals("You should check out the immigration website.") : "Test failed: expected response was not returned";
        System.out.println("testLongInputSentence passed");
    }

    public void testCaseInsensitiveKeywordMatching() {
        Bot bot = new Bot(true);
        Bot.getKeywords().add("immigrate");
        Bot.getAnswers().put("immigrate", "You should check out the immigration website.");
        
        String sentence = "I want to Immigrate to Canada";
        String result = bot.generateAppropriateResponse(sentence);
        
        assert result.equals("You should check out the immigration website.") : "Test failed: expected response was not returned";
        System.out.println("testCaseInsensitiveKeywordMatching passed");
    }

    public void testMultipleMatchesSameResponse() {
        Bot bot = new Bot(true);
        Bot.getKeywords().add("immigrate");
        Bot.getAnswers().put("immigrate", "You should check out the immigration website.");
        
        String sentence = "I am thinking of immigrating to Canada, and I want to learn how to immigrate.";
        
        String result = bot.generateAppropriateResponse(sentence);
        
        assert result.equals("You should check out the immigration website.") : "Test failed: expected response was not returned";
        System.out.println("testMultipleMatchesSameResponse passed");
    }
}
