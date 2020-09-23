package pl.sda.dto;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Runner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "ten_km_best_time")
    private LocalTime tenKmBestTime;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "runner")
    private List<Run> listOfRuns;

    public Runner() {
    }

    public Runner(String firstName, String lastName, LocalTime tenKmBestTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenKmBestTime = tenKmBestTime;

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalTime getTenKmBestTime() {
        return tenKmBestTime;
    }

    public String getTenKmBestTimeAsString() {
        return tenKmBestTime != null ? tenKmBestTime.toString() : "";
    }

    public void setTenKmBestTime(LocalTime tenKmBestTime) {
        this.tenKmBestTime = tenKmBestTime;
    }

//    public LocalTime getCurrentRunTime() {
//        return currentRunTime;
//    }
//
//    public void setCurrentRunTime(LocalTime currentRunTime) {
//        this.currentRunTime = currentRunTime;
//    }
//
//    public List<RunningEvent> getListOfEvents() {
//        return listOfEvents;
//    }
//
//    public void setListOfEvents(List<RunningEvent> listOfEvents) {
//        this.listOfEvents = listOfEvents;
//    }


    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Runner)) return false;
        Runner runner = (Runner) o;
        return id.equals(runner.id) &&
                firstName.equals(runner.firstName) &&
                lastName.equals(runner.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
