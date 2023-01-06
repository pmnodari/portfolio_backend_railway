
package com.portfolio.pnodari.Security.Repository;
import com.portfolio.pnodari.Security.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
    //Variables verificacion
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
    
}
