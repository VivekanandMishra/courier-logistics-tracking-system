package jsp.courier.service;

import java.util.List;
import org.springframework.stereotype.Service;
import jsp.courier.entity.DeliveryAgent;

@Service
public interface DeliveryAgentService {

    // Save Delivery Agent
    DeliveryAgent saveDeliveryAgent(DeliveryAgent deliveryAgent);

    // Get All Delivery Agents
    List<DeliveryAgent> getAllDeliveryAgents();

    // Get Delivery Agent By Id
    DeliveryAgent getDeliveryAgentById(Long id);

    // Update Delivery Agent
    DeliveryAgent updateDeliveryAgent(int id, DeliveryAgent deliveryAgent);

    // Delete Delivery Agent
    String deleteDeliveryAgent(int id);

}