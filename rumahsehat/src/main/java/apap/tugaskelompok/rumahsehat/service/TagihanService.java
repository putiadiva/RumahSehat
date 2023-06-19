package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.ObatModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.repository.AppointmentDb;
import apap.tugaskelompok.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface TagihanService {

    public TagihanModel getTagihanById(String kode);

}
