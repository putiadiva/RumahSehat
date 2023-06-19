package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObatDb extends JpaRepository<ObatModel, String> {

    public ObatModel findObatModelByIdObat(String idObat);

}
