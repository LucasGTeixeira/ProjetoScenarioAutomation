package application.sockets;

import application.repository.mocked.LuminariaMockDAO;
import domain.entities.item.luminaria.Luminaria;
import domain.usecases.item.luminaria.ListarLuminariasUseCase;
import domain.usecases.item.luminaria.LuminariaDAO;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        injecaoLuminaria();
        Socket socket = new Socket("localhost", 4999);
        testConnection(socket);

        Luminaria luminaria = new Luminaria("luminariaTeste");

        toggleLuminaria(luminaria, socket);

        LigarLuminariaTimer(luminaria, 16, socket);
    }

    private static void LigarLuminariaTimer(Luminaria luminaria, int tempo, Socket socket) throws IOException {
        Command command = new Command(luminaria, tempo);

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(command);
    }

    private static void toggleLuminaria(Luminaria luminaria, Socket socket) throws IOException {
        Command command = new Command(luminaria);

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(command);
    }

    private static void testConnection(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("ping");
        printWriter.flush();

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(inputStreamReader);

        String str = bf.readLine();
        System.out.println("servidor : " + str);
    }

    private static void injecaoLuminaria(){
        LuminariaDAO luminariaDAO = new LuminariaMockDAO();

        ListarLuminariasUseCase listarLuminariasUseCase = new ListarLuminariasUseCase(luminariaDAO);
    }

}
