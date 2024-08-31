package AshutoshRajput.Softnerve.Repository;

import AshutoshRajput.Softnerve.Entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student,Long> {
    Optional<Student> findByemail(String email);

}
