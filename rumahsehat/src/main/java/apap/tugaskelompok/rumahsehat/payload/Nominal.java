package apap.tugaskelompok.rumahsehat.payload;

import java.io.Serializable;

public class Nominal implements Serializable {
    private String nominal;

    public Nominal() {};

    public Nominal (String nominal) {
        this.nominal = nominal;
    }

    public String getnominal() {
        return this.nominal;
    }
    
}