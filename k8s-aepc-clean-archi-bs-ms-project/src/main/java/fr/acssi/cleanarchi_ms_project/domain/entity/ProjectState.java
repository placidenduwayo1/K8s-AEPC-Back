package fr.acssi.cleanarchi_ms_project.domain.entity;

public enum ProjectState {
    END("end"),
    ONGOING("ongoing"),
    ARCHIVE("archived"),
    OUTDATED("outdated");
    private String state;

    ProjectState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
