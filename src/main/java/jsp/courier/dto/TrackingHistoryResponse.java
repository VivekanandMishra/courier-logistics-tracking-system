package jsp.courier.dto;

import java.time.LocalDateTime;

import jsp.courier.entity.ShipmentStatus;
import lombok.Data;

@Data
public class TrackingHistoryResponse {

    private Long id;
    private Long shipmentId;
    private ShipmentStatus status;
    private String location;
    private String remarks;
    private LocalDateTime updatedAt;
}