package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERROLE")
public class Role implements Serializable {
  
  @ManyToMany(mappedBy = "roles")
  private List<User> users = new ArrayList();
  
  private static final long serialVersionUID = 1L;
  @Id
  private String roleName ;

  public Role(String roleName) {
    this.roleName = roleName;
  }

  public Role() {
  }
  
  public void addUser(User user){
    users.add(user);
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
}