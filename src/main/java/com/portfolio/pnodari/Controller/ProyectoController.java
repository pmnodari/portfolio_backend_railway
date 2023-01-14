
package com.portfolio.pnodari.Controller;

import com.portfolio.pnodari.Dto.DtoProyecto;
import com.portfolio.pnodari.Entity.Proyecto;
import com.portfolio.pnodari.Security.Controller.Mensaje;
import com.portfolio.pnodari.Service.ImpProyectoService;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://portfoliofrontend-736c5.web.app/","http://localhost:4200/"})
public class ProyectoController {
    
    @Autowired
    ImpProyectoService  impProyectoService;
    
    //Traemos la lista completa
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> lista=impProyectoService.lista();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    //Se crea nuevo registro
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto){
        
        if (StringUtils.isBlank(dtoProyecto.getNombreP())) {            
            return new ResponseEntity(new Mensaje("El campo nombre no puede estar vacío"), 
                    HttpStatus.BAD_REQUEST);            
        }
        if (StringUtils.isBlank(dtoProyecto.getDescripcionP())) {            
            return new ResponseEntity(new Mensaje("El campo descripción no puede estar vacío"), 
                    HttpStatus.BAD_REQUEST);            
        }
        
        if (impProyectoService.existsByNombrePro(dtoProyecto.getNombreP())) {
            return new ResponseEntity(new Mensaje("Este nombre ya existe"), 
                    HttpStatus.BAD_REQUEST); 
        }
        
        Proyecto proyecto= new Proyecto(dtoProyecto.getNombreP(), 
                                        dtoProyecto.getDescripcionP(), 
                                        dtoProyecto.getFecha_realizacion(),
                                        dtoProyecto.getImg());
        
        impProyectoService.save(proyecto);
        
        return new ResponseEntity(proyecto, HttpStatus.OK);
    
    }
    //Buscar Proyecto
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getProyectoById(@PathVariable ("id") int id){
        if (!impProyectoService.existsByIdPro(id)) {
            return new ResponseEntity(new Mensaje("El proyecto no existe"), 
                    HttpStatus.NOT_FOUND);
        }
        
        Proyecto proyecto=impProyectoService.getOne(id).get();
        
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    //Actualizar Proyecto
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") int id,
                                    @RequestBody DtoProyecto dtoProyecto){
        
        //Validacion del Id y el nombre
        if (!impProyectoService.existsByIdPro(id)) {
            return new ResponseEntity(new Mensaje("El proyecto no existe"), 
                    HttpStatus.NOT_FOUND);
        }
        if (impProyectoService.existsByNombrePro(dtoProyecto.getNombreP()) &&
            impProyectoService.getByNombrePro(dtoProyecto.getNombreP()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre no existe"), 
                    HttpStatus.NOT_FOUND);           
        }
        //Campos en blanco
        if (StringUtils.isBlank(dtoProyecto.getNombreP())) {            
            return new ResponseEntity(new Mensaje("El campo nombre no puede estar vacío"), 
                    HttpStatus.BAD_REQUEST);            
        }
        if (StringUtils.isBlank(dtoProyecto.getDescripcionP())) {            
            return new ResponseEntity(new Mensaje("El campo descripción no puede estar vacío"), 
                    HttpStatus.BAD_REQUEST);            
        }
        
        Proyecto proyecto=impProyectoService.getOne(id).get();
        
        proyecto.setNombreP(dtoProyecto.getNombreP());
        proyecto.setDescripcionP(dtoProyecto.getDescripcionP());
        proyecto.setImg(dtoProyecto.getImg());
        
        impProyectoService.save(proyecto);
        
        return new ResponseEntity(new Mensaje("El proyecto fue actualizado"), 
                    HttpStatus.OK);
    
    }
    
    //Borrar Registro
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
        
        if (!impProyectoService.existsByIdPro(id)) {
            return new ResponseEntity(new Mensaje("El proyecto no existe"), 
                    HttpStatus.NOT_FOUND);
        }
        impProyectoService.delete(id);
        
        return new ResponseEntity(new Mensaje("El proyecto fue eliminado"), 
                    HttpStatus.OK);
    }
    
}
