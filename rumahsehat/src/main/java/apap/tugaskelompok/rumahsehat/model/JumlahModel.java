package apap.tugaskelompok.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jumlah")

public class JumlahModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, name = "kuantitas")
    private Integer kuantitas;

    @ManyToOne
    @JoinColumn(name = "resep_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    ResepModel resep;

    @ManyToOne
    @JoinColumn(name = "obat_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    ObatModel obat;

}
