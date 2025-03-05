package co.edu.uniandes.dse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.services.ConsultaMedicaService;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(ConsultaMedicaService.class)
public class ConsultaMedicaServiceTest {

    @Autowired
	private ConsultaMedicaService consultaService;

	@Autowired
	private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();



    @Test
	void testCreateconsulta() throws IllegalOperationException {
		ConsultaMedicaEntity newEntity = factory.manufacturePojo(ConsultaMedicaEntity.class);
		
		ConsultaMedicaEntity result = consultaService.createConsulta(newEntity);
		assertNotNull(result);

		ConsultaMedicaEntity entity = entityManager.find(ConsultaMedicaEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getFecha(), entity.getFecha());
		assertEquals(newEntity.getCausa(), entity.getCausa());

	}
	






    
}
