package demo;


import java.util.*;
import javax.swing.JOptionPane;

public class Analizador {
    
    public static void main(String[] args) {
        
       

        String[] palabrasReservadas = {"int", "if", "else", "while", "switch", "for", "each", "try", "catch", "then","double"};

        String[] delimitadores = {"(", ")"};

        String[] operadoresLogico = {"=", "<", ">", "<=", ">="};

        String[] operadores = {"+", "-", "*", "/"};

        String[] separadores = {" "};

         String cadena = JOptionPane.showInputDialog("Ingrese cadena");
        StringTokenizer tokens = new StringTokenizer(cadena, "=<>/()*+-^ ", true);
        
       
        inicio:
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            for (String reservada : palabrasReservadas) {
                if (reservada.equals(token)) {
                    System.out.println(reservada + " -> Reservada");
                    continue inicio;
                    
                }
                
            }
            for (String delimitador : delimitadores) {
                if (delimitador.equals(token)) {
                    System.out.println(delimitador + " -> Delimitador");
                    continue inicio;
                }
            }

            for (String operador : operadoresLogico) {
                if (operador.equals(token)) {
                    System.out.println(operador + " -> Operador Logico");
                    continue inicio;
                }
            }

            for (String separador : separadores) {
                if (separador.equals(token)) {
                    System.out.println('"' + separador + '"' + " -> Separador");
                    continue inicio;
                }
            }

            for (String ope : operadores) {
                if (ope.equals(token)) {
                    System.out.println(ope + " -> Operador");
                    continue inicio;
                }
            }

            System.out.println(token + " -> Variable");
            
            
              
        }
    }
}
