package logic;

import java.util.ArrayList;

public class logica {

    private final ArrayList<Character> simbolos;
    private final int[][] matrizJerarquia = {{1, 1, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0}};

    public logica(String TxtPrefijo) {

        simbolos = new ArrayList<>();

        simbolos.add('0');
        simbolos.add('+');
        simbolos.add('-');
        simbolos.add('*');
        simbolos.add('/');
        simbolos.add('^');
        simbolos.add('(');
        simbolos.add(')');

        calcularSufijo(TxtPrefijo);

    }

    public void calcularSufijo(String prefijo) {
        
        String sufijo = "";

        char[] charArray = prefijo.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(simbolos.contains(charArray[i])){
                
            }else{
                
                
            }
        }

    }

}
