package jsp.courier.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agentCode;

    private String firstName;

    private String lastName;

    @Email(message = "Invalid email")
    private String email;

    private String phoneNo;

    @Column(nullable = false)
    private String vehicleType;

    @Column(unique = true, nullable = false)
    private String vehicleNumber;

    @Column(unique = true)
    private String drivingLicenseNo;

    private String currentLocation;

    @Column(nullable = false)
    private Boolean available;

    private LocalDate joiningDate;

    private Double salary;

//    if Uncomment if Shipment entity exists
     @OneToMany(mappedBy = "deliveryAgent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private List<Shipment> shipments;
}