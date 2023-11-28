import java.util.ArrayList;
import java.util.Iterator;

public class GyhSint {

    ArrayList<String> tokens = new ArrayList<>();
    ArrayList<Integer> line = new ArrayList<>();
    int index;
    public GyhSint(ArrayList<String> tokenList, ArrayList<Integer> lineList){
        this.tokens = tokenList;
        this.index = 0;
        this.line = lineList;
    }

    public void printTokenList(){
        for(int i = 0; i < tokens.size(); i++){
            System.out.println(tokens.get(i) + " " + line.get(i));
        }
    }
    //Programa → '[' 'dec'']' ListaDeclaracoes '[' 'prog' ']' ListaComandos;
    public void startSint(){
        if(this.tokens.get(0).equals("IniDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("PCDec")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(2).equals("FimDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        listaDeclaracoes();

        if(this.tokens.get(index).equals("IniDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }
        if(this.tokens.get(index).equals("PCProg")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }
        if(this.tokens.get(index).equals("FimDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }
        listaComandos();

    }

    //ListaDeclaracoes → Declaracao ListaDeclaracoes'';
    public void listaDeclaracoes(){
        declaracao();
        listaDeclaracoes2();
    }

    //Declaracao → VARIAVEL '[' TipoVar ']';
    public void declaracao(){
        if(this.tokens.get(index).equals("Var")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("IniDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            tipoVar();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("FimDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

    }

    //ListaDeclaracoes' → ListaDeclaracoes| simboloVazio ;
    public void listaDeclaracoes2(){
        if(this.tokens.get(index).equals("Var")){
            listaDeclaracoes();
        }
    }



    //TipoVar → 'integer' | 'float';
    public void tipoVar(){
        if(this.tokens.get(index).equals("PCInt")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else if(this.tokens.get(index).equals("PCReal")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }
    }

    //ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica'' ;
    public void expressaoAritmetica(){
        termoAritmetico();
        if(this.tokens.get(index).equals("OpAritSoma") || this.tokens.get(index).equals("OpAritSub")){
            expressaoAritmetica2();
        }

    }
    //ExpressaoAritmetica'' → '+' TermoAritmetico ExpressaoAritmetica''|'-' TermoAritmetico ExpressaoAritmetica''| simboloVazio ;
    public void expressaoAritmetica2(){
        System.out.println("Tokeneeeee: " + this.tokens.get(index));
        if(this.tokens.get(index).equals("OpAritSoma")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            termoAritmetico();
            expressaoAritmetica2();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("OpAritSub")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            termoAritmetico();
            expressaoAritmetica2();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }
    }

    //TermoAritmetico → FatorAritmetico TermoAritmetico'';
    public void termoAritmetico(){
        fatorAritmetico();
        if(this.tokens.get(index).equals("OpAritMul") || this.tokens.get(index).equals("OpAritDiv")){
            termoAritmetico2();
        }
    }
    //TermoAritmetico'' → '*' FatorAritmetico TermoAritmetico''|'/' FatorAritmetico TermoAritmetico'' | simboloVazio ;
    public void termoAritmetico2(){
        if(this.tokens.get(index).equals("OpAritMult")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            fatorAritmetico();
            termoAritmetico2();
        }else {
            System.out.println("Entrando: " + this.tokens.get(index));
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("OpAritDiv")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            fatorAritmetico();
            termoAritmetico2();
        }else {
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }
    }

    //FatorAritmetico → NUMINT| NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')' ;
    public void fatorAritmetico(){
        System.out.println("IDnsad" + this.tokens.get(index));
        switch(this.tokens.get(index)){
            case "NumInt":
            case "NumReal":
            case "Var":
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
                break;
            case "AbrePar":
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
                expressaoAritmetica();
                if(this.tokens.get(index).equals("FechaPar")){
                    System.out.println("Token: " + this.tokens.get(index) + "\n");
                    index++;
                }else{
                    System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "Token: " + this.tokens.get(index) + "\n");
                    System.exit(0);
                }
                break;
            default:
                System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "Token: " + this.tokens.get(index) + "\n");
                System.exit(0);

        }
    }

    //ExpressaoRelacional → TermoRelacional ExpressaoRelacional'';
    public void expressaoRelacional(){

        termoRelacional();
        expressaoRelacional2();
    }
    //ExpressaoRelacional'' → OperadorBooleano TermoRelacional ExpressaoRelacional'' | simboloVazio ;
    public void expressaoRelacional2(){
        if(operadorBooleano()){
            termoRelacional();
            expressaoRelacional2();
        }

    }
    //TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')';
    public void termoRelacional(){
        switch (this.tokens.get(index)){
            case "NumInt":
            case "NumReal":
            case "Var":
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
                switch (this.tokens.get(index)) {
                    case "OpRelMenor", "OpRelMenorIgual", "OpRelMaior", "OpRelMaiorIgual", "OpRelIgual", "OpRelDif" -> {
                        System.out.println("Token: " + this.tokens.get(index) + "\n");
                        index++;
                        expressaoAritmetica();
                    }
                    default -> {
                        System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "Token: " + this.tokens.get(index) + "\n");
                        System.exit(0);
                    }
                }
                break;
            case "AbrePar":
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
                switch (this.tokens.get(index)) {
                    case "OpRelMenor", "OpRelMenorIgual", "OpRelMaior", "OpRelMaiorIgual", "OpRelIgual", "OpRelDif" -> {
                        System.out.println("Token: " + this.tokens.get(index) + "\n");
                        index++;
                        expressaoAritmetica();
                    }
                    default -> {
                        expressaoRelacional();
                        System.out.println("Token: " + this.tokens.get(index) + "\n");
                        index++;
                        if (this.tokens.get(index).equals("FechaPar")) {
                            System.out.println("Token: " + this.tokens.get(index) + "\n");
                            index++;
                        } else {
                            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "Token: " + this.tokens.get(index) + "\n");
                            System.exit(0);
                        }
                    }
                }
        }
    }

    //OperadorBooleano → 'or' | 'and';
    public Boolean operadorBooleano(){
        if(this.tokens.get(index).equals("OpBoolE")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            return true;
        }else if(this.tokens.get(index).equals("OpBoolOu")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            return true;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            return false;
        }
    }

    //ListaComandos → Comando ListaComandos'' ;
    public void listaComandos(){
        comando();
        listaComandos2();
    }
    //ListaComandos'' → ListaComandos| simboloVazio ;
    public void listaComandos2(){
        switch (this.tokens.get(index)) {
            case "Var" -> listaComandos();
            case "PCLer" -> listaComandos();
            case "PCImprimir" -> listaComandos();
            case "PCEnqto" -> listaComandos();
            case "PCSe" -> listaComandos();
            case "PCIni" -> listaComandos();
        }
    }


    //Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
    public void comando(){
        switch (this.tokens.get(index)) {
            case "Var" -> comandoAtribuicao();
            case "PCLer" -> comandoEntrada();
            case "PCImprimir" -> comandoSaida();
            case "PCEnqto" -> comandoRepeticao();
            case "PCIni" -> subAlgoritmo();
            case "PCSe" ->  comandoCondicao();
            default -> {
                System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "Token: " + this.tokens.get(index) + "\n");
                System.exit(0);
            }
        }

    }

    //ComandoAtribuicao → VARIAVEL '<<' ExpressaoAritmetica;
    public void comandoAtribuicao(){
        if(this.tokens.get(index).equals("Var")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            if(this.tokens.get(index).equals("Atrib")){
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
                expressaoAritmetica();
            }else{
                System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
                System.exit(0);
            }
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

    }

    //ComandoEntrada → 'read' VARIAVEL;
    public void comandoEntrada(){
        if(this.tokens.get(index).equals("PCLer")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            if(this.tokens.get(index).equals("Var")) {
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
            }
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }


    }
    //ComandoSaida → 'print'  VARIAVEL | 'print' CADEIA;
    public void comandoSaida(){
        if(this.tokens.get(index).equals("PCImprimir")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else if(this.tokens.get(index).equals("Var")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else if(this.tokens.get(index).equals("Cadeia")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

    }

    //ComandoCondicao → 'if' ExpressaoRelacional 'then' Comando ComandoCondicao'' ;
    public void comandoCondicao(){
        if(this.tokens.get(index).equals("PCSe")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            expressaoRelacional();
            if(this.tokens.get(index).equals("PCEntao")){
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
                comando();
                comandoCondicao2();
            }
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

    }
    //Comando ComandoCondicao'' → 'else' Comando | simboloVazio ;
    public void comandoCondicao2(){
        if(this.tokens.get(index).equals("PCSenao")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            comando();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

    }

    //ComandoRepeticao → 'while' ExpressaoRelacional 'then' Comando;
    public void comandoRepeticao(){
        if(this.tokens.get(index).equals("PCEnqto")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            expressaoRelacional();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("PCEntao")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            comando();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        comando();

    }

    //SubAlgoritmo → 'start' ListaComandos 'end';
    public void subAlgoritmo(){
        if(this.tokens.get(index).equals("PCIni")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            listaComandos();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("PCFim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) +  "Token: " + this.tokens.get(index) + "\n");
            System.exit(0);
        }

    }
}
