package nightsout.control.appcontroller;

import nightsout.model.ClubOwnerModel;
import nightsout.model.EventModel;
import nightsout.model.RequestModel;
import nightsout.model.UserModel;
import nightsout.utils.bean.*;
import nightsout.utils.dao.ClubOwnerDAO;
import nightsout.utils.dao.EventDAO;
import nightsout.utils.dao.RequestDAO;
import nightsout.utils.dao.UserDAO;
import nightsout.utils.exception.myexception.SystemException;
import nightsout.utils.observer.GenericBeanList;
import nightsout.utils.observer.ManageRequestBeanList;
import nightsout.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class JoinEventAppController {

    public void acceptRequest(IdBean idBean) throws SystemException {
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.updateRequestStatus(idBean.getId(),"accepted");}

    public void declineRequest(IdBean idBean) throws SystemException {
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.updateRequestStatus(idBean.getId(),"declined");}

    public UserBean searchUserByUsername(UsernameBean usernameBean) throws SystemException {
        UserDAO userDAO = new UserDAO();
        UserModel userModel = userDAO.getUserByUsername(usernameBean.getUsername());
        return new UserBean(userModel);
    }
    public void sendRequest(UserBean userBean, EventBean eventBean) throws SystemException {
        UserModel userModel = new UserModel(userBean);
        EventModel eventModel = new EventModel(eventBean);
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.createRequest(userModel, eventModel);
    }

    public EventBean searchEventByIdEvent(IdBean idBean) throws SystemException {
        EventDAO eventDAO = new EventDAO();
        EventModel eventModel = eventDAO.getEventByIdEvent(idBean.getId());
        return new EventBean(eventModel);
    }

    public void search(Observer observer, SearchBean searchBean) throws SystemException {

        GenericBeanList list = new GenericBeanList(observer);
        list.addEventsToList(searchEventsByName(searchBean));
        list.addUsersToList(searchUsersByUsername(searchBean.getSearchText()));
        list.addClubOwnersToList(searchClubOwnersByUsername(searchBean.getSearchText()));
    }

    private List<UserBean> searchUsersByUsername(String input) throws SystemException {

        List<UserModel> list = null;
        List<UserBean> listBean = null;
        UserDAO userDAO = new UserDAO();
        list = userDAO.getUsersByUsername(input);
        listBean = new ArrayList<>();
        if (list != null) {
            for (UserModel um : list) {
                UserBean bean = new UserBean(um);
                listBean.add(bean);
            }
        }
        return listBean;
    }

    private List<ManageRequestBean> searchRequestsByIdClubOwner(int idClubOwner) throws SystemException {

        RequestDAO requestDAO = new RequestDAO();
        List<RequestModel> list = requestDAO.getRequestsByIdClubOwner(idClubOwner);
        List<ManageRequestBean> listBean = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        EventDAO eventDAO = new EventDAO();

        for(RequestModel rm : list){
            EventBean eb = new EventBean(eventDAO.getEventByIdEvent(rm.getIdEvent()));
            UserBean ub = new UserBean(userDAO.getUserByidUser(rm.getIdUser()));
            ManageRequestBean bean = new ManageRequestBean(rm,ub,eb);
            listBean.add(bean);
        }
        return listBean;
    }
    private List<ClubOwnerBean> searchClubOwnersByUsername(String input) throws SystemException {

        ClubOwnerDAO clubOwnerDAO = new ClubOwnerDAO();
        List<ClubOwnerModel> list = clubOwnerDAO.getClubOwnersByUsername(input);
        List<ClubOwnerBean> listBean = new ArrayList<>();
        if (list != null) {
            for (ClubOwnerModel clubOwnerModel : list) {
                ClubOwnerBean bean = new ClubOwnerBean(clubOwnerModel);
                listBean.add(bean);
            }
        }
        return listBean;
    }

    public List<EventBean> searchEventsByName(SearchBean searchBean) throws SystemException {

        List<EventModel> list = null;
        List<EventBean> listBean = null;
        EventDAO eventDAO = new EventDAO();
        list = eventDAO.getEventsByName(searchBean.getSearchText());
        listBean = new ArrayList<>();
        if (list != null) {
            for (EventModel eventModel : list) {
                EventBean bean = new EventBean(eventModel);
                listBean.add(bean);
            }
        }
        return listBean;
    }

    public void manageRequests(Observer observer, IdBean idBean) throws SystemException {

        ManageRequestBeanList list = new ManageRequestBeanList(observer);
        list.addRequestsToList(searchRequestsByIdClubOwner(idBean.getId()));
    }

}
