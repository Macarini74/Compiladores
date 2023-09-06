public class Token {
    public String lexema;
    public TokenType source;
    public int line;

    public Token(String lex, TokenType src, int row){
        this.lexema = lex;
        this.source = src;
        this.line = row;
    }

    public String toString(){
        return "<" + this.lexema + ", " + this.source + ", " + this.line + ">";
    }
}
