package nightsout.control.guicontroller.interface1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import nightsout.control.appcontroller.SubscriptionVipAppController;
import nightsout.utils.bean.LoggedUserBean;
import nightsout.utils.bean.UserBean;
import nightsout.utils.scene.ReplaceSceneDynamic1;

import java.io.IOException;
import java.sql.SQLException;

public class SubscriptionVipPageGUIController1 {

    private UserBean userBean;
    @FXML
    private MenuUserGUIController1 menuController;

    @FXML
    private void backToUserPage(ActionEvent actionEvent) throws IOException {
        ReplaceSceneDynamic1 replacer = new ReplaceSceneDynamic1();
        replacer.switchAndSetScene(actionEvent, "/UserPage1.fxml");
    }

    @FXML
    private void confirmSubscription(ActionEvent actionEvent) throws IOException, SQLException {
        //UserBean userBeanUpdated = SubscriptionVipAppController.subscription(userBean);
        UserBean userBean= LoggedUserBean.getInstance();
        SubscriptionVipAppController.subscription(userBean);
        //cambiare il bean perche cosi ho cambiato solo il db
        ReplaceSceneDynamic1 replacer = new ReplaceSceneDynamic1();
        replacer.switchAndSetScene(actionEvent, "/UserPage1.fxml");
    }

    public void setAll() throws SQLException {
        this.userBean = LoggedUserBean.getInstance();
        this.menuController.setAll();
    }

}
