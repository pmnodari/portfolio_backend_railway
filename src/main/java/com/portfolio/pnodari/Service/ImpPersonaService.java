
package com.portfolio.pnodari.Service;

import com.portfolio.pnodari.Entity.Persona;
import com.portfolio.pnodari.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonaService{
    //Instanciamos e injectamos las dependencia del Repository
    @Autowired
    IPersonaRepository iPersonaRepository;
    
    //Obtener Lista completa
    public List<Persona> list(){        
        return iPersonaRepository.findAll();
    }
    
    //Obtener por Id
    public Optional<Persona> getOne(int id){
        return iPersonaRepository.findById(id);
    }
    
    //Obtener por nombre
    public Optional<Persona> getByNombrePerso(String nombre){
        return iPersonaRepository.findByNombre(nombre);
    }
    
    //Guardar el objeto o persona
    public void save(Persona persona){
        iPersonaRepository.save(persona);
    }
    
    //Eliminar por Id
    public void delete(int id){
        iPersonaRepository.deleteById(id);
    }
    
    //Existe Persona por Id
    public boolean existsPersonaById(int id){
        return iPersonaRepository.existsById(id);
    }
    
    //Existe Persona por Nombre
    public boolean existsByNombrePerso(String nombre){
        return iPersonaRepository.existsByNombre(nombre);
    }
   
}