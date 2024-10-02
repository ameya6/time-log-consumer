package org.time.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "process_time_log")
public class ProcessTimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "start_time_ns", nullable = false)
    private Long startTimeNs;

    @Column(name = "end_time_ns", nullable = false)
    private Long endTimeNs;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "host_name", nullable = false)
    private String hostName;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "process_time", nullable = false)
    private Double processTime;
}
