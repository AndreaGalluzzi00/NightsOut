package nightsout.control.guicontroller.interface1.clubowner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import nightsout.utils.Session;
import nightsout.utils.db.MySqlConnection;
import nightsout.utils.exception.ExceptionHandler;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.scene.switchpage.SwitchPage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MenuClubOwnerGUIController1 implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private Circle circleProfile;
    @FXML
    private ImageView imageViewProfile;

    private SwitchPage switchPage = new SwitchPage();
    private MySqlConnection mySqlConnection = new MySqlConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image img = new Image(Session.getInstance().getClubOwner().getImg().toURI().toString());
        circleProfile.setFill(new ImagePattern(img));
        this.usernameLabel.setText(Session.getInstance().getClubOwner().getUsername());
    }
    @FXML
    public void goToCreateEventPage(ActionEvent actionEvent){switchPage.replaceScene(actionEvent,"/CreateEventPage1.fxml");}
    @FXML
    public void goToManageRequestsPage(ActionEvent actionEvent){switchPage.replaceScene(actionEvent,"/ManageRequests1.fxml");}
    @FXML
    public void goToResponsePage(ActionEvent actionEvent){switchPage.replaceScene(actionEvent,"/ReviewsCOPage1.fxml");}
    @FXML
    private void logout(ActionEvent actionEvent) {

        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            try {
                switchPage.replaceScene(actionEvent, "/Welcome1.fxml");
                mySqlConnection.closeConnection();
                Session.getInstance().deleteSession();
                FileUtils.cleanDirectory(new File("eventImgs"));
                FileUtils.cleanDirectory(new File("profileImgs"));
            } catch (SQLException | IOException e) {
                SystemException ex = new SystemException();
                ex.initCause(e);
                ExceptionHandler.getInstance().handleException(ex);
            }
        }
    }
    @FXML
    public void goToHome(ActionEvent actionEvent){switchPage.replaceScene(actionEvent,"/ClubOwnerPage1.fxml");}
}
