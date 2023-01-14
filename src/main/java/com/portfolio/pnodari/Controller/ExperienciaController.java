
package com.portfolio.pnodari.Controller;

import com.portfolio.pnodari.Dto.DtoExperiencia;
import com.portfolio.pnodari.Entity.Experiencia;
import com.portfolio.pnodari.Security.Controller.Mensaje;
import com.portfolio.pnodari.Service.impExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiencia")
@CrossOrigin(origins = {"https://portfoliofrontend-736c5.web.app/","http://localhost:4200/"})
public class ExperienciaController {
    
    @Autowired
    impExperienciaService iExperienciaService;
    
    //Traemos la lista con todas las experiencias
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        
        //Variable con una lista
        List<Experiencia> list=iExperienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    //Crear nueva experiencia
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExpe){
        //Realizamos la validación (dependencia commons): Nombre 
        if (StringUtils.isBlank(dtoExpe.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"),
                                                  HttpStatus.BAD_REQUEST);
        }
        //Validación experiencia no repetidas
        if (iExperienciaService.existsExperienciaByNombreExp(dtoExpe.getNombreExp())) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"),
                                                  HttpStatus.BAD_REQUEST);
        }
        
        //Pasan las dos validaciones        
        Experiencia experiencia=new Experiencia(dtoExpe.getNombreExp(), 
                                                dtoExpe.getDescripcionExp(),
                                                dtoExpe.getFecha_fin(),
                                                dtoExpe.getFecha_inicio());
        
        //Guardamos objeto
        iExperienciaService.save(experiencia);
        
        //Mandamos mensaje
        return new ResponseEntity(new Mensaje("Experiencia Agregada"),
                                              HttpStatus.OK);
    }
    //Buscar experiencia
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!iExperienciaService.existsExperienciaById(id))
            return new ResponseEntity(new Mensaje("No existe el registro"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = iExperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    //Actualizar Experiencia
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, 
                                    @RequestBody DtoExperiencia dtoExpe){
        
        //Validación si existe el ID
        if (!iExperienciaService.existsExperienciaById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), 
                                                  HttpStatus.NOT_FOUND);
        }
        
        //Validamos nombre de experiencias
        if (iExperienciaService.existsExperienciaByNombreExp(dtoExpe.getNombreExp()) &&
                iExperienciaService.getByNombreExp(dtoExpe.getNombreExp()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        
        //Validamos que no esten en blanco el campo
        if (StringUtils.isBlank(dtoExpe.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre no puede estar en blanco"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        
        Experiencia experiencia=iExperienciaService.getOne(id).get();
        
        experiencia.setNombreExp(dtoExpe.getNombreExp());
        experiencia.setDescripcionExp(dtoExpe.getDescripcionExp());
        experiencia.setFecha_fin(dtoExpe.getFecha_fin());
        experiencia.setFecha_inicio(dtoExpe.getFecha_inicio());
    
        iExperienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia Actualizada"),
                                              HttpStatus.OK);
    }
    
    //Borrar Experiencia
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id ){
        
        //Validación si existe el ID
        if (!iExperienciaService.existsExperienciaById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), 
                                                  HttpStatus.NOT_FOUND);
        } 
        
        iExperienciaService.delete(id);
        
        return new ResponseEntity(new Mensaje("Experiencia eliminada"),
                                              HttpStatus.OK);
    }
    
    
}
