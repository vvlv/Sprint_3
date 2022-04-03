import java.util.ArrayList;
import java.util.List;

public class Orders {

    private String firstName ;
    private String lastName ;
    private String address;
    private int metroStation ;
    private String phone;
    private int rentTime ;
    private String deliveryDate ;
    private String comment ;
    private String colorBlack ;
    private String colorGrey;
    private int status;
    private int track;
    List<String> colors = new ArrayList<String>();

public void setColors (String colorBlack, String colorGrey) {
    colors.add(colorBlack);
    colors.add(colorGrey);
}

    public List<String> getColors() {
        return colors;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColorBlack() {
        return colorBlack;
    }

    public void setColorBlack(String colorBlack) {
        this.colorBlack = colorBlack;
    }

    public String getColorGrey() {
        return colorGrey;
    }

    public void setColorGrey(String colorGrey) {
        this.colorGrey = colorGrey;
    }


    public void setTrack (int track) {
        this.track = track;
    }
    public int getTrack () {
        return track;
    }
    public void setStatus (int status) {
        this.status = status;
    }
    public int getStatus () {
        return status;
    }

    public Orders () {

    }
    public Orders (String firstName,String lastName,String address,int metroStation,String phone,int rentTime,String deliveryDate,String comment,String colorBlack,String colorGrey) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhone(phone);
        setRentTime(rentTime);
        setDeliveryDate(deliveryDate);
        setComment(comment);
        setColors(colorBlack,colorGrey);
    }
}
