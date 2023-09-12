public class GyhLex {
    public ArchiveReader rdat;

    private char c;
    private int aux;

    private int lines = 1;

    private String tempString ;

    public GyhLex(String arqName) {
        this.rdat = new ArchiveReader(arqName);
        this.aux = 1;
    }

    public Token nextToken() {

        int nextChar = -1;

        while((nextChar = rdat.readNextChar()) != -1) {
            c = (char)nextChar;
            if(c == '\n') this.lines += 1;

            switch (aux){
                case 1:

                    switch(c){
                        case '[':
                            return (new Token("[", TokenType.IniDelim, this.lines));
                        case ']':
                            return (new Token("]", TokenType.FimDelim, this.lines));
                        case '*':
                            return(new Token("*", TokenType.OpAritMult, this.lines));
                        case '/':
                            return (new Token("/", TokenType.OpAritDiv, this.lines));
                        case '+':
                            return (new Token("+", TokenType.OpAritSoma, this.lines));
                        case '-':
                            return  (new Token("-", TokenType.OpAritSub, this.lines));
                        case '(':
                            return (new Token("(", TokenType.AbrePar, this.lines));
                        case ')':
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
                        default:
                            if(Character.isUpperCase(c)){
                                this.tempString = "";
                                this.tempString = this.tempString + c;
                                this.aux = 44;
                            }
                            if(Character.isDigit(c)){
                                this.tempString = "";
                                this.tempString += c;
                                this.aux = 45;
                                break;
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
                            this.aux = 1;
                    }
                    break;
                case 3:
                    switch(c){
                        case 'c':
                            this.aux = 1;
                            return (new Token("dec", TokenType.PCDec, this.lines));
                        default:
                            System.out.println("Error. de\n\n");
                            this.aux = 1;
                    }
                    break;
                case 4:
                    switch (c){
                        case 'r':
                            this.aux = 5;
                            break;
                        default:
                            System.out.println("Error. P\\n");
                            this.aux = 1;
                    }
                    break;
                case 5:
                    switch (c){
                        case 'o':
                            this.aux = 6;
                            break;
                        default:
                            System.out.println("Error. PR\\n");
                            this.aux = 1;
                    }
                    break;
                case 6:
                    switch (c){
                        case 'g':
                            this.aux = 1;
                            return (new Token("prog", TokenType.PCProg, this.lines));
                        case 'i':
                            this.aux = 20;
                            break;
                        default:
                            System.out.println("Error. PRO\\n");
                            this.aux = 1;
                    }
                    break;
                case 7:
                    switch (c){
                        case 'n':
                            this.aux = 8;
                            break;
                        case 'f':
                            this.aux = 1;
                            return (new Token("if", TokenType.PCSe, 1));
                        default:
                            System.out.println("Error. I\\n");
                            this.aux = 1;
                    }
                    break;
                case 8:
                    switch (c){
                        case 't':
                            this.aux = 9;
                            break;
                        default:
                            System.out.println("Error. in\n");
                            this.aux = 1;
                    }
                    break;
                case 9:
                    switch (c){
                        case 'e':
                            this.aux = 10;
                            break;
                        default:
                            System.out.println("Error. int\\n");
                            this.aux = 1;
                    }
                    break;
                case 10:
                    switch (c){
                        case 'g':
                            this.aux = 11;
                            break;
                        default:
                            System.out.println("Error. inte\\n");
                            this.aux = 1;
                    }
                    break;
                case 11:
                    switch (c){
                        case 'e':
                            this.aux = 12;
                            break;
                        default:
                            System.out.println("Error. integ\\n");
                            this.aux = 1;
                    }
                    break;
                case 12:
                    switch (c){
                        case 'r':
                            this.aux = 1;
                            return (new Token("PCInt", TokenType.PCInt, this.lines));
                        default:
                            System.out.println("Error. intege\\n");
                            this.aux = 1;
                    }
                    break;
                case 13:
                    switch (c){
                        case 'l':
                            this.aux = 14;
                            break;
                        default:
                            System.out.println("Error. f\\n");
                            this.aux = 1;
                    }
                    break;
                case 14:
                    switch (c){
                        case 'o':
                            this.aux = 15;
                            break;
                        default:
                            System.out.println("Error. fl\\n");
                            this.aux = 1;
                    }
                    break;
                case 15:
                    switch (c){
                        case 'a':
                            this.aux = 16;
                            break;
                        default:
                            System.out.println("Error. flo\\n");
                            this.aux = 1;
                    }
                    break;
                case 16:
                    switch (c){
                        case 't':
                            this.aux = 1;
                            return (new Token("PCReal", TokenType.PCReal, this.lines));
                        default:
                            System.out.println("Error. floa\\n");
                            this.aux = 1;
                    }
                    break;
                case 17:
                    switch (c){
                        case 'e':
                            this.aux = 18;
                            break;
                        default:
                            System.out.println("Error. r\\n");
                            this.aux = 1;
                    }
                    break;
                case 18:
                    switch (c){
                        case 'a':
                            this.aux = 19;
                            break;
                        default:
                            System.out.println("Error. re\\n");
                            this.aux = 1;
                    }
                    break;
                case 19:
                    switch (c){
                        case 'd':
                            this.aux = 1;
                            return (new Token("PCLer", TokenType.PCLer, this.lines));
                        default:
                            System.out.println("Error. rea\\n");
                            this.aux = 1;
                    }
                    break;
                case 20:
                    switch (c){
                        case 'n':
                            this.aux = 21;
                            break;
                        default:
                            System.out.println("Error. pri\\n");
                            this.aux = 1;
                    }
                    break;
                case 21:
                    switch (c){
                        case 't':
                            this.aux = 1;
                            return (new Token("PCImprimir", TokenType.PCImprimir, this.lines));
                        default:
                            System.out.println("Error. prin\\n");
                            this.aux = 1;
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
                            System.out.println("Error. e\\n");
                            this.aux = 1;
                    }
                    break;
                case 23:
                    switch (c){
                        case 's':
                            this.aux = 24;
                            break;
                        default:
                            System.out.println("Error. el\\n");
                            this.aux = 1;
                    }
                    break;
                case 24:
                    switch (c){
                        case 'e':
                            this.aux = 1;
                            return (new Token("PCSenao", TokenType.PCSenao, this.lines));
                        default:
                            System.out.println("Error. els\\n");
                            this.aux = 1;
                    }
                    break;
                case 25:
                    switch (c){
                        case 'h':
                            this.aux = 26;
                            break;
                        default:
                            System.out.println("Error. t\\n");
                            this.aux = 1;
                    }
                    break;
                case 26:
                    switch (c){
                        case 'e':
                            this.aux = 27;
                            break;
                        default:
                            System.out.println("Error. th\\n");
                            this.aux = 1;
                    }
                    break;
                case 27:
                    switch (c){
                        case 'n':
                            this.aux = 1;
                            return (new Token("PCEntao", TokenType.PCEntao, this.lines));
                        default:
                            System.out.println("Error. the\\n");
                            this.aux = 1;
                    }
                case 28:
                    switch (c){
                        case 'h':
                            this.aux = 29;
                            break;
                        default:
                            System.out.println("Error. w\\n");
                            this.aux = 1;
                    }
                    break;
                case 29:
                    switch (c){
                        case 'i':
                            this.aux = 30;
                            break;
                        default:
                            System.out.println("Error. wh\\n");
                            this.aux = 1;
                    }
                    break;
                case 30:
                    switch (c){
                        case 'l':
                            this.aux = 31;
                            break;
                        default:
                            System.out.println("Error. whi\\n");
                            this.aux = 1;
                    }
                    break;
                case 31:
                    switch (c){
                        case 'e':
                            this.aux = 1;
                            return (new Token("PCEnqto", TokenType.PCEnqto, this.lines));
                        default:
                            System.out.println("Error. whil\\n");
                            this.aux = 1;
                    }
                    break;
                case 32:
                    switch (c){
                        case 't':
                            this.aux = 33;
                            break;
                        default:
                            System.out.println("Error. s\\n");
                            this.aux = 1;
                    }
                    break;
                case 33:
                    switch (c){
                        case 'a':
                            this.aux = 34;
                            break;
                        default:
                            System.out.println("Error. st\\n");
                            this.aux = 1;
                    }
                    break;
                case 34:
                    switch (c){
                        case 'r':
                            this.aux = 35;
                            break;
                        default:
                            System.out.println("Error. sta\\n");
                            this.aux = 1;
                    }
                    break;
                case 35:
                    switch (c){
                        case 't':
                            this.aux = 1;
                            return (new Token("PCIni", TokenType.PCIni, this.lines));
                        default:
                            System.out.println("Error, star\\n");
                            this.aux = 1;
                    }
                    break;
                case 36:
                    switch (c){
                        case 'd':
                            this.aux = 1;
                            return (new Token("PCFim", TokenType.PCFim, this.lines));
                        default:
                            System.out.println("Error, en\\n");
                            this.aux = 1;
                    }
                    break;
                case 38:
                    switch (c){
                        case '=':
                            this.aux = 1;
                            return (new Token("OpRelMenorIgual", TokenType.OpRelMenorIgual, this.lines));
                        case '>':
                            this.aux = 1;
                            return (new Token("OpRelDif", TokenType.OpRelDif, this.lines));
                        case '<':
                            this.aux = 1;
                            return (new Token("Atrib", TokenType.Atrib, this.lines));
                        default:
                            this.aux = 1;
                            return (new Token("OpRelMenor", TokenType.OpRelMenor, this.lines));
                    }

                case 39:
                    switch (c){
                        case '=':
                            this.aux = 1;
                            return (new Token("OpRelMaiorIgual", TokenType.OpRelMaiorIgual, this.lines));
                        default:
                            this.aux = 1;
                            return (new Token("OpRelMaior", TokenType.OpRelMaior, this.lines));
                    }
                case 40:
                    switch (c){
                        case '=':
                            this.aux = 1;
                            return (new Token("OpRelIgual", TokenType.OpRelIgual, this.lines));
                        default:
                            System.out.println("Error. =\\n");
                            this.aux = 1;
                    }
                    break;
                case 41:
                    switch (c){
                        case 'n':
                            this.aux = 42;
                            break;
                        default:
                            System.out.println("Error. a\\n");
                            this.aux = 1;
                    }
                    break;
                case 42:
                    switch (c){
                        case 'd':
                            this.aux = 1;
                            return (new Token("OpBoolE", TokenType.OpBoolE, this.lines));
                        default:
                            System.out.println("Error. an\\n");
                            this.aux = 1;
                    }
                    break;
                case 43:
                    switch (c){
                        case 'r':
                            this.aux = 1;
                            return (new Token("OpBoolOu", TokenType.OpBoolOu, this.lines));
                        default:
                            System.out.println("Error. o\\n");
                            this.aux = 1;
                    }
                    break;
                case 44:
                    if(Character.isLetter(c)){
                        this.tempString += c;
                        this.aux = 44;
                        break;
                    }else if(!(Character.isLetter(c))){
                        this.aux = 1;
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
                        return (new Token(this.tempString, TokenType.NumInt, this.lines));
                    }

                case 46:
                    if(Character.isDigit(c)){
                        this.tempString += c;
                        this.aux = 46;
                        break;
                    }else if(c == ' '){
                        this.aux = 1;
                        return (new Token(this.tempString, TokenType.NumReal, this.lines));
                    }


            }


        }

        return null;
    }
}
