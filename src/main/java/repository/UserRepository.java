package repository;

import com.ecom.model.Registration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Configuration
public interface UserRepository extends CrudRepository<Registration, Integer> {
}
