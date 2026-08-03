package jsp.courier.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String shipmentNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_agent_id")
    private DeliveryAgent deliveryAgent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;

    @Column(nullable = false)
    private String sourceLocation;

    @Column(nullable = false)
    private String destinationLocation;

    @Column(nullable = false)
    private String currentLocation;

    @Column(nullable = false)
    private LocalDateTime shippedDate;

    private LocalDateTime estimatedDeliveryDate;

    private LocalDateTime deliveredDate;

    private String remarks;

	public void setStatus(ShipmentStatus created) {
		// TODO Auto-generated method stub
		
	}

	public Object getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setShippedDate(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}
}