import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //drawAFigure(4);
        String input = "Litw0^0jczyzno moja, Ty jestes jak zdr0w13, ile C13^c3n1c, t3n ty1k0 si3 d0wie^_kt0 C13 stracil.";
        char symbol = '^';
        pairSwapPattern(input, symbol);
    }
    public static void pairSwapPattern(String text, char symbol) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9_]+)"+Pattern.quote(String.valueOf(symbol))+"([a-zA-Z0-9_]+)");

        Matcher matcher = pattern.matcher(text);

        int index = 0;
        char[] result = new char[text.length()];

        while (matcher.find()) {
            String first = matcher.group(1);
            String second = matcher.group(2);

            for (int i = index; i < matcher.start(); i++) {
                result[i] = text.charAt(i);
            }
            index = matcher.end();

            for (int i = 0; i < second.length(); i++) {
                result[matcher.start() + i] = second.charAt(i);
            }

            result[matcher.start() + second.length()] = symbol;

            for (int i = 0; i < first.length(); i++) {
                result[matcher.start() + second.length() + 1 + i] = first.charAt(i);
            }
        }

        for (int i = index; i < text.length(); i++) {
            result[i] = text.charAt(i);
        }

        // Fix misplaced space
        for (int i = 0; i < result.length - 1; i++) {
            if (result[i] == ' ' && result[i + 1] == symbol) {
                result[i] = result[i + 1];
                result[i + 1] = ' ';
            }
        }

        System.out.println(String.valueOf(result));
    }


    public static void pairSwap(String text){
        String[] slowa = text.split(" ");
        String wynik = "";
        for(int i=0; i<slowa.length; i++){
            if(slowa[i].contains("=")){
                String[] para = slowa[i].split("=");
                slowa[i] = para[1] + "=" + para[0];
            }
            wynik += slowa[i] + " ";
        }
        System.out.println(wynik);
    }

    // Zad 1:
    public static void drawRaw(int space, int raw){
        for(int k=0; k<space; k++){
            System.out.print(" ");
        }

        for(int j=0; j<raw; j++){
            System.out.print("X");
        }
    }

    public static void drawPyramid(int n, int h){
        int raw = 2*n+1;
        int space = h-1;

        for(int i=0; i<h; i++){
            drawRaw(space, raw);
            space -= 1;
            raw += 2;
            System.out.println();
        }
    }

    public static void drawAFigure(int n){
        int raw = 1;
        int space = n-1;

        for(int i=0; i<n; i++){
            drawRaw(space, raw);
            space -= 1;
            raw += 2;
            System.out.println();
        }

        int start = 1;
        while(n>0){
            drawPyramid(start, n-1);
            start += 1;
            n -= 1;
        }
    }
}
