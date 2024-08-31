package AshutoshRajput.Softnerve.Service.ServiceImpl;

import AshutoshRajput.Softnerve.Entity.Student;
import AshutoshRajput.Softnerve.ExceptionHandling.ResourceNotFoundException;
import AshutoshRajput.Softnerve.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustonUserDetailService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(
                student.getEmail(),
                student.getPassword(),
                new ArrayList<>());

    }
}
