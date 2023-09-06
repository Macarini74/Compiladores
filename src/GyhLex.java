public class GyhLex {
    private char aux;
    public ArchiveReader rdat;

    private char c;

    public GyhLex(String arqName) {
        this.rdat = new ArchiveReader(arqName);
    }

    public Token nextToken() {
        int nextChar = -1;

        while((nextChar = rdat.readNextChar()) != -1) {
            c = (char)nextChar;
            if(c == ' ' || c == '\n') continue;

            if(c == '=') {
                if(c == aux) {
                    return (new Token("==", TokenType.OpRelIgual, 1));
                }else if(aux == '>'){
                    return (new Token(">=", TokenType.OpRelMaiorIgual, 1));
                }else if(aux == '<'){
                    return(new Token("<=", TokenType.OpRelMenorIgual, 1));
                }
            }

            if(Character.isDigit(c)){
                String ch = ""+c;
                if((c = (char)rdat.readNextChar()) == '.'){
                    if(Character.isDigit(c = (char)rdat.readNextChar())){
                        //Falta passar o número Real como parâmetro para printar (ex: 1.1)
                        return (new Token(ch, TokenType.NumReal, 1));
                    }
                }else {
                    return (new Token(ch, TokenType.NumInt, 1));
                }
            }

            //Falta verifica a parte dos caracteres
            //Se é uma cadeia ou uma variável
                //Se a primeira letra é maiúscula -> Variável
                //Do contrário -> cadeia de caracteres

            switch(c) {
                case '+' : return (new Token("+", TokenType.OpAritSoma, 1));
                case '-' : return (new Token("-", TokenType.OpAritSub, 1));
                case '/' : return (new Token("/", TokenType.OpAritDiv, 1));
                case '*' : return (new Token("*", TokenType.OpAritMult, 1));
                case '[' : return (new Token("[", TokenType.IniDelim, 1));
                case ']' : return (new Token("]", TokenType.FimDelim, 1));
                case '(' : return (new Token("(", TokenType.AbrePar, 1));
                case ')' : return (new Token("(", TokenType.FechaPar, 1));
                case '>' : return (new Token(">", TokenType.OpRelMaior, 1));
                default: System.out.println(c);
            }

            if(c == '<') {
                if((c = (char)rdat.readNextChar()) == '<') {
                    return (new Token("<<", TokenType.Atrib, 1));
                }else if((c = (char)rdat.readNextChar()) == ' ' || (c = (char)nextChar) == '\n') {
                    return (new Token("<", TokenType.OpRelMenor, 1));
                }else if((c = (char)rdat.readNextChar()) == '>') {
                    return (new Token("<>", TokenType.OpRelDif, 1));
                }
            }

            aux = c;
        }

        return null;
    }
}
