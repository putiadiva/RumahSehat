//package apap.tugaskelompok.rumahsehat.service;
//
//import apap.tugaskelompok.rumahsehat.model.UserModel;
//import apap.tugaskelompok.rumahsehat.repository.UserDb;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private UserDb userDb;
//
//    //MASIH BLM
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserModel user = userDb.findByUsername(username);
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
//        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
//    }
//}