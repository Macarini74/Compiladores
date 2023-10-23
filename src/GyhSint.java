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
            System.out.println(tokens.get(i));
        }
    }
    ///Programa → '[' 'dec'']' ListaDeclaracoes '[' 'prog' ']' ListaComandos;
    public void startSint(){
        if(this.tokens.get(0).equals("IniDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("PCDec")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(2).equals("FimDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        listaDeclaracoes();

        if(this.tokens.get(index).equals("IniDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }
        if(this.tokens.get(index).equals("PCProg")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }
        if(this.tokens.get(index).equals("FimDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
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
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("IniDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            tipoVar();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }



        if(this.tokens.get(index).equals("FimDelim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
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
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }
    }

    //ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica'' ;
    public void expressaoAritmetica(){
        termoAritmetico();
        expressaoAritmetica2();
    }
    //ExpressaoAritmetica'' → '+' TermoAritmetico ExpressaoAritmetica''|'-' TermoAritmetico ExpressaoAritmetica''| simboloVazio ;
    public void expressaoAritmetica2(){
        if(this.tokens.get(index).equals("OpAritSoma")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            termoAritmetico();
            expressaoAritmetica2();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("OpAritSub")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            termoAritmetico();
            expressaoAritmetica2();
        }else{
            System.out.println("Erro Sintatico Linha:  " + this.line.get(index) + "\n");
            System.exit(0);
        }
    }

    //TermoAritmetico → FatorAritmetico TermoAritmetico'';
    public void termoAritmetico(){
        fatorAritmetico();
        termoAritmetico2();
    }
    //TermoAritmetico'' → '*' FatorAritmetico TermoAritmetico''|'/' FatorAritmetico TermoAritmetico'' | simboloVazio ;
    public void termoAritmetico2(){
        if(this.tokens.get(index).equals("OpAritMult")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            fatorAritmetico();
            termoAritmetico2();
        }else {
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("OpAritDiv")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            fatorAritmetico();
            termoAritmetico2();
        }else {
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }
    }

    //FatorAritmetico → NUMINT| NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')' ;
    public void fatorAritmetico(){
        if(this.tokens.get(index).equals("NumInt")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else if(this.tokens.get(index).equals("NumReal")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else if(this.tokens.get(index).equals("Var")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("AbrePar")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            expressaoAritmetica();
        } else if (this.tokens.get(index).equals("FechaPar")) {
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
        }
    }

    //ExpressaoRelacional → TermoRelacional ExpressaoRelacional'';
    public void expressaoRelacional(){
        termoRelacional();
        expressaoRelacional2();
    }
    //ExpressaoRelacional'' → OperadorBooleano TermoRelacional ExpressaoRelacional'' | simboloVazio ;
    public void expressaoRelacional2(){
        if(this.tokens.get(index).equals("OpBoolE") || this.tokens.get(index).equals("OpBoolOu")){
            operadorBooleano();
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
                switch (this.tokens.get(index)){
                    case "OpRelMenor":
                    case "OpRelMenorIgual":
                    case "OpRelMaior":
                    case "OpRelMaiorIgual":
                    case "OpRelIgual":
                    case "OpRelDif":
                        System.out.println("Token: " + this.tokens.get(index) + "\n");
                        index++;
                        expressaoAritmetica();
                        break;
                    default:
                        System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
                        System.exit(0);
                }
                break;
            case "AbrePar":
                System.out.println("Token: " + this.tokens.get(index) + "\n");
                index++;
                switch (this.tokens.get(index)){
                    case "OpRelMenor":
                    case "OpRelMenorIgual":
                    case "OpRelMaior":
                    case "OpRelMaiorIgual":
                    case "OpRelIgual":
                    case "OpRelDif":
                        System.out.println("Token: " + this.tokens.get(index) + "\n");
                        index++;
                        expressaoAritmetica();
                        break;
                    default:
                        expressaoRelacional();
                        System.out.println("Token: " + this.tokens.get(index) + "\n");
                        index++;

                        if(this.tokens.get(index).equals("FechaPar")){
                            System.out.println("Token: " + this.tokens.get(index) + "\n");
                            index++;
                        }else{
                            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
                            System.exit(0);
                        }
                }
        }
    }

    //OperadorBooleano → 'or' | 'and';
    public void operadorBooleano(){
        if(this.tokens.get(index).equals("OpBoolE")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else if(this.tokens.get(index).equals("OpBoolOu")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }
    }

    //ListaComandos → Comando ListaComandos'' ;
    public void listaComandos(){
        comando();
        listaComandos2();
    }
    //ListaComandos'' → ListaComandos| simboloVazio ;
    public void listaComandos2(){
        if(this.tokens.get(index).equals("Var")) {
            listaComandos();
        }else if(this.tokens.get(index).equals("PCLer")) {
            listaComandos();
        }else if(this.tokens.get(index).equals("PCImprimir")){
            listaComandos();
        }else if(this.tokens.get(index).equals("PCEnqto")){
            listaComandos();
        }else if(this.tokens.get(index).equals("PCSe")){
            listaComandos();
        }else if(this.tokens.get(index).equals("PCIni")){
            listaComandos();
        }
    }


    //Comando → ComandoAtribuicao | ComandoEntrada | ComandoSaida | ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
    public void comando(){
        if(this.tokens.get(index).equals("Var")) {
            comandoAtribuicao();
        }else if(this.tokens.get(index).equals("PCLer")) {
            comandoEntrada();
        }else if(this.tokens.get(index).equals("PCImprimir")) {
            comandoSaida();
        }else if(this.tokens.get(index).equals("PCEnqto")) {
            comandoRepeticao();
        }else if(this.tokens.get(index).equals("PCIni")){
            subAlgoritmo();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

    }

    //ComandoAtribuicao → VARIAVEL '<<' ExpressaoAritmetica;
    public void comandoAtribuicao(){
        if(this.tokens.get(index).equals("Var")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else {
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("Atrib")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        expressaoAritmetica();
    }

    //ComandoEntrada → 'read' VARIAVEL;
    public void comandoEntrada(){
        if(this.tokens.get(index).equals("PCLer")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("Var")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }


    }
    //ComandoSaida → 'print'  VARIAVEL | 'print' CADEIA;
    public void comandoSaida(){
        if(this.tokens.get(index).equals("PCImprimir")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("Var")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        if(this.tokens.get(index).equals("Cadeia")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

    }

    //ComandoCondicao → 'if' ExpressaoRelacional 'then' Comando ComandoCondicao'' ;
    public void comandoCondicao(){
        if(this.tokens.get(index).equals("PCSe")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        expressaoRelacional();

        if(this.tokens.get(index).equals("PCEntao")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        comando();
        comandoCondicao2();

    }
    //Comando ComandoCondicao'' → 'else' Comando | simboloVazio ;
    public void comandoCondicao2(){
        if(this.tokens.get(index).equals("PCSenao")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
            comando();
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

    }

    //ComandoRepeticao → 'while' ExpressaoRelacional 'then' Comando;
    public void comandoRepeticao(){
        if(this.tokens.get(index).equals("PCEnqto")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        expressaoRelacional();

        if(this.tokens.get(index).equals("PCEntao")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        comando();

    }

    //SubAlgoritmo → 'start' ListaComandos 'end';
    public void subAlgoritmo(){
        if(this.tokens.get(index).equals("PCIni")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

        listaComandos();

        if(this.tokens.get(index).equals("PCFim")){
            System.out.println("Token: " + this.tokens.get(index) + "\n");
            index++;
        }else{
            System.out.println("Erro Sintatico Linha: " + this.line.get(index) + "\n");
            System.exit(0);
        }

    }
}
