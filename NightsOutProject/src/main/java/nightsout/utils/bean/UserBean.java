package nightsout.utils.bean;

import nightsout.model.UserModel;
import nightsout.utils.exception.Trigger;
import nightsout.utils.exception.myexception.EmptyInputException;

import java.time.LocalDate;

public class UserBean extends ProfileBean {

    private static Trigger trigger = new Trigger();

    //Attributi User
    protected String surname;
    protected String gender;
    protected LocalDate birthday;
    protected LocalDate creationDateVIP;
    protected boolean vip;


    public UserBean(){
    }

    public UserBean(UserModel userModel) {
        this.surname = userModel.getSurname();
        this.name = userModel.getName();
        this.username = userModel.getUsername();
        this.gender = userModel.getGender();
        this.email = userModel.getEmail();
        this.id = userModel.getId();
        this.img = userModel.getProfileImg();
        this.birthday = userModel.getBirthday();
        this.vip = userModel.getVip();
        this.creationDateVIP = userModel.getCreationDateVip();
    }

    public UserBean(UserBean userBean) {
        this.surname = userBean.getSurname();
        this.name = userBean.getName();
        this.username = userBean.getUsername();
        this.gender = userBean.getGender();
        this.email = userBean.getEmail();
        this.id = userBean.getId();
        this.img = userBean.getImg();
        this.birthday = userBean.getBirthday();
        this.vip = userBean.getVip();
        this.creationDateVIP = userBean.getCreationDateVIP();
    }

    // Getter
    public String getSurname() {return surname;}
    public String getGender() {return gender;}

    public LocalDate getBirthday() { return birthday;}

    // Setter
    public void setSurname(String surname) throws EmptyInputException {
        if (surname.equals(""))
            trigger.throwEmptyInputException("Surname");
        this.surname = surname;
    }

    public boolean getVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public LocalDate getCreationDateVIP() {
        return creationDateVIP;
    }

    public void setCreationDateVIP(LocalDate creationDateVIP) {
        this.creationDateVIP = creationDateVIP;
    }

}
