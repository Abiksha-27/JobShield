package com.jobshield.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.jobshield.security.JwtUtil;
import com.jobshield.dto.LoginRequest;
import com.jobshield.dto.RegisterRequest;
import com.jobshield.entity.User;
import com.jobshield.repository.UserRepository;

@Service
public class AuthService {
	  @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private JwtUtil jwtUtil;
	  

	    public String registerUser(RegisterRequest request) {

	    	User user = new User();

	        user.setName(request.getName());
	        user.setEmail(request.getEmail());
	        

	        // encode password
	        user.setPassword(
	                passwordEncoder.encode(request.getPassword()));
	        user.setRole("USER");

	        userRepository.save(user);

	        return "User Registered Successfully";
	    }
	    public String loginUser(LoginRequest request) {

	        Optional<User> optionalUser =
	                userRepository.findByEmail(
	                        request.getEmail());

	        if (optionalUser.isEmpty()) {
	            return "User Not Found";
	        }

	        User user = optionalUser.get();

	        boolean passwordMatch =
	                passwordEncoder.matches(
	                        request.getPassword(),
	                        user.getPassword());

	        if (!passwordMatch) {
	            return "Invalid Password";
	        }

	        return jwtUtil.generateToken(
	                user.getEmail(),
	                user.getRole()
	        );
	    }
}
 