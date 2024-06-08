package studentClasses;

public class GroupsData {

     private int GID;
     private String gropnmae;
             private String groupDescription;
             private String groupicon;

    public int getGID() {
        return GID;
    }

    public void setGID(int GID) {
        this.GID = GID;
    }

    public String getGropnmae() {
        return gropnmae;
    }

    public void setGropnmae(String gropnmae) {
        this.gropnmae = gropnmae;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupicon() {
        return groupicon;
    }

    public void setGroupicon(String groupicon) {
        this.groupicon = groupicon;
    }


    public GroupsData(int GID, String gropnmae, String groupDescription, String groupicon) {
        this.GID = GID;
        this.gropnmae = gropnmae;
        this.groupDescription = groupDescription;
        this.groupicon = groupicon;
    }
}
