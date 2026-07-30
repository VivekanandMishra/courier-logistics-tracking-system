package jsp.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.courier.entity.DeliveryAgent;
import jsp.courier.service.CustomerService;
import jsp.courier.service.DeliveryAgentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/delivery-agents")
public class DeliveryAgentController {
	
    @Autowired
    private DeliveryAgentService deliveryAgentService;

    // Save Delivery Agent
    @PostMapping("/save")
    public ResponseEntity<DeliveryAgent> saveDeliveryAgent(
            @RequestBody DeliveryAgent deliveryAgent) {

        return new ResponseEntity<>(
                deliveryAgentService.saveDeliveryAgent(deliveryAgent),
                HttpStatus.CREATED);
    }

    // Get All Delivery Agents
    @GetMapping("/All-agents")
    public ResponseEntity<List<DeliveryAgent>> getAllDeliveryAgents() {

        return ResponseEntity.ok(
                deliveryAgentService.getAllDeliveryAgents());
    }
    // Get Delivery Agent By Id
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAgent> getDeliveryAgentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                deliveryAgentService.getDeliveryAgentById(id));
    }
//
    // Update Delivery Agent
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAgent> updateDeliveryAgent(
            @PathVariable Long id,
            @RequestBody DeliveryAgent deliveryAgent) {

        return ResponseEntity.ok(
                deliveryAgentService.updateDeliveryAgent(id, deliveryAgent));
    }
//
    // Delete Delivery Agent
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryAgent(
            @PathVariable Long id) {

        deliveryAgentService.deleteDeliveryAgent(id);

        return ResponseEntity.ok("Delivery Agent deleted successfully.");
    }

}