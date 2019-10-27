package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class LevelAndRoundInfo {
    private String template;
    private ContentBeanX content;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public ContentBeanX getContent() {
        return content;
    }

    public void setContent(ContentBeanX content) {
        this.content = content;
    }

    public static class ContentBeanX {

        private String description;
        private List<RoundsBean> rounds;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<RoundsBean> getRounds() {
            return rounds;
        }

        public void setRounds(List<RoundsBean> rounds) {
            this.rounds = rounds;
        }

        public static class RoundsBean {

            private ContentBean content;
            private String template;

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
            }

            public static class ContentBean {

                private String name;
                private List<DataBean> data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<DataBean> getData() {
                    return data;
                }

                public void setData(List<DataBean> data) {
                    this.data = data;
                }

                public static class DataBean {

                    private int fs_A;
                    private int fs_B;
                    private boolean show;
                    private int team_A_id;
                    private String team_A_logo;
                    private String team_A_name;
                    private int team_A_rank;
                    private int team_B_id;
                    private String team_B_logo;
                    private String team_B_name;
                    private int team_B_rank;
                    private String winner;

                    public int getFs_A() {
                        return fs_A;
                    }

                    public void setFs_A(int fs_A) {
                        this.fs_A = fs_A;
                    }

                    public int getFs_B() {
                        return fs_B;
                    }

                    public void setFs_B(int fs_B) {
                        this.fs_B = fs_B;
                    }

                    public boolean isShow() {
                        return show;
                    }

                    public void setShow(boolean show) {
                        this.show = show;
                    }

                    public int getTeam_A_id() {
                        return team_A_id;
                    }

                    public void setTeam_A_id(int team_A_id) {
                        this.team_A_id = team_A_id;
                    }

                    public String getTeam_A_logo() {
                        return team_A_logo;
                    }

                    public void setTeam_A_logo(String team_A_logo) {
                        this.team_A_logo = team_A_logo;
                    }

                    public String getTeam_A_name() {
                        return team_A_name;
                    }

                    public void setTeam_A_name(String team_A_name) {
                        this.team_A_name = team_A_name;
                    }

                    public int getTeam_A_rank() {
                        return team_A_rank;
                    }

                    public void setTeam_A_rank(int team_A_rank) {
                        this.team_A_rank = team_A_rank;
                    }

                    public int getTeam_B_id() {
                        return team_B_id;
                    }

                    public void setTeam_B_id(int team_B_id) {
                        this.team_B_id = team_B_id;
                    }

                    public String getTeam_B_logo() {
                        return team_B_logo;
                    }

                    public void setTeam_B_logo(String team_B_logo) {
                        this.team_B_logo = team_B_logo;
                    }

                    public String getTeam_B_name() {
                        return team_B_name;
                    }

                    public void setTeam_B_name(String team_B_name) {
                        this.team_B_name = team_B_name;
                    }

                    public int getTeam_B_rank() {
                        return team_B_rank;
                    }

                    public void setTeam_B_rank(int team_B_rank) {
                        this.team_B_rank = team_B_rank;
                    }

                    public String getWinner() {
                        return winner;
                    }

                    public void setWinner(String winner) {
                        this.winner = winner;
                    }
                }
            }
        }
    }
}
