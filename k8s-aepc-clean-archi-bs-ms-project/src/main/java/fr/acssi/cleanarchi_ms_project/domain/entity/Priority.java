package fr.acssi.cleanarchi_ms_project.domain.entity;

public enum Priority {
    P1((byte)1),
    P2((byte)2),
    P3((byte)3),
    P4((byte)4),
    P5((byte)5);

    private byte projectPriority;

    Priority(byte projectPriority) {
        this.projectPriority = projectPriority;
    }

    public byte getProjectPriority() {
        return projectPriority;
    }
}
