package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller{

public static Result create(){
		Form<User> userForm = Form.form(User.class);
		return ok(views.html.registration.render(userForm));
	}
	
public static Result all(){
		List<User> users = User.all();
		return ok(views.html.user_list_success.render(users));
	}

public static Result save(){
	Form<User> userForm = Form.form(User.class);
	Form<User> filledForm = userForm.bindFromRequest();
	if(filledForm.hasErrors()){
		return badRequest(views.html.registration.render(filledForm));
	}
	else{
		User user = filledForm.get();
		user.save();
	}
	return redirect(routes.UserController.all());

}

public static Result delete(Long id){
	User.delete(id);
	return redirect(routes.UserController.all());
}

public static Result editUser(Long id){
	User user = User.find.byId(id);
	Form<User> form = Form.form(User.class);
	return ok(views.html.edit_user.render(form.fill(user),user));
}

public static Result updateUser(Long id){
	Form<User> form = Form.form(User.class);
	Form<User> userForm = form.bindFromRequest();
	User u = userForm.get();
	User.update(u,id);
	return redirect(routes.UserController.all());
}
 }
