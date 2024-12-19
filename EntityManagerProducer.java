import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.ext.Provider;

/**
 *
 * @author USER
 */
@Provider
public class EntityManagerProducer {
    @Produces
    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;
}
