package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
	public PacienteEntity createAuthor(PacienteEntity paciente) throws IllegalOperationException {
		log.info("Inicia proceso de creación del paciente");

        if (paciente.getNombre() == null)
			throw new IllegalOperationException("Debe tener un nombre el paciente");
        if (paciente.getEdad() == null)
		throw new IllegalOperationException("Debe tener una edad valida");

        if (paciente.getCelular() == null)
			throw new IllegalOperationException("Debe tener un celular el paciente");
        if (paciente.getCorreo() == null)
		throw new IllegalOperationException("Debe tener un correo el paciente");
	
		
		return pacienteRepository.save(paciente);
	}

    
}
