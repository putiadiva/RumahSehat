package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.ApotekerModel;
import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.ObatModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.repository.DokterDb;
import apap.tugaskelompok.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagihanServiceImpl implements TagihanService{

    @Autowired
    TagihanDb tagihanDb;

    public TagihanModel getTagihanById(String kode){
        Optional<TagihanModel> tagihan = tagihanDb.findByKode(kode);
        if (tagihan.isPresent()){
            return tagihan.get();
        }else return null;
    }

}
