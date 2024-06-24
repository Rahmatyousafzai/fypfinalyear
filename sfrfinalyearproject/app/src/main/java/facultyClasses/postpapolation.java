package facultyClasses;

import java.util.List;

public class postpapolation {


        private String senderid;
        private String content;
        private String esid;
        private List<Audience> audienceList;

        public postpapolation(String senderid, String content, String esid, List<Audience> audienceList) {
            this.senderid = senderid;
            this.content = content;
            this.esid = esid;
            this.audienceList = audienceList;
        }

        public String getSenderid() {
            return senderid;
        }

        public void setSenderid(String senderid) {
            this.senderid = senderid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getEsid() {
            return esid;
        }

        public void setEsid(String esid) {
            this.esid = esid;
        }

        public List<Audience> getAudienceList() {
            return audienceList;
        }

        public void setAudienceList(List<Audience> audienceList) {
            this.audienceList = audienceList;
        }
    }


