package apap.tugaskelompok.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "obat")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ObatModel {

    @Id
    @NotNull
    @Column(name = "id_obat")
    private String idObat;

    @NotNull
    @Column(name = "nama_obat")
    private String namaObat;

    @NotNull
    @Column(name = "tanggal_terbuat", columnDefinition = "integer default 100")
    private Integer stok;

    @NotNull
    @Column(name = "harga")
    private Integer harga;
}
