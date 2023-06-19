package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.ApotekerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, Long> {

    Optional<ApotekerModel> findByUuid(String idAptk);
    Optional<ApotekerModel> findByUsername(String username);

//    @Query("SELECT c FROM ApotekerModel c")
//    List<ApotekerModel> findAll();

}
