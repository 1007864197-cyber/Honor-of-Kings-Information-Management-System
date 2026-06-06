public class Equipment implements Searchable {
    private String id;
    private String name;
    private String slot;
    private String description;

    public Equipment(String id, String name, String slot, String description) {
        this.id = id;
        this.name = name;
        this.slot = slot;
        this.description = description;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSlot() { return slot; }
    public void setSlot(String slot) { this.slot = slot; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        return (name != null && name.contains(keyword)) || (description != null && description.contains(keyword));
    }
}
