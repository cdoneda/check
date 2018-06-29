package br.edu.ifrs.alvorada.check.service;

import br.edu.ifrs.alvorada.check.domain.User;
import br.edu.ifrs.alvorada.check.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User save(User user) {
		User fetchedUser = this.getOne(user);
		if (fetchedUser == null)
			return null;
		fetchedUser.setName(user.getName());
		fetchedUser.setEmail(user.getEmail());
		return userRepository.save(fetchedUser);
	}

	public User getOne(User user) {
		if (user == null || user.getId() == null)
			return null;
		Optional<User> optionalUser = userRepository.findById(user.getId());
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}
//
//	public User getOneByUsername(String username) {
//		if (username == null)
//			return null;
//		Optional<User> optionalUser = userRepository.findByUsername(username);
//		return optionalUser.isPresent() ? optionalUser.get() : null;
//	}

}
