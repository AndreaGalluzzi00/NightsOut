package nightsout.control.appcontroller;

import nightsout.model.ClubOwnerModel;
import nightsout.model.EventModel;
import nightsout.model.RequestModel;
import nightsout.model.UserModel;
import nightsout.utils.bean.ClubOwnerBean;
import nightsout.utils.bean.EventBean;
import nightsout.utils.bean.RequestBean;
import nightsout.utils.bean.UserBean;
import nightsout.utils.dao.ClubOwnerDAO;
import nightsout.utils.dao.EventDAO;
import nightsout.utils.dao.RequestDAO;
import nightsout.utils.dao.UserDAO;
import nightsout.utils.exception.myexception.SystemException;

import java.util.ArrayList;
import java.util.List;

public class EventPageDecoratorAppController {

    private EventPageDecoratorAppController() {
        //ignored
    }

    public static RequestBean checkRequestStatus(UserBean userBean, EventBean eventBean) throws SystemException {

        UserModel userModel = new UserModel(userBean);
        EventModel eventModel = new EventModel(eventBean);
        RequestModel requestModel = RequestDAO.checkRequestStatus(userModel, eventModel);
        if(requestModel==null)
            return null;
        return (new RequestBean(requestModel));
    }

    public static List<EventBean> searchEventsByIdClubOwner(int idClubOwner) throws SystemException {

        List<EventModel> list = EventDAO.getCreatedEventsByIdClubOwner(idClubOwner);
        List<EventBean>  listBean = new ArrayList<>();

        for(EventModel um : list){
            EventBean bean = new EventBean(um);
            listBean.add(bean);
        }
        return listBean;
    }

    public static String getClubAddress(int idEvent) throws SystemException {

        ClubOwnerModel clubOwnerModel = ClubOwnerDAO.getClubAddressByIdEvent(idEvent);
        return (clubOwnerModel.getAddress() + ", " + clubOwnerModel.getCity());
    }


    public static ClubOwnerBean getClubOwner(int idClubOwner) throws SystemException {

        ClubOwnerModel clubOwnerModel = ClubOwnerDAO.getClubOwnerById(idClubOwner);
        return new ClubOwnerBean(clubOwnerModel);
    }

    public static List<UserBean> searchUsersByIdEvent(int idEvent) throws SystemException {

        List<UserModel> list = UserDAO.getUsersByIdEvent(idEvent);
        List<UserBean> listBean = new ArrayList<>();

        for(UserModel um : list){
            UserBean bean = new UserBean(um);
            listBean.add(bean);
        }
        return listBean;
    }


}
