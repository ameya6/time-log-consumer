package org.time.service;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.time.dao.ProcessTimeLogDao;
import org.time.model.ProcessTimeLog;
import org.time.model.ProcessTimeLogDTO;

@Service
@Log4j2
public class TimeLogConsumer {

    @Autowired
    private Gson gson;

    @Autowired
    private ProcessTimeLogDao ProcessTimeLogDao;

    @KafkaListener(topics = "time-log-topic", groupId = "process-time-log", concurrency = "10")
    public void consume(String processTimeLogDTOJson) {
        log.info("Consumed : {}", processTimeLogDTOJson);
        ProcessTimeLogDTO processTimeLogDTO = gson.fromJson(processTimeLogDTOJson, ProcessTimeLogDTO.class);
        ProcessTimeLog processTimeLog = ProcessTimeLogDao.save(toProcessTimeLog(processTimeLogDTO));
        log.info("Saved : {}", processTimeLog);
    }

    private ProcessTimeLog toProcessTimeLog(ProcessTimeLogDTO processTimeLogDTO) {
        return ProcessTimeLog.builder()
                .id(processTimeLogDTO.getId())
                .startTimeNs(processTimeLogDTO.getStartTimeNs())
                .endTimeNs(processTimeLogDTO.getEndTimeNs())
                .startTime(processTimeLogDTO.getStartTime())
                .endTime(processTimeLogDTO.getEndTime())
                .processTime(processTimeLogDTO.getProcessTime())
                .hostName(processTimeLogDTO.getHostName())
                .ipAddress(processTimeLogDTO.getIpAddress())
                .serviceName(processTimeLogDTO.getServiceName())
                .build();
    }
}
