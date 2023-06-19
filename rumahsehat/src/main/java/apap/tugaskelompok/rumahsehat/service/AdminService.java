package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.AdminModel;

public interface AdminService {

    public AdminModel getAdminByUsername(String username);

    public void addAdmin(AdminModel admin);
}
