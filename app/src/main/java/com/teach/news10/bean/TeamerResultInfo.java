package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/13.
 */
public class TeamerResultInfo {

    private String template;
    private ContentBean content;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {

        private String description;
        private int end;
        private int top;
        private List<DataBean> data;
        private List<String> header;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<String> getHeader() {
            return header;
        }

        public void setHeader(List<String> header) {
            this.header = header;
        }

        public static class DataBean {

            private String person_id;
            private String person_logo;
            private String person_name;
            private String rank;
            private String team_id;
            private String team_logo;
            private String team_name;
            private String value;

            public String getPerson_id() {
                return person_id;
            }

            public void setPerson_id(String person_id) {
                this.person_id = person_id;
            }

            public String getPerson_logo() {
                return person_logo;
            }

            public void setPerson_logo(String person_logo) {
                this.person_logo = person_logo;
            }

            public String getPerson_name() {
                return person_name;
            }

            public void setPerson_name(String person_name) {
                this.person_name = person_name;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getTeam_id() {
                return team_id;
            }

            public void setTeam_id(String team_id) {
                this.team_id = team_id;
            }

            public String getTeam_logo() {
                return team_logo;
            }

            public void setTeam_logo(String team_logo) {
                this.team_logo = team_logo;
            }

            public String getTeam_name() {
                return team_name;
            }

            public void setTeam_name(String team_name) {
                this.team_name = team_name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
