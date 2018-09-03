package com.a3k.entity;

public class Collection {

    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private String programId;
    private String gradeLevel;

    public Collection(String id, String name, String startDate, String endDate, String programId, String gradeLevel) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.programId = programId;
        this.gradeLevel = gradeLevel;
    }

    public Collection() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String toString() {
        return "name='" + name;
    }
}
