package nightsout.control.guicontroller.interface1.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nightsout.control.appcontroller.JoinEventAppController;
import nightsout.utils.Session;
import nightsout.utils.bean.interface1.ClubOwnerBean1;
import nightsout.utils.bean.interface1.UserBean1;
import nightsout.utils.exception.ErrorDialog;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.switchpage.SwitchAndSetPage1;

public class UserItemGUIController1 {

    private UserBean1 userBean1;
    private ClubOwnerBean1 clubOwnerBean1;
    private SwitchAndSetPage1 switchAndSetPage1 = new SwitchAndSetPage1();
    @FXML
    private Label labelType;
    @FXML
    private Label labelUsername;
    @FXML
    private ImageView imageViewProfilePic;

    private JoinEventAppController joinEventAppController;

    public void setAll(UserBean1 userBean1) {
        this.userBean1 = userBean1;
        labelUsername.setText(this.userBean1.getUsername());
        labelType.setText("USER");
        imageViewProfilePic.setImage(new Image(this.userBean1.getImg().toURI().toString()));
    }


    public void setAll(UserBean1 userBean1, JoinEventAppController joinEventAppController) {

        this.joinEventAppController = joinEventAppController;
        setAll(userBean1);

    }

    public void setAll(ClubOwnerBean1 clubOwnerBean1) {
        this.clubOwnerBean1 = clubOwnerBean1;
        labelUsername.setText(this.clubOwnerBean1.getUsername());
        labelType.setText("CLUB OWNER");
        imageViewProfilePic.setImage(new Image(this.clubOwnerBean1.getImg().toURI().toString()));
    }

    public void setAll(ClubOwnerBean1 clubOwnerBean1, JoinEventAppController joinEventAppController) {

        this.joinEventAppController = joinEventAppController;
        setAll(clubOwnerBean1);
    }

    @FXML
    private void goToProfile(ActionEvent actionEvent) {

        try {
            String type = Session.getInstance().checkInstanceType();
            if (clubOwnerBean1 != null) {
                switchAndSetPage1.switchAndSetSceneClubOwner(actionEvent, "/ViewClubOwnerPageFromUser1.fxml", this.clubOwnerBean1, joinEventAppController);
            } else {
                if (type.equalsIgnoreCase("Free")) {
                    switchAndSetPage1.switchAndSetSceneUser(actionEvent, "/ViewUserPageFromUser1.fxml", this.userBean1, joinEventAppController);
                } else {
                    switchAndSetPage1.switchAndSetSceneUser(actionEvent, "/ViewUserPageFromCO1.fxml", this.userBean1);
                }
            }
        } catch (SystemException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }
}
