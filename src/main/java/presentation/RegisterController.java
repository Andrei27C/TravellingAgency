package presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;

import java.io.IOException;

public class RegisterController {
    public TextField usernameTF;
    public TextField passwordTF;
    public TextField emailTF;

    UserService userService = new UserService();

    public void register(ActionEvent actionEvent) throws IOException {
        String userFinder = userService.registerUser(usernameTF.getText(), emailTF.getText(), passwordTF.getText());
        if(userFinder.equals("username_found"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Username already in use!");
            alert.showAndWait();
        }
        else if(userFinder.equals("email_found"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Email already in use!");
            alert.showAndWait();
        }
        else if(userFinder.equals("invalid_password"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Password should contain at least one numeric character, one lowercase, one uppercase, a special symbol(@#$%), and be at least 8 characters long!");
            alert.showAndWait();
        }
        else if(userFinder.equals("invalid_email"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Mail not good");
            alert.showAndWait();
        }
        else if(userFinder.equals("username_empty"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please Input Username!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("User added!");
            alert.showAndWait();
        }

    }
}
