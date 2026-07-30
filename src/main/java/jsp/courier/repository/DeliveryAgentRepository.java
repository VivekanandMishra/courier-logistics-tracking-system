package jsp.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.courier.entity.DeliveryAgent;

	public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {


}
