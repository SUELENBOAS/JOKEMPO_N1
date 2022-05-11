package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;




public class Jogada extends Thread {
    Socket socket, socket2;

    static String resposta;

    char continuar, continuar2;

    static String jor1, jor2, nome, nome2;

    public static int operando1, operando2, valor, valor2, emp, vit, perd, vit2, perd2;

    Scanner input;
    PrintStream output = null;

    public Jogada(Socket socket) throws IOException {

        this.socket = socket;

        do {

            input = new Scanner(socket.getInputStream());

            operando1 = Integer.parseInt(input.nextLine());

            Random gerador = new Random();

            operando2 = gerador.nextInt(3);

            if (operando2 == 0) {
                jor2 = ("PC escolheu Pedra");
            } else if (operando2 == 1) {
                jor2 = ("PC escolheu Papel");
            } else if (operando2 == 2) {
                jor2 = ("PC escolheu Tesoura");
            }

            if (operando1 == 0) {
                jor1 = ("  Você escolheu Pedra ");
            } else if (operando1 == 1) {
                jor1 = ("  Você escolheu Papel");
            } else if (operando1 == 2) {
                jor1 = ("  você escolheu Tesoura");
            }

            output = new PrintStream(socket.getOutputStream());
            output.println(Jogada.jor1 + "  vs  " + Jogada.jor2);

            if (operando1 == operando2) {

                resposta = "empate";
                emp++;
            }
            if (operando1 == 0 & operando2 == 1) {

                resposta = "PC  ganhou";
                perd++;
            }
            if (operando1 == 0 & operando2 == 2) {

                resposta = "Você ganhou!!!";
                vit++;
            }

            if (operando1 == 1 & operando2 == 0) {

                resposta = "Você ganhou!!!";
                vit++;
            }
            if (operando1 == 1 & operando2 == 2) {

                resposta = "PC ganhou";
                perd++;
            }

            if (operando1 == 2 & operando2 == 0) {

                resposta = "PC ganhou";
                perd++;
            }
            if (operando1 == 2 & operando2 == 1) {

                resposta = "Você ganhou";
                vit++;
            }

            output = new PrintStream(socket.getOutputStream());
            output.println(resposta);

            output.println(emp);

            output.println(vit);

            output.println(perd);

            input = new Scanner(socket.getInputStream());
            continuar = input.next().charAt(0);

            System.out.println(continuar);

        } while (Character.toUpperCase(continuar) == 'S');

        emp = 0;
        perd = 0;
        vit = 0;

    }




    

    public Jogada(Socket socket, Socket socket2) throws IOException {

        this.socket = socket;
        this.socket = socket2;

        input = new Scanner(socket2.getInputStream());

        nome2 = input.nextLine();

        input = new Scanner(socket.getInputStream());

        nome = input.nextLine();

        do {

            input = new Scanner(socket2.getInputStream());

            operando2 = Integer.parseInt(input.nextLine());

            System.out.println("Valor recebido " + operando2 + " do Jogador  " + nome2);

            input = new Scanner(socket.getInputStream());

            operando1 = Integer.parseInt(input.nextLine());

            System.out.println("Valor recebido " + operando1 + " do Jogador  " + nome);

            if (operando1 == 0) {
                jor1 = nome + "  escolheu Pedra ";
            } else if (operando1 == 1) {
                jor1 = nome + "  escolheu Papel";
            } else if (operando1 == 2) {
                jor1 = nome + "  escolheu Tesoura";
            }

            if (operando2 == 0) {
                jor2 = nome2 + "  escolheu Pedra ";
            } else if (operando2 == 1) {
                jor2 = nome2 + "  escolheu Papel";
            } else if (operando2 == 2) {
                jor2 = nome2 + "  escolheu Tesoura";
            }

            output = new PrintStream(socket.getOutputStream());
            output.println(Jogada.jor1 + "  vs  " + Jogada.jor2);

            output = new PrintStream(socket2.getOutputStream());

            output.println(Jogada.jor1 + "  vs  " + Jogada.jor2);

            System.out.println(jor1 + " vs " + jor2);

            if (operando1 == operando2) {

                resposta = " empate";

                emp++;

            }
            if (operando1 == 0 & operando2 == 1) {

                resposta = nome2 + " ganhou";
                vit2++;
                perd++;

            }
            if (operando1 == 0 & operando2 == 2) {

                resposta = nome + " ganhou";
                vit++;
                perd2++;

            }

            if (operando1 == 1 & operando2 == 0) {

                resposta = nome + " ganhou";

                vit++;
                perd2++;
            }
            if (operando1 == 1 & operando2 == 2) {

                resposta = nome2 + " ganhou";
                vit2++;
                perd++;
            }

            if (operando1 == 2 & operando2 == 0) {

                resposta = nome2 + " ganhou";

                vit2++;
                perd++;
            }
            if (operando1 == 2 & operando2 == 1) {

                resposta = nome + " ganhou";
                vit++;
                perd2++;
            }

            output = new PrintStream(socket.getOutputStream());
            output.println(Jogada.resposta);

            output = new PrintStream(socket2.getOutputStream());

            output.println(Jogada.resposta);

            output = new PrintStream(socket.getOutputStream());
            output.println(emp);
            output.println(vit);
            output.println(perd);

            output = new PrintStream(socket2.getOutputStream());

            output.println(emp);
            output.println(vit2);
            output.println(perd2);

            input = new Scanner(socket2.getInputStream());
            continuar2 = input.next().charAt(0);

            System.out.println(continuar2);

            input = new Scanner(socket.getInputStream());
            continuar = input.next().charAt(0);

            System.out.println(continuar);

            if (Character.toUpperCase(continuar) == 'N' || Character.toUpperCase(continuar2) == 'N') {

                output = new PrintStream(socket2.getOutputStream());

                output.println("jogada encerrada um jogador saiu da partida");

                output = new PrintStream(socket.getOutputStream());

                output.println("jogada encerrada um jogador saiu da partida");

            } else {

                output = new PrintStream(socket.getOutputStream());

                output.println("Boa Sorte!!");

                output = new PrintStream(socket2.getOutputStream());

                output.println("Boa Sorte!!");

            }

        } while (Character.toUpperCase(continuar) == 'S' & Character.toUpperCase(continuar2) == 'S');

        emp = 0;
        perd = 0;
        perd2 = 0;
        vit = 0;
        vit2 = 0;

    }

}
