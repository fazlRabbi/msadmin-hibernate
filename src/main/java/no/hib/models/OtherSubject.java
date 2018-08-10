package no.hib.models;

public class OtherSubject {
    private String name;
    private String uuid;

    public OtherSubject(String name) {
        this.name = name;
    }

    public OtherSubject(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public OtherSubject() {

    }

    public String getId() {
        return uuid;
    }

    public void setId(String id) {
        this.uuid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
