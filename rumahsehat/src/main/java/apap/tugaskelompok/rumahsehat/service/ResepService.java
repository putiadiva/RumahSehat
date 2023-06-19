package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.ResepModel;
import apap.tugaskelompok.rumahsehat.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResepService {
    @Autowired
    ResepDb resepDb;

    public void addResep(ResepModel resep) {

        resepDb.save(resep);
    }

    public List<ResepModel> getListResep(){

        return resepDb.findAll();
    }


    public ResepModel getResepById(Long idResep) {
        Optional<ResepModel> resep = resepDb.findById(idResep);
        return resep.orElse(null);
    }

}
