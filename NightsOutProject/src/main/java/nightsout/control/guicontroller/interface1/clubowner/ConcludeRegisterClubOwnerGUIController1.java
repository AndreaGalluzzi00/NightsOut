package nightsout.control.guicontroller.interface1.clubowner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nightsout.control.appcontroller.RegisterAppController;
import nightsout.utils.bean.CredentialsBean;
import nightsout.utils.bean.interface1.ClubOwnerBean1;
import nightsout.utils.exception.ErrorDialog;
import nightsout.utils.exception.myexception.*;
import nightsout.utils.switchpage.SwitchPage;

import java.io.File;

public class ConcludeRegisterClubOwnerGUIController1 {

    private ClubOwnerBean1 clubOwnerBean1;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView imageViewProfile;
    @FXML
    private TextField textFieldEmail;
    private File img;

    private SwitchPage switchPage = new SwitchPage();

    @FXML
    private void backToRegister(ActionEvent actionEvent) {
        try {
            switchPage.replaceScene(actionEvent, "/RegisterClubOwner1.fxml");
        } catch (SystemException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }

    @FXML
    private void goToWelcomePage(ActionEvent actionEvent) {
        RegisterAppController controller;
        CredentialsBean credentialsBean;
        try {
            controller = new RegisterAppController();
            credentialsBean = new CredentialsBean();
            clubOwnerBean1.setUsername(textFieldUsername.getText());
            credentialsBean.setUsername(textFieldUsername.getText());
            credentialsBean.setPassword(passwordField.getText());
            credentialsBean.setType("ClubOwner");
            clubOwnerBean1.setEmail(textFieldEmail.getText());
            clubOwnerBean1.setImg(img);
            controller.registerClubOwner(clubOwnerBean1, credentialsBean);
            switchPage.replaceScene(actionEvent, "/Welcome1.fxml");
        } catch (EmptyInputException | EmailNotValidException | SystemException | UsernameAlreadyTakenException |
                 PasswordNotCompliantException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }

    public void setAll(ClubOwnerBean1 clubOwnerBean1) {
        this.clubOwnerBean1 = clubOwnerBean1;
    }

    @FXML
    private void loadImage() {

        Stage stage = (Stage) textFieldUsername.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files", "*.png", "*.jpg", "*.jpeg"));
        img = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        imageViewProfile.setImage(new Image(img.toURI().toString()));
    }
}