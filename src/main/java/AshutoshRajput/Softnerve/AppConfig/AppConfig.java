package AshutoshRajput.Softnerve.AppConfig;

import AshutoshRajput.Softnerve.Mapper.StudentMapper;
import AshutoshRajput.Softnerve.Service.ServiceImpl.StudentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public StudentMapper studentMapper(){return new StudentMapper();}

    @Bean
    public StudentServiceImpl studentService(){return new StudentServiceImpl();}
}
