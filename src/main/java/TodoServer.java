import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    int port;
    Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        ServerSocket serverSocket = new ServerSocket(port);
        JSONParser parser = new JSONParser();

        while (true) {
            try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                Object obj = parser.parse(in.readLine());
                JSONObject jsonObject = (JSONObject) obj;
                String typeOperation = (String) jsonObject.get("type");
                String task = (String) jsonObject.get("task");

                if (typeOperation.equals("ADD")) {
                    todos.addTask(task);
                    out.println("Задача: " + task + " внесена в список задач. Актуальные задачи: " + todos.getAllTasks());
                }

                if (typeOperation.equals("REMOVE")) {
                    todos.removeTask(task);
                    out.println("Задача " + task + " удалена из списка задач. Актуальные задачи: " + todos.getAllTasks());
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
}