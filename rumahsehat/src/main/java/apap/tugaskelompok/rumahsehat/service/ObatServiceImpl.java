package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.ObatModel;
import apap.tugaskelompok.rumahsehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ObatServiceImpl implements ObatService {

    @Autowired
    ObatDb obatDb;

    @Override
    public List<ObatModel> getAllObat() {
        return obatDb.findAll();
    }

    @Override
    public ObatModel getObatById(String idObat) {
        return obatDb.findObatModelByIdObat(idObat);
    }

    @Override
    public void updateStokObat(ObatModel obat, Integer stok) {
        obat.setStok(stok);
        obatDb.save(obat);
    }
}
