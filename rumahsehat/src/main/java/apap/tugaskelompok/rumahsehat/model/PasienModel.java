package apap.tugaskelompok.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "pasien")
@PrimaryKeyJoinColumn(name="uuid")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "password"})
public class PasienModel extends UserModel {

    @NotNull
    @Column(name = "saldo")
    private Integer saldo;

    @NotNull
    @Column(name = "umur")
    private Integer umur;
}
