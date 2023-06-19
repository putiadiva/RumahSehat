package apap.tugaskelompok.rumahsehat.security;

import apap.tugaskelompok.rumahsehat.model.UserModel;
import apap.tugaskelompok.rumahsehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDb userDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userDb.findUserModelByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
//        if ("apap".equals(username)) {
//            return new User("apap", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }
}
