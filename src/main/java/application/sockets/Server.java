package application.sockets;

import application.repository.mocked.LuminariaMockDAO;
import domain.usecases.item.luminaria.ControlarLuminariaUseCase;
import domain.usecases.item.luminaria.ListarLuminariasUseCase;
import domain.usecases.item.luminaria.LuminariaDAO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ControlarLuminariaUseCase controlarLuminariaUseCase;
    private static ListarLuminariasUseCase listarLuminariasUseCase;

    public static void main(String[] args) throws IOException { //Todo receber objeto Command
        InjecaoDependencia();

        ServerSocket serverSocket = new ServerSocket(4999);
        Socket socket = serverSocket.accept();

        testConnection(socket);
    }

    private static void testConnection(Socket socket) throws IOException {
        System.out.println("Client connected");

        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(inputStreamReader);

        String str = bf.readLine();
        System.out.println("client : " + str);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("ping");
        printWriter.flush();
    }

    private static void InjecaoDependencia() {
        LuminariaDAO luminariaDAO = new LuminariaMockDAO();
        listarLuminariasUseCase = new ListarLuminariasUseCase(luminariaDAO);
        controlarLuminariaUseCase = new ControlarLuminariaUseCase(luminariaDAO, listarLuminariasUseCase);
    }
}
