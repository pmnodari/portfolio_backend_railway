package com.portfolio.pnodari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PnodariApplication {

	public static void main(String[] args) {
		SpringApplication.run(PnodariApplication.class, args);
	}

}
/*
Entidad -> Repositorio -> Interface -> Servicio -> Controlador (Lado Backend)
Entidad <- Repositorio <- Interface <- Servicio <- Controlador (Lado FrontEnd)
*/
