package nightsout.utils.scenes.interface1;

import nightsout.control.guicontroller.interface1.EventPageSendRequestGUIController1;
import nightsout.utils.bean.EventBean;
import nightsout.utils.bean.UserBean;

public class EventPageSetter1 {
    public static void setter(UserBean userBean, EventBean eventBean, String oldInput, EventPageSendRequestGUIController1 eventPageSendRequestGUIController1) {
        eventPageSendRequestGUIController1.setAll(userBean, eventBean, oldInput);
    }
}
