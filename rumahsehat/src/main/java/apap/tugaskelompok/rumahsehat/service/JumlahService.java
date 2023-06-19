package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.JumlahModel;
import apap.tugaskelompok.rumahsehat.repository.JumlahDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JumlahService {
    @Autowired
    JumlahDb jumlahDb;

    public List<JumlahModel> getListJumlah(){
        return jumlahDb.findAll();
    }
    public void addJumlah(JumlahModel jumlah) {

        jumlahDb.save(jumlah);
    }
}
