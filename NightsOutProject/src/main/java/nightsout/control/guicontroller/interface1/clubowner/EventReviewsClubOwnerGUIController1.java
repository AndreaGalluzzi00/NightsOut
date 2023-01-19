package nightsout.control.guicontroller.interface1.clubowner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import nightsout.control.appcontroller.ManageReviewAppController;
import nightsout.control.guicontroller.interface1.item.ReviewItemGUIController1;
import nightsout.utils.Session;
import nightsout.utils.bean.ReviewBean;
import nightsout.utils.exception.ErrorDialog;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.observer.Observer;
import nightsout.utils.scene.switchpage.SwitchPage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EventReviewsClubOwnerGUIController1 implements Observer, Initializable {

    @FXML
    private ListView listViewReviews;

    private SwitchPage switchPage = new SwitchPage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ManageReviewAppController controller;
        try {
            controller = new ManageReviewAppController();
            controller.eventReviews(this,  Session.getInstance().getClubOwner().getId());
        } catch (SystemException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }
    @FXML
    public void backToClubOwnerPage(ActionEvent actionEvent) {switchPage.replaceScene(actionEvent,"/ClubOwnerPage1.fxml");}
    @FXML
    public void goToCommunity(ActionEvent actionEvent) {switchPage.replaceScene(actionEvent,"/MyCommunityPage1.fxml");}

    @Override
    public void update(Object ob) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof ReviewBean reviewBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/ReviewItem1.fxml")).openStream());
                ReviewItemGUIController1 controller = fxmlLoader.getController();
                controller.setAll(reviewBean);
                this.listViewReviews.getItems().add(pane);
            } catch (IOException | SystemException e) {
                ErrorDialog.getInstance().handleException(e);
            }
        }
    }
}
