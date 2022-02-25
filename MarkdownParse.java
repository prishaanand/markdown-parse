// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            System.out.println("Markdown Length:" + markdown.length());
            System.out.println("Current Index:" + currentIndex); 

            int firstBacktick = markdown.indexOf("`[", currentIndex);
            int secondBacktick = markdown.indexOf("`", firstBacktick);
            boolean disregard = false;
            //if there are a set of backticks
            if(firstBacktick >= 0 && secondBacktick >= 0){
                System.out.println("invalidate the link with []");
                disregard = true; 
            }   

            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            //add to break the for loop
            if (nextOpenBracket < 0) {
                break;
            }
            System.out.println("Next open bracket: " + nextOpenBracket);
            int nextCloseBracket = markdown.indexOf("](", nextOpenBracket);
            System.out.println("Next close bracket: " + nextCloseBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            System.out.println("Open paren: " + openParen);
            int closeParen = markdown.indexOf(")", openParen);
            System.out.println("Close paren: " + closeParen);
            if(!disregard){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}