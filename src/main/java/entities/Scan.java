package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="scan")
public class Scan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Date date;

    public Scan(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Scan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Scan{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
