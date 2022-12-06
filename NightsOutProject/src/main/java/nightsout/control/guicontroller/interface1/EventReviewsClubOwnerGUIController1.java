package nightsout.control.guicontroller.interface1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import nightsout.control.guicontroller.MyNotification;
import nightsout.utils.exception.ExceptionHandler;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.bean.LoggedClubOwnerBean;
import nightsout.utils.observer.Observer;
import nightsout.utils.observer.engineering.ResponseEngineering;
import nightsout.utils.bean.ClubOwnerBean;
import nightsout.utils.bean.ReviewBean;
import nightsout.utils.scene.ReplaceSceneDynamic1;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class EventReviewsClubOwnerGUIController1 implements Observer {
    @FXML
    private MenuClubOwnerGUIController1 menuController;
    private ClubOwnerBean clubOwnerBean;
    @FXML
    ListView listViewReviews;

    public EventReviewsClubOwnerGUIController1() {

    }

    public void setAll() throws SQLException, SystemException {
        this.clubOwnerBean = LoggedClubOwnerBean.getInstance();
        this.menuController.setAll();
        ResponseEngineering.eventReviews(this, clubOwnerBean.getId());
    }

    public void backToClubOwnerPage(ActionEvent actionEvent) throws IOException {
        try {
            ReplaceSceneDynamic1 replacer = new ReplaceSceneDynamic1();
            replacer.switchAndSetScene(actionEvent, "/ClubOwnerPage1.fxml");

        } catch (SystemException e) {
            MyNotification.createNotification(e);
        }
    }

    @Override
    public void update(Object ob) throws SQLException, SystemException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof ReviewBean reviewBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/ReviewItem1.fxml")).openStream());
            } catch (IOException e) {
                try {
                    ExceptionHandler.handleException(e);
                } catch (SystemException ex) {
                    MyNotification.createNotification(e);
                }
            }
            ReviewItemGUIController1 controller = fxmlLoader.getController();
            controller.setAll(reviewBean);
            this.listViewReviews.getItems().add(pane);
        }
    }
}
