package jsp.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jsp.courier.dto.TrackingHistoryResponse;
import jsp.courier.entity.TrackingHistory;
import jsp.courier.service.TrackingHistoryService;

@RestController
@RequestMapping("/tracking-history")
public class TrackingHistoryController {

    @Autowired
    private TrackingHistoryService trackingHistoryService;

    @PostMapping("/save")
    public TrackingHistoryResponse saveTrackingHistory(@RequestBody TrackingHistory trackingHistory) {
        return trackingHistoryService.saveTrackingHistory(trackingHistory);
    }

    @GetMapping
    public List<TrackingHistoryResponse> getAllTrackingHistory() {
        return trackingHistoryService.getAllTrackingHistory();
    }

    @GetMapping("/{id}")
    public TrackingHistoryResponse getTrackingHistoryById(@PathVariable Long id) {
        return trackingHistoryService.getTrackingHistoryById(id);
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<TrackingHistoryResponse> getTrackingHistoryByShipmentId(@PathVariable Long shipmentId) {
        return trackingHistoryService.getTrackingHistoryByShipmentId(shipmentId);
    }

    @DeleteMapping("/{id}")
    public String deleteTrackingHistory(@PathVariable Long id) {
        return trackingHistoryService.deleteTrackingHistory(id);
    }
}