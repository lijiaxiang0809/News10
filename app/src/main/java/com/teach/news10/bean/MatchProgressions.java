package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/14.
 */
public class MatchProgressions {


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

        private String prev;
        private String next;
        private List<RoundsBean> rounds;
        private List<MatchesBean> matches;

        public String getPrev() {
            return prev;
        }

        public void setPrev(String prev) {
            this.prev = prev;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public List<RoundsBean> getRounds() {
            return rounds;
        }

        public void setRounds(List<RoundsBean> rounds) {
            this.rounds = rounds;
        }

        public List<MatchesBean> getMatches() {
            return matches;
        }

        public void setMatches(List<MatchesBean> matches) {
            this.matches = matches;
        }

        public static class RoundsBean {
            /**
             * name : 2018-09-29(2场)
             * url : http://sport-data.dqdgame.com/sd/biz/data/schedule?platform=&version=0&season_id=102&tz=8&date=2018-09-29
             * current : true
             */

            private String name;
            private String url;
            private boolean current;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isCurrent() {
                return current;
            }

            public void setCurrent(boolean current) {
                this.current = current;
            }
        }

        public static class MatchesBean {
            /**
             * match_id : 11525
             * team_A_id : 17
             * team_A_name : 勇士
             * team_A_logo : http://img1.dqdgame.com/fastdfs/M00/00/24/ChOlBlvGrr6AcmobAAAe3sUXDds953.png
             * team_B_id : 18
             * team_B_name : 开拓者
             * team_B_logo : http://img1.dqdgame.com/fastdfs/M00/00/24/ChM9m1vGrsCAEwkyAAAoo3UMQ2Q443.png
             * round_name : 季后赛
             * fs_A :
             * fs_B :
             * start_play : 2019-05-15 01:00:00
             * end_play :
             * status : Fixture
             * transfer : 1
             */

            private String match_id;
            private String team_A_id;
            private String team_A_name;
            private String team_A_logo;
            private String team_B_id;
            private String team_B_name;
            private String team_B_logo;
            private String round_name;
            private String fs_A;
            private String fs_B;
            private String start_play;
            private String end_play;
            private String status;
            private String transfer;

            public String getMatch_id() {
                return match_id;
            }

            public void setMatch_id(String match_id) {
                this.match_id = match_id;
            }

            public String getTeam_A_id() {
                return team_A_id;
            }

            public void setTeam_A_id(String team_A_id) {
                this.team_A_id = team_A_id;
            }

            public String getTeam_A_name() {
                return team_A_name;
            }

            public void setTeam_A_name(String team_A_name) {
                this.team_A_name = team_A_name;
            }

            public String getTeam_A_logo() {
                return team_A_logo;
            }

            public void setTeam_A_logo(String team_A_logo) {
                this.team_A_logo = team_A_logo;
            }

            public String getTeam_B_id() {
                return team_B_id;
            }

            public void setTeam_B_id(String team_B_id) {
                this.team_B_id = team_B_id;
            }

            public String getTeam_B_name() {
                return team_B_name;
            }

            public void setTeam_B_name(String team_B_name) {
                this.team_B_name = team_B_name;
            }

            public String getTeam_B_logo() {
                return team_B_logo;
            }

            public void setTeam_B_logo(String team_B_logo) {
                this.team_B_logo = team_B_logo;
            }

            public String getRound_name() {
                return round_name;
            }

            public void setRound_name(String round_name) {
                this.round_name = round_name;
            }

            public String getFs_A() {
                return fs_A;
            }

            public void setFs_A(String fs_A) {
                this.fs_A = fs_A;
            }

            public String getFs_B() {
                return fs_B;
            }

            public void setFs_B(String fs_B) {
                this.fs_B = fs_B;
            }

            public String getStart_play() {
                return start_play;
            }

            public void setStart_play(String start_play) {
                this.start_play = start_play;
            }

            public String getEnd_play() {
                return end_play;
            }

            public void setEnd_play(String end_play) {
                this.end_play = end_play;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTransfer() {
                return transfer;
            }

            public void setTransfer(String transfer) {
                this.transfer = transfer;
            }
        }
    }
}
