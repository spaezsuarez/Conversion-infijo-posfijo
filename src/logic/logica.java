package logic;

import java.util.ArrayList;
import java.util.Stack;

public class logica {

    private final ArrayList<String> simbolos;
    private final int[][] matrizJerarquia = {{1, 1, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0}};
    private Stack<String> pila = new Stack();

    public logica() {

        simbolos = new ArrayList<>();

        simbolos.add("0");
        simbolos.add("+");
        simbolos.add("-");
        simbolos.add("*");
        simbolos.add("/");
        simbolos.add("^");
        simbolos.add("(");
        simbolos.add(")");

    }

    public String calcularSufijo(String prefijo) {

        String postfijo = "";

        for (int i = 0; i < prefijo.length(); i++) {
            if (simbolos.contains(prefijo.substring(i, i + 1))) {
                if (pila.isEmpty()) {
                    pila.push(prefijo.substring(i, i + 1));
                } else {
                    int operacion = operacionArreglo(pila.get(pila.size() - 1), prefijo.substring(i, i + 1));
                    if (operacion == 1) {
                        String pop = pila.pop();
                        postfijo += (pop);

                        boolean salir = false;
                        while (!salir && !pila.isEmpty()) {
                            int operacionBucle = operacionArreglo(pila.get(pila.size() - 1), prefijo.substring(i, i + 1));
                            if (operacionBucle == 1) {
                                String popBucle = pila.pop();
                                postfijo += (popBucle);
                            } else {
                                if (!(prefijo.substring(i, i + 1).equals(")"))) {
                                    pila.push(prefijo.substring(i, i + 1));
                                    salir = true;
                                }
                            }
                        }

                        if (pila.isEmpty()) {
                            pila.push(prefijo.substring(i, i + 1));
                        }
                    } else {
                        if (!(prefijo.substring(i, i + 1).equals(")"))) {
                            pila.push(prefijo.substring(i, i + 1));
                        }
                    }

                }

            } else {
                postfijo += (String.valueOf(prefijo.substring(i, i + 1)));
            }
        }

        for (int i = 0; i < pila.size(); i++) {
            System.out.println(pila.get(pila.size() - i - 1));
            System.out.println("WTF1: " + postfijo);
            postfijo += (pila.pop());
            System.out.println("WTF2: " + postfijo);
        }

        return postfijo;
    }

    public int operacionArreglo(String simbolo1, String simbolo2) {
        int fila = simbolos.indexOf(simbolo1);
        int columna = simbolos.indexOf(simbolo2);
        return matrizJerarquia[fila - 1][columna - 1];
    }

}
