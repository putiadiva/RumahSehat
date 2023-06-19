package apap.tugaskelompok.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "dokter")
@PrimaryKeyJoinColumn(name="uuid")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DokterModel extends UserModel {

    @NotNull
    @Column(name = "tarif")
    private Integer tarif;

//    @OneToMany(mappedBy = "dokter", fetch = FetchType.EAGER)
//    private List<AppointmentModel> listAppointment;
}
