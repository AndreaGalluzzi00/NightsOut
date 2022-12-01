package nightsout.control.guicontroller.interface1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nightsout.utils.bean.EventBean;
import nightsout.utils.bean.RequestBean;
import nightsout.utils.scene.ReplaceSceneDynamic1;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class CheckRequestsItemGUIController1 {


    @FXML
    Label labelEventName;
    @FXML
    Label labelEventDate;
    @FXML
    Label labelUsername;
    @FXML
    Button buttonStatus;


    private EventBean eventBean;

    public void setAll(RequestBean requestBean, EventBean eventBean){
        this.eventBean = eventBean;
        this.labelEventName.setText(String.valueOf(requestBean.getIdEvent()));
        this.buttonStatus.setText(requestBean.getStatus());
        //this.labelUsername.setText(String.valueOf(requestBean.get()));
        this.labelEventDate.setText(requestBean.getRequestDate().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")));
        //this.labelEventDate.setText(requestBean.getRequestDate().toString());  UGUALE A SOPRA
    }

    @FXML
    private void goToEventPage(ActionEvent actionEvent) throws IOException {
        ReplaceSceneDynamic1 replacer = new ReplaceSceneDynamic1();
        replacer.switchAndSetSceneEventUser(actionEvent, "/EventPageDecoratorUser1.fxml", this.eventBean);
    }

}
