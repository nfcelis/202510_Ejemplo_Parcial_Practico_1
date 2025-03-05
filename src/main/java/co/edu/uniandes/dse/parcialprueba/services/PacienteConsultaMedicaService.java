package co.edu.uniandes.dse.parcialprueba.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteConsultaMedicaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaMedicaRepository consultamedicaRepository;

    @Transactional
	public ConsultaMedicaEntity addConsulta(Long pacienteId, Long consultaId) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de asociarle un libro al autor con id = {0}", pacienteId);
		Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);
		Optional<ConsultaMedicaEntity> consultamedicaEntity = consultamedicaRepository.findById(consultaId);

		if (pacienteEntity.isEmpty())
			throw new EntityNotFoundException("El paciente no fue encontrado");

		if (consultamedicaEntity.isEmpty())
			throw new EntityNotFoundException("La consulta no fue encontrada");

        Calendar calendar = Calendar.getInstance();
        List <ConsultaMedicaEntity> consultaspaciente =pacienteEntity.get().getConsultas();
        for(int i=0;i<consultaspaciente.size();i++){
            if(consultaspaciente.get(i).getFecha().equals(calendar.getTime())) throw new IllegalOperationException("La fecha de la cita coincide con otra que ya tiene agendada");
        }

		pacienteEntity.get().getConsultas().add(consultamedicaEntity.get());
		log.info("Termina proceso de asociarle una consulta al paciente con id = {0}", pacienteId);
		return consultamedicaEntity.get();
	}


    @Transactional
	public List<ConsultaMedicaEntity> getConsultasProgramadas(Long pacienteId) throws EntityNotFoundException, IllegalOperationException {
		log.info("Inicia proceso de consultar las consultas del paciente  = {0} del paciente con id = " +  pacienteId);
		Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);
        List <ConsultaMedicaEntity> consultaspaciente =pacienteEntity.get().getConsultas();
        List <ConsultaMedicaEntity> consultasprogramadas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(int i=0;i<consultaspaciente.size();i++){
            if(consultaspaciente.get(i).getFecha().after(calendar.getTime())){
                consultasprogramadas.add(consultaspaciente.get(i));
            }

        }
        return consultasprogramadas;




    
}
}
