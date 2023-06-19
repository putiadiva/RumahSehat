package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(String username);
    boolean checkIfMatched(String newpass, String confirmnewpass);
    boolean checkIfValidRequirement(String newpass);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel getUserByUsername(String username);
}
