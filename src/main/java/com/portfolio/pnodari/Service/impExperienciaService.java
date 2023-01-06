
package com.portfolio.pnodari.Service;

import com.portfolio.pnodari.Entity.Experiencia;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.pnodari.Repository.IExperienciaRepository;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class impExperienciaService {
    
    @Autowired
    IExperienciaRepository iExperienciaRepository;
    
    //Obtener Lista completa
    public List<Experiencia> list(){        
        return iExperienciaRepository.findAll();
    }
    
    //Obtener por Id
    public Optional<Experiencia> getOne(int id){
        return iExperienciaRepository.findById(id);
    }
    
    //Obtener por nombre
    public Optional<Experiencia> getByNombreExp(String nombreExp){
        return iExperienciaRepository.findByNombreExp(nombreExp);
    }
    
    //Guardar el objeto o ecperiencia
    public void save(Experiencia expe){
        iExperienciaRepository.save(expe);
    }
    
    //Eliminar por Id
    public void delete(int id){
        iExperienciaRepository.deleteById(id);
    }
    
    //Existe experiencia por Id
    public boolean existsExperienciaById(int id){
        return iExperienciaRepository.existsById(id);
    }
    
    //Existe experiencia por Nombre
    public boolean existsExperienciaByNombreExp(String expe){
        return iExperienciaRepository.existsByNombreExp(expe);
    }
    
}
