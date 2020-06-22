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