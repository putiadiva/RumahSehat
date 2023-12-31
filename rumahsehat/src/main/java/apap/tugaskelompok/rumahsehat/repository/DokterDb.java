package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, String> {

    Optional<DokterModel> findDokterModelByUuid(String uuid);
}
