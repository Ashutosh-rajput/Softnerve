package AshutoshRajput.Softnerve.Service.ServiceImpl;

import AshutoshRajput.Softnerve.DTO.StudentDTO;
import AshutoshRajput.Softnerve.Entity.Student;
import AshutoshRajput.Softnerve.ExceptionHandling.ResourceAlreadyExistsException;
import AshutoshRajput.Softnerve.ExceptionHandling.ResourceNotFoundException;
import AshutoshRajput.Softnerve.Mapper.StudentMapper;
import AshutoshRajput.Softnerve.Repository.StudentRepository;
import AshutoshRajput.Softnerve.Service.ServiceInterface.StudentServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            logger.error("Student creation failed: Student already exists with email: {}", studentDTO.getEmail());
            throw new ResourceAlreadyExistsException("Student already exists with this email.");
        }
        Student student = studentMapper.StudentDTOtoStudent(studentDTO);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        Student savedStudent = studentRepository.save(student);
        logger.info("Student created successfully with ID: {}", savedStudent.getId());
        return studentMapper.StudenttoStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO getStudentByid(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            logger.error("Student not found with ID: {}", id);
            return new ResourceNotFoundException("Student not found with this ID.");
        });
        logger.info("Student found with ID: {}", id);
        return studentMapper.StudenttoStudentDTO(student);
    }

    @Override
    public StudentDTO upadateStudent(StudentDTO studentDTO, Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            logger.error("Student update failed: Student not found with ID: {}", id);
            return new ResourceNotFoundException("Student not found with this ID.");
        });

        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        student.setPincode(studentDTO.getPincode());


        Student updatedStudent = studentRepository.save(student);
        logger.info("Student updated successfully with ID: {}", updatedStudent.getId());
        return studentMapper.StudenttoStudentDTO(updatedStudent);
    }

    @Override
    public StudentDTO deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            logger.error("Student deletion failed: Student not found with ID: {}", id);
            return new ResourceNotFoundException("Student not found with this ID.");
        });
        studentRepository.delete(student);
        logger.info("Student deleted successfully with ID: {}", id);
        return studentMapper.StudenttoStudentDTO(student);
    }

    @Override
    public Page<StudentDTO> ListOfStudent(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = studentRepository.findAll(pageable);
        if (students.isEmpty()) {
            logger.warn("No students found for the given criteria.");
        } else {
            logger.info("Number of students found: {}", students.getTotalElements());
        }
        return students.map(student -> studentMapper.StudenttoStudentDTO(student));
    }
}
