package nightsout.control.guicontroller.interface1.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import nightsout.control.appcontroller.ManageReviewAppController;
import nightsout.utils.Session;
import nightsout.utils.bean.ReviewBean;
import nightsout.utils.bean.interface1.EventBean1;
import nightsout.utils.bean.interface1.UserBean1;
import nightsout.utils.exception.ErrorDialog;
import nightsout.utils.exception.myexception.EmptyInputException;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.switchpage.SwitchPage;

public class MakeReviewGUIController1 {

    private EventBean1 eventBean1;
    private UserBean1 userBean1;
    private SwitchPage switchPage = new SwitchPage();
    @FXML
    private Label labelEventName;
    @FXML
    private TextArea textAreaReview;
    private ManageReviewAppController manageReviewAppController;

    public void setAll(EventBean1 eventBean1, ManageReviewAppController manageReviewAppController) {

        this.userBean1 = new UserBean1(Session.getInstance().getUser());
        this.manageReviewAppController = manageReviewAppController;
        this.eventBean1 = eventBean1;
        this.labelEventName.setText(eventBean1.getName());
    }

    @FXML
    private void createReview(ActionEvent actionEvent) {

        try {
            ReviewBean reviewBean= new ReviewBean();
            reviewBean.setComment(textAreaReview.getText());
            reviewBean.setIdUser(userBean1.getId());
            reviewBean.setIdEvent(eventBean1.getIdEvent());
            manageReviewAppController.createEventReview(reviewBean);
            switchPage.replaceScene(actionEvent,"/UserPage1.fxml");
        } catch (SystemException | EmptyInputException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }

    @FXML
    private void backToEndedBookedEventsPage(ActionEvent actionEvent) {
        try {
            switchPage.replaceScene(actionEvent, "/EndedBookedEventsPage1.fxml");
        } catch (SystemException e) {
            ErrorDialog.getInstance().handleException(e);
        }
    }
}
