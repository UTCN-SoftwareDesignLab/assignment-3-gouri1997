package hospital.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Patient patientId;
    private Date date;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User doctor;
    private String diagnosis;

    public Consultation() {
    }

    public Consultation(Integer id, Patient patientId, Date date, User doctor, String diagnosis) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
    }

    public Consultation(Patient patientId, Date date, User doctor) {
        this.patientId = patientId;
        this.date = date;
        this.doctor = doctor;
    }

    public Consultation(Patient patientId, String diagnosis) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
