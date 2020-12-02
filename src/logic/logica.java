package logic;

import java.util.ArrayList;
import java.util.Stack;

public class logica {

    private final ArrayList<String> simbolos;
    private final int[][] matrizJerarquia = {{1, 1, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 0}};
    private Stack<String> pila = new Stack();

    public logica() {

        simbolos = new ArrayList<>();

        simbolos.add("br");
        simbolos.add("+");
        simbolos.add("-");
        simbolos.add("*");
        simbolos.add("/");
        simbolos.add("^");
        simbolos.add("(");
        simbolos.add(")");

    }

    public String[] calcularSufijo(String prefijo) {
        String postfijo = "";
        for (int i = 0; i < prefijo.length(); i++) {
            if (simbolos.contains(prefijo.substring(i, i + 1))) {
                if (pila.isEmpty() && !prefijo.substring(i, i + 1).equals(")")) {
                    pila.push(prefijo.substring(i, i + 1));
                    if (!postfijo.substring(postfijo.length() - 1, postfijo.length()).equals(" ")) {
                        postfijo += " ";
                    }
                } else if (prefijo.substring(i, i + 1).equals(")")) {
                    postfijo += " ";
                    while (!pila.isEmpty()) {
                        String temp = pila.pop();
                        if (!temp.equals("(") && !temp.equals(")")) {
                            postfijo += temp + " ";
                        }
                    }
                } else if (prefijo.substring(i, i + 1).equals("(")) {
                    pila.push(prefijo.substring(i, i + 1));
                } else {
                    int operacion = operacionArreglo(pila.get(pila.size() - 1), prefijo.substring(i, i + 1));
                    if (operacion == 1) {
                        String pop = pila.pop();
                        postfijo += " " + (pop);
                        boolean salir = false;
                        while (!salir && !pila.isEmpty()) {
                            int operacionBucle = operacionArreglo(pila.get(pila.size() - 1), prefijo.substring(i, i + 1));
                            if (operacionBucle == 1) {
                                String popBucle = pila.pop();
                                postfijo += " " + (popBucle);
                            } else {
                                if (!(prefijo.substring(i, i + 1).equals(")"))) {
                                    pila.push(prefijo.substring(i, i + 1));
                                    postfijo += " ";
                                } else {
                                    while (!pila.isEmpty()) {
                                        String temp = pila.pop();
                                        if (!temp.equals("(") && !temp.equals(")")) {
                                            postfijo += temp + " ";
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
                            postfijo += " ";
                        } else {
                            postfijo += " ";
                            while (!pila.isEmpty()) {
                                String temp = pila.pop();
                                if (!temp.equals("(") && !temp.equals(")")) {
                                    postfijo += temp + " ";
                                }
                            }
                        }
                    }
                }
            } else {
                if (postfijo.isEmpty()) {
                    postfijo += (String.valueOf(prefijo.substring(i, i + 1)));
                } else {
                    if (isOp((postfijo.substring(postfijo.length() - 1, postfijo.length())))) {
                        postfijo += " " + (String.valueOf(prefijo.substring(i, i + 1)));
                    } else {
                        postfijo += (String.valueOf(prefijo.substring(i, i + 1)));
                    }
                }
            }
        }

        while (!pila.isEmpty()) {

            postfijo += " ";
            String popFinal = pila.pop();
            if (!popFinal.equals(")") && !popFinal.equals("(")) {
                postfijo += popFinal;
            }
        }

        String[] retorno = new String[2];
        retorno[0] = postfijo;
        System.out.println(postfijo);

        retorno[1] = calcularOperacion(postfijo);
        return retorno;
        //128/7+5/2-54
    }

    public int operacionArreglo(String simbolo1, String simbolo2) {
        int fila = simbolos.indexOf(simbolo1);
        int columna = simbolos.indexOf(simbolo2);
        System.out.println("wtf1: " + simbolo1);
        System.out.println("wtf2: " + simbolo2);
        return matrizJerarquia[fila - 1][columna - 1];
        //1+(0/8)-(4*(4/5))*((1+2)/5)
    }

    public String calcularOperacion(String postfijoEspacios) {
        Stack<String> pilaOperacion = new Stack();

        String[] partesString = postfijoEspacios.split(" ");

        for (int i = 0; i < partesString.length; i++) {
            if (!isOp(partesString[i])) {
                pilaOperacion.push(partesString[i]);
            } else {
                String a = pilaOperacion.pop();
                String b = pilaOperacion.pop();
                double valorNuevo = operacion(Double.parseDouble(b), Double.parseDouble(a), partesString[i]);
                pilaOperacion.push(String.valueOf(valorNuevo));
            }
        }
        return pilaOperacion.pop();
    }

    public boolean isOp(String simbol) {
        if (simbolos.contains(simbol)) {
            return true;
        }
        return false;
    }

    public double operacion(double a, double b, String operacion) {
        switch (operacion) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "^":
                return Math.pow(a, b);
            default:
                return 0;
        }
    }
}
