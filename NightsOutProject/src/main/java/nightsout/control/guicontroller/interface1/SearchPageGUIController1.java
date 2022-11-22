package nightsout.control.guicontroller.interface1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import nightsout.utils.observer.Observer;
import nightsout.utils.observer.engineering.SearchEngineering;
import nightsout.utils.bean.ClubOwnerBean;
import nightsout.utils.bean.EventBean;
import nightsout.utils.bean.UserBean;
import nightsout.utils.scene.ReplaceSceneDynamic1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SearchPageGUIController1 implements Observer {

    private UserBean userBean;
    private String input;

    @FXML
    private TextField textFieldSearch;
    @FXML
    private ListView listView;

    public void setAll(UserBean userBean) {
        this.userBean = userBean;
    }

    public void setAllOldInput(UserBean userBean, String oldInput) throws SQLException {
        this.userBean = userBean;
        textFieldSearch.setText(oldInput);
        this.search();
    }

    @FXML
    private void backToUserPage(ActionEvent actionEvent) throws IOException {
        ReplaceSceneDynamic1 replacer = new ReplaceSceneDynamic1();
        replacer.switchAndSetScene(actionEvent, "/UserPage1.fxml", userBean, null);
    }

    @FXML
    private void search() throws SQLException {
        input = textFieldSearch.getText();
        this.listView.getItems().clear();
        if(!input.isBlank())
            SearchEngineering.search(this, input);
    }

    @Override
    public void update(Object ob) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;

        if(ob instanceof UserBean uBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/UserItem1.fxml")).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            UserItemGUIController1 controller = fxmlLoader.getController();
            controller.setAll(uBean);

            this.listView.getItems().add(pane);
        }

        if(ob instanceof EventBean eBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/EventItem1.fxml")).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            EventItemGUIController1 controller = fxmlLoader.getController();
            controller.setAll(userBean, eBean, "/SearchPage1.fxml");

            this.listView.getItems().add(pane);
        }

        if(ob instanceof ClubOwnerBean cBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/UserItem1.fxml")).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            UserItemGUIController1 controller = fxmlLoader.getController();
            controller.setAll(this.userBean, cBean);

            this.listView.getItems().add(pane);
        }
    }
/*
    @Override
    public void updateFrom(Object ob, Object from) {}
 */
}
