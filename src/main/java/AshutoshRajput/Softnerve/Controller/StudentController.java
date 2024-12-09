package AshutoshRajput.Softnerve.Controller;

import AshutoshRajput.Softnerve.DTO.StudentDTO;
import AshutoshRajput.Softnerve.Entity.Student;
import AshutoshRajput.Softnerve.Service.ServiceImpl.MongoDbAutoSequenceService;
import AshutoshRajput.Softnerve.Service.ServiceImpl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private MongoDbAutoSequenceService sequenceService;

    @GetMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("message","Welcome this endpoint is freeeee....");
        return "welcome";
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create a new student",
            description = "This endpoint allows creating a new student without requiring authentication."
    )

    public StudentDTO createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        studentDTO.setId(sequenceService.getsequencenumber(Student.SEQUENCE_NAME));

        return studentService.createStudent(studentDTO);
    }

    @GetMapping("/get/{id}")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(
            summary = "Create a new student",
            description = "This endpoint allows creating a new student without requiring authentication."
    )
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentByid(id));
    }

    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody @Valid StudentDTO studentDTO, @PathVariable Long id) {
        return ResponseEntity.ok(studentService.upadateStudent(studentDTO, id));
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with ID: " + id);
    }

    @GetMapping("/students/{page}/{size}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<Page<StudentDTO>> getListOfStudents(@PathVariable int page, @PathVariable int size) {
        return ResponseEntity.ok(studentService.ListOfStudent(page, size));
    }
}
