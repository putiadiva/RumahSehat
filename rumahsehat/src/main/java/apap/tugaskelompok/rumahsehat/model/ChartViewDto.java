package apap.tugaskelompok.rumahsehat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ChartViewDto {

    private DokterModel dokter1;

    private DokterModel dokter2;

    private DokterModel dokter3;

    private DokterModel dokter4;

    private DokterModel dokter5;

    private Boolean isBulananNotTahunan;
}
