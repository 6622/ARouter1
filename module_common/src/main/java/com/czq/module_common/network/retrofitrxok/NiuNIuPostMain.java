package com.czq.module_common.network.retrofitrxok;


import com.czq.module_common.zhujie.ViewInject;

/**
 * Created by chen on 2016/8/21.
 * 参数提交模型
 */
public class NiuNIuPostMain {
    private String token = "";

    @ViewInject(key = "wx_username")
    private String username = "";

    @ViewInject(key = "wx_uid")
    private String unionid = "";

    @ViewInject(key = "sid")
    private String sid = "";

    @ViewInject(key = "wx_img")
    private String img = "";



    private String shareurl="";

    //创建房间
    private String people_number="6";
    private String games_number="10";
    private String special_patterns="";
    private String banned_from="0";
    private String go_shares="0";
    private String banker_gambling_game_reg="0";
    //加入房间
    private String room_id="";


    private String sex = "";
    private String tel = "";
    private String rid = "";
    private String msg = "";
    private String type = "";
    private String u_num = "";
    private String card_list = "";
    private String room_type = "";
    private String double_point = "";
    private String amount = "";

    private String dealer_mode ="0";
    private String special_mode ="1";
    private String double_card ="0";
    private String point_mul = "1";
    private String player_num = "";
    private String color_num = "4";
    private String games_num = "10";
    private String double_a = "0";
    private String card_num = "0";
    private String change = "";
    private String timeout = "";
    private String pid = "";
    private String uid = "";
    private String captcha = "";


    private int music =0;
    private int sound =0;
    private String language ="";
    private long timestr =0;

    //支付提交的参数
    private String pay_type ="2";
    private String mod ="diamond";
    private String list ="0";
    //代理
    private String agent_admin="";



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDouble_card() {
        return double_card;
    }

    public void setDouble_card(String double_card) {
        this.double_card = double_card;
    }

    public String getCard_list() {
        return card_list;
    }

    public void setCard_list(String card_list) {
        this.card_list = card_list;
    }


    public String getU_num() {
        return u_num;
    }

    public void setU_num(String u_num) {
        this.u_num = u_num;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getDouble_point() {
        return double_point;
    }

    public void setDouble_point(String double_point) {
        this.double_point = double_point;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getDealer_mode() {
        return dealer_mode;
    }

    public void setDealer_mode(String dealer_mode) {
        this.dealer_mode = dealer_mode;
    }

    public String getSpecial_mode() {
        return special_mode;
    }

    public void setSpecial_mode(String special_mode) {
        this.special_mode = special_mode;
    }

    public String getPoint_mul() {
        return point_mul;
    }

    public void setPoint_mul(String point_mul) {
        this.point_mul = point_mul;
    }

    public String getPlayer_num() {
        return player_num;
    }

    public void setPlayer_num(String player_num) {
        this.player_num = player_num;
    }

    public String getColor_num() {
        return color_num;
    }

    public void setColor_num(String color_num) {
        this.color_num = color_num;
    }

    public String getGames_num() {
        return games_num;
    }

    public void setGames_num(String games_num) {
        this.games_num = games_num;
    }


    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getDouble_a() {
        return double_a;
    }

    public void setDouble_a(String double_a) {
        this.double_a = double_a;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public long getTimestr() {
        return timestr;
    }

    public void setTimestr(long timestr) {
        this.timestr = timestr;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getAgent_admin() {
        return agent_admin;
    }

    public void setAgent_admin(String agent_admin) {
        this.agent_admin = agent_admin;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getPeople_number() {
        return people_number;
    }

    public void setPeople_number(String people_number) {
        this.people_number = people_number;
    }

    public String getGames_number() {
        return games_number;
    }

    public void setGames_number(String games_number) {
        this.games_number = games_number;
    }

    public String getSpecial_patterns() {
        return special_patterns;
    }

    public void setSpecial_patterns(String special_patterns) {
        this.special_patterns = special_patterns;
    }

    public String getBanned_from() {
        return banned_from;
    }

    public void setBanned_from(String banned_from) {
        this.banned_from = banned_from;
    }

    public String getGo_shares() {
        return go_shares;
    }

    public void setGo_shares(String go_shares) {
        this.go_shares = go_shares;
    }


    public String getBanker_gambling_game_reg() {
        return banker_gambling_game_reg;
    }

    public void setBanker_gambling_game_reg(String banker_gambling_game_reg) {
        this.banker_gambling_game_reg = banker_gambling_game_reg;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
