package iChat;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientController {
    @FXML
    Button buttonSendMessage;
    @FXML
    ListView<String> clientsList;
    @FXML
    TextArea messages;
    @FXML
    TextField messageArea;
    @FXML
    TextField nicknameInput;
    @FXML
    Button nicknameBtn;

    private File history = new File("history.txt");
    private final int HISTORY_CAPACITY = 100;

    private DataInputStream inputStream = ChatApp.getInputStream();
    private DataOutputStream outputStream = ChatApp.getOutputStream();
    private Socket socket = ChatApp.getSocket();
    private ObservableList<String> nickListItems = FXCollections.observableArrayList();


    public void sendMessage(ActionEvent event) {
        try {
            if (!messageArea.getText().trim().isEmpty()) {
                outputStream.writeUTF(messageArea.getText());
            }
        } catch (IOException e) {
            messages.appendText("Server: error sending message, try again");
        }
        messageArea.clear();
        messageArea.requestFocus();
    }

    @FXML
    private void initialize() {
        start();
    }

    private void start() {
        Thread thread = new Thread(() -> {
            try {
                loadChatHistory();
                while (socket.isConnected()) {
                    if (inputStream.available() > 0) {
                        String strFromServer = inputStream.readUTF();
                        if (strFromServer.startsWith("/clientConnected") || strFromServer.startsWith("/clientDisconnected")) {
                            updateClientsList(strFromServer);
                        } else if (strFromServer.equals("/end")) {
                            messages.appendText("You have been disconnected from server");
                            break;
                        } else {
                            messages.appendText(strFromServer);
                            saveChatHistory(strFromServer);
                            messages.appendText("\n");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void saveChatHistory(String message) {
        message += "\n";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(history, true);
            fileWriter.write(message);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadChatHistory() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            history.createNewFile();
            fileReader = new FileReader(history);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            Path path = Paths.get(String.valueOf(history));
            long skipLines = Files.lines(path).count();
            if (skipLines > HISTORY_CAPACITY) {
                skipLines -= HISTORY_CAPACITY;
            } else {
                skipLines = 0;
            }
            while (line != null) {
                if (skipLines == 0) {
                    messages.appendText(line);
                    messages.appendText("\n");
                    line = bufferedReader.readLine();
                } else {
                    line = bufferedReader.readLine();
                    skipLines--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateClientsList(String strFromServer) {
        String[] parts = strFromServer.split("\\s");
        switch (parts[0]) {
            case "/clientConnected":
                Platform.runLater(() -> {
                    for (int i = 1; i < parts.length; i++) {
                        if (!nickListItems.contains(parts[i])) {
                            nickListItems.add(parts[i]);
                        }
                    }
                });
                break;
            case "/clientDisconnected":
                Platform.runLater(() -> {
                    nickListItems.retainAll(parts);
                    clientsList.getItems().retainAll(nickListItems);
                });
                break;
        }
        clientsList.setItems(nickListItems);
        clientsList.refresh();
    }
}