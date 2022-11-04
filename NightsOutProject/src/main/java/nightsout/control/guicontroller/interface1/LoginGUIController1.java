package nightsout.control.guicontroller.interface1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nightsout.control.appcontroller.LoginAppController;
import nightsout.control.guicontroller.MyNotification;
import nightsout.utils.bean.ClubOwnerBean;
import nightsout.utils.bean.UserBean;
import nightsout.utils.bean.interface1.LoginBean1;
import nightsout.utils.exception.myexception.WrongPasswordException;
import nightsout.utils.scenes.ReplaceSceneDynamic1;

import java.util.Objects;

public class LoginGUIController1 {

    @FXML
    TextField textFieldUsername;

    @FXML
    PasswordField passwordField;

    @FXML
    Button buttonLogin;

    @FXML
    CheckBox checkBoxClubOwner;

    @FXML
    void loginAction(ActionEvent ae) throws Exception {
        String type = "Free";

        if(checkBoxClubOwner.isSelected()) {
            type = "ClubOwner";
        }

        try {
            LoginBean1 loginBean = new LoginBean1(textFieldUsername.getText(), passwordField.getText(), type);
            ReplaceSceneDynamic1 replacer = new ReplaceSceneDynamic1();

            if (Objects.equals(type,"ClubOwner")) {
                ClubOwnerBean clubOwnerBean = LoginAppController.loginClubOwner(loginBean);
                replacer.switchAndSetScene(ae, "/ClubOwnerPage1.fxml", null, clubOwnerBean);
            } else {
                UserBean userBean = LoginAppController.loginUser(loginBean);
                replacer.switchAndSetScene(ae, "/UserPage1.fxml", userBean, null);
            }

        } catch (WrongPasswordException e) { MyNotification.createNotification(e); }
    }


}
