package project.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.entity.User;
import project.api.repository.IUserRepository;
import project.api.service.IUserService;


@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public Iterable<User> getAllList() {
		return userRepository.findAll();
	}

	@Override
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public User Insert(User user) {		
		return userRepository.save(user);
	}
	
	@Override
	public User Update(User user, Long id) {		
		user.setId(id);
		return userRepository.save(user);
	}

	@Override
	public User Login(String username, String password) {
		List<User> users= (List<User>) userRepository.findAll();		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getUserName().equals(username) && users.get(i).getPassword().equals(password)) {				
				return users.get(i);
			}			
		}
		return new User();
	}
	
	@Override
	public List<User> getByUserType(Integer id){
		List<User> UsersType = new ArrayList<User>();
		List<User> AllUsers= (List<User>) userRepository.findAll();
		for(User user: AllUsers) {
			if(user.getUserType() == id) {
				UsersType.add(user);
			}
		}
		return UsersType;
	}

}
