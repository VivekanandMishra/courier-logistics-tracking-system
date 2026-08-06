
package jsp.courier.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import jsp.courier.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

	static Users saveAll(Users user) {
		// TODO Auto-generated method stub
		return null;
	}
}