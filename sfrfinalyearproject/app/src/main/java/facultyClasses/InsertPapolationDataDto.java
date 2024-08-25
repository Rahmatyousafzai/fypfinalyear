package facultyClasses;

import java.util.List;

import Faculty.AdueinceDetail;

public class InsertPapolationDataDto {




public InsertPapolationDataDto(){

}

    private publicWish Wish;

    public List<String> getSelectedcourseIds() {
        return SelectedcourseIds;
    }

    public void setSelectedcourseIds(List<String> selectedcourseIds) {
        SelectedcourseIds = selectedcourseIds;
    }

    private  List<String> SelectedcourseIds;

    private List<Audience> AudienceList;

    public InsertPapolationDataDto(publicWish wish, List<Audience> audienceList, List<AdueinceDetail> adueinceDetailsList, List<Integer> selectedSemesterIds, List<Integer> selectedSectionIds) {
        Wish = wish;
        AudienceList = audienceList;
        AdueinceDetailsList = adueinceDetailsList;
        SelectedSemesterIds = selectedSemesterIds;
        SelectedSectionIds = selectedSectionIds;
    }

    private List<AdueinceDetail> AdueinceDetailsList;

    public publicWish getWish() {
        return Wish;
    }

    public void setWish(publicWish wish) {
        Wish = wish;
    }

    public List<Audience> getAudienceList() {
        return AudienceList;
    }

    public void setAudienceList(List<Audience> audienceList) {
        AudienceList = audienceList;
    }

    public List<AdueinceDetail> getAdueinceDetailsList() {
        return AdueinceDetailsList;
    }

    public void setAdueinceDetailsList(List<AdueinceDetail> adueinceDetailsList) {
        AdueinceDetailsList = adueinceDetailsList;
    }

    public List<Integer> getSelectedSemesterIds() {
        return SelectedSemesterIds;
    }

    public void setSelectedSemesterIds(List<Integer> selectedSemesterIds) {
        SelectedSemesterIds = selectedSemesterIds;
    }

    public List<Integer> getSelectedSectionIds() {
        return SelectedSectionIds;
    }

    public void setSelectedSectionIds(List<Integer> selectedSectionIds) {
        SelectedSectionIds = selectedSectionIds;
    }

    private List<Integer> SelectedSemesterIds;
    private List<Integer> SelectedSectionIds;











}