import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
  
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    

    @Test
    public void testSnippet1() throws IOException{
        String contents = Files.readString(Path.of("./snippet1.md"));
        List<String> expect = List.of("`google.com", 
            "google.com", "ucsd.edu" );
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
     
    

   
    @Test
    public void testSnippet2() throws IOException{
        String contents = Files.readString(Path.of("./snippet2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
     

    
    @Test
    public void testSnippet3() throws IOException{
        String contents = Files.readString(Path.of("./snippet3.md"));
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    
}
