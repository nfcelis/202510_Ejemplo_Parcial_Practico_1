package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class ConsultaMedicaService {

    @Autowired
    ConsultaMedicaRepository consultaRepository;

    @Transactional
	public ConsultaMedicaEntity createConsulta(ConsultaMedicaEntity consulta) throws IllegalOperationException {
        log.info("Inicia proceso de creación del paciente");
		Calendar calendar = Calendar.getInstance();
		if(!consulta.getFecha().after(calendar.getTime())) throw new IllegalOperationException("La fecha de la cita ya paso");


        return consultaRepository.save(consulta);
	    
    

}

}
