
package com.portfolio.pnodari.Security.Repository;
import com.portfolio.pnodari.Security.Entity.Rol;
import com.portfolio.pnodari.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author pmnod
 */
@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    
}