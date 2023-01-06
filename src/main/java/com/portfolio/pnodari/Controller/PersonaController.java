
package com.portfolio.pnodari.Controller;

import com.portfolio.pnodari.Dto.DtoPersona;
import com.portfolio.pnodari.Entity.Persona;
import com.portfolio.pnodari.Security.Controller.Mensaje;
import com.portfolio.pnodari.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://portfoliofrontend-736c5.web.app/","http://localhost:4200/"})
public class PersonaController {
    
    @Autowired
    ImpPersonaService iPersonaService;
    
    /*
    *Solo el Admin puede crear, editar y eliminar (Se agrega una pre autorizacion)
    *El user solo puede ver el portfolio
    */
    
    //Traemos la lista con todas las personas
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        
        //Variable con una lista
        List<Persona> list=iPersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Buscar persona
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!iPersonaService.existsPersonaById(id)){
            return new ResponseEntity(new Mensaje("No existe el registro"), HttpStatus.NOT_FOUND);
        }
        Persona persona = iPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    //Actualizar Persona
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, 
                                    @RequestBody DtoPersona dtoPerso){
        
        //Validación si existe el ID
        if (!iPersonaService.existsPersonaById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), 
                                                  HttpStatus.NOT_FOUND);
        }
        
        //Validamos nombre de persona
        if (iPersonaService.existsByNombrePerso(dtoPerso.getNombre()) &&
                iPersonaService.getByNombrePerso(dtoPerso.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        //Validamos apellido de persona
        if (iPersonaService.existsByNombrePerso(dtoPerso.getApellido()) &&
                iPersonaService.getByNombrePerso(dtoPerso.getApellido()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese apellido ya existe"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        
        //Validamos que no esten en blanco los campos
        if (StringUtils.isBlank(dtoPerso.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre no puede estar en blanco"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoPerso.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido no puede estar en blanco"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoPerso.getProfecion())) {
            return new ResponseEntity(new Mensaje("La profecion no puede estar en blanco"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoPerso.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción no puede estar en blanco"), 
                                                  HttpStatus.BAD_REQUEST);
        }
        
        //Obtenemos la persona
        Persona persona=iPersonaService.getOne(id).get();
        
        //Setteamos los cambios
        persona.setNombre(dtoPerso.getNombre());
        persona.setApellido(dtoPerso.getApellido());
        persona.setProfecion(dtoPerso.getProfecion());
        persona.setDescripcion(dtoPerso.getDescripcion());
        persona.setImg(dtoPerso.getImg());
        
        //Guardamos los cambios
        iPersonaService.save(persona);
        
        //Enviamos los cambios
        return new ResponseEntity(new Mensaje("Persona Actualizada"),
                                              HttpStatus.OK);
    }
    
    //Crear nueva persona
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoPerso){
        //Realizamos la validación (dependencia commons): Nombre 
        if (StringUtils.isBlank(dtoPerso.getNombre()) && 
            StringUtils.isBlank(dtoPerso.getApellido())&& 
            StringUtils.isBlank(dtoPerso.getProfecion()) &&
            StringUtils.isBlank(dtoPerso.getDescripcion()) ) {
            return new ResponseEntity(new Mensaje("Los campos no pueden estar vacíos"),
                                                  HttpStatus.BAD_REQUEST);
        }
        //Validación experiencia no repetidas
        if (iPersonaService.existsByNombrePerso(dtoPerso.getNombre()) &&
            iPersonaService.existsByNombrePerso(dtoPerso.getApellido())    ) {
            return new ResponseEntity(new Mensaje("Ese nombre y apellido ya existen"),
                                                  HttpStatus.BAD_REQUEST);
        }
        
        //Pasan las dos validaciones        
        Persona persona=new Persona(dtoPerso.getNombre(),
                                    dtoPerso.getApellido(),
                                    dtoPerso.getProfecion(),
                                    dtoPerso.getDescripcion(), 
                                    dtoPerso.getImg());
        
        //Guardamos objeto
        iPersonaService.save(persona);
        
        //Mandamos mensaje
        return new ResponseEntity(new Mensaje("Persona Agregada"),
                                              HttpStatus.OK);
    }
    
    /*
    //Borrar Persona
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id ){
        
        //Validación si existe el ID
        if (!iPersonaService.existsPersonaById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), 
                                                  HttpStatus.NOT_FOUND);
        } 
        
        iPersonaService.delete(id);
        
        return new ResponseEntity(new Mensaje("Persona eliminada"),
                                              HttpStatus.OK);
    }
    */
}
