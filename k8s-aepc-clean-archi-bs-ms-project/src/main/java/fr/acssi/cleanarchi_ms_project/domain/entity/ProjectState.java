package fr.acssi.cleanarchi_ms_project.domain.entity;

public enum ProjectState {
    END("end"),
    ONGOING("ongoing"),
    ARCHIVE("archived"),
    OUTDATED("outdated");
    private String projectState;

    ProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectState() {
        return projectState;
    }
}
