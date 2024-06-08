package studentClasses;

public class GroupMember {
    private int GroupId;
    private String GroupMemberId;
    private String MemberType;



    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        this.GroupId = groupId;
    }

    public String getGroupMemberId() {
        return GroupMemberId;
    }

    public void setGroupMemberId(String groupMemberId) {
        this.GroupMemberId = groupMemberId;
    }

    public String getMemberType() {
        return MemberType;
    }

    public void setMemberType(String memberType) {
        this.MemberType = memberType;
    }
}
