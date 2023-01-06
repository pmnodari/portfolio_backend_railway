package com.portfolio.pnodari.Security.Service;

import com.portfolio.pnodari.Security.Entity.Rol;
import com.portfolio.pnodari.Security.Enums.RolNombre;
import com.portfolio.pnodari.Security.Repository.IRolRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class RolService {

    @Autowired
    IRolRepository iRolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return iRolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol) {
        iRolRepository.save(rol);
    }
}
/*
*Transactional-> Si una operacion falla la BBDD no se altere y quede es un estado
*anterior.
*/
