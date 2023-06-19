package apap.tugaskelompok.rumahsehat.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PasienDTO implements Serializable {
    private String nama;

    private String username;

    private String password;

    private String email;

    private Boolean isSso;

    private Integer saldo;

    private Integer umur;
}
