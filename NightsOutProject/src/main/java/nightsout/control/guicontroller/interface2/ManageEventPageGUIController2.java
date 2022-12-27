package nightsout.control.guicontroller.interface2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nightsout.control.appcontroller.CreateEventAppController;
import nightsout.control.guicontroller.MyNotification;
import nightsout.control.guicontroller.interface2.Item.EventItemGUIController2;
import nightsout.utils.Email;
import nightsout.utils.bean.EventBean;
import nightsout.utils.bean.LoggedClubOwnerBean2;
import nightsout.utils.bean.interface2.EventBean2;
import nightsout.utils.exception.myexception.*;
import nightsout.utils.observer.Observer;
import nightsout.utils.observer.engineering.CreatedEventsEngineering;
import nightsout.utils.scene.switchPage.SwitchAndSetPage2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageEventPageGUIController2 implements Initializable, Observer {

    @FXML
    Button buttonCreateEvent;
    @FXML
    Slider sliderTime;
    @FXML
    TextField textFieldEventDate;
    @FXML
    TextField textFieldEventTime;
    @FXML
    TextField textFieldName;
    @FXML
    TextField textFieldPrice;
    @FXML
    TextField textFieldHours;
    @FXML
    TextField textFieldMinutes;
    @FXML
    TextArea textFieldDescription;
    @FXML
    ImageView imageViewProfile;
    @FXML
    ListView listViewCreatedEvents;

    private File img;

    @FXML
    public void createEvent(ActionEvent actionEvent) {

        EventBean2 eventBean = new EventBean2();

        try {
            eventBean.setEventDate(textFieldEventDate.getText());
            eventBean.setDuration((int) sliderTime.getValue());
            eventBean.setTime(textFieldEventTime.getText());
            eventBean.setName(textFieldName.getText());
            eventBean.setIdClubOwner(LoggedClubOwnerBean2.getInstance().getId());
            eventBean.setDescription(textFieldDescription.getText());
            eventBean.setPrice(textFieldPrice.getText());
            eventBean.setImg(this.img);

            CreateEventAppController.createEvent(eventBean);

            try {
                Email.sendEmail(LoggedClubOwnerBean2.getInstance().getEmail(), "Evento creato con successo!", "L'evento " + eventBean.getName() + " è stato creato con successo.");
            } catch (EmailException e) {
                MyNotification.createNotification(e);
            }

            SwitchAndSetPage2 replacer = new SwitchAndSetPage2();
            replacer.switchAndSetScene(actionEvent, "/ManageEventPage2.fxml");

        } catch (WrongInputTypeException | EmptyInputException | SystemException | BeforeDateException e) {
            MyNotification.createNotification(e);
        }


    }

    public void loadImage() {
        Stage stage = (Stage) textFieldName.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files", "*.png", "*.jpg", "*.jpeg"));
        img = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        this.imageViewProfile.setImage(new Image(img.toURI().toString()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            CreatedEventsEngineering.createdEvents(this, LoggedClubOwnerBean2.getInstance().getId());
        } catch (SystemException e) {
            MyNotification.createNotification(e);
            e.getCause().printStackTrace();
        }
    }

    @Override
    public void update(Object ob) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = null;

        if(ob instanceof EventBean eBean) {
            try {
                pane = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("/EventItem2.fxml")).openStream());
                EventItemGUIController2 controller = fxmlLoader.getController();
                controller.setAll(new EventBean2(eBean));
                this.listViewCreatedEvents.getItems().add(pane);
            }
            catch (IOException e) {
                MyNotification.createNotification(e);
            }
        }
    }
}
