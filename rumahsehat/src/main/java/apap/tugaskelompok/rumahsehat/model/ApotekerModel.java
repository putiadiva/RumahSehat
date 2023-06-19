package apap.tugaskelompok.rumahsehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apoteker")
@PrimaryKeyJoinColumn(name="apotekerUuid")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApotekerModel extends UserModel {

    @OneToMany(mappedBy = "apoteker", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResepModel> listResep;

}
