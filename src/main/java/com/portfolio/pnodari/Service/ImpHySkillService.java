
package com.portfolio.pnodari.Service;
import com.portfolio.pnodari.Entity.HyS;
import com.portfolio.pnodari.Repository.IHySRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpHySkillService {
    
    @Autowired
    IHySRepository iHySRepository;
    
    //Lista completa
    public List<HyS> list(){
        return iHySRepository.findAll();
    }
    //Obtener habilidad por ID
    public Optional<HyS> getOne(int id){
        return iHySRepository.findById(id);
    }  
    //Obtener habilidad por nombre
    public Optional<HyS> getByNombreHS(String nombreHS){
        return iHySRepository.findByNombreHS(nombreHS);
    } 
    //Crear habilidad
    public void save(HyS skill){
         iHySRepository.save(skill);
    }
    //Eliminar
    public void delete(int id){
        iHySRepository.deleteById(id);
    }
    //Existe habilidad por nombre
    public boolean existsHySByNombre(String nombreHS){
       return iHySRepository.existsByNombreHS(nombreHS);
    }
    //Existe habilidad por nombre
    public boolean existsHySById(int id){
       return iHySRepository.existsById(id);
    }
    
    
    
}
