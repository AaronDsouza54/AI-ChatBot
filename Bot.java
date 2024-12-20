package com.aaron.nlp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Pattern;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Bot extends JFrame {
    
	private static List<String> keywords = new ArrayList<String>();
	private static JTextArea ChatArea = new JTextArea();
    private static JTextField ChatBox = new JTextField();
    private static HashMap<String, String> answers = new HashMap<String, String>();
    public Bot() {
    	makeHashMap("data.txt");
        // Set up the JFrame (the window)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ChatBot");
        setSize(600, 600);
        setResizable(false);
        setLayout(null);  // Absolute positioning

        // Set up the JTextArea (ChatArea) inside a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(ChatArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);  // Disable horizontal scroll
        scrollPane.setBounds(2, 2, 600, 500);  // Set bounds for the scroll pane (matching the size of ChatArea)
        
        // Set properties for the ChatArea
        ChatArea.setEditable(false); // Disable editing of the chat area
        ChatArea.setLineWrap(true);  // Enable word wrapping
        ChatArea.setWrapStyleWord(true); // Wrap at word boundaries

        // Set properties for the ChatBox (text input field)
        ChatBox.setSize(540, 30);
        ChatBox.setLocation(2, 500);
        
        botResponse("Hi! My name is Quanta. I can help you answer questions you have related to immigration to Canada. How can I help you today?");
        // Add action listener to handle user input from ChatBox
        ChatBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gtext = ChatBox.getText().toLowerCase();
                ChatArea.append("You -> " + gtext + "\n");  // Display the user input in ChatArea
                ChatBox.setText("");  // Clear the input field
                getAppropriateResponse(gtext);  // Get the bot's response and display it in ChatArea
                
                // Auto-scroll to the bottom of the JTextArea
                ChatArea.setCaretPosition(ChatArea.getDocument().getLength());
            }
        });

        // Add components to the JFrame
        add(scrollPane);
        add(ChatBox);

        // Make the JFrame visible after adding components
        setVisible(true);
    }
    
    public Bot(boolean forTestingOnly) {	//This constructor will be used only to test each function and avoid the GUI from opening
    	makeHashMap("data.txt");
    }
    
    public static void makeHashMap(String fileName) {
        String line; 
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            
            if (inputStream == null) {
                System.err.println("File not found in resources: " + fileName);
                return;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        
            line = in.readLine(); 
        
            while (line != null)  {
            	addToHashmap(line.split("~"));
                line = in.readLine();  // Make sure to read the next line
            }
        
            in.close();
        }
        catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());    
        } // end catch
    }
    
    public static JTextArea getChatArea() {
    	return ChatArea;
    }
    
    public static List<String> getKeywords() {
		return keywords;
	}

	public static HashMap<String, String> getAnswers() {
		return answers;
	}

	public static String normalizeAndLemmatizeWords(String words) {
        String[] lemma = lemmatizeList(words);	//returns a lemmatized list
        StringBuilder finalVal = new StringBuilder();
        for (String word : lemma)
            finalVal.append(word.trim().toLowerCase()).append(" ");  // Normalize each word
        return finalVal.toString().trim();
    }
    
    public static void addToHashmap(String [] line) {
    	String [] keywordList = line[0].split(",");
    	for (String word : keywordList) { 	//iterates through all the keywords in the list.
    		String keyword = normalizeAndLemmatizeWords(word);
            keywords.add(keyword);
            answers.put(keyword, line[1]);
    	}
    }
    
    public static void getAppropriateResponse(String gtext) {
        if (!isAppropriateEntry(gtext))
            botResponse("Invalid Entry! Please try again!");
        else 
            botResponse(generateAppropriateResponse(gtext));
    }
    
    public static String generateAppropriateResponse(String text) {
    	String answer = findKeywords(normalizeAndLemmatizeWords(breakDownSentence(text)));
    	if (!answer.equals(""))
    		return answer;
    	return "I am having trouble understanding. Maybe you could rephrase it?";
    }
    
    public static boolean isAppropriateEntry(String text) {
    	if (text == null || text.isBlank())
    		return false;
    	return Pattern.matches("[a-zA-Z]+", text) && text.length() > 2;
    }
    
    public static String breakDownSentence(String text) {
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeLine();
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        StringBuilder words = new StringBuilder();

        // Iterate over tokens and ignore punctuation marks
        for (CoreLabel coreLabel : coreLabelList) {
            String token = coreLabel.toString().trim();
            token = token.split("-")[0].trim();  // This removes anything after a "-" and trims the result
            if (!"!@#$%^&*()-_=+?></.,';\":|\\][}{`~".contains(token))
                words.append(token.toLowerCase()).append(" ");  // Normalize the token
        }
        return words.toString().trim();  // Return the processed sentence without extra spaces
    }

    public static String [] lemmatizeList(String words) {
    	StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeLine();
		CoreDocument coreDocument = new CoreDocument(words);
		stanfordCoreNLP.annotate(coreDocument);
		List <CoreLabel> coreLabelList = coreDocument.tokens();
		String [] temp = new String[coreLabelList.size()];
		int i = 0;
		for (CoreLabel coreLabel : coreLabelList) {
			temp[i] = coreLabel.lemma();
			i++;
		}
		return temp;
    }

    public static String findKeywords(String sentence) {
        String lemmatizedSentence = normalizeAndLemmatizeWords(sentence.toLowerCase());
        
        List<String> matchedKeywords = new ArrayList<>();
        
        // Check if any keyword is a substring of the user's input (case-insensitive)
        for (String keyword : keywords)
            if (lemmatizedSentence.contains(keyword)) //check if a sentence contains the keyword in it
            	matchedKeywords.add(keyword);
        
        return matchKeyWords(lemmatizedSentence, matchedKeywords);
    }
    
    public static String matchKeyWords(String sentence ,List<String> words) {
    	String [] sentenceWords = sentence.split(" ");
    	String currentKeyword = "";
    	int currentMax = 0;
    	
    	if (words.size() == 0)
    		return "";
    	
    	else if (words.size() == 1)
    		return answers.get(words.get(0));
    	
    	for (String word : words) {
    		String [] wordsList = word.split(" ");
    		int x = 0;
    		
    		for (String n : wordsList)
    			if (contains(sentenceWords,n))
    				x++;
    		
    		if (currentMax < x) {
    			currentKeyword = word;
    			currentMax = x;
    		}
    	}
    	if (answers.containsKey(currentKeyword))
    		return answers.get(currentKeyword);
    	
    	return "";
    }
    
    public static boolean contains(String [] sentence, String message) {
    	for (String word : sentence)
    		if (word.equals(message))
    			return true;
    	return false;
    }
    
    public static void botResponse(String message) {
        ChatArea.append("Bot -> "+message+"\n");
    }
    
    public static void main(String [] args) {
        new Bot();
    }
}
