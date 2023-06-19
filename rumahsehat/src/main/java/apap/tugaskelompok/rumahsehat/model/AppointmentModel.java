package apap.tugaskelompok.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value ={"dokter", "pasien", "tagihan"}, allowSetters = true)
public class AppointmentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
    @GenericGenerator(name = "appointment_seq",
            strategy = "apap.tugaskelompok.rumahsehat.model.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "APT-")
            }
    )
    private String kode;

    @NotNull
    @Column(name = "waktu_awal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuAwal;

    @NotNull
    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuid_dokter", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DokterModel dokter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuid_pasien", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PasienModel pasien;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kode_tagihan", referencedColumnName = "kode")
    private TagihanModel tagihan;

    @OneToOne(mappedBy = "appointment")
    private ResepModel resep;

    public LocalDateTime getWaktuAkhir(LocalDateTime waktuMulai) {
        LocalDateTime waktuAkhir = waktuMulai.plusHours(1);
        return waktuAkhir;
    }
    // 1 appointment bisa punya 0 atau 1 resep.




}

