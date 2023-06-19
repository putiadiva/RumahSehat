package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.UserModel;
import apap.tugaskelompok.rumahsehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDb userDb;

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public UserModel addUser(String username) {
        UserModel user = getUserByUsername(username);
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public boolean checkIfMatched(String newpass, String confirmnewpass) {
        return newpass.equals(confirmnewpass);
    }

    @Override
    public boolean checkIfValidRequirement(String newpass) {
        return Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~()+#?!@$%^&*-_]).{8,}$",
                newpass);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

}