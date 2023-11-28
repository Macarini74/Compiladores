/*
    Integrantes:
    Iago Macarini Brito RA: 2320665
    João Pedro de Paula RA: 2208458
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GyhLex lex = new GyhLex("C:\\Users\\Administrator\\IdeaProjects\\Compiladores\\src\\programa11.gyh");
        Token t;
        while((t = lex.nextToken()) != null) {
            System.out.println(t.toString() + " ");
        }

        GyhSint sint = new GyhSint(lex.tokenList, lex.lineList);
        //sint.printTokenList();
        sint.startSint();
    }
}
