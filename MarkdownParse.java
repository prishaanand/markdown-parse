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
            System.out.println("Current Index:" + currentIndex); //intially prints 0 and 41
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket < 0){
                break;
            }
            System.out.println("Next Open Bracket:" + nextOpenBracket);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket); //check open is consecutive
            System.out.println("Next Close Bracket:" + nextCloseBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            System.out.println("Open Parenthesis:" + openParen);
            int closeParen = markdown.indexOf(")", openParen);
            System.out.println("Close Parenthesis:" + closeParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
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