
package com.portfolio.pnodari.Service;
import com.portfolio.pnodari.Entity.Educacion;
import com.portfolio.pnodari.Repository.IEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducacionService {
    
    @Autowired
    IEducacionRepository iEducacionRepository;
    
    //Obtener lista    
    public List<Educacion> list(){
        return iEducacionRepository.findAll();
    }
    
    //Obtener un registro Educacion
    public Optional<Educacion> getOne(int id){
        return iEducacionRepository.findById(id);
    }
    
    //Obtener por nombre
    public Optional<Educacion> getByNombreEdu(String nombreEdu){
        return iEducacionRepository.findByNombreEdu(nombreEdu);
    }
    
    //Guardar registro Educacion
    public void save(Educacion edu){
        iEducacionRepository.save(edu);
    }
    
    //Eliminar por ID
    public void delete(int id){
        iEducacionRepository.deleteById(id);
    }
    
    //Existe Educacion por ID
    public boolean existsEducacionById(int id){
        return iEducacionRepository.existsById(id);
    }
    
    //Existe Educacion por nombre
    public boolean existsEducacionByNombreEdu(String nombreEdu){
        return iEducacionRepository.existsByNombreEdu(nombreEdu);
    }
    
    
    
}
