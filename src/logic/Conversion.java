package logic;

import java.util.ArrayList;
import java.util.Stack;

public class Conversion {

    private final ArrayList<String> simbolos, grupos;
    private final int[][] matrizJerarquia = {{1, 1, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0}};
    private final Stack<String> pila = new Stack();
    private String postfijo = "";

    public Conversion() {

        simbolos = new ArrayList<>();
        grupos = new ArrayList<>();

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

        for (int i = 0; i < prefijo.length(); i++) {
            if (simbolos.contains(prefijo.substring(i, i + 1))) {
                if (pila.isEmpty()) {
                    pila.push(prefijo.substring(i, i + 1));
                } else if (prefijo.substring(i, i + 1).equals(")")) {
                    while (!pila.isEmpty()) {
                        String temp = pila.pop();
                        if (!temp.equals("(") && !temp.equals(")")) {
                            postfijo += temp;
                        }
                    }
                } else if (prefijo.substring(i, i + 1).equals("(")) {
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
                                } else {
                                    while (!pila.isEmpty()) {
                                        String temp = pila.pop();
                                        if (!temp.equals("(") && !temp.equals(")")) {
                                            postfijo += temp;
                                        }
                                    }
                                }
                                salir = true;
                            }
                        }

                        if (pila.isEmpty()) {
                            pila.push(prefijo.substring(i, i + 1));
                        }

                    } else {
                        if (!(prefijo.substring(i, i + 1).equals(")"))) {
                            pila.push(prefijo.substring(i, i + 1));
                        } else {
                            while (!pila.isEmpty()) {
                                String temp = pila.pop();
                                if (!temp.equals("(") && !temp.equals(")")) {
                                    postfijo += temp;
                                }
                            }
                        }
                    }
                }
            } else {
                postfijo += (String.valueOf(prefijo.substring(i, i + 1)));
            }

        }

        while (!pila.isEmpty()) {
            String popFinal = pila.pop();
            if (!popFinal.equals(")") && !popFinal.equals("(")) {
                postfijo += popFinal;
            }
        }
        return postfijo;

        //17+25/2^3
    }

    public int operacionArreglo(String simbolo1, String simbolo2) {
        int fila = simbolos.indexOf(simbolo1);
        int columna = simbolos.indexOf(simbolo2);
        return matrizJerarquia[fila - 1][columna - 1];
    }

    public boolean isOp(String simbol) {
        if (simbolos.contains(simbol)) {
            return true;
        }
        return false;
    }

    public void calcularGruposNumeros(String infijo) {
        String temp = "";
        for (int i = 0; i < infijo.length(); i++) {
            String caracter = String.valueOf(infijo.charAt(i));
            if (isOp(caracter)) {
                grupos.add(temp);
                temp = "";
            } else {
                temp += caracter;
                if (i == infijo.length() - 1) {
                    grupos.add(temp);
                }
            }
        }

        for (String a : grupos) {
            System.out.println(a);
        }

    }

    public boolean isContent(String principal, String secundario) {
        for (int i = 0; i < principal.length(); i++) {
            System.out.println("Principal: " + principal + " Secundario: " + secundario);
            if (principal.substring(i, i + 1).equals(secundario)) {
                //System.out.println("Secundario: " + secundario + " Principal (cortado): " + principal.substring(i, i + 1));
                return true;
            }
        }
        return false;

    }

    public void refactorExpression() {
        String temp = "";
        int index = 0;
        System.out.println("POS: " + postfijo);

        for (int i = 0; i < postfijo.length(); i++) {
            try {
                System.out.println("Elemento cadena: " + postfijo.substring(i, i + 1));
                if (isContent(grupos.get(index), postfijo.substring(i, i + 1))) {
                    temp += postfijo.substring(i, i + 1);

                } else if (isContent(grupos.get(index), temp)) {
                    System.out.println("Si eso es cierto");
                    temp += " ";
                    index++;
                } else {
                    temp += " " + postfijo.substring(i, i + 1);
                    index++;
                }

            } catch (IndexOutOfBoundsException e) {
                temp += " " + postfijo.substring(postfijo.length() - 1);
            }

        }

        System.out.println("temp: " + temp);
    }

}
