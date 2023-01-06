
package com.portfolio.pnodari.Repository;
import com.portfolio.pnodari.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaRepository extends JpaRepository<Experiencia, Integer>{
    
    //Metodos adicionales
    public Optional<Experiencia> findByNombreExp(String nombreExp);
    
    public boolean existsByNombreExp(String nombreExp);
    
}
