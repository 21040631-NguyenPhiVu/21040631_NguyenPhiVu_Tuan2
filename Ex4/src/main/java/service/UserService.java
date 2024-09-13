package service;

import entity.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserService {
	public User getUser() {
		return new User("Vu", 20, "TP.HCM");
	}
}
