package pl.sda.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
public class Run implements Serializable {

    @Transient
    private int placeOnScoreboard = 0;
    @Id
    @ManyToOne
    @JoinColumn(name = "runner_id")
    private Runner runner;
    @Id
    @ManyToOne
    @JoinColumn(name = "running_event_id")
    private RunningEvent runningEvent;
    @Column(name = "run_time")
    private LocalTime runTime;

    public Run() {
    }

    public Run(Runner runner, RunningEvent runningEvent) {
        this.runner = runner;
        this.runningEvent = runningEvent;
    }

    public Run(Runner runner, RunningEvent runningEvent, LocalTime runTime) {
        this.runner = runner;
        this.runningEvent = runningEvent;
        this.runTime = runTime;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public String getRunnerAsString() {
        return runner != null ? runner.toString() : "";
    }

    public RunningEvent getRunningEvent() {
        return runningEvent;
    }

    public void setRunningEvent(RunningEvent runningEvent) {
        this.runningEvent = runningEvent;
    }

    public String getRunningEventAsString() {
        return runningEvent != null ? runningEvent.toString() : "";
    }

    public LocalTime getRunTime() {
        return runTime;
    }

    public void setRunTime(LocalTime runTime) {
        this.runTime = runTime;
    }

    public String getRunTimeAsString() {
        return runTime != null ? runTime.toString() : "";
    }

    public String incrementPlace() {
        int nextPlaceOnScoreboard = placeOnScoreboard;
        nextPlaceOnScoreboard++;
        return nextPlaceOnScoreboard + "";
    }

    @Override
    public String toString() {
        return "" + runner + runningEvent + runTime;
    }
}
