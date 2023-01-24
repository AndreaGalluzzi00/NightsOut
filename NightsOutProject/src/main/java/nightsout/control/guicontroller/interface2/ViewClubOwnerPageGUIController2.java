package nightsout.control.guicontroller.interface2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import nightsout.control.guicontroller.interface2.item.EventItemGUIController2;
import nightsout.control.guicontroller.interface2.item.ResponseItemGUIController2;
import nightsout.control.guicontroller.interface2.item.ReviewItemGUIController2;
import nightsout.utils.bean.ClubOwnerBean;
import nightsout.utils.bean.EventBean;
import nightsout.utils.bean.ResponseBean;
import nightsout.utils.bean.ReviewBean;
import nightsout.utils.bean.interface2.ClubOwnerBean2;
import nightsout.utils.bean.interface2.EventBean2;
import nightsout.utils.engineering.CreatedEventsEngineering;
import nightsout.utils.engineering.CommunityEngineering;
import nightsout.utils.exception.ErrorDialog;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.observer.Observer;

import java.io.IOException;
import java.util.Objects;

public class ViewClubOwnerPageGUIController2 implements Observer {
    @FXML
    private Label labelCity;
    @FXML
    private Label labelName;
    @FXML
    private ListView listViewCreatedEvents;
    @FXML
    private ListView listViewCommunity;
    private ClubOwnerBean clubOwnerBean;
    @FXML
    private Label labelAddress;
    @FXML
    private Label labelEmail;
    @FXML
    private ImageView imageViewProfile;
    @FXML
    private Label labelDiscountVip;
    @FXML
    private Label labelUsername;

    public void setAll(ClubOwnerBean2 clubOwnerBean) throws SystemException {

        this.clubOwnerBean = clubOwnerBean;
        this.labelName.setText(clubOwnerBean.getName());
        this.labelUsername.setText(clubOwnerBean.getUsername());
        this.labelCity.setText(clubOwnerBean.getCity());
        this.labelAddress.setText(clubOwnerBean.getAddress());
        this.labelEmail.setText(clubOwnerBean.getEmail());
        this.labelDiscountVip.setText(String.valueOf(clubOwnerBean.getDiscountVIP())+"%");
        this.imageViewProfile.setImage(new Image(clubOwnerBean.getImg().toURI().toString()));
        CreatedEventsEngineering createdEventsEngineering = new CreatedEventsEngineering();
        createdEventsEngineering.createdEvents(this, clubOwnerBean.getId());
        CommunityEngineering communityEngineering = new CommunityEngineering();
        communityEngineering.eventReviews(this, this.clubOwnerBean.getId());
    }

    @Override
    public void update(Object ob) {

        CommunityEngineering communityEngineering;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;


        if (ob instanceof ReviewBean reviewBean) {
            try {
                communityEngineering = new CommunityEngineering();
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/ReviewItem2.fxml")).openStream());
                ReviewItemGUIController2 controller = fxmlLoader.getController();
                controller.setAll(reviewBean);
                this.listViewCommunity.getItems().add(pane);
                communityEngineering.responseOfOneReview(this, reviewBean.getIdReview());
            } catch (IOException | SystemException e) {
                ErrorDialog.getInstance().handleException(e);
            }
        }
        if(ob instanceof EventBean eBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/EventItem2.fxml")).openStream());
                EventItemGUIController2 controller = fxmlLoader.getController();
                controller.setAll(new EventBean2(eBean));
                this.listViewCreatedEvents.getItems().add(pane);
            } catch (IOException e) {
                ErrorDialog.getInstance().handleException(e);
            }
        }
        if(ob instanceof ResponseBean responseBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/ResponseItem2.fxml")).openStream());
                ResponseItemGUIController2 controller = fxmlLoader.getController();
                controller.setAllCommunity(responseBean, this.clubOwnerBean);
                this.listViewCommunity.getItems().add(pane);
            } catch (IOException e) {
                ErrorDialog.getInstance().handleException(e);
            }
        }
    }
}


