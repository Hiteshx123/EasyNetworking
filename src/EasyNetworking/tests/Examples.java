package EasyNetworking.tests;

import EasyNetworking.library.EasyClientSocket;
import EasyNetworking.library.EasyServerSocket;

import java.io.IOException;
import java.util.Scanner;
public class Examples {

    public static void main(String[] args) {
        Examples practise = new Examples();
        Thread server = practise.new Server();
        Thread client = practise.new Client();
        server.start();
        client.start();
    }

    public class Server extends Thread {

        public void run() {
            //Server Code Goes Here
            exampleONE();
            exampleTWO();
        }

        private void exampleONE() {
            //Assuming default port 3000
            try {
                EasyServerSocket serverSocket = new EasyServerSocket(3000);

                //Server is now waiting 10 seconds for client
                serverSocket.accept(10000, true);

                //sends hi Client to client
                serverSocket.send("hi Client");
                //prints out received String
                println((String) serverSocket.receive());
            } catch (IOException E) {
                System.out.println(E);
            }
        }

        private void exampleTWO() {
            sleep(500);

            EasyServerSocket serverSocket;

            //Assuming custom port 5000
            try {
                serverSocket = new EasyServerSocket(5000);
                //Server is now waiting 10 seconds for client
                serverSocket.accept(10000, true);
                String text;

                Scanner in = new Scanner(System.in);

                //loops receiving and sending
                do {
                    //prints out Server -
                    print("");
                    text = in.nextLine();
                    //sends inputted text
                    serverSocket.send(text);
                    //prints out the received string
                    println((String) serverSocket.receive());
                } while (!text.equalsIgnoreCase("exit"));
            } catch (IOException E) {

            }
            System.out.println("*********************EXAMPLE*TWO**************************");
        }

        private void println(String print) {
            System.out.println("Server - " + print);
        }

        private void print(String print) {
            System.out.print("Server - " + print);
        }

        private void sleep(int time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException E) {
                System.out.println(E);
            }
        }
    }

    public class Client extends Thread {

        public void run() {
            //Client Code Goes Here
            exampleONE();
            exampleTWO();
        }

        private void exampleONE() {
            System.out.println("*********************EXAMPLE*ONE**************************");
            //Makes client wait 3 seconds to allow time for server to come online
            sleep(3000);

            EasyClientSocket clientSocket = new EasyClientSocket();

            //String host can be found by running EasyServerSocket.acceptConnection();, Connection detail will be printed out

            clientSocket.connect("0.0.0.0", 3000);

            //Since I know the server is going to send a String we can cast the Object to a String to allow it to print correctly
            //println is a special method to print thing in a specific format

            println((String) clientSocket.receive());
            clientSocket.send("Hi Server");
        }

        private void exampleTWO() {
            sleep(250);
            System.out.println("*********************EXAMPLE*TWO**************************");
            sleep(1000);

            //Makes client wait 3 seconds to allow time for server to come online
            sleep(3000);

            EasyClientSocket clientSocket = new EasyClientSocket();

            //String host can be found by running EasyServerSocket.acceptConnection();, Connection detail will be printed out

            clientSocket.connect("0.0.0.0", 5000);

            String text = "";

            Scanner in = new Scanner(System.in);

            //loops receiving and sending
            do {
                //prints received String
                println((String) clientSocket.receive());
                print("");
                text = in.nextLine();
                //sends inputted string
                clientSocket.send(text);
            } while (!text.equalsIgnoreCase("exit"));
        }

        private void println(String print) {
            System.out.println("Client - " + print);
        }

        private void print(String print) {
            System.out.print("Client - " + print);
        }

        private void sleep(int time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException E) {
                System.out.println(E);
            }
        }
    }


}
