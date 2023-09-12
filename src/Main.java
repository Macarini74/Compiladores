import com.sun.security.jgss.GSSUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GyhLex lex = new GyhLex("C:\\Users\\Administrator\\IdeaProjects\\Compiladores\\src\\programa1.gyh");
        Token t;
        while((t = lex.nextToken()) != null) {
            System.out.println(t.toString() + " ");
        }




        //System.out.println("\n\nRemanejar");
    }
}
