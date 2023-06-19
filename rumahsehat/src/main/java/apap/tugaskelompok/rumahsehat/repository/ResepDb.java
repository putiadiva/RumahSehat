package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResepDb extends JpaRepository<ResepModel, Long> {

    Optional<ResepModel> findById(Long idResep);
}
