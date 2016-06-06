package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import security.IUser;

@Entity
@Table(name="SystemUser")
public class User implements Serializable, IUser {
  private static final long serialVersionUID = 1L;
  private String password;  //Pleeeeease dont store me in plain text
  
  @Id
  private String userName;
  
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "SystemUser_USERROLE", joinColumns = {
  @JoinColumn(name = "userName", referencedColumnName = "userName")}, inverseJoinColumns = {
  @JoinColumn(name = "roleName")})
  private List<Role> roles = new ArrayList();

  public User() {
  }

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  @Override
  public List<String> getRolesAsStrings(){
    List<String> rolesAsStrings = new ArrayList();
    for(Role role : roles){
      rolesAsStrings.add(role.getRoleName());
    }
    return rolesAsStrings;
  }
  
  public void AddRole(Role role){
    roles.add(role);
    role.addUser(this);
  }
    
  public List<Role> getRoles() {
   return roles;
  }
 
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
   
}