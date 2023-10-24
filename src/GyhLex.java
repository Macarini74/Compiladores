import java.util.ArrayList;
import java.util.List;

public class GyhLex {
    public ArchiveReader rdat;
    ArrayList<String> tokenList = new ArrayList<String>();
    ArrayList<Integer> lineList = new ArrayList<Integer>();

    private char c;
    private int aux;
    private char ch;

    private int lines = 1;

    private String tempString ;

    public GyhLex(String arqName) {
        this.rdat = new ArchiveReader(arqName);
        this.aux = 1;
    }

    //aux -> seria tipo os estados de um autômato
    //stringTemp -> guarda os valores do char para mostras os números (reais/int) e strings (variável/palavra reservada)

    public Token nextToken() {

        int nextChar = -1;

        while((nextChar = rdat.readNextChar()) != -1) {
            c = (char)nextChar;
            if(c == '\n') this.lines += 1;

            switch (aux){
                case 1:
                //Caso padrão para selecionar as condições de tokens
                    switch(c){
                        case '[':
                            this.tokenList.add("IniDelim");
                            this.lineList.add(lines);
                            return (new Token("[", TokenType.IniDelim, this.lines));
                        case ']':
                            this.tokenList.add("FimDelim");
                            this.lineList.add(lines);
                            return (new Token("]", TokenType.FimDelim, this.lines));
                        case '*':
                            this.tokenList.add("OpAritMult");
                            this.lineList.add(lines);
                            return(new Token("*", TokenType.OpAritMult, this.lines));
                        case '/':
                            this.tokenList.add("OpAritDiv");
                            this.lineList.add(lines);
                            return (new Token("/", TokenType.OpAritDiv, this.lines));
                        case '+':
                            this.tokenList.add("OpAritSoma");
                            this.lineList.add(lines);
                            return (new Token("+", TokenType.OpAritSoma, this.lines));
                        case '-':
                            this.tokenList.add("OpAritSub");
                            this.lineList.add(lines);
                            return  (new Token("-", TokenType.OpAritSub, this.lines));
                        case '(':
                            this.tokenList.add("AbrePar");
                            this.lineList.add(lines);
                            return (new Token("(", TokenType.AbrePar, this.lines));
                        case ')':
                            this.tokenList.add("FechaPar");
                            this.lineList.add(lines);
                            return (new Token(")", TokenType.FechaPar, this.lines));
                        case '<':
                            this.aux = 38;
                            break;
                        case '>':
                            this.aux = 39;
                            break;
                        case '=':
                            this.aux = 40;
                            break;
                        case 'd':
                            this.aux = 2;
                            break;
                        case 'p':
                            this.aux = 4;
                            break;
                        case 'i':
                            this.aux = 7;
                            break;
                        case 'f':
                            this.aux = 13;
                            break;
                        case 'r':
                            this.aux = 17;
                            break;
                        case 'e':
                            this.aux = 22;
                            break;
                        case 't':
                            this.aux = 25;
                            break;
                        case 'w':
                            this.aux = 28;
                            break;
                        case 's':
                            this.aux = 32;
                            break;
                        case 'a':
                            this.aux = 41;
                            break;
                        case 'o':
                            this.aux = 43;
                            break;
                        case '#':
                            this.aux = 47;
                            break;
                        default:
                            if(Character.isUpperCase(c)){
                                this.tempString = "";
                                this.tempString += c;
                                this.aux = 44;
                            }
                            if(Character.isDigit(c)){
                                this.tempString = "";
                                this.tempString += c;
                                this.aux = 45;
                                break;
                            }
                            if(c == '"'){
                                this.tempString = "";
                                this.aux = 48;
                            }

                    }
                    break;
                case 2:
                    switch(c){
                        case 'e':
                            this.aux = 3;
                            break;
                        default:
                            System.out.println("Error. d\\n");
                            System.exit(0);
                    }
                    break;
                case 3:
                    switch(c){
                        case 'c':
                            this.aux = 1;
                            this.lineList.add(lines);
                            this.tokenList.add("PCDec");
                            return (new Token("dec", TokenType.PCDec, this.lines));
                        default:
                            System.out.println("Erro Léxico. de\n\n");
                            System.exit(0);
                    }
                    break;
                case 4:
                    switch (c){
                        case 'r':
                            this.aux = 5;
                            break;
                        default:
                            System.out.println("Error. P\\n");
                            System.exit(0);
                    }
                    break;
                case 5:
                    switch (c){
                        case 'o':
                            this.aux = 6;
                            break;
                        case 'i':
                            this.aux = 20;
                            break;
                        default:
                            System.out.println("Erro Léxico. PR\\n");
                            System.exit(0);
                    }
                    break;
                case 6:
                    switch (c){
                        case 'g':
                            this.aux = 1;
                            this.lineList.add(lines);
                            this.tokenList.add("PCProg");
                            return (new Token("prog", TokenType.PCProg, this.lines));
                        case 'i':
                            this.aux = 20;
                            break;
                        default:
                            System.out.println("Erro Léxico. PRO\\n");
                            System.exit(0);
                    }
                    break;
                case 7:
                    switch (c){
                        case 'n':
                            this.aux = 8;
                            break;
                        case 'f':
                            this.aux = 1;
                            this.lineList.add(lines);
                            this.tokenList.add("PCSe");
                            return (new Token("if", TokenType.PCSe, this.lines));
                        default:
                            System.out.println("Erro Léxico. i\\n");
                            System.exit(0);
                    }
                    break;
                case 8:
                    switch (c){
                        case 't':
                            this.aux = 9;
                            break;
                        default:
                            System.out.println("Erro Léxico. in\n");
                            System.exit(0);
                    }
                    break;
                case 9:
                    switch (c){
                        case 'e':
                            this.aux = 10;
                            break;
                        default:
                            System.out.println("Erro Léxico. int\\n");
                            System.exit(0);
                    }
                    break;
                case 10:
                    switch (c){
                        case 'g':
                            this.aux = 11;
                            break;
                        default:
                            System.out.println("Erro Léxico. inte\\n");
                            System.exit(0);
                    }
                    break;
                case 11:
                    switch (c){
                        case 'e':
                            this.aux = 12;
                            break;
                        default:
                            System.out.println("Erro Léxico. integ\\n");
                            System.exit(0);
                    }
                    break;
                case 12:
                    switch (c){
                        case 'r':
                            this.aux = 1;
                            this.tokenList.add("PCInt");
                            this.lineList.add(lines);
                            return (new Token("PCInt", TokenType.PCInt, this.lines));
                        default:
                            System.out.println("Erro Léxico. intege\\n");
                            System.exit(0);
                    }
                    break;
                case 13:
                    switch (c){
                        case 'l':
                            this.aux = 14;
                            break;
                        default:
                            System.out.println("Erro Léxico. f\\n");
                            System.exit(0);
                    }
                    break;
                case 14:
                    switch (c){
                        case 'o':
                            this.aux = 15;
                            break;
                        default:
                            System.out.println("Erro Léxico. fl\\n");
                            System.exit(0);
                    }
                    break;
                case 15:
                    switch (c){
                        case 'a':
                            this.aux = 16;
                            break;
                        default:
                            System.out.println("Erro Léxico. flo\\n");
                            System.exit(0);
                    }
                    break;
                case 16:
                    switch (c){
                        case 't':
                            this.aux = 1;
                            this.tokenList.add("PCReal");
                            this.lineList.add(lines);
                            return (new Token("PCReal", TokenType.PCReal, this.lines));
                        default:
                            System.out.println("Erro Léxico. floa\\n");
                            System.exit(0);
                    }
                    break;
                case 17:
                    switch (c){
                        case 'e':
                            this.aux = 18;
                            break;
                        default:
                            System.out.println("Erro Léxico. r\\n");
                            System.exit(0);
                    }
                    break;
                case 18:
                    switch (c){
                        case 'a':
                            this.aux = 19;
                            break;
                        default:
                            System.out.println("Erro Léxico. re\\n");
                            System.exit(0);
                    }
                    break;
                case 19:
                    switch (c){
                        case 'd':
                            this.aux = 1;
                            this.tokenList.add("PCLer");
                            this.lineList.add(lines);
                            return (new Token("PCLer", TokenType.PCLer, this.lines));
                        default:
                            System.out.println("Erro Léxico. rea\\n");
                            System.exit(0);
                    }
                    break;
                case 20:
                    switch (c){
                        case 'n':
                            this.aux = 21;
                            break;
                        default:
                            System.out.println("Erro Léxico. pri\\n");
                            System.exit(0);
                    }
                    break;
                case 21:
                    switch (c){
                        case 't':
                            this.aux = 1;
                            this.tokenList.add("PCImprimir");
                            this.lineList.add(lines);
                            return (new Token("PCImprimir", TokenType.PCImprimir, this.lines));
                        default:
                            System.out.println("Erro Léxico. prin\\n");
                            System.exit(0);
                    }
                    break;
                case 22:
                    switch (c){
                        case 'l':
                            this.aux = 23;
                            break;
                        case 'n':
                            this.aux = 36;
                            break;
                        default:
                            System.out.println("Erro Léxico. e\\n");
                            System.exit(0);
                    }
                    break;
                case 23:
                    switch (c){
                        case 's':
                            this.aux = 24;
                            break;
                        default:
                            System.out.println("Erro Léxico. el\\n");
                            System.exit(0);
                    }
                    break;
                case 24:
                    switch (c){
                        case 'e':
                            this.aux = 1;
                            this.tokenList.add("PCSenao");
                            this.lineList.add(lines);
                            return (new Token("PCSenao", TokenType.PCSenao, this.lines));
                        default:
                            System.out.println("Erro Léxico. els\\n");
                            System.exit(0);
                    }
                    break;
                case 25:
                    switch (c){
                        case 'h':
                            this.aux = 26;
                            break;
                        default:
                            System.out.println("Erro Léxico. t\\n");
                            System.exit(0);
                    }
                    break;
                case 26:
                    switch (c){
                        case 'e':
                            this.aux = 27;
                            break;
                        default:
                            System.out.println("Erro Léxico. th\\n");
                            System.exit(0);
                    }
                    break;
                case 27:
                    switch (c){
                        case 'n':
                            this.aux = 1;
                            this.tokenList.add("PCEntao");
                            this.lineList.add(lines);
                            return (new Token("PCEntao", TokenType.PCEntao, this.lines));
                        default:
                            System.out.println("Erro Léxico. the\\n");
                            System.exit(0);
                    }
                case 28:
                    switch (c){
                        case 'h':
                            this.aux = 29;
                            break;
                        default:
                            System.out.println("Erro Léxico. w\\n");
                            System.exit(0);
                    }
                    break;
                case 29:
                    switch (c){
                        case 'i':
                            this.aux = 30;
                            break;
                        default:
                            System.out.println("Erro Léxico. wh\\n");
                            System.exit(0);
                    }
                    break;
                case 30:
                    switch (c){
                        case 'l':
                            this.aux = 31;
                            break;
                        default:
                            System.out.println("Erro Léxico. whi\\n");
                            System.exit(0);
                    }
                    break;
                case 31:
                    switch (c){
                        case 'e':
                            this.aux = 1;
                            this.tokenList.add("PCEnqto");
                            this.lineList.add(lines);
                            return (new Token("PCEnqto", TokenType.PCEnqto, this.lines));
                        default:
                            System.out.println("Erro Léxico. whil\\n");
                            System.exit(0);
                    }
                    break;
                case 32:
                    switch (c){
                        case 't':
                            this.aux = 33;
                            break;
                        default:
                            System.out.println("Erro Léxico. s\\n");
                            System.exit(0);
                    }
                    break;
                case 33:
                    switch (c){
                        case 'a':
                            this.aux = 34;
                            break;
                        default:
                            System.out.println("Erro Léxico. st\\n");
                            System.exit(0);
                    }
                    break;
                case 34:
                    switch (c){
                        case 'r':
                            this.aux = 35;
                            break;
                        default:
                            System.out.println("Erro Léxico. sta\\n");
                            System.exit(0);
                    }
                    break;
                case 35:
                    switch (c){
                        case 't':
                            this.aux = 1;
                            this.tokenList.add("PCIni");
                            this.lineList.add(lines);
                            return (new Token("PCIni", TokenType.PCIni, this.lines));
                        default:
                            System.out.println("Erro Léxico, star\\n");
                            System.exit(0);
                    }
                    break;
                case 36:
                    switch (c){
                        case 'd':
                            this.aux = 1;
                            this.tokenList.add("PCFim");
                            this.lineList.add(lines);
                            return (new Token("PCFim", TokenType.PCFim, this.lines));
                        default:
                            System.out.println("Erro Léxico, en\\n");
                            System.exit(0);
                    }
                    break;
                case 38:

                    switch (c){
                        case '=':
                            this.aux = 1;
                            this.tokenList.add("OpRelMenorIgual");
                            this.lineList.add(lines);
                            return (new Token("OpRelMenorIgual", TokenType.OpRelMenorIgual, this.lines));
                        case '>':
                            this.aux = 1;
                            this.tokenList.add("OpRelDif");
                            this.lineList.add(lines);
                            return (new Token("OpRelDif", TokenType.OpRelDif, this.lines));
                        case '<':
                            //System.out.println(this.tempString + "-" + this.c);
                            this.aux = 1;
                            this.tokenList.add("Atrib");
                            this.lineList.add(lines);
                            return (new Token("Atrib", TokenType.Atrib, this.lines));
                        default:
                            this.aux = 1;
                            this.tokenList.add("OpRelMenor");
                            this.lineList.add(lines);
                            return (new Token("OpRelMenor", TokenType.OpRelMenor, this.lines));
                    }

                case 39:
                    switch (c){
                        case '=':
                            this.aux = 1;
                            this.tokenList.add("OpRelMaiorIgual");
                            this.lineList.add(lines);
                            return (new Token("OpRelMaiorIgual", TokenType.OpRelMaiorIgual, this.lines));
                        default:
                            this.aux = 1;
                            this.tokenList.add("OpRelMaior");
                            this.lineList.add(lines);
                            return (new Token("OpRelMaior", TokenType.OpRelMaior, this.lines));
                    }
                case 40:
                    switch (c){
                        case '=':
                            this.aux = 1;
                            this.tokenList.add("OpRelIgual");
                            this.lineList.add(lines);
                            return (new Token("OpRelIgual", TokenType.OpRelIgual, this.lines));
                        default:
                            System.out.println("Error. =\\n");
                            System.exit(0);
                    }
                    break;
                case 41:
                    switch (c){
                        case 'n':
                            this.aux = 42;
                            break;
                        default:
                            System.out.println("Error. a\\n");
                            System.exit(0);
                    }
                    break;
                case 42:
                    switch (c){
                        case 'd':
                            this.aux = 1;
                            this.tokenList.add("OpBoolE");
                            this.lineList.add(lines);
                            return (new Token("OpBoolE", TokenType.OpBoolE, this.lines));
                        default:
                            System.out.println("Error. an\\n");
                            System.exit(0);
                    }
                    break;
                case 43:
                    switch (c){
                        case 'r':
                            this.aux = 1;
                            this.tokenList.add("OpBoolOu");
                            this.lineList.add(lines);
                            return (new Token("OpBoolOu", TokenType.OpBoolOu, this.lines));
                        default:
                            System.out.println("Erro Léxico. o\\n");
                            System.exit(0);
                    }
                    break;
                case 44:
                    if(Character.isLetter(c)){
                            this.tempString += c;
                            this.aux = 44;

                }else if(!(Character.isLetter(c))){
                        if(c == '['){
                            this.aux = 1;
                            this.tokenList.add("Var");
                            this.lineList.add(lines);
                            this.tokenList.add("IniDelim");
                            this.lineList.add(lines);
                        }
                                this.aux = 1;
                                this.tokenList.add("Var");
                                this.lineList.add(lines);
                                return (new Token(this.tempString, TokenType.Var, this.lines));
                        }
                    break;
                case 45:
                     if(Character.isDigit(c)){
                        this.tempString = this.tempString + c;
                        this.aux = 45;
                        break;
                    }else if(c == '.'){
                        this.tempString += c;
                        this.aux = 46;
                        break;
                    }else if(c == ' '){
                        this.aux = 1;
                        this.lineList.add(lines);
                        this.tokenList.add("NumInt");
                        return (new Token(this.tempString, TokenType.NumInt, this.lines));
                    }else if(c == ')'){
                        this.aux = 1;
                        this.tokenList.add("NumInt");
                        this.lineList.add(lines);
                        this.tokenList.add("FechaPar");
                        this.lineList.add(lines);
                        break;
                }

                case 46:
                    if(Character.isDigit(c)){
                        this.tempString += c;
                        this.aux = 46;
                        break;
                    }else if(c == ' '){
                        this.aux = 1;
                        this.lineList.add(lines);
                        this.tokenList.add("NumReal");
                        return (new Token(this.tempString, TokenType.NumReal, this.lines));
                    }
                case 47:
                    if(c == '\n'){
                        this.aux = 1;
                        break;
                    }else{
                        this.aux = 47;
                    }
                case 48:
                    if (c == '"') {
                        this.aux = 1;
                        this.lineList.add(lines);
                        this.tokenList.add("Cadeia");
                        return(new Token(this.tempString, TokenType.Cadeia, this.lines));
                    } else if (nextChar == -1) {
                        System.out.println("Erro Léxico. Aspas não fechadas corretamente.");
                        System.exit(0);
                    } else {
                        this.tempString += c;
                    }
                    break;
            }
        }
            return null;
    }
}
