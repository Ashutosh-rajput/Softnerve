package AshutoshRajput.Softnerve.Service.ServiceInterface;

import AshutoshRajput.Softnerve.DTO.StudentDTO;
import org.springframework.data.domain.Page;

public interface StudentServiceInterface {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO getStudentByid(Long id);
    StudentDTO upadateStudent(StudentDTO studentDTO,Long id);
    StudentDTO deleteStudent(Long id);
    Page<StudentDTO> ListOfStudent(int page, int size);

}
