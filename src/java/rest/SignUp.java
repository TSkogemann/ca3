package rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import openshift_deploy.DeploymentConfiguration;
import security.PasswordStorage;

@Path("signUp")
public class SignUp {
  EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String signUp(String jsonString){
      try {
          JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
          String username = json.get("username").getAsString();
          String password = json.get("password").getAsString();
          User createUser = new User(username, PasswordStorage.createHash(password));
          final EntityManager em = emf.createEntityManager();
          try {
          em.getTransaction().begin();
          em.persist(createUser);
          em.getTransaction().commit();
          } finally{em.close();}
          
      } catch (PasswordStorage.CannotPerformOperationException ex) {
          Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
     
      }
      return "{}";
  }
 
}