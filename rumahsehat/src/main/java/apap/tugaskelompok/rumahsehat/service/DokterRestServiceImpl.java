package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DokterRestServiceImpl implements DokterRestService {
    @Autowired
    DokterDb dokterDb;
    @Override
    public DokterModel addDokter(DokterModel dokter) {
        return dokterDb.save(dokter);
    }


}
