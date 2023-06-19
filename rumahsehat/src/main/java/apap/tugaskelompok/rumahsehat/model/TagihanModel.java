package apap.tugaskelompok.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tagihan")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value ={"appointment"}, allowSetters = true)
public class TagihanModel implements Serializable {

    @Id
    @NotNull
    private String kode;

    @NotNull
    @Column(name = "tanggal_terbuat")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate tanggalTerbuat;

    @Column(name = "tanggal_bayar")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate tanggalBayar;

    @NotNull
    @Column(name = "is_paid")
    private Boolean isPaid;

    @NotNull
    @Column(name = "jumlah_tagihan")
    private Integer jumlahTagihan;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kode_appointment", referencedColumnName = "kode")
    private AppointmentModel appointment;
}
