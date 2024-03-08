package my.system.management.domain.address.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.system.management.domain.address.dto.DataAddress;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String district;
    private String number;
    private String complement;

    public Address(DataAddress address){
        this.street = address.street();
        this.district = address.district();
        this.number = address.number();
        this.complement = address.complement();
    }

    public void updateAddress(DataAddress data) {
        if(data.street() != null){
            this.street = data.street();
        }

        if(data.district() != null){
            this.district = data.district();
        }

        if(data.number() != null){
            this.number = data.number();
        }

        if(data.complement() != null){
            this.complement = data.complement();
        }
    }
}
