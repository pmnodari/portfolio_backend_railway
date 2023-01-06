
package com.portfolio.pnodari.Controller;

import com.portfolio.pnodari.Dto.DtoHyS;
import com.portfolio.pnodari.Entity.HyS;
import com.portfolio.pnodari.Security.Controller.Mensaje;
import com.portfolio.pnodari.Service.ImpHySkillService;
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
@RequestMapping("/hys")
@CrossOrigin(origins = {"https://portfoliofrontend-736c5.web.app/","http://localhost:4200/"})
public class HySController {
    
    @Autowired
    ImpHySkillService impHySkillService;
    
    //Lista completa
    @GetMapping("/lista")
    public ResponseEntity<List<HyS>> list(){
        List<HyS> lista=impHySkillService.list();
        
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    
    //Nueva habilidad
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHyS dtoHyS){
        
        //Dos validaciones: no se permiten registros en blanco ni repetidos
        if(StringUtils.isBlank(dtoHyS.getNombreHS())){
            return new ResponseEntity(new Mensaje("Los campos no pueden estar vacíos"), 
                    HttpStatus.BAD_REQUEST);
        }
        
        if (impHySkillService.existsHySByNombre(dtoHyS.getNombreHS())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), 
                    HttpStatus.BAD_REQUEST);
        }
        
        HyS skill=new HyS(dtoHyS.getNombreHS(), dtoHyS.getPorcentaje());
        
        impHySkillService.save(skill);
        
        return new ResponseEntity(skill, HttpStatus.OK);
    }
    
    //Buscar habilidad
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getHySById(@PathVariable ("id") int id){
        
        //Si el Id NO existe
        if (!impHySkillService.existsHySById(id)) {
            return new ResponseEntity(new Mensaje("La habilidad no existe"),
            HttpStatus.NOT_FOUND);
        }
        
        HyS skill=impHySkillService.getOne(id).get();
        
        return new ResponseEntity(skill, HttpStatus.OK);    
    }
    
    //Actualizar
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") int id,
                                    @RequestBody DtoHyS dtoHyS){
        
         //Validamos que el registro exista por id y por nombre; y que los campos 
        //no este en blanco
        if (!impHySkillService.existsHySById(id)) {
            return new ResponseEntity(new Mensaje("El id del registro no existe"),
                        HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(dtoHyS.getNombreHS())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"),
                        HttpStatus.BAD_REQUEST);
        }
        if (impHySkillService.existsHySByNombre(dtoHyS.getNombreHS()) && 
                impHySkillService.getByNombreHS(dtoHyS.getNombreHS()).get().getId() !=id) {
            return new ResponseEntity(new Mensaje("Ese registro de habilidad ya existe"),
                        HttpStatus.BAD_REQUEST);
        }
        
        HyS skill=impHySkillService.getOne(id).get();
        
        skill.setNombreHS(dtoHyS.getNombreHS());
        skill.setPorcentaje(dtoHyS.getPorcentaje());
        
        impHySkillService.save(skill);
        
        return new ResponseEntity(new Mensaje("Registro habilidad actulizado"), 
                HttpStatus.OK);
    
    }
    //Borrar Registro
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") int id){
        
        //Validamos si el Id existe
        if (!impHySkillService.existsHySById(id)) {
            return new ResponseEntity(new Mensaje("El id del registro no existe"), 
                    HttpStatus.NOT_FOUND);
        }
        
        impHySkillService.delete(id);
        return new ResponseEntity(new Mensaje("Registro habilidad eliminado"), 
                HttpStatus.OK);
    }
    
    
}
