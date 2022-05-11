package client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import server.Jogada;

public class Client {
    public static void main(String[] args) {
        final int PORT = 9876;
        final String IP = "127.0.0.1";
        Socket socket;
        int operando1, tpjogada, operando2;
        PrintStream output = null;
        Scanner input;
        String retorno,saida;
        char continuar;

        try {

            Scanner scanner = new Scanner(System.in);

            System.out.println("*********************************");
            System.out.println("***  JOKEMPÔ  ***");
            System.out.println("*********************************");

            System.out.println("Digite a modalidade de partida ");
            System.out.println("1 para jogar contra o PC ");
            System.out.println("2 para jogar contra outro jogador ");

            socket = new Socket(IP, PORT);

            output = new PrintStream(socket.getOutputStream());

            tpjogada = Integer.parseInt(scanner.nextLine());

            output.println(tpjogada);

           

             

                if (tpjogada == 1) {

                    do {

                        Scanner scanner2 = new Scanner(System.in);

                    do {

                        System.out.println("Escolha entre 0, 1 e 2 , sendo : ");
                        System.out.println("Pedra = 0 ");
                        System.out.println("Papel = 1  ");
                        System.out.println("Tesoura = 2");

                        operando1 = Integer.parseInt(scanner2.nextLine());

                    }

                    while (operando1 > 2 || operando1 < 0);

                    if (operando1 == 0) {

                        System.out.println("Você escolheu Pedra");
                    }

                    if (operando1 == 1) {

                        System.out.println("Você escolheu Papel");

                    }

                    if (operando1 == 2) {

                        System.out.println("Você escolheu Tesoura");

                    }

                    output.println(operando1);

                    input = new Scanner(socket.getInputStream());

                    String partida = input.nextLine();

                    System.out.println("Partida : " + partida);

                    String calculo = input.nextLine();

                    System.out.println("Placar : " + calculo);

                    String empate = input.nextLine();

                    System.out.println("Total de empates " + empate);

                    String vitoria = input.nextLine();

                    System.out.println("Total de vitórias " + vitoria);

                    String perda = input.nextLine();

                    System.out.println("Total de perdas " + perda);


                    do {
                        System.out.println("Deseja continuar jogando? S/N");
    
                        continuar = scanner2.next().charAt(0);
                    } while (Character.toUpperCase(continuar) != 'S' & Character.toUpperCase(continuar) != 'N');
    
                    output.println(continuar);
    
    
    
    
                } while (Character.toUpperCase(continuar) == 'S');

                }

                


          

                if (tpjogada == 2) {                    


                    System.out.println("Aguardar conexão do segundo jogador...");

                    input = new Scanner(socket.getInputStream());

                    retorno = input.nextLine();

                    System.out.println("Retorno Servidor " + retorno);

                    System.out.println("Digite seu nome ");

                    String nome = scanner.nextLine();

                    output.println(nome);                   

                    do{  
                        
                        

                        Scanner scanner3 = new Scanner(System.in);

                    

                         do {

                        System.out.println("Escolha entre 0, 1 e 2 , sendo : ");
                        System.out.println("Pedra = 0 ");
                        System.out.println("Papel = 1  ");
                        System.out.println("Tesoura = 2");

                        operando2 = scanner3.nextInt();

                             } while (operando2 > 2 || operando2 < 0);

                    output.println(operando2);

                    String partida = input.nextLine();

                    System.out.println("Partida : " + partida);

                    
                    String calculo = input.nextLine();

                    System.out.println("Placar " + calculo);

                    String empate = input.nextLine();

                    System.out.println("Total de empates " + empate);

                    String vitoria = input.nextLine();

                    System.out.println("Total de vitórias " + vitoria);

                    String perda = input.nextLine();

                    System.out.println("Total de perdas " + perda);


                   do {
                        System.out.println("Deseja continuar jogando? S/N");
    
                        continuar = scanner3.next().charAt(0);

                    } while (Character.toUpperCase(continuar) != 'S' & Character.toUpperCase(continuar) != 'N');
    
                    output.println(continuar);     
                    
                    saida = input.nextLine();

                

                    System.out.println( saida);

                   
                        
                } while (Character.toUpperCase(continuar) == 'S'& saida.equals("Boa Sorte!!"));


                



            }

                

                if (tpjogada != 1 & tpjogada != 2) {

                    System.out.println("Opção Invalida");
                }

               

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        

    }

}
