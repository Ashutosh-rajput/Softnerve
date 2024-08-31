package AshutoshRajput.Softnerve.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Db_Sequence")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DbSequence {
    @Id
    private String id;
    private Long seq;
}
