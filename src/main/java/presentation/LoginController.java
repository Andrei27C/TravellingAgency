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
import java.util.Objects;

public class LoginController {

    public TextField usernameTF;
    public TextField passwordTF;

    UserService userService = new UserService();

    public void login(ActionEvent actionEvent) throws IOException {
        String userFinder = userService.userOrAdmin(usernameTF.getText(), passwordTF.getText());
        if(userFinder.equals("admin"))
        {
            loadAdmin();
        }
        else if(userFinder.equals("user"))
            loadUser();
        else if(userFinder.equals("incorrect_password"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect Password!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("User not found!");
            alert.showAndWait();
        }

    }

    private void loadUser() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("userMenu.fxml"));
            Parent root = loader.load();

            //UserMenuController secController = loader.getController();
            //secController.getUsername(usernameTF.getText());
            //secController.setDeliveryService(deliveryService);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1011, 720));
            stage.show();
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    private void loadAdmin() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("adminMenu.fxml"));
            Parent root = loader.load();

//            AdminMenuController secController = loader.getController();
//            secController.setDeliveryService(deliveryService);

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1280, 620));
            stage.show();
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }


//todo
    public void register(ActionEvent actionEvent) {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root, 250, 350));
            registerStage.show();

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }
}
