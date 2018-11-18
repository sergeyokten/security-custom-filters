package oktenweb.securityfilters.dao;

import oktenweb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDAO extends JpaRepository<User, Integer>, JpaSpecificationExecutor {
}
