package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;

import java.util.List;

public interface TagihanRestService {
    List<TagihanModel> retrieveListTagihan(String username);
    List<TagihanModel> retrieveAllListTagihan();
    TagihanModel getTagihanByKode(String kode);
    TagihanModel update(TagihanModel tagihan);
}
