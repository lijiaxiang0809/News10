package com.teach.news10.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2019/7/16.
 */
public class DataCubaInfo {

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
                /**
                 * data : [{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1080","team_logo":"http://www.cuba.edu.cn/up/team/66/team_Logo66-L.jpg","team_name":"太原理工","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1074","team_logo":"http://www.cuba.edu.cn/up/team/70/team_Logo70-L.jpg","team_name":"新疆师范","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1087","team_logo":"http://www.cuba.edu.cn/up/team/119/team_Logo119-L.jpg","team_name":"内蒙古师范","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1082","team_logo":"http://www.cuba.edu.cn/up/team/60/team_Logo60-L.jpg","team_name":"宁夏大学","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1081","team_logo":"http://www.cuba.edu.cn/up/team/62/team_Logo62-L.jpg","team_name":"青海师范","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西北赛区-A组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1078","team_logo":"http://www.cuba.edu.cn/up/team/64/team_Logo64-L.jpg","team_name":"山西大学","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1084","team_logo":"http://www.cuba.edu.cn/up/team/58/team_Logo58-L.jpg","team_name":"河南理工","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1072","team_logo":"http://www.cuba.edu.cn/up/team/139/team_Logo139-L.jpg","team_name":"内蒙古农业","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1070","team_logo":"http://www.cuba.edu.cn/up/team/191/team_Logo191-L.jpg","team_name":"宁夏师范","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1069","team_logo":"http://www.cuba.edu.cn/up/team/190/team_Logo190-L.jpg","team_name":"呼和浩特民族","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西北赛区-B组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1068","team_logo":"http://www.cuba.edu.cn/up/team/162/team_Logo162-L.jpg","team_name":"西安交大","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1073","team_logo":"http://www.cuba.edu.cn/up/team/71/team_Logo71-L.jpg","team_name":"郑州大学","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1076","team_logo":"http://www.cuba.edu.cn/up/team/69/team_Logo69-L.jpg","team_name":"新疆大学","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1083","team_logo":"http://www.cuba.edu.cn/up/team/61/team_Logo61-L.jpg","team_name":"青海民族","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1071","team_logo":"http://www.cuba.edu.cn/up/team/137/team_Logo137-L.jpg","team_name":"西安工业","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西北赛区-C组","top":0},{"data":[{"gb":"6","matches_total":"4","rank":"1","streak":"7","team_id":"1077","team_logo":"http://www.cuba.edu.cn/up/team/65/team_Logo65-L.jpg","team_name":"石河子大学","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-6","matches_total":"4","rank":"2","streak":"7","team_id":"1075","team_logo":"http://www.cuba.edu.cn/up/team/67/team_Logo67-L.jpg","team_name":"西北工业","win_lost":"3-1","win_rate":"80.0%"},{"gb":"4","matches_total":"4","rank":"3","streak":"6","team_id":"1079","team_logo":"http://www.cuba.edu.cn/up/team/63/team_Logo63-L.jpg","team_name":"山西财经","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-4","matches_total":"4","rank":"4","streak":"6","team_id":"1085","team_logo":"http://www.cuba.edu.cn/up/team/57/team_Logo57-L.jpg","team_name":"河南大学","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1086","team_logo":"http://www.cuba.edu.cn/up/team/120/team_Logo120-L.jpg","team_name":"西北师范","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西北赛区-D组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1040","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHG3CAXUKAAAAV3yaZiYI581.jpg","team_name":"北京大学","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1036","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EC/ChM9m1yHHI2AZ2XuAAAelALOZCA983.jpg","team_name":"山东科技","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1107","team_logo":"http://www.cuba.edu.cn/up/team/176/team_Logo176-L.jpg","team_name":"北京体育","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1102","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EC/ChM9m1yHHNiAJp3sAAAPfUBTi7E333.jpg","team_name":"天津体育","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1104","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHG62AT1k9AAAoC6-lr8s486.jpg","team_name":"东北财经","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东北赛区-A组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1037","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHIDOAN7uvAABtUO5pQMM650.png","team_name":"清华大学","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1039","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EC/ChM9m1yHHs6AX1IsAAAzmONzFFU325.jpg","team_name":"吉林大学","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1033","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EC/ChM9m1yHHeuAI8XNAAAPNGgeHh4389.jpg","team_name":"哈工大","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1105","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHHvOAKxk9AAAz6fvCKHk403.jpg","team_name":"沈阳体育","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1101","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHHcCAThy9AAAqefCSrEA220.jpg","team_name":"长春师范","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东北赛区-B组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1032","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHH4GARIufAACgQ3mndxg189.png","team_name":"中国民航","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1100","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChM9m1yHGWCANeaAAAAbI5Rz5Yw556.jpg","team_name":"中国石油","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1109","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHGFqASOVTAAAjFcI1-f8316.jpg","team_name":"东北大学","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1108","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChM9m1yHGKSAMSsGAAAvHe2JeO4501.jpg","team_name":"哈师大","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1034","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHGPaABbX_AAAjn96PFWs548.jpg","team_name":"天津大学","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东北赛区-C组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1106","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHHjiACo16AAAuWyCOuvU425.jpg","team_name":"北京化工","win_lost":"4-0","win_rate":"100.0%"},{"gb":"22","matches_total":"4","rank":"2","streak":"6","team_id":"1038","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EC/ChM9m1yHH9iADMfoAAAkC4Cv2aQ755.jpg","team_name":"山东农业","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-22","matches_total":"4","rank":"3","streak":"6","team_id":"1103","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EC/ChM9m1yHHnyAKEtfAAAhI8k9epk799.jpg","team_name":"北京交大","win_lost":"2-2","win_rate":"50.0%"},{"gb":"8","matches_total":"4","rank":"4","streak":"5","team_id":"1035","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EB/ChOlBlyHHR6ARoLkAAAyAhii19k334.jpg","team_name":"河北工程","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-8","matches_total":"4","rank":"5","streak":"5","team_id":"1110","team_logo":"http://img1.dqdgame.com/fastdfs/M00/04/EC/ChM9m1yHHW-AceXFAAAtD7PzFAc925.jpg","team_name":"中国海洋","win_lost":"1-3","win_rate":"20.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东北赛区-D组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1049","team_logo":"http://www.cuba.edu.cn/up/team/98/team_Logo98-L.jpg","team_name":"中南大学","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1052","team_logo":"http://www.cuba.edu.cn/up/team/96/team_Logo96-L.jpg","team_name":"云南财经","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1057","team_logo":"http://www.cuba.edu.cn/up/team/89/team_Logo89-L.jpg","team_name":"湖北工业","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1055","team_logo":"http://www.cuba.edu.cn/up/team/91/team_Logo91-L.jpg","team_name":"华中师大","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1062","team_logo":"http://www.cuba.edu.cn/up/team/151/team_Logo151-L.jpg","team_name":"西藏民族","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西南赛区-A组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1056","team_logo":"http://www.cuba.edu.cn/up/team/90/team_Logo90-L.jpg","team_name":"湖南师大","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1064","team_logo":"http://www.cuba.edu.cn/up/team/168/team_Logo168-L.jpg","team_name":"华中科技","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1051","team_logo":"http://www.cuba.edu.cn/up/team/97/team_Logo97-L.jpg","team_name":"云南师大","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1050","team_logo":"http://www.cuba.edu.cn/up/team/99/team_Logo99-L.jpg","team_name":"重庆大学","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1059","team_logo":"http://www.cuba.edu.cn/up/team/87/team_Logo87-L.jpg","team_name":"贵州大学","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西南赛区-B组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1047","team_logo":"http://www.cuba.edu.cn/up/team/100/team_Logo100-L.jpg","team_name":"重庆文理","win_lost":"4-0","win_rate":"100.0%"},{"gb":"3","matches_total":"4","rank":"2","streak":"6","team_id":"1060","team_logo":"http://www.cuba.edu.cn/up/team/85/team_Logo85-L.jpg","team_name":"广西大学","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-3","matches_total":"4","rank":"3","streak":"6","team_id":"1053","team_logo":"http://www.cuba.edu.cn/up/team/93/team_Logo93-L.jpg","team_name":"武汉理工","win_lost":"2-2","win_rate":"50.0%"},{"gb":"4","matches_total":"4","rank":"4","streak":"5","team_id":"1067","team_logo":"http://www.cuba.edu.cn/up/team/188/team_Logo188-L.jpg","team_name":"湖南大学","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-4","matches_total":"4","rank":"5","streak":"5","team_id":"1054","team_logo":"http://www.cuba.edu.cn/up/team/95/team_Logo95-L.jpg","team_name":"西南交大","win_lost":"1-3","win_rate":"20.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西南赛区-C组","top":0},{"data":[{"gb":"14","matches_total":"4","rank":"1","streak":"7","team_id":"1058","team_logo":"http://www.cuba.edu.cn/up/team/86/team_Logo86-L.jpg","team_name":"广西师大","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-14","matches_total":"4","rank":"2","streak":"7","team_id":"1063","team_logo":"http://www.cuba.edu.cn/up/team/150/team_Logo150-L.jpg","team_name":"重庆师大","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1066","team_logo":"http://www.cuba.edu.cn/up/team/185/team_Logo185-L.jpg","team_name":"成都体育","win_lost":"2-2","win_rate":"50.0%"},{"gb":"17","matches_total":"4","rank":"4","streak":"5","team_id":"1061","team_logo":"http://www.cuba.edu.cn/up/team/154/team_Logo154-L.jpg","team_name":"电子科大","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-17","matches_total":"4","rank":"5","streak":"5","team_id":"1065","team_logo":"http://www.cuba.edu.cn/up/team/115/team_Logo115-L.jpg","team_name":"澳门大学","win_lost":"1-3","win_rate":"20.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"西南赛区-D组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1044","team_logo":"http://www.cuba.edu.cn/up/team/157/team_Logo157-L.jpg","team_name":"厦门大学","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1041","team_logo":"http://www.cuba.edu.cn/up/team/4/team_Logo4-L.jpg","team_name":"华东师范","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1094","team_logo":"http://www.cuba.edu.cn/up/team/11/team_Logo11-L.jpg","team_name":"宁波大学","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1042","team_logo":"http://www.cuba.edu.cn/up/team/125/team_Logo125-L.jpg","team_name":"浙江师范","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1089","team_logo":"http://www.cuba.edu.cn/up/team/13/team_Logo13-L.jpg","team_name":"上海工程技术","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东南赛区-A组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1098","team_logo":"http://www.cuba.edu.cn/up/team/5/team_Logo5-L.jpg","team_name":"华侨大学","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1048","team_logo":"http://www.cuba.edu.cn/up/team/124/team_Logo124-L.jpg","team_name":"上海交大","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1096","team_logo":"http://www.cuba.edu.cn/up/team/7/team_Logo7-L.jpg","team_name":"南昌大学","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1097","team_logo":"http://www.cuba.edu.cn/up/team/6/team_Logo6-L.jpg","team_name":"集美大学","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1099","team_logo":"http://www.cuba.edu.cn/up/team/183/team_Logo183-L.jpg","team_name":"海南热带海洋","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东南赛区-B组","top":0},{"data":[{"gb":"5","matches_total":"4","rank":"1","streak":"7","team_id":"1090","team_logo":"http://www.cuba.edu.cn/up/team/15/team_Logo15-L.jpg","team_name":"浙江大学","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-5","matches_total":"4","rank":"2","streak":"7","team_id":"1091","team_logo":"http://www.cuba.edu.cn/up/team/12/team_Logo12-L.jpg","team_name":"汕头大学","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1093","team_logo":"http://www.cuba.edu.cn/up/team/21/team_Logo21-L.jpg","team_name":"江西师范","win_lost":"2-2","win_rate":"50.0%"},{"gb":"3","matches_total":"4","rank":"4","streak":"5","team_id":"1045","team_logo":"http://www.cuba.edu.cn/up/team/3/team_Logo3-L.jpg","team_name":"华东交通","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-3","matches_total":"4","rank":"5","streak":"5","team_id":"1218","team_logo":"","team_name":"南京财经","win_lost":"1-3","win_rate":"20.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东南赛区-C组","top":0},{"data":[{"gb":"-","matches_total":"4","rank":"1","streak":"8","team_id":"1088","team_logo":"http://www.cuba.edu.cn/up/team/16/team_Logo16-L.jpg","team_name":"中国矿业","win_lost":"4-0","win_rate":"100.0%"},{"gb":"-","matches_total":"4","rank":"2","streak":"7","team_id":"1043","team_logo":"http://www.cuba.edu.cn/up/team/146/team_Logo146-L.jpg","team_name":"广东工业","win_lost":"3-1","win_rate":"80.0%"},{"gb":"-","matches_total":"4","rank":"3","streak":"6","team_id":"1092","team_logo":"http://www.cuba.edu.cn/up/team/9/team_Logo9-L.jpg","team_name":"南京理工","win_lost":"2-2","win_rate":"50.0%"},{"gb":"-","matches_total":"4","rank":"4","streak":"5","team_id":"1046","team_logo":"http://www.cuba.edu.cn/up/team/171/team_Logo171-L.jpg","team_name":"合肥师范","win_lost":"1-3","win_rate":"20.0%"},{"gb":"-","matches_total":"4","rank":"5","streak":"4","team_id":"1095","team_logo":"http://www.cuba.edu.cn/up/team/8/team_Logo8-L.jpg","team_name":"南京航空航天","win_lost":"0-4","win_rate":"0.0%"}],"end":0,"header":["胜-负","胜率","净胜分","积分"],"name":"东南赛区-D组","top":0}]
                 * name :
                 */

                private String name;
                private List<DataBeanX> data;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<DataBeanX> getData() {
                    return data;
                }

                public void setData(List<DataBeanX> data) {
                    this.data = data;
                }

                public static class DataBeanX {

                    private int end;
                    private String name;
                    private int top;
                    private List<DataBean> data;
                    private List<String> header;

                    public int getEnd() {
                        return end;
                    }

                    public void setEnd(int end) {
                        this.end = end;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
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

                        private String gb;
                        private String matches_total;
                        private String rank;
                        private String streak;
                        private String team_id;
                        private String team_logo;
                        private String team_name;
                        private String win_lost;
                        private String win_rate;

                        public String getGb() {
                            return gb;
                        }

                        public void setGb(String gb) {
                            this.gb = gb;
                        }

                        public String getMatches_total() {
                            return matches_total;
                        }

                        public void setMatches_total(String matches_total) {
                            this.matches_total = matches_total;
                        }

                        public String getRank() {
                            return rank;
                        }

                        public void setRank(String rank) {
                            this.rank = rank;
                        }

                        public String getStreak() {
                            return streak;
                        }

                        public void setStreak(String streak) {
                            this.streak = streak;
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

                        public String getWin_lost() {
                            return win_lost;
                        }

                        public void setWin_lost(String win_lost) {
                            this.win_lost = win_lost;
                        }

                        public String getWin_rate() {
                            return win_rate;
                        }

                        public void setWin_rate(String win_rate) {
                            this.win_rate = win_rate;
                        }
                    }
                }
            }
        }
    }
}
