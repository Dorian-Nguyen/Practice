package Class3W2;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedLabels;
    private int wordsconsidered;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "C:\\Users\\doria\\eclipse-workspace\\LearningJava\\extra files\\data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
    	myMap = new HashMap<String, ArrayList<String>>();
    	usedWords = new ArrayList<String>();
    	usedLabels = new ArrayList<String>();
    	String[] labels = {"country","noun","animal","adjective","name","color","timeframe","verb","fruit"};
    	for (String s: labels) {
    		ArrayList<String> list = readIt(source + "\\"+s+".txt");
    		myMap.put(s,list);
    	}
    }
    
    private String randomFrom(ArrayList<String> source){	
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
       if (!usedLabels.contains(label)) {
    	   usedLabels.add(label);
    	   wordsconsidered += myMap.get(label).size();
    	   
       }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        boolean newWord = false;
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int idx = usedWords.indexOf(sub);
        while ( newWord == false){
            if (idx == -1){
                usedWords.add(sub);
                newWord = true;
            }
            else{
                sub = getSubstitute(w.substring(first+1,last));
                idx = usedWords.indexOf(sub);
            }
        }   
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("C:\\Users\\doria\\eclipse-workspace\\LearningJava\\extra files\\data\\madtemplate2.txt");
        printOut(story, 60);
    }
   
    public int totalWordsInMap() {
    	int totWords = 0;
    	for (String key : myMap.keySet()) {
    		totWords += myMap.get(key).size();
    	}
    	return totWords;
    }
    
    public int totalWordsConsidered() {
    	return wordsconsidered;
    }
    
    
    public static void main(String[] args){
	GladLibMap gl = new GladLibMap();
	gl.makeStory();
	System.out.println(gl.totalWordsInMap());
    }

}