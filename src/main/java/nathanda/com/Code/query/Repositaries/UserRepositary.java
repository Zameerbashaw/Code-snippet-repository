package nathanda.com.Code.query.Repositaries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nathanda.com.Code.query.Models.Users;

@Repository
public interface UserRepositary extends JpaRepository<Users, Long> {
  Users findByUsername(String username);
}
