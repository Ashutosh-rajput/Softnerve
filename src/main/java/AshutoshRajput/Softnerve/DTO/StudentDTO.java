package AshutoshRajput.Softnerve.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private Long id;
    @NotBlank(message = "Please enter Student name.")
    private String name;
    @NotBlank(message = "Please enter Password.")
    private String password;
    @NotBlank(message = "Please enter email.")
    @Email
    private String email;
    private String address;
    private String pincode;

}
