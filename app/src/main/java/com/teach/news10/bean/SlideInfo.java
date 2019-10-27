package com.teach.news10.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 任小龙 on 2019/4/20.
 */
public class SlideInfo {

    @SerializedName("config")
    private ConfigBean _$Config191; // FIXME check this code
    private EmojiBean emoji;
    private String updated_at;
    private List<ModulesBean> modules;
    private List<?> localpush;
    private List<TemplateBean> template;

    public ConfigBean get_$Config191() {
        return _$Config191;
    }

    public void set_$Config191(ConfigBean _$Config191) {
        this._$Config191 = _$Config191;
    }

    public EmojiBean getEmoji() {
        return emoji;
    }

    public void setEmoji(EmojiBean emoji) {
        this.emoji = emoji;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<ModulesBean> getModules() {
        return modules;
    }

    public void setModules(List<ModulesBean> modules) {
        this.modules = modules;
    }

    public List<?> getLocalpush() {
        return localpush;
    }

    public void setLocalpush(List<?> localpush) {
        this.localpush = localpush;
    }

    public List<TemplateBean> getTemplate() {
        return template;
    }

    public void setTemplate(List<TemplateBean> template) {
        this.template = template;
    }

    public static class ConfigBean {
        @SerializedName("gif_max_size")
        private int _$Gif_max_size149; // FIXME check this code
        private int max_upload_files;
        private int match_refresh_interval;
        private boolean qr_login;
        private int max_upload_files_size;
        private String sidebar_bg_image;
        private boolean article_preload;
        private Object my_favorite;
        private int close_flutter;
        private boolean in_player;
        private int chat_connect_timeout;
        private PlayerBean player;
        private int reviewing;
        private StoryBean story;
        private LaunchImageBean launch_image;
        private ShareBean share;
        private HotBean hot;
        private int dns;
        private int notify_rate;
        private String default_scheme;
        private int show_guide_img;
        private int show_gamecenter;
        private String gamecenter_url;
        private int show_video_upload;
        private int show_video_parse;
        private int show_score;
        private String score_update_time;
        private int show_minitop_video_upload;
        private int show_minitop_video_parse;
        private String video_parse_text;
        private String video_parse_link;
        private int free_flow_open;
        private int bullet_screen_num;
        private int show_bullet_screen;
        private int start_image_duration;
        private String dqh_application_scheme;
        private Object footer_icons;
        private int socket_open;
        private int socket_refresh_interval;
        private int match_sign;
        private int close_grow_io;
        private ChatImageBean chat_image;
        private List<String> url_black_list;

        public int get_$Gif_max_size149() {
            return _$Gif_max_size149;
        }

        public void set_$Gif_max_size149(int _$Gif_max_size149) {
            this._$Gif_max_size149 = _$Gif_max_size149;
        }

        public int getMax_upload_files() {
            return max_upload_files;
        }

        public void setMax_upload_files(int max_upload_files) {
            this.max_upload_files = max_upload_files;
        }

        public int getMatch_refresh_interval() {
            return match_refresh_interval;
        }

        public void setMatch_refresh_interval(int match_refresh_interval) {
            this.match_refresh_interval = match_refresh_interval;
        }

        public boolean isQr_login() {
            return qr_login;
        }

        public void setQr_login(boolean qr_login) {
            this.qr_login = qr_login;
        }

        public int getMax_upload_files_size() {
            return max_upload_files_size;
        }

        public void setMax_upload_files_size(int max_upload_files_size) {
            this.max_upload_files_size = max_upload_files_size;
        }

        public String getSidebar_bg_image() {
            return sidebar_bg_image;
        }

        public void setSidebar_bg_image(String sidebar_bg_image) {
            this.sidebar_bg_image = sidebar_bg_image;
        }

        public boolean isArticle_preload() {
            return article_preload;
        }

        public void setArticle_preload(boolean article_preload) {
            this.article_preload = article_preload;
        }

        public Object getMy_favorite() {
            return my_favorite;
        }

        public void setMy_favorite(Object my_favorite) {
            this.my_favorite = my_favorite;
        }

        public int getClose_flutter() {
            return close_flutter;
        }

        public void setClose_flutter(int close_flutter) {
            this.close_flutter = close_flutter;
        }

        public boolean isIn_player() {
            return in_player;
        }

        public void setIn_player(boolean in_player) {
            this.in_player = in_player;
        }

        public int getChat_connect_timeout() {
            return chat_connect_timeout;
        }

        public void setChat_connect_timeout(int chat_connect_timeout) {
            this.chat_connect_timeout = chat_connect_timeout;
        }

        public PlayerBean getPlayer() {
            return player;
        }

        public void setPlayer(PlayerBean player) {
            this.player = player;
        }

        public int getReviewing() {
            return reviewing;
        }

        public void setReviewing(int reviewing) {
            this.reviewing = reviewing;
        }

        public StoryBean getStory() {
            return story;
        }

        public void setStory(StoryBean story) {
            this.story = story;
        }

        public LaunchImageBean getLaunch_image() {
            return launch_image;
        }

        public void setLaunch_image(LaunchImageBean launch_image) {
            this.launch_image = launch_image;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public HotBean getHot() {
            return hot;
        }

        public void setHot(HotBean hot) {
            this.hot = hot;
        }

        public int getDns() {
            return dns;
        }

        public void setDns(int dns) {
            this.dns = dns;
        }

        public int getNotify_rate() {
            return notify_rate;
        }

        public void setNotify_rate(int notify_rate) {
            this.notify_rate = notify_rate;
        }

        public String getDefault_scheme() {
            return default_scheme;
        }

        public void setDefault_scheme(String default_scheme) {
            this.default_scheme = default_scheme;
        }

        public int getShow_guide_img() {
            return show_guide_img;
        }

        public void setShow_guide_img(int show_guide_img) {
            this.show_guide_img = show_guide_img;
        }

        public int getShow_gamecenter() {
            return show_gamecenter;
        }

        public void setShow_gamecenter(int show_gamecenter) {
            this.show_gamecenter = show_gamecenter;
        }

        public String getGamecenter_url() {
            return gamecenter_url;
        }

        public void setGamecenter_url(String gamecenter_url) {
            this.gamecenter_url = gamecenter_url;
        }

        public int getShow_video_upload() {
            return show_video_upload;
        }

        public void setShow_video_upload(int show_video_upload) {
            this.show_video_upload = show_video_upload;
        }

        public int getShow_video_parse() {
            return show_video_parse;
        }

        public void setShow_video_parse(int show_video_parse) {
            this.show_video_parse = show_video_parse;
        }

        public int getShow_score() {
            return show_score;
        }

        public void setShow_score(int show_score) {
            this.show_score = show_score;
        }

        public String getScore_update_time() {
            return score_update_time;
        }

        public void setScore_update_time(String score_update_time) {
            this.score_update_time = score_update_time;
        }

        public int getShow_minitop_video_upload() {
            return show_minitop_video_upload;
        }

        public void setShow_minitop_video_upload(int show_minitop_video_upload) {
            this.show_minitop_video_upload = show_minitop_video_upload;
        }

        public int getShow_minitop_video_parse() {
            return show_minitop_video_parse;
        }

        public void setShow_minitop_video_parse(int show_minitop_video_parse) {
            this.show_minitop_video_parse = show_minitop_video_parse;
        }

        public String getVideo_parse_text() {
            return video_parse_text;
        }

        public void setVideo_parse_text(String video_parse_text) {
            this.video_parse_text = video_parse_text;
        }

        public String getVideo_parse_link() {
            return video_parse_link;
        }

        public void setVideo_parse_link(String video_parse_link) {
            this.video_parse_link = video_parse_link;
        }

        public int getFree_flow_open() {
            return free_flow_open;
        }

        public void setFree_flow_open(int free_flow_open) {
            this.free_flow_open = free_flow_open;
        }

        public int getBullet_screen_num() {
            return bullet_screen_num;
        }

        public void setBullet_screen_num(int bullet_screen_num) {
            this.bullet_screen_num = bullet_screen_num;
        }

        public int getShow_bullet_screen() {
            return show_bullet_screen;
        }

        public void setShow_bullet_screen(int show_bullet_screen) {
            this.show_bullet_screen = show_bullet_screen;
        }

        public int getStart_image_duration() {
            return start_image_duration;
        }

        public void setStart_image_duration(int start_image_duration) {
            this.start_image_duration = start_image_duration;
        }

        public String getDqh_application_scheme() {
            return dqh_application_scheme;
        }

        public void setDqh_application_scheme(String dqh_application_scheme) {
            this.dqh_application_scheme = dqh_application_scheme;
        }

        public Object getFooter_icons() {
            return footer_icons;
        }

        public void setFooter_icons(Object footer_icons) {
            this.footer_icons = footer_icons;
        }

        public int getSocket_open() {
            return socket_open;
        }

        public void setSocket_open(int socket_open) {
            this.socket_open = socket_open;
        }

        public int getSocket_refresh_interval() {
            return socket_refresh_interval;
        }

        public void setSocket_refresh_interval(int socket_refresh_interval) {
            this.socket_refresh_interval = socket_refresh_interval;
        }

        public int getMatch_sign() {
            return match_sign;
        }

        public void setMatch_sign(int match_sign) {
            this.match_sign = match_sign;
        }

        public int getClose_grow_io() {
            return close_grow_io;
        }

        public void setClose_grow_io(int close_grow_io) {
            this.close_grow_io = close_grow_io;
        }

        public ChatImageBean getChat_image() {
            return chat_image;
        }

        public void setChat_image(ChatImageBean chat_image) {
            this.chat_image = chat_image;
        }

        public List<String> getUrl_black_list() {
            return url_black_list;
        }

        public void setUrl_black_list(List<String> url_black_list) {
            this.url_black_list = url_black_list;
        }

        public static class PlayerBean {
            @SerializedName("video")
            private String _$Video249; // FIXME check this code
            private String ad;
            private String live;
            private String url;
            private String key;

            public String get_$Video249() {
                return _$Video249;
            }

            public void set_$Video249(String _$Video249) {
                this._$Video249 = _$Video249;
            }

            public String getAd() {
                return ad;
            }

            public void setAd(String ad) {
                this.ad = ad;
            }

            public String getLive() {
                return live;
            }

            public void setLive(String live) {
                this.live = live;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }

        public static class StoryBean {
            /**
             * text : 我的上篮故事
             * url : shanglan://news/56341
             * display : false
             */

            private String text;
            private String url;
            private boolean display;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isDisplay() {
                return display;
            }

            public void setDisplay(boolean display) {
                this.display = display;
            }
        }

        public static class LaunchImageBean {
            /**
             * img_url :
             * redirect_url :
             * duration : 3
             */

            private String img_url;
            private String redirect_url;
            private int duration;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getRedirect_url() {
                return redirect_url;
            }

            public void setRedirect_url(String redirect_url) {
                this.redirect_url = redirect_url;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }
        }

        public static class ShareBean {
            /**
             * mine : 我的这句话说的真精辟，尔等速来围观！
             * user : 我发现了一条神评论，推荐给你们膜拜下
             */

            private String mine;
            private String user;

            public String getMine() {
                return mine;
            }

            public void setMine(String mine) {
                this.mine = mine;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }
        }

        public static class HotBean {
            /**
             * count : 6
             * show_time_start : 8
             * show_time_end : 24
             * gap_time : 60
             */

            private String count;
            private String show_time_start;
            private String show_time_end;
            private int gap_time;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getShow_time_start() {
                return show_time_start;
            }

            public void setShow_time_start(String show_time_start) {
                this.show_time_start = show_time_start;
            }

            public String getShow_time_end() {
                return show_time_end;
            }

            public void setShow_time_end(String show_time_end) {
                this.show_time_end = show_time_end;
            }

            public int getGap_time() {
                return gap_time;
            }

            public void setGap_time(int gap_time) {
                this.gap_time = gap_time;
            }
        }

        public static class ChatImageBean {
            /**
             * img_url :
             * h : 200
             * w : 300
             * into_type : 0
             * redirect_url :
             */

            private String img_url;
            private int h;
            private int w;
            private int into_type;
            private String redirect_url;

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public int getH() {
                return h;
            }

            public void setH(int h) {
                this.h = h;
            }

            public int getW() {
                return w;
            }

            public void setW(int w) {
                this.w = w;
            }

            public int getInto_type() {
                return into_type;
            }

            public void setInto_type(int into_type) {
                this.into_type = into_type;
            }

            public String getRedirect_url() {
                return redirect_url;
            }

            public void setRedirect_url(String redirect_url) {
                this.redirect_url = redirect_url;
            }
        }
    }

    public static class EmojiBean {
        /**
         * version : 1537962652
         * emojis : [{"id":40,"name":"上篮","ecount":79,"icon":"https://img.dqdgame.com/fastdfs/M00/00/0A/ChM9m1vBvb6APIsrAABCm00qvII057.png","pkg":"https://img1.dqdgame.com/fastdfs/M00/00/0A/ChOlBlvBvb6AUmjdAA1zKxyCQ2Q162.zip","note":"","status":true,"deleted_at":null,"version":1539423682,"sort":3,"type":0,"scenario":0}]
         * chat : []
         */

        private String version;
        private List<EmojisBean> emojis;
        private List<?> chat;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<EmojisBean> getEmojis() {
            return emojis;
        }

        public void setEmojis(List<EmojisBean> emojis) {
            this.emojis = emojis;
        }

        public List<?> getChat() {
            return chat;
        }

        public void setChat(List<?> chat) {
            this.chat = chat;
        }

        public static class EmojisBean {
            /**
             * id : 40
             * name : 上篮
             * ecount : 79
             * icon : https://img.dqdgame.com/fastdfs/M00/00/0A/ChM9m1vBvb6APIsrAABCm00qvII057.png
             * pkg : https://img1.dqdgame.com/fastdfs/M00/00/0A/ChOlBlvBvb6AUmjdAA1zKxyCQ2Q162.zip
             * note :
             * status : true
             * deleted_at : null
             * version : 1539423682
             * sort : 3
             * type : 0
             * scenario : 0
             */

            private int id;
            private String name;
            private int ecount;
            private String icon;
            private String pkg;
            private String note;
            private boolean status;
            private Object deleted_at;
            private int version;
            private int sort;
            private int type;
            private int scenario;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getEcount() {
                return ecount;
            }

            public void setEcount(int ecount) {
                this.ecount = ecount;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getPkg() {
                return pkg;
            }

            public void setPkg(String pkg) {
                this.pkg = pkg;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }

            public Object getDeleted_at() {
                return deleted_at;
            }

            public void setDeleted_at(Object deleted_at) {
                this.deleted_at = deleted_at;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getScenario() {
                return scenario;
            }

            public void setScenario(int scenario) {
                this.scenario = scenario;
            }
        }
    }

    public static class ModulesBean {

        private int id;
        private String name;
        private String image;
        private String scheme;
        private int firstTip;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public int getFirstTip() {
            return firstTip;
        }

        public void setFirstTip(int firstTip) {
            this.firstTip = firstTip;
        }
    }

    public static class TemplateBean {
        /**
         * name : all
         * url : https://static1.dqdgame.com/b-node/public/news.201904161738.zip
         */

        private String name;
        private String url;

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
    }
}
