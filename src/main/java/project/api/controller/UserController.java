package project.api.controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.api.entity.User;
import project.api.service.IUserService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})

@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/list")
	public Iterable<User> getList(){
		return userService.getAllList();
	}
	
	@GetMapping("/userstype/{id}")
	public Iterable<User> getByUserType(@PathVariable Integer id){
		return userService.getByUserType(id);
	}
	
	@GetMapping("/{id}")
	public User getById(@PathVariable Long id) {
		return userService.getById(id);
	}
	
	@PostMapping(path="/add", consumes = {"application/json"})
	public @ResponseBody String Insert (@RequestBody Map<String, Object> json) {
		try {
			User user = new User();
			user.setName(json.get("name").toString());
			user.setEmail(json.get("email").toString());
			user.setUserName(json.get("username").toString());
			user.setPassword(json.get("password").toString());
			user.setUserType(Integer.parseInt(json.get("usertype").toString()));
			userService.Insert(user);
			return "Saved";	
		}
		catch(Exception ex){
			return "Error: "+ex;	
		}			
	}
	@PutMapping(path="/edit/{id}", consumes = {"application/json"})
	public @ResponseBody String Update (@RequestBody Map<String, Object> json, @PathVariable Long id) {
		try {
			User user = new User();
			user.setName(json.get("name").toString());
			user.setEmail(json.get("email").toString());
			user.setUserName(json.get("username").toString());
			user.setPassword(json.get("password").toString());
			user.setUserType(Integer.parseInt(json.get("usertype").toString()));
			userService.Update(user,id);
			return "Saved";	
		}
		catch(Exception ex){
			return "Error: "+ex;	
		}			
	}
	@PostMapping(path="/login", produces = {"application/json"}, consumes = {"application/json"})
	public User Login (@RequestBody Map<String, Object> json) {		

		return userService.Login(json.get("username").toString(), json.get("password").toString());			
	}
	
	
}
