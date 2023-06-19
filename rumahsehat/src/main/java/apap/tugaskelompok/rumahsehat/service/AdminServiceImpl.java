package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.AdminModel;
import apap.tugaskelompok.rumahsehat.repository.AdminDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDb adminDb;

    @Override
    public AdminModel getAdminByUsername(String username) {
        return adminDb.findAdminModelByUsername(username);
    }

    @Override
    public void addAdmin(AdminModel admin) {
        admin.setPassword(encrypt(admin.getPassword()));
        adminDb.save(admin);
    }

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
