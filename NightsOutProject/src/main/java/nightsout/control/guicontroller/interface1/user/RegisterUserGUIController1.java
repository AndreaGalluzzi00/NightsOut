package nightsout.control.guicontroller.interface1.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import nightsout.utils.bean.interface1.UserBean1;
import nightsout.utils.exception.ErrorDialog;
import nightsout.utils.exception.myexception.AdultException;
import nightsout.utils.exception.myexception.EmptyInputException;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.switchpage.SwitchAndSetPage1;
import nightsout.utils.switchpage.SwitchPage;

public class RegisterUserGUIController1 {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private RadioButton radioFemale;
    @FXML
    private DatePicker dateBirthday;
    private SwitchAndSetPage1 switchAndSetPage1 = new SwitchAndSetPage1();
    private SwitchPage switchPage = new SwitchPage();

    @FXML
    private void backToChoice(ActionEvent actionEvent) {
        try {
            switchPage.replaceScene(actionEvent, "/RegisterChoice1.fxml");
        } catch (SystemException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }

    @FXML
    private void goToConcludeRegister(ActionEvent actionEvent) {

        try {
            UserBean1 userBean1 = new UserBean1();
            userBean1.setName(textFieldName.getText());
            userBean1.setSurname(textFieldSurname.getText());
            userBean1.setGender(radioFemale.isSelected() ? "Female" : "Male");
            userBean1.setBirthday(dateBirthday.getValue());
            switchAndSetPage1.switchAndSetSceneUser(actionEvent, "/ConcludeRegisterUser1.fxml", userBean1);
        } catch (EmptyInputException | SystemException | AdultException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }
}
