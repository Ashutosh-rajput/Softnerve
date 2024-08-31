package AshutoshRajput.Softnerve.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "students")
public class Student {
    @Transient
    public static final String SEQUENCE_NAME="studentSequence";
    @Id
    private Long id;
    private String name;
    private String password;
    private String email;
    private String address;
    private String pincode;
}
