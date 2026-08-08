package jsp.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.courier.entity.TrackingHistory;

public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Long> {

    List<TrackingHistory> findByShipmentId(Long shipmentId);

}