
package com.portfolio.pnodari.Controller;

import com.portfolio.pnodari.Dto.DtoEducacion;
import com.portfolio.pnodari.Entity.Educacion;
import com.portfolio.pnodari.Security.Controller.Mensaje;
import com.portfolio.pnodari.Service.ImpEducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = {"https://portfoliofrontend-736c5.web.app/","http://localhost:4200/"})
public class EducacionController {
    
    @Autowired
    ImpEducacionService impEducacionService;
    
    //Traemos la lista completa   
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        
        List<Educacion> lista=impEducacionService.list();
        
        return new ResponseEntity(lista, HttpStatus.OK );
    }
    
    //Se crea nuevo registro educacion    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEdu){
        
        //Dos validaciones: no se permiten registros en blanco ni repetidos
        
        if (StringUtils.isBlank(dtoEdu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), 
                    HttpStatus.BAD_REQUEST);
        }
        
        if (impEducacionService.existsEducacionByNombreEdu(dtoEdu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El registro ya existe con el mismo nombre"), 
                    HttpStatus.BAD_REQUEST);
        }
        
        //Pasan las validaciones
        Educacion educacion=new Educacion(dtoEdu.getNombreEdu(), 
                dtoEdu.getDescripcionEdu());
        
        impEducacionService.save(educacion);
        
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    //Buscar Experiencia
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getEducacionById(@PathVariable ("id") int id){
        if (!impEducacionService.existsEducacionById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), 
                    HttpStatus.NOT_FOUND);
        }
        //Si el registro existe
        Educacion educacion=impEducacionService.getOne(id).get();
                
        return new ResponseEntity(educacion,HttpStatus.OK);
    }
    
    //Actualizar Educacion
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") int id,
                                    @RequestBody DtoEducacion dtoEdu){
        
        //Validamos que el registro exista por id y por nombre; y que los campos 
        //no este en blanco
        if (!impEducacionService.existsEducacionById(id)) {
            return new ResponseEntity(new Mensaje("El id del registro no existe"), 
                    HttpStatus.NOT_FOUND);
        }
        
        if (impEducacionService.existsEducacionByNombreEdu(dtoEdu.getNombreEdu()) && 
                impEducacionService.getByNombreEdu(dtoEdu.getNombreEdu()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("El nombre no existe"), 
                    HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoEdu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El campo nombre no puede estar vacío"), 
                    HttpStatus.BAD_REQUEST);             
        }
        
        //Pasan todas las validaciones, y localizo el registro por el ID
        Educacion educacion=impEducacionService.getOne(id).get();
        
        //Seteo los cambios del registro
        educacion.setNombreEdu(dtoEdu.getNombreEdu());
        educacion.setDescripcionEdu(dtoEdu.getDescripcionEdu());
        
        //Guardo los cambios
        impEducacionService.save(educacion);
        
        return new ResponseEntity(new Mensaje("Registro educación actualizado"), 
                HttpStatus.OK);
    }
    
    //Borrar Registro
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
        
        //Validamos si el Id existe
        if (!impEducacionService.existsEducacionById(id)) {
            return new ResponseEntity(new Mensaje("El id del registro no existe"), 
                    HttpStatus.NOT_FOUND);
        }
        
        impEducacionService.delete(id);
        return new ResponseEntity(new Mensaje("Registro educación eliminado"), 
                HttpStatus.OK);
    }
    
    
  
}
