package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/13.
 */
public class NBAPersonInfo {
    /**
     * template : ranking_types
     * content : {"data":[{"name":"得分","type":"pts_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=pts_avg&season_id=119"},{"name":"篮板","type":"rbs_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=rbs_avg&season_id=119"},{"name":"助攻","type":"asts_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=asts_avg&season_id=119"},{"name":"抢断","type":"stls_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=stls_avg&season_id=119"},{"name":"盖帽","type":"blks_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=blks_avg&season_id=119"},{"name":"薪水","type":"salary","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=salary&season_id=119"},{"name":"投篮命中率","type":"fgp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=fgp&season_id=119"},{"name":"三分命中率","type":"tfgp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=tfgp&season_id=119"},{"name":"罚球命中率","type":"ftp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ftp&season_id=119"},{"name":"篮板率","type":"trbp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=trbp&season_id=119"},{"name":"进攻篮板","type":"orbs_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=orbs_avg&season_id=119"},{"name":"进攻篮板率","type":"orbp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=orbp&season_id=119"},{"name":"防守篮板","type":"drbs_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=drbs_avg&season_id=119"},{"name":"防守篮板率","type":"drbp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=drbp&season_id=119"},{"name":"助攻率","type":"astr","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=astr&season_id=119"},{"name":"助攻失误比","type":"asttop","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=asttop&season_id=119"},{"name":"助攻比率","type":"astp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=astp&season_id=119"},{"name":"三分总命中数","type":"tfgm","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=tfgm&season_id=119"},{"name":"场均三分命中","type":"tfgm_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=tfgm_avg&season_id=119"},{"name":"eFG%","type":"efg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=efg&season_id=119"},{"name":"罚球命中总数","type":"ftm","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ftm&season_id=119"},{"name":"场均罚球出手","type":"fta_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=fta_avg&season_id=119"},{"name":"场均罚球命中","type":"ftm_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ftm_avg&season_id=119"},{"name":"TS%","type":"ts","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ts&season_id=119"},{"name":"失误","type":"tos_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=tos_avg&season_id=119"},{"name":"失误率","type":"top","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=top&season_id=119"},{"name":"利用失误得分","type":"topts_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=topts_avg&season_id=119"},{"name":"内线得分","type":"ppts_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ppts_avg&season_id=119"},{"name":"内线命中率","type":"pfgp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=pfgp&season_id=119"},{"name":"二次进攻得分","type":"spts_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=spts_avg&season_id=119"},{"name":"二次进攻命中率","type":"sfgp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=sfgp&season_id=119"},{"name":"快攻得分","type":"qpts_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=qpts_avg&season_id=119"},{"name":"快攻命中率","type":"qfgp","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=qfgp&season_id=119"},{"name":"两双","type":"dds","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=dds&season_id=119"},{"name":"三双","type":"tds","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=tds&season_id=119"},{"name":"犯规","type":"fls_avg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=fls_avg&season_id=119"},{"name":"USG%","type":"usg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=usg&season_id=119"},{"name":"贡献值","type":"ws","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ws&season_id=119"},{"name":"进攻效率","type":"ortg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ortg&season_id=119"},{"name":"防守效率","type":"drtg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=drtg&season_id=119"},{"name":"净效率","type":"trtg","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=trtg&season_id=119"},{"name":"回合数","type":"pss","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=pss&season_id=119"},{"name":"技术犯规","type":"tfls","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=tfls&season_id=119"},{"name":"恶意犯规","type":"ffls","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=ffls&season_id=119"},{"name":"上场时间","type":"minutes","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=minutes&season_id=119"},{"name":"赛季最高分","type":"max_pts","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=max_pts&season_id=119"},{"name":"正负值","type":"bpm","url":"http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=bpm&season_id=119"}]}
     */

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
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * name : 得分
             * type : pts_avg
             * url : http://sport-data.dqdgame.com/sd/biz/data/person_ranking?version=0&platform=&type=pts_avg&season_id=119
             */

            private String name;
            private String type;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
