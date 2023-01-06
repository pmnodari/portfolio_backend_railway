package com.portfolio.pnodari.Service;

import com.portfolio.pnodari.Entity.Proyecto;
import com.portfolio.pnodari.Repository.IProyectoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpProyectoService {

    @Autowired
    IProyectoRepository iProyectoRepository;

    //Obtener lista
    public List<Proyecto> lista(){
        return iProyectoRepository.findAll();
    }
    //Obtener un registro Proyecto
    public Optional<Proyecto> getOne(int id){
        return iProyectoRepository.findById(id);
    }
    //Obtener por nombre
    public Optional<Proyecto> getByNombrePro(String nombreP){
        return iProyectoRepository.findByNombreP(nombreP);
    }
    //Guardar registro Proyecto
    public void save(Proyecto proyecto){
        iProyectoRepository.save(proyecto);
    }
    //Eliminar por ID
    public void delete(int id){
        iProyectoRepository.deleteById(id);
    }
    //Existe Proyecto por ID
    public boolean existsByIdPro(int id){
        return iProyectoRepository.existsById(id);
    }
    //Existe Proyecto por nombre
    public boolean existsByNombrePro(String nombreP){
        return iProyectoRepository.existsByNombreP(nombreP);
    }
}
