package application.sockets;

import domain.entities.item.luminaria.Luminaria;

import java.io.*;
import java.net.Socket;

public class Client { //Todo Enviar Objeto Command
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4999);

        testConnection(socket);

    }

//    private static void LuminariaTimer(Luminaria luminaria, int tempo) throws IOException {
//        Command command = new Command(luminaria, tempo);
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(command);
//    }

//    private static void toggleLuminaria(Luminaria luminaria) throws IOException {
//        Command command = new Command(luminaria);
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(command);
//    }

    private static void testConnection(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("ping");
        printWriter.flush();

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(inputStreamReader);

        String str = bf.readLine();
        System.out.println("servidor : " + str);
    }
}
