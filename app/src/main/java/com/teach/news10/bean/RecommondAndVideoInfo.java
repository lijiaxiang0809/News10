package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/5/7.
 */
public class RecommondAndVideoInfo {

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private TopicBannerBean topic_banner;
        private String next;
        private List<FeedsListBean> feeds_list;

        public TopicBannerBean getTopic_banner() {
            return topic_banner;
        }

        public void setTopic_banner(TopicBannerBean topic_banner) {
            this.topic_banner = topic_banner;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public List<FeedsListBean> getFeeds_list() {
            return feeds_list;
        }

        public void setFeeds_list(List<FeedsListBean> feeds_list) {
            this.feeds_list = feeds_list;
        }

        public static class TopicBannerBean {

            private String icon;
            private String title;
            private List<TopicListBean> topic_list;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<TopicListBean> getTopic_list() {
                return topic_list;
            }

            public void setTopic_list(List<TopicListBean> topic_list) {
                this.topic_list = topic_list;
            }

            public static class TopicListBean {

                private String title;
                private String subtitle;
                private String scheme;
                private String bg_url;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public void setSubtitle(String subtitle) {
                    this.subtitle = subtitle;
                }

                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }

                public String getBg_url() {
                    return bg_url;
                }

                public void setBg_url(String bg_url) {
                    this.bg_url = bg_url;
                }
            }
        }

        public static class FeedsListBean {
            public TopicBannerBean topic_banner;
            private String id;
            private String content;
            private String thread_type;
            private String created_at;
            private String like_num;
            private String comment_num;
            private String share_num;
            private String view_num;
            private String scheme;
            private int attachments_total;
            private boolean self_only;
            private String share_url;
            private String share_title;
            private TopicBean topic;
            private AuthorBean author;
            private String user_id;
            private VideoInfoBean video_info;
            private List<AttachmentsBean> attachments;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getThread_type() {
                return thread_type;
            }

            public void setThread_type(String thread_type) {
                this.thread_type = thread_type;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getLike_num() {
                return like_num;
            }

            public void setLike_num(String like_num) {
                this.like_num = like_num;
            }

            public String getComment_num() {
                return comment_num;
            }

            public void setComment_num(String comment_num) {
                this.comment_num = comment_num;
            }

            public String getShare_num() {
                return share_num;
            }

            public void setShare_num(String share_num) {
                this.share_num = share_num;
            }

            public String getView_num() {
                return view_num;
            }

            public void setView_num(String view_num) {
                this.view_num = view_num;
            }

            public String getScheme() {
                return scheme;
            }

            public void setScheme(String scheme) {
                this.scheme = scheme;
            }

            public int getAttachments_total() {
                return attachments_total;
            }

            public void setAttachments_total(int attachments_total) {
                this.attachments_total = attachments_total;
            }

            public boolean isSelf_only() {
                return self_only;
            }

            public void setSelf_only(boolean self_only) {
                this.self_only = self_only;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public String getShare_title() {
                return share_title;
            }

            public void setShare_title(String share_title) {
                this.share_title = share_title;
            }

            public TopicBean getTopic() {
                return topic;
            }

            public void setTopic(TopicBean topic) {
                this.topic = topic;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public VideoInfoBean getVideo_info() {
                return video_info;
            }

            public void setVideo_info(VideoInfoBean video_info) {
                this.video_info = video_info;
            }

            public List<AttachmentsBean> getAttachments() {
                return attachments;
            }

            public void setAttachments(List<AttachmentsBean> attachments) {
                this.attachments = attachments;
            }

            public static class TopicBean {

                private String id;
                private String content;
                private String scheme;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }
            }

            public static class AuthorBean {

                private int id;
                private String username;
                private String avatar;
                private int team_id;
                private int is_admin;
                private int follow_status;
                private String medal_url;
                private String team_icon;
                private List<?> personal_pendant;
                private List<PendantBean> pendant;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getTeam_id() {
                    return team_id;
                }

                public void setTeam_id(int team_id) {
                    this.team_id = team_id;
                }

                public int getIs_admin() {
                    return is_admin;
                }

                public void setIs_admin(int is_admin) {
                    this.is_admin = is_admin;
                }

                public int getFollow_status() {
                    return follow_status;
                }

                public void setFollow_status(int follow_status) {
                    this.follow_status = follow_status;
                }

                public String getMedal_url() {
                    return medal_url;
                }

                public void setMedal_url(String medal_url) {
                    this.medal_url = medal_url;
                }

                public String getTeam_icon() {
                    return team_icon;
                }

                public void setTeam_icon(String team_icon) {
                    this.team_icon = team_icon;
                }

                public List<?> getPersonal_pendant() {
                    return personal_pendant;
                }

                public void setPersonal_pendant(List<?> personal_pendant) {
                    this.personal_pendant = personal_pendant;
                }

                public List<PendantBean> getPendant() {
                    return pendant;
                }

                public void setPendant(List<PendantBean> pendant) {
                    this.pendant = pendant;
                }

                public static class PendantBean {

                    private String url;
                    private String title;
                    private String type;
                    private int width;
                    private int height;
                    private String scheme;
                    private int level;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getScheme() {
                        return scheme;
                    }

                    public void setScheme(String scheme) {
                        this.scheme = scheme;
                    }

                    public int getLevel() {
                        return level;
                    }

                    public void setLevel(int level) {
                        this.level = level;
                    }
                }
            }

            public static class VideoInfoBean {

                private String url;
                private String height;
                private String width;
                private String size;
                private Object mime;
                private String thumb;
                private String length;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public Object getMime() {
                    return mime;
                }

                public void setMime(Object mime) {
                    this.mime = mime;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getLength() {
                    return length;
                }

                public void setLength(String length) {
                    this.length = length;
                }
            }

            public static class AttachmentsBean {

                private String height;
                private String width;
                private String size;
                private String mime;
                private String thumb;
                private String large;
                private String url;

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getMime() {
                    return mime;
                }

                public void setMime(String mime) {
                    this.mime = mime;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
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
}
