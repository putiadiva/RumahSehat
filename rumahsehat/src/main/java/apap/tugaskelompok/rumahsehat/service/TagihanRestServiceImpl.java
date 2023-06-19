package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService {

    @Autowired
    private TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> retrieveListTagihan(String username){
        List<TagihanModel> listAllTagihan = tagihanDb.findAll();
        List<TagihanModel> listTagihanPasien = new ArrayList<>();

        for(TagihanModel tagihan : listAllTagihan){
            if(tagihan.getAppointment().getPasien().getUsername().equals(username)){
                listTagihanPasien.add(tagihan);
            }
        }

        return listTagihanPasien;
    }

    @Override
    public List<TagihanModel> retrieveAllListTagihan(){
        return tagihanDb.findAll();
    }

    @Override
    public TagihanModel getTagihanByKode(String kode){
        Optional<TagihanModel> tagihan = tagihanDb.findTagihanModelByKode(kode);
        if(tagihan.isPresent()) {
            return tagihan.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public TagihanModel update(TagihanModel tagihan){
        return tagihanDb.save(tagihan);
    }

}
