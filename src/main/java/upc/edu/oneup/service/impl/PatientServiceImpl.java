package upc.edu.oneup.service.impl;

import upc.edu.oneup.model.Device;
import upc.edu.oneup.model.Patient;
import upc.edu.oneup.model.Report;
import upc.edu.oneup.repository.PatientRepository;
import upc.edu.oneup.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    @Override
    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Device getDeviceByPatientId(int id) {
        return patientRepository.findById(id).get().getDevice();
    }

    @Override
    public List<Report> getReportByPatientId(int id) {return patientRepository.findById(id).get().getReports();}


    @Override
    public Patient updatePatient(Patient updatedPatient) {
        int id = updatedPatient.getId();
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        // Actualizar los campos que se pueden cambiar
        existingPatient.setName(updatedPatient.getName());
        existingPatient.setAddress(updatedPatient.getAddress());
        existingPatient.setDate(updatedPatient.getDate());
        existingPatient.setPhone(updatedPatient.getPhone());
        existingPatient.setWeight(updatedPatient.getWeight());
        existingPatient.setHeight(updatedPatient.getHeight());

        // Mantener el id_user existente si no se proporciona en updatedPatient
        if (updatedPatient.getUser() == null) {
            existingPatient.setUser(existingPatient.getUser());
        } else {
            existingPatient.setUser(updatedPatient.getUser());
        }

        // Guardar y devolver el paciente actualizado
        return patientRepository.save(existingPatient);
    }


    @Override
    public boolean userHasPatient(int userId) {
        return patientRepository.existsByUserId(userId);
    }


}