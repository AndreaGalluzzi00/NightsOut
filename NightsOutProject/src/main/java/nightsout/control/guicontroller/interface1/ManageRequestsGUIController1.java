package nightsout.control.guicontroller.interface1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import nightsout.utils.observer.engineering.ManageRequestsEngineering;
import nightsout.utils.observer.Observer;
import nightsout.utils.bean.ClubOwnerBean;
import nightsout.utils.bean.ManageRequestBean;
import nightsout.utils.scene.ReplaceSceneDynamic1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ManageRequestsGUIController1 implements Observer {

    private ClubOwnerBean clubOwnerBean;

    @FXML
    ListView listViewPendingRequests;

    @FXML
    public void backToWelcomePage(ActionEvent actionEvent) throws IOException {
        ReplaceSceneDynamic1 replacer = new ReplaceSceneDynamic1();
        replacer.switchAndSetScene(actionEvent, "/ClubOwnerPage1.fxml", null, clubOwnerBean);
    }

    @FXML
    private void manageRequests() throws SQLException {
        this.listViewPendingRequests.getItems().clear();
        ManageRequestsEngineering.manageRequests(this, clubOwnerBean.getId());
    }

    public void setAll(ClubOwnerBean clubOwnerBean) throws SQLException {

        this.clubOwnerBean = clubOwnerBean;
        this.manageRequests();
    }

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;
        if(ob instanceof ManageRequestBean mRBean) {

            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/ManageRequestsItem1.fxml")).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ManageRequestsItemGUIController1 controller = fxmlLoader.getController();
            controller.setAll(mRBean,clubOwnerBean);
            this.listViewPendingRequests.getItems().add(pane);
        }
    }
}
