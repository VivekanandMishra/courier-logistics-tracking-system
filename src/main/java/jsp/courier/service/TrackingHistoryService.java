package jsp.courier.service;

import java.util.List;

import jsp.courier.dto.TrackingHistoryResponse;
import jsp.courier.entity.TrackingHistory;

public interface TrackingHistoryService {

    TrackingHistoryResponse saveTrackingHistory(TrackingHistory trackingHistory);

    List<TrackingHistoryResponse> getAllTrackingHistory();

    TrackingHistoryResponse getTrackingHistoryById(Long id);

    List<TrackingHistoryResponse> getTrackingHistoryByShipmentId(Long shipmentId);

    String deleteTrackingHistory(Long id);

}