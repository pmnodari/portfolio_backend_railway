
package com.portfolio.pnodari.Repository;

import com.portfolio.pnodari.Entity.HyS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHySRepository extends JpaRepository<HyS, Integer>{
    
    public Optional<HyS> findByNombreHS(String nombreHS);
    
    public boolean existsByNombreHS(String nombreHS);
    
}
