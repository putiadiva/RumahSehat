package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.ApotekerModel;
import apap.tugaskelompok.rumahsehat.model.UserModel;

import java.util.List;

public interface ApotekerService {

    ApotekerModel addApoteker(ApotekerModel apoteker);
    List<ApotekerModel> getListApoteker();
    ApotekerModel getApotekerByUsername(String idAptk);
    String encrypt(String password);
//    ApotekerModel getApotekerById(String idAptk);
}
