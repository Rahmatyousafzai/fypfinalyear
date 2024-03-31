package adopter;

public class student_show_samester {


    private String name;
    private boolean isChecked;

    public student_show_samester(String name, boolean b) {
        this.name = name;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }
}
