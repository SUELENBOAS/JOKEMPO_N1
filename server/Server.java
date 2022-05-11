package server;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) {
        final int PORT = 9876;
        ServerSocket server;
        Socket client;
        int tpjogada;
        Scanner input;
        PrintStream output = null;
        String retorno;
        Socket jogador = null, jogador1 = null, jogador2 = null;
        
        try {
            server = new ServerSocket(PORT);

        } catch (Exception e) {
            System.out.println("Porta em uso...");
            return;
        }

        try {

           

            while (true) {

                System.out.println("Aguardando conexão...");

               
                client = server.accept();

                input = new Scanner(client.getInputStream());
                tpjogada = input.nextInt();

                System.out.println("tipo de partida escolhida: " + tpjogada);

                

                if (tpjogada == 1) {

                    jogador = client;

                    System.out.println("jogada iniciada ");

                    Jogada calcula = new Jogada(jogador);

                    jogador = null;

                }

                if (tpjogada == 2) {

                    

                    if (jogador1 == null) {

                        jogador1 = client;

                        System.out.println("Servidor aguardando conexão do segundo jogador");

                        jogador2 = server.accept();

                        input = new Scanner(jogador2.getInputStream());

                        tpjogada = input.nextInt();

                        System.out.println("tipo de partida escolhida: " + tpjogada);

                        

                        if (tpjogada == 2) {

                            output = new PrintStream(jogador1.getOutputStream());

                            retorno = "Conexão recebida";

                            output.println(retorno);

                            output = new PrintStream(jogador2.getOutputStream());

                            retorno = "Conexão recebida";

                            output.println(retorno);

                            System.out.println("jogada iniciada contra dois jogadores seguidos");

                            Jogada calcula = new Jogada(jogador1, jogador2);                          

                            
                            jogador2 = null;
                            jogador1 = null;


                        } else{

                        System.out.println("jogada iniciada ");

                        Jogada calcula = new Jogada(jogador2);

                     

                        jogador2 = null;}

                    }

                    

                    if (jogador1 != null & tpjogada == 2) {

                        jogador2 = client;

                        output = new PrintStream(jogador1.getOutputStream());

                        retorno = "Conexão recebida";

                        output.println(retorno);

                        output = new PrintStream(jogador2.getOutputStream());

                        retorno = "Conexão recebida";

                        output.println(retorno);

                        System.out.println("jogada iniciada com dois jogadores");

                        Jogada calcula1 = new Jogada(jogador1, jogador2);                        

                        jogador1 = null;
                        jogador2 = null;

                    }

                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
