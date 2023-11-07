package kata.makers;

public class Customer {
  String firstName;
  String lastName;
  String emailAddress;

  public Customer(String firstName, String lastName, String emailAddress){
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
  }

  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  public String getFirstName(){
    return this.firstName;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  public String getLastName(){
    return this.lastName;
  }

  public void setEmailAddress(String emailAddress){
    this.emailAddress = emailAddress;
  }

  public String getEmailAddress(){
    return this.emailAddress;
  }

}
