import java.util.Date;

public class Appointment {
    private Patient patient;
    private Date timeAndDate;
    private Physician physician;
    private Transferorder transferorder;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Date timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public Transferorder getTransferorder() {
        return transferorder;
    }

    public void setTransferorder(Transferorder transferorder) {
        this.transferorder = transferorder;
    }





}
