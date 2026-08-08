package jsp.courier.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jsp.courier.dto.TrackingHistoryResponse;
import jsp.courier.entity.TrackingHistory;
import jsp.courier.repository.TrackingHistoryRepository;

@Service
public class TrackingHistoryServiceImpl implements TrackingHistoryService {

    @Autowired
    private TrackingHistoryRepository trackingHistoryRepository;

    @Override
    public TrackingHistoryResponse saveTrackingHistory(TrackingHistory trackingHistory) {

        TrackingHistory saved = trackingHistoryRepository.save(trackingHistory);

        return mapToResponse(saved);
    }

    @Override
    public List<TrackingHistoryResponse> getAllTrackingHistory() {

        return trackingHistoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TrackingHistoryResponse getTrackingHistoryById(Long id) {

        TrackingHistory history = trackingHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tracking History not found"));

        return mapToResponse(history);
    }

    @Override
    public List<TrackingHistoryResponse> getTrackingHistoryByShipmentId(Long shipmentId) {

        return trackingHistoryRepository.findByShipmentId(shipmentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteTrackingHistory(Long id) {

        TrackingHistory history = trackingHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tracking History not found"));

        trackingHistoryRepository.delete(history);

        return "Tracking History deleted successfully";
    }

    private TrackingHistoryResponse mapToResponse(TrackingHistory history) {

        TrackingHistoryResponse response = new TrackingHistoryResponse();

        response.setId(history.getId());

        if (history.getShipment() != null) {
            response.setShipmentId(history.getShipment().getId());
        }

        response.setStatus(history.getStatus());
        response.setLocation(history.getLocation());
        response.setRemarks(history.getRemarks());
        response.setUpdatedAt(history.getUpdatedAt());

        return response;
    }
}