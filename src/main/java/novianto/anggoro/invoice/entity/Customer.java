package novianto.anggoro.invoice.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE customer SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record = 'ACTIVE'")
public class Customer extends BaseEntity{

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String code;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String name;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Email
    private String email;
    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String mobilePhone;

}
