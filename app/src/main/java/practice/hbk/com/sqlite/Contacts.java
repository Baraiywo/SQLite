package practice.hbk.com.sqlite;

/**
 * Created by H.B.K on 19-Oct-17.
 */

public class Contacts {

    //private variables
    int _id;
    String _name;
    String _phone_number;


    //Empty Constructor
    public Contacts() {

    }


    // constructor
    public Contacts(int id, String name, String _phone_number) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    public Contacts(String _name, String _phone_number) {
        this._name = _name;
        this._phone_number = _phone_number;

    }

    //getting id
    public int getId() {
        return this._id;
    }

    //setting ID
    public void setId(int id) {
        this._id = id;
    }

    //getting name
    public String getName(){
        return this._name;
    }

    //setting name
    public void setName(String name){
        this._name = name;
    }

    //getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    //setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}