package pl.sda.dto;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "running_event")
public class RunningEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String place;
    @Column(name = "distance_in_kms")
    private Float distanceInKms;
    @OneToMany(mappedBy = "runningEvent", cascade = {CascadeType.REMOVE})
    private List<Run> listOfRuns;

    public RunningEvent() {
    }

    public RunningEvent(String name, String place, Float distanceInKms) {
        this.name = name;
        this.place = place;
        this.distanceInKms = distanceInKms;
    }

    public Long getId() {
        return id;
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

    public Float getDistanceInKms() {
        return distanceInKms;
    }

    public String getDistanceInKmsAsString() {
        return distanceInKms != 0 ? String.valueOf(distanceInKms) : "";
    }

    public void setDistanceInKms(Float distanceInKms) {
        this.distanceInKms = distanceInKms;
    }

//    public List<Runner> getListOfRunners() {
//        return listOfRunners;
//    }
//
//    public void setListOfRunners(List<Runner> listOfRunners) {
//        this.listOfRunners = listOfRunners;
//    }

    @Override
    public String toString() {
        return name;
    }
}
