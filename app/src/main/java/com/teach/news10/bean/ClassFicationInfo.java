package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/12.
 */
public class ClassFicationInfo {
    /**
     * id : 99
     * label : 专题
     * prev : null
     * next : null
     * articles : [{"id":38,"title":"2011年西部半决赛：诺天王勇斗黑曼巴，禅师时代画上句号","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/25/640x400/-/-/rB8AdVzX1JeAMobPAAEO0ER3bN0718.jpg","published_at":"2019-05-12 21:08:38","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/38"},{"id":27,"title":"NBA明日看点：猛龙76人迎来最后一战，掘金开拓者抢七决斗","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/25/640x400/-/-/rB8AGFzXp_2ABWhmAAXqWIns93w186.png","published_at":"2023-05-11 20:15:12","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/27"},{"id":15,"title":"上篮问答：火箭休赛期应该做怎样的阵容调整？","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/25/640x400/-/-/rB8AdVzX3iiAFMzqAAUHCDfyURs917.png","published_at":"2024-05-10 17:45:45","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/15"},{"id":18,"title":"每日篮球教你一招：快攻三打二跑位教学","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/23/640x400/-/-/rB8AdVzW8AqAd9JqAARxrO8Lfeg039.png","published_at":"2019-05-12 16:00:20","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/18"},{"id":16,"title":"历史上的今天：J博士总决赛上演\u201c世纪拉杆\u201d，成为永恒经典","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/25/640x400/-/-/rB8AGFzXtEaAMNEHAAMxwvzJPEU312.jpg","published_at":"2019-05-12 13:54:28","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/16"},{"id":17,"title":"睡前一读：曾经第六人到当今超巨，从头开始对哈登不算什么","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/00/75/640x400/-/-/rB8AGFy4iNyAfwE-AABg8S8CBUA075.jpg","published_at":"2019-05-12 07:05:53","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/17"},{"id":8,"title":"勇士5年4次淘汰火箭，连续5年挺进西决，库里末节狂飙23分","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/20/640x400/-/-/rB8AGFzWRWCASR69AAMLYyOwAbE373.png","published_at":"2019-05-12 07:00:50","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/8"},{"id":23,"title":"火勇G6：这世上最伟大的天赋，就是一颗总冠军的心","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/22/640x400/-/-/rB8AdVzWacGAbfttAAELDXu8cJY384.jpg","published_at":"2019-05-11 20:06:02","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/23"},{"id":25,"title":"NBA今日五佳球：保罗连线卡佩拉，克莱嗜血三分杀人诛心","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/21/640x400/-/-/rB8AGFzWWh6AHEYMAAL-Dw4ybKk828.jpg","published_at":"2019-05-11 14:44:22","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/25"},{"id":9,"title":"NBA今日最佳球员：库里下半场狂砍33分，带队挺进西决","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/21/640x400/-/-/rB8AdVzWXQOATsHQAAK2-t5pdyE177.png","published_at":"2019-05-11 13:35:42","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/9"},{"id":10,"title":"球色怡人：红遍亚洲的最美混血湖人球迷\u2014\u2014莉亚-迪桑","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/1C/640x400/-/-/rB8AdVzVNYuAJkCvAACts_uPkf8738.jpg","published_at":"2019-05-11 07:05:18","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/10"},{"id":41,"title":"Inside the NBA：杜兰特受伤后，勇士已没有机会战胜火箭？","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/19/640x400/-/-/rB8AdVzT9IKAXkZWAAI6vYSj41A654.jpg","published_at":"2019-05-10 09:00:12","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/41"},{"id":24,"title":"凯尔特人赛季总结：动荡不安的赛季与少年欧文之烦恼","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/18/640x400/-/-/rB8AGFzT2P2Abhz5AACtsLlbb-s026.jpg","published_at":"2019-05-10 07:50:35","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/24"},{"id":39,"title":"上篮小测试：火勇大战激战正酣，两队恩怨史你了解多少呢？","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/1C/640x400/-/-/rB8AGFzVNG-AHKi-ABCb1f7CwhU793.png","published_at":"2019-05-10 06:55:16","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/39"},{"id":12,"title":"三大品牌宣布重回篮坛！他们能成功撼动球鞋市场吗？","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/14/640x400/-/-/rB8AdVzSkjaAeUb3AAJeFk8nHnM517.png","published_at":"2019-05-08 17:37:53","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/12"},{"id":30,"title":"走近NBA：系列赛最关键的比赛，天王山之战的来历","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/00/5B/640x400/-/-/rB8AGFyxtFWAe87HAAM2RuqULJo452.png","published_at":"2019-05-07 17:50:31","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/30"},{"id":22,"title":"上篮战术板：火勇大战比胜负更有趣的事儿\u2014\u2014哈登出题勇士解","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/0C/640x400/-/-/rB8AdVzPusSAQYB-AAEk3hn78P0551.jpg","published_at":"2019-05-06 19:47:48","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/22"},{"id":19,"title":"凯文-杜兰特，最终会在NBA得分榜上占据一个什么位置？","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/05/640x400/-/-/rB8AGFzOjtaAWN4oAAEmlgIbFok621.jpg","published_at":"2019-05-05 21:47:48","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/19"},{"id":40,"title":"费根专栏：面对勇士，火箭应该做出怎样的改变？","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/03/D6/640x400/-/-/rB8AGFzNPUKATSaOAAXzPZ58L6w196.png","published_at":"2019-05-04 20:13:51","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/40"},{"id":11,"title":"易建联37分，广东4-0横扫新疆成就九冠王，阿不都18分","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/03/D2/640x400/-/-/rB8AGFzMS6eAKnp4AAQMZIOuuqE703.png","published_at":"2019-05-04 07:11:23","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/11"},{"id":28,"title":"冰城球迷热情高涨！中国三人篮球擂台赛东北分区赛圆满落幕","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/01/E5/640x400/-/-/rB8AGFzG4ymAEvkxAAR8eaix4QI844.jpg","published_at":"2019-04-29 19:43:04","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/28"},{"id":34,"title":"奥尼尔五大囧：大帝跨栏式飞扑防守，内皮尔庆祝肘击误伤队友","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/00/98/640x400/-/-/rB8AGFzC0BKAf5itAAKchCdanZE927.png","published_at":"2019-04-26 17:35:53","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/34"},{"id":33,"title":"奇葩说：哈登竟然造成球迷防守犯规，心无杂念的佛祖又回来了","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/00/86/640x400/-/-/rB8AdVy9pKCAHUb0AACy7gfan4U867.jpg","published_at":"2019-04-23 07:55:26","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/33"},{"id":26,"title":"那一记该死的封闭针，毁了麦迪的整个职业生涯","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/03/23/640x400/-/-/ChM9m1wjjzyAdUPFAACj5KMCnz8315.jpg","published_at":"2019-04-11 13:12:29","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/26"},{"id":14,"title":"李春江晒领带：依然是生死之战","description":"今晚19点35分，广厦将在主场迎战新疆，目前广厦大比分1-2落后。","thumb":"https://img1.dqdgame.com/fastdfs/M00/00/23/640x400/-/-/rB8AdVyjGimAZCcBAAU1SeYb1Rg627.png","published_at":"2019-04-02 16:15:59","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/14"},{"id":31,"title":"NBA水浒108将第1位\u2014\u2014\u201c及时雨\u201d姚明","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/05/02/640x400/-/-/ChM9m1yMwamAMqYJAAEbj5KwO8k344.jpg","published_at":"2019-03-16 17:28:12","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/31"},{"id":37,"title":"美媒评NBA历史主教练排名：第一位\u2014\u2014菲尔-杰克逊","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/F2/640x400/-/-/ChOlBlyJB_GAS9tOAADE5SlDZ1Y480.jpg","published_at":"2019-03-13 21:36:25","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/37"},{"id":13,"title":"不说篮球：游泳世界冠军宁泽涛在26岁生日当天宣布退役","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/D1/640x400/-/-/ChM9m1x_emyAWu5ZAAUWV5QRv74524.png","published_at":"2019-03-06 15:45:21","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/13"},{"id":36,"title":"2013总决赛回忆录：时代转折点上的视觉盛宴","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/23/640x400/-/-/ChOlBlxeeBGAfGiBAAr0mjAR95c022.png","published_at":"2019-03-01 09:30:08","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/36"},{"id":35,"title":"上篮单挑之王：乔丹力压科比夺魁，麦迪AI分列三四名","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/48/640x400/-/-/ChM9m1xnuomATORGAA0lPV9JTtQ587.png","published_at":"2019-02-18 08:25:13","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/35"},{"id":32,"title":"NBA历年MVP和最佳阵容回顾之2018：勇士4年3冠，哈登MVP","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/49/640x400/-/-/ChM9m1xn5TmAdWkEAADrAkY0X54324.jpg","published_at":"2019-02-16 22:01:12","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/32"},{"id":29,"title":"【2K评队史最佳阵容】洛杉矶湖人：魔韦科勾鲨领衔","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/03/B1/640x400/-/-/ChM9m1xBiNCAP8HbAAgiZbh9FVw057.png","published_at":"2019-01-18 22:04:58","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/29"},{"id":21,"title":"ESPN百大球星第1位：迈克尔-不好意思就是我-乔丹","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/03/9E/640x400/-/-/ChOlBlw9fgiAK7OPAAYGABnm0n0546.png","published_at":"2019-01-16 03:35:36","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/21"},{"id":20,"title":"美媒重排选秀之2014年：约基奇超级逆袭，维金斯力压恩比德","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/02/D7/640x400/-/-/ChM9m1wV69KAA2ZaAAC08LZvwOc976.jpg","published_at":"2018-12-16 17:18:01","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/20"},{"id":6,"title":"外国也有\u201c大喷子\u201d：德罗赞抗詹终成功，本场MVP是隆多？","description":"","thumb":"https://img1.dqdgame.com/fastdfs/M00/00/54/640x400/-/-/ChM9m1vVgfKAFESgAAFgcU5wTfo178.jpg","published_at":"2018-10-28 19:00:25","channel":"ccover","api":"http://bkbapi.dqdgame.com/old/columns/6"}]
     */

    private int id;
    private String label;
    private Object prev;
    private Object next;
    private List<ArticlesBean> articles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getPrev() {
        return prev;
    }

    public void setPrev(Object prev) {
        this.prev = prev;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * id : 38
         * title : 2011年西部半决赛：诺天王勇斗黑曼巴，禅师时代画上句号
         * description :
         * thumb : https://img1.dqdgame.com/fastdfs/M00/04/25/640x400/-/-/rB8AdVzX1JeAMobPAAEO0ER3bN0718.jpg
         * published_at : 2019-05-12 21:08:38
         * channel : ccover
         * api : http://bkbapi.dqdgame.com/old/columns/38
         */

        private int id;
        private String title;
        private String description;
        private String thumb;
        private String published_at;
        private String channel;
        private String api;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }
    }
}
