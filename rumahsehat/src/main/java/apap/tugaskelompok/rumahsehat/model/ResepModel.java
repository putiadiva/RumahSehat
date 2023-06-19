package apap.tugaskelompok.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
// @JsonIgnoreProperties(value={"resep"}, allowSetters = true)
@Table(name= "resep")

public class ResepModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "isdone", nullable = false)
    private Boolean isDone;

    @NotNull
    @Column(nullable = false, name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "resep_appt",
            joinColumns =
                    { @JoinColumn(name = "resep_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "appointment_kode", referencedColumnName = "kode") })
    private AppointmentModel appointment;


    @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <JumlahModel> jumlah;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "confirmer_uuid", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ApotekerModel apoteker;


}
