import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import models.User;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.WS;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Hello World!.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Hello World!.");
    }
    
    @Test
    public void renderRegistration() {
    User user = new User();	
    user.setCompany("Juhomi");
    user.setEmail("ravi@juhomi.com");
    user.setFullName("ravikumar");
    Form<User> userForm = Form.form(User.class);
    Content html = views.html.edit_user.render(userForm.fill(user),user);
    assertThat(contentType(html)).isEqualTo("text/html");
    assertThat(contentAsString(html)).contains("Juhomi");
    }
    
    @Test
    public void create() {
        Result result = callAction(
          controllers.routes.ref.UserController.create()
        );
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("FullName");
    }
    @Test
    public void badRoute() {
    	  Result result = routeAndCall(fakeRequest(GET, "/xyz"));
    	  assertThat(result).isNull();
    	}
    
    @Test
    public void testInServer() {
      running(testServer(3333), new Runnable() {
          public void run() {
             assertThat(
               WS.url("http://localhost:3333/registration").get().get().getStatus()
             ).isEqualTo(OK);
             assertThat(
                     WS.url("http://localhost:3333/registration").get().get().getBody()
                   ).contains("FullName");
          }
      });
    }
    
    @Test
    public void runInBrowser() {
        running(testServer(3333), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
               browser.goTo("http://localhost:3333/registration"); 
               assertThat(browser.$("#title").getTexts().get(0)).isEqualTo("GATHI::Sign Up");
               browser.fill("fullName").with("ravikumar");
               browser.fill("company").with("juhomi");
               browser.fill("email").with("ravi@juhomi.com");
               browser.$("#create").click();
               assertThat(browser.url()).isEqualTo("http://localhost:3333/registration");
               assertThat(browser.$("#userList").getText().contains("ravikumar"));
            }
        });
    }


}
