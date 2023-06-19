package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.ObatModel;

import java.util.List;

public interface ObatService {

    public List<ObatModel> getAllObat();

    public ObatModel getObatById(String idObat);

    public void updateStokObat(ObatModel obat, Integer stok);
}
