package work.szczepanskimichal.project13snippetapp.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    // TODO create full CRUD for roles in repository
}
