package com.example.servingwebcontent.security;

import com.example.servingwebcontent.model.entity.Role;
import com.example.servingwebcontent.model.entity.Status;
import com.example.servingwebcontent.model.entity.User;
import com.example.servingwebcontent.model.repository.UserRepository;
import com.example.servingwebcontent.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service("userDetailServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    private final MailSender mailSender;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException  {
        User user = userRepository.findByUsername(username);
        if (user == null){
            new UsernameNotFoundException(username + "doesn't exists");
        }

        if(user.getActivationCode() != null){
            throw new LockedException("User "+ user.getUsername() +" not yet activated. Please check your email and activate it"); // may be it's not need
        }

        return SecurityUser.fromUser(user);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getUsername()));
        user.setStatus(Status.ACTIVE);
        user.setRole(Collections.singleton(Role.USER));

        userRepository.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if(!StringUtils.hasLength(user.getEmail())){
            String message = String.format(
                    "hello, %s! \n" +
                            "welcome to app. Please, visit next link: http://localhost:8090/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if(user == null){
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRole().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRole().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged){
            user.setEmail(email);

            if (!StringUtils.hasLength(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        if (!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepository.save(user);

        if(isEmailChanged){
            sendMessage(user);
        }
    }
}
