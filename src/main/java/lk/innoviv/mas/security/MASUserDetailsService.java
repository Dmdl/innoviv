package lk.innoviv.mas.security;

import lk.innoviv.mas.exception.UnknownIdException;
import lk.innoviv.mas.persistence.model.Admin;
import lk.innoviv.mas.persistence.model.BEUser;
import lk.innoviv.mas.persistence.model.User;
import lk.innoviv.mas.persistence.service.UserRepository;
import lk.innoviv.mas.security.model.MASUserDetails;
import lk.innoviv.mas.security.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MASUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, noRollbackFor = UsernameNotFoundException.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.getUser(username);
            return getRoleBasedDetails(user);
        } catch (UnknownIdException e) {
            throw new UsernameNotFoundException(String.format("Could not find user with username: %s", username));
        }
    }

    private UserDetails getRoleBasedDetails(User user) {
//        UserDetails details;
//        if (Security.isBEUser(user)) {
//            //BEUser beUser = patientRepository.getPatient(user);
//            //details = new MASUserDetails(beUser);
//            details = null;
//        } else if (Security.isAdmin(user)) {
//            Admin admin = new Admin(user);
//            details = new MASUserDetails(admin);
//        } else {
//            details = new MASUserDetails(user);
//        }
//        return details;
        return new MASUserDetails(user);
    }
}
