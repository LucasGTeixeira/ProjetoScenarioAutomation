package application.sockets;

import application.repository.mocked.LuminariaMockDAO;
import domain.entities.item.luminaria.Luminaria;
import domain.usecases.item.luminaria.AdicionarLuminariaUseCase;
import domain.usecases.item.luminaria.ControlarLuminariaUseCase;
import domain.usecases.item.luminaria.ListarLuminariasUseCase;
import domain.usecases.item.luminaria.LuminariaDAO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ControlarLuminariaUseCase controlarLuminariaUseCase;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InjecaoDependencia();

        ServerSocket serverSocket = new ServerSocket(4999);
        Socket socket = serverSocket.accept();

        testConnection(socket);

        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        // read the list of messages from the socket
        Command command = (Command) objectInputStream.readObject();
        processaComando(command);

    }

    private static void processaComando(Command command){
        if (command.getTempo() > 0)
            ligarTimerLuminaria(command);
        toggleLuminaria(command);
    }

    private static void ligarTimerLuminaria(Command command) {
        controlarLuminariaUseCase.ligarLuminaria(command.getLuminaria(), command.getTempo());
    }

    private static void toggleLuminaria(Command command){
        controlarLuminariaUseCase.toggle(command.getLuminaria());
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
        ListarLuminariasUseCase listarLuminariasUseCase = new ListarLuminariasUseCase(luminariaDAO);
        controlarLuminariaUseCase = new ControlarLuminariaUseCase(luminariaDAO, listarLuminariasUseCase);
        AdicionarLuminariaUseCase adicionarLuminariaUseCase = new AdicionarLuminariaUseCase(luminariaDAO, listarLuminariasUseCase);

        Luminaria luminaria = new Luminaria("luminariaTeste");
        adicionarLuminariaUseCase.insert(luminaria);
    }
}
