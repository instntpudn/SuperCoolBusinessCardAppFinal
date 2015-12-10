package sjcnymobile.supercoolbusinesscardapp.DataObjects;

import java.io.File;

/**
 * Created by Thomas on 12/1/2015.
 */
public class Bizcard
{
    String id;
    String name;
    String businessName;
    String address;
    String phone;
    String description;
    String email;
    String website;
    String imageName;

    public Bizcard()
    {

    }

    public Bizcard(String id, String name, String businessName, String address, String phone, String description, String email,String website, String imageName)
    {
        setId(id);
        setName(name);
        setBusinessName(businessName);
        setAddress(address);
        setPhone(phone);
        setDescription(description);
        setEmail(email);
        setWebsite(website);
        setImageName(imageName);
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
//        String name, String bizName, String address, String city,
//            String state, String zip, String phone, String description, String email;
        return id + " Name " + name + ", BizName " + businessName + ", address " + address + ", phone " + phone + ", description " + description + ", email " + email+ ", website " + website + ", image " + imageName;
    }
}
