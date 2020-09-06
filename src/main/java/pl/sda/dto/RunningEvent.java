package pl.sda.dto;

import javax.persistence.*;
import java.util.List;
@Entity
public class RunningEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String place;
    @Column(name = "distance_in_kms")
    private int distanceInKms;
    @Column(name = "list_of_events")
    @ManyToMany(mappedBy = "listOfEvents")
    private List<Runner> listOfRunners;

    public RunningEvent() {
    }

    public RunningEvent(String name, String place, int distanceInKms) {
        this.name = name;
        this.place = place;
        this.distanceInKms = distanceInKms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getDistanceInKms() {
        return distanceInKms;
    }

    public void setDistanceInKms(int distanceInKms) {
        this.distanceInKms = distanceInKms;
    }

    public List<Runner> getListOfRunners() {
        return listOfRunners;
    }

    public void setListOfRunners(List<Runner> listOfRunners) {
        this.listOfRunners = listOfRunners;
    }

    @Override
    public String toString() {
        return name;
    }
}
