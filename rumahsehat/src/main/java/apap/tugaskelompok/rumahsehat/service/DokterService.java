package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.projection.DokterIncomeProjection;
import apap.tugaskelompok.rumahsehat.projection.DokterMonthlyIncomeProjection;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    DokterModel addDokter(DokterModel dokter);
    List<DokterModel> getListDokter();
    List<DokterIncomeProjection> getTagihanEachDokterThisYear();
    List<DokterMonthlyIncomeProjection> getIncomeEachDokterThisYearPerMonth();
    Optional<DokterModel> getDokterByUuid(String uuid);
    List<DokterMonthlyIncomeProjection> getIncomeByDokterByYear(DokterModel dokter, Integer tahun);
    DokterModel getDokterById(String Id);
    String encrypt(String password);
    boolean checkIfTarifInt(String tarif);
}
