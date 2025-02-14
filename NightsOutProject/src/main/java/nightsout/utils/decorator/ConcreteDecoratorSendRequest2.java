package nightsout.utils.decorator;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nightsout.control.appcontroller.JoinEventAppController;
import nightsout.utils.Session;
import nightsout.utils.bean.interface2.EventBean2;
import nightsout.utils.bean.interface2.UserBean2;
import nightsout.utils.exception.ErrorDialog;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.switchpage.SwitchAndSetPage2;

public class ConcreteDecoratorSendRequest2 extends Decorator {

    private UserBean2 userBean;
    private EventBean2 eventBean;
    private SwitchAndSetPage2 switchAndSetPage2 = new SwitchAndSetPage2();
    private String toWrite;
    private JoinEventAppController joinEventAppController;

    public ConcreteDecoratorSendRequest2(Component component, EventBean2 eventBean, JoinEventAppController joinEventAppController) {
        super(component);
        this.joinEventAppController = joinEventAppController;
        this.userBean = new UserBean2(Session.getInstance().getUser());
        this.eventBean = eventBean;
    }

    protected void applyDecorationSendRequest(Button myButton) {
        myButton.setText(toWrite);
        myButton.setMinHeight(65);
        myButton.setMinWidth(125);
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        myButton.setFont(font);
        myButton.setStyle("-fx-background-color: #b3b3ff;" + "-fx-background-radius: 28;" + "-fx-text-fill: white;");
        myButton.setOnAction(this::sendRequest);
    }

    private void sendRequest(ActionEvent actionEvent){

        try {
            joinEventAppController.sendRequest(this.userBean, eventBean);
            switchAndSetPage2.switchAndSetSceneEvent(actionEvent, "/EventPageFromUser2.fxml", eventBean);
        } catch (SystemException e){
            ErrorDialog.getInstance().handleException(e);
        }
    }

    @Override
    public Button getButton() {
        Button myButton = super.getButton();
        this.toWrite = "Send Request";
        this.applyDecorationSendRequest(myButton);
        return myButton;
    }

}
