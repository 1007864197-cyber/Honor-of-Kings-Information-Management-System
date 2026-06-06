public class Admin extends Person {
    private String department;

    public Admin(String id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        return getName() != null && getName().contains(keyword) || (department != null && department.contains(keyword));
    }
}
