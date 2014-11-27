package sg.edu.nyp.silvervitality.MedBook;

/**
 * Created by Kasdeyae on 11/3/2014.
 */
public class Doctor {

private int id;
private String note;
private String date;
private String doctor;

public Doctor(){}

public Doctor(String note, String date, String doctor) {
        super();
        this.note = note;
        this.date = date;
        this.doctor = doctor;
        }

//getters & setters

@Override
public String toString() {
        return "Doctor [id=" + id + ", note=" + note + ", date=" + date
        + ", doctor =" +doctor
        +"]";
        }

    public String getDate() {
        return date;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }
}
