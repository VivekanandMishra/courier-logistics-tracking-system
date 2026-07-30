package jsp.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsp.courier.entity.DeliveryAgent;
import jsp.courier.repository.DeliveryAgentRepository;

@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    @Override
    public DeliveryAgent saveDeliveryAgent(DeliveryAgent deliveryAgent) {
        return deliveryAgentRepository.save(deliveryAgent);
    }

    @Override
    public List<DeliveryAgent> getAllDeliveryAgents() {
        return deliveryAgentRepository.findAll();
    }

    @Override
    public DeliveryAgent getDeliveryAgentById(Long id) {
        return deliveryAgentRepository.findById(id).orElse(null);
    }

    @Override
    public DeliveryAgent updateDeliveryAgent(Long id, DeliveryAgent deliveryAgent) {

        DeliveryAgent existingAgent = deliveryAgentRepository.findById(id).orElse(null);

        if (existingAgent != null) {

//        	existingAgent.setName(deliveryAgent.getName());
        	existingAgent.setEmail(deliveryAgent.getEmail());
        	existingAgent.setPhoneNo(deliveryAgent.getPhoneNo());
        	existingAgent.setVehicleType(deliveryAgent.getVehicleType());
        	existingAgent.setVehicleNumber(deliveryAgent.getVehicleNumber());
        	existingAgent.setCurrentLocation(deliveryAgent.getCurrentLocation());
//        	existingAgent.setAvailable(deliveryAgent.isAvailable());

            return deliveryAgentRepository.save(existingAgent);
        }

        return null;
    }

    @Override
    public String deleteDeliveryAgent(Long id) {

        DeliveryAgent existingAgent = deliveryAgentRepository.findById(id).orElse(null);

        if (existingAgent != null) {
            deliveryAgentRepository.delete(existingAgent);
            return "Delivery Agent deleted successfully.";
        }

        return "Delivery Agent not found.";
    }
}
