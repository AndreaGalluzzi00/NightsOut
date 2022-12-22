package nightsout.utils.scene.scenesetter;

import nightsout.control.guicontroller.interface1.EventReviewsClubOwnerGUIController1;
import nightsout.control.guicontroller.interface1.MakeResponseGUIController1;
import nightsout.utils.bean.ReviewBean;
import nightsout.utils.bean.interface1.UserBean1;
import nightsout.utils.exception.myexception.SystemException;

public class ResponsePageSetter1 {

    private ResponsePageSetter1() {
        //ignored
    }

    public static void setter(EventReviewsClubOwnerGUIController1 eventReviewsClubOwnerGUIController1) throws SystemException {
        eventReviewsClubOwnerGUIController1.setAll();
    }

    public static void setter2(UserBean1 userBean1, ReviewBean reviewBean, MakeResponseGUIController1 makeResponseGUIController1) throws SystemException {
        makeResponseGUIController1.setAll(userBean1,reviewBean);
    }
}
