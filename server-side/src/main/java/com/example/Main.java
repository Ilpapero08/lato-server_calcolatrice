package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket socket = serverSocket.accept();

        System.out.println("Client collegato" + "\n");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Boolean running = true;

        String operatore = "";
        Double numero1 = 0.0;
        Double numero2 = 0.0;
        boolean datiValidi = false;

        while (running){
            Double risultato = 0.0;
            do {
                try {
                    operatore = in.readLine();
                    
                    numero1 = Double.parseDouble(in.readLine());
                    numero2 = Double.parseDouble(in.readLine());
                    
                    if (operatore.equals("+") || operatore.equals("-") || operatore.equals("*") || operatore.equals("/")) {
                        datiValidi = true; 
                    } else {
                        out.println("Operatore non valido. Riprova.");
                    }
                    
                } catch (NumberFormatException e) {
                    out.println("Errore: Il client ha inserito lettere al posto di numeri. Riprova.");
                    datiValidi = false; 
                }

            } while (!datiValidi);

            if(numero1==0 && numero2==0){
                break;
            }
            switch (operatore) {
                
                case "+":
                    risultato = numero1 + numero2;
                    break;

                case "-":
                    risultato = numero1 - numero2;
                    break;

                case "*":
                    risultato = numero1 * numero2;
                    break;

                case "/":
                    if(numero2==0){
                        out.println("Error cod: Division_By_Zero");
                    } else{
                        risultato = numero1 / numero2;
                    }
                    break;

                case "exit":
                    out.println("exit");
                    running = false;
                    break;
            
                default:
                    break;
            }

            out.println(risultato);
        }
        System.out.println("Client disconnesso");
    }
}