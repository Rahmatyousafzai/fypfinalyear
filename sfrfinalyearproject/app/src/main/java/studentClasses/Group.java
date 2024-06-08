package studentClasses;

public class Group {
    private String gropnmae;
    private String groupDescription;
    private String groupIcon;

    public Group(String groupName, String groupDescription, String groupIcon) {
        this.gropnmae = groupName;
        this.groupDescription = groupDescription;
        this.groupIcon = groupIcon;
    }

    public String getGroupName() {
        return gropnmae;
    }

    public void setGroupName(String groupName) {
        this.gropnmae = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }
}
