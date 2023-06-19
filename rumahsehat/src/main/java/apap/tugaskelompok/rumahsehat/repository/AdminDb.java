package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDb extends JpaRepository<AdminModel, String> {

    public AdminModel findAdminModelByUsername(String username);
}
