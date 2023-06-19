package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.PasienModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {
    Optional<PasienModel> findByUsername(String username);

    Optional<PasienModel> findByUuid(String uuid);

    Optional<PasienModel> findPasienModelByUsername(String username);
}
