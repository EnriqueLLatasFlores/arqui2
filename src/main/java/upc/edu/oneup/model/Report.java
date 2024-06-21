package upc.edu.oneup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reports")
public class    Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "heart_rate",nullable = false, length = 50)
    private double heartRate;

    @Column(name = "breathing_frequency",nullable = false, length = 50)
    private double breathingFrequency;

    @Column(name = "temperature",nullable = false, length = 50)
    private double temperature;

    @Column(name = "pressure",nullable = false,  length = 50)
    private double pressure;

    @Column(name = "longitude",nullable = false,  length = 50)
    private String longitude;

    @Column(name = "latitude",nullable = false,  length = 50)
    private String latitude;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;
}
