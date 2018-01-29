package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="access_log")
public class AccessLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Date date;

    @Column
    String ip;

    @Column
    String protocol;

    @Column(name="status_code")
    int statusCode;

    @Column
    String client;

    public AccessLog(Long id, Date date, String ip, String protocol, int statusCode, String client) {
        this.id = id;
        this.date = date;
        this.ip = ip;
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.client = client;
    }

    public AccessLog() {
    }


    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "id=" + id +
                ", date=" + date +
                ", ip='" + ip + '\'' +
                ", protocol='" + protocol + '\'' +
                ", statusCode=" + statusCode +
                ", client='" + client + '\'' +
                '}';
    }
}
