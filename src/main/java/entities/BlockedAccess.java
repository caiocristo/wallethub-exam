package entities;

import javax.persistence.*;

@Entity
@Table(name = "blocked_access")
public class BlockedAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String ip;

    @Column
    String comment;

    public BlockedAccess(Long id, String ip, String comment) {
        this.id = id;
        this.ip = ip;
        this.comment = comment;
    }

    public BlockedAccess() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "BlockedAccess{" +
                "id=" + id +
                ", accessLog=" + ip +
                ", comment='" + comment + '\'' +
                '}';
    }
}
