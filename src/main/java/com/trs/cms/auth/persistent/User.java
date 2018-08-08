////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.trs.cms.auth.persistent;
//
//import com.trs.DreamFactory;
//import com.trs.cms.ContextHelper;
//import com.trs.cms.content.CMSObj;
//import com.trs.components.wcm.resource.DocLevel;
//import com.trs.infra.I18NMessage;
//import com.trs.infra.common.WCMException;
//import com.trs.infra.persistent.BaseObj;
//import com.trs.infra.persistent.WCMFilter;
//import com.trs.infra.util.CMyDateTime;
//import com.trs.infra.util.CMyEncrypt;
//import com.trs.infra.util.CMyString;
//import org.apache.log4j.Logger;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.util.Hashtable;
//
//public class User extends CMSObj implements Serializable {
//    private static final long serialVersionUID = 1354095122078583355L;
//    private static Logger s_logger = Logger.getLogger(User.class);
//    public static final int OBJ_TYPE = 204;
//    public static final String DB_TABLE_NAME = "WCMUSER";
//    public static final String DB_ID_NAME = "USERID";
//    public static final int USER_STATUS_APPLY = 0;
//    public static final int USER_STATUS_REG = 30;
//    public static final int USER_STATUS_DEL = 10;
//    public static final int USER_STATUS_FORBID = 20;
//    public static final int USER_STATUS_ALL = -1;
//    public static final int USER_STATUS_DROPED = 40;
//    public static final char USER_DROPED_NAME_FLAG = '$';
//    public static final String SYSTEM_USERNAME = "system";
//    private static final int SYSTEM_USEID = 2147483647;
//    private static User USER_SYSTEM = null;
//    private boolean bLogined = false;
//    private CMyDateTime dtLastLoginTime = null;
//    private static final char[] INVALID_FOR_NAME = new char[]{'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '\'', '"', ':', '>', '<', '?'};
//    private boolean m_bCacheGroupsAndRoles = false;
//    private Groups m_oGroups = null;
//    private Roles m_oRoles = null;
//
//    public User() {
//    }
//
//    public String getDbTableName() {
//        return "WCMUSER";
//    }
//
//    public String getIdFieldName() {
//        return "USERID";
//    }
//
//    public int getWCMType() {
//        return 204;
//    }
//
//    public boolean isValidInstance() {
//        return super.isValidInstance() || this.isSystem();
//    }
//
//    public boolean isSystem() {
//        return this.id == 2147483647;
//    }
//
//    public boolean isForbidden() {
//        return this.getStatus() == 20;
//    }
//
//    public boolean equals(Object var1) {
//        if (var1 == this) {
//            return true;
//        } else if (!(var1 instanceof User)) {
//            return false;
//        } else if (super.equals(var1)) {
//            return true;
//        } else {
//            User var2 = (User)var1;
//            return this.isSystem() && var2.isSystem();
//        }
//    }
//
//    public String getName() {
//        return (String)this.getProperty("USERNAME");
//    }
//
//    public boolean nameIs(String var1) {
//        if (var1 == null) {
//            return false;
//        } else {
//            return var1.compareTo(this.getName()) == 0;
//        }
//    }
//
//    public boolean setName(String var1) throws WCMException {
//        if (!this.isAddMode()) {
//            throw new WCMException(1102, I18NMessage.get(User.class, "User.label1", "用户名称不允许修改(User.setName)"));
//        } else {
//            return this.setProperty("USERNAME", var1);
//        }
//    }
//
//    public boolean setPassword(String var1) throws WCMException {
//        int var2 = var1.length();
//        if (var2 < 8) {
//            throw new WCMException(1104, I18NMessage.get(User.class, "User.label2", "密码要求至少4个字符 (User.setPassword)"));
//        } else {
//            return this.setProperty("PASSWORD", var1);
//        }
//    }
//
//    public String getReminderQuestion() {
//        return (String)this.getProperty("REMINDERQUESTION");
//    }
//
//    public boolean setReminderQuestion(String var1) throws WCMException {
//        return this.setProperty("REMINDERQUESTION", var1);
//    }
//
//    public String getReminderAnswer() {
//        return (String)this.getProperty("REMINDERANSWER");
//    }
//
//    public boolean setReminderAnswer(String var1) throws WCMException {
//        return this.setProperty("REMINDERANSWER", var1);
//    }
//
//    public String getNickName() {
//        return (String)this.getProperty("NICKNAME");
//    }
//
//    public boolean setNickName(String var1) throws WCMException {
//        return this.setProperty("NICKNAME", var1);
//    }
//
//    public String getTrueName() {
//        return (String)this.getProperty("TRUENAME");
//    }
//
//    public boolean setTrueName(String var1) throws WCMException {
//        return this.setProperty("TRUENAME", var1);
//    }
//
//    public String getAddress() {
//        return (String)this.getProperty("ADDRESS");
//    }
//
//    public boolean setAddress(String var1) throws WCMException {
//        return this.setProperty("ADDRESS", var1);
//    }
//
//    public String getTel() {
//        return (String)this.getProperty("TEL");
//    }
//
//    public boolean setTel(String var1) throws WCMException {
//        return this.setProperty("TEL", var1);
//    }
//
//    public String getMobile() {
//        return (String)this.getProperty("MOBILE");
//    }
//
//    public boolean setMobile(String var1) throws WCMException {
//        return this.setProperty("MOBILE", var1);
//    }
//
//    public String getEmail() {
//        return (String)this.getProperty("EMAIL");
//    }
//
//    public boolean setEmail(String var1) throws WCMException {
//        return this.setProperty("EMAIL", var1);
//    }
//
//    public int getStatus() {
//        return this.getPropertyAsInt("STATUS", 0);
//    }
//
//    public boolean setStatus(int var1) throws WCMException {
//        if (!this.setProperty("STATUS", (long)var1)) {
//            return false;
//        } else {
//            return var1 == 30 ? this.setRegTime() : true;
//        }
//    }
//
//    public String getStatusString() {
//        return this.isDeleted() ? I18NMessage.get(User.class, "User.label3", "已删除") : getStatusString(this.getStatus());
//    }
//
//    public int getLoginTimes() {
//        return this.getPropertyAsInt("LOGINTIMES", 0);
//    }
//
//    public CMyDateTime getRegTime() {
//        return this.getPropertyAsDateTime("REGTIME");
//    }
//
//    private boolean setRegTime() throws WCMException {
//        CMyDateTime var1 = new CMyDateTime();
//        var1.setDateTimeWithCurrentTime();
//        return this.setProperty("REGTIME", var1);
//    }
//
//    public CMyDateTime getLoginTime() {
//        return this.getPropertyAsDateTime("LOGINTIME");
//    }
//
//    public CMyDateTime getLastLoginTime() {
//        return this.dtLastLoginTime;
//    }
//
//    public boolean isPubMyInfo() {
//        return this.getPropertyAsBoolean("IFPUBMYINFO", true);
//    }
//
//    public boolean setPubMyInfo(boolean var1) throws WCMException {
//        return this.setProperty("IFPUBMYINFO", var1);
//    }
//
//    public int getMsgInterval() {
//        return this.getPropertyAsInt("MSGINTERVAL", 0);
//    }
//
//    public boolean setMsgInterval(int var1) throws WCMException {
//        return this.setProperty("MSGINTERVAL", (long)var1);
//    }
//
//    public int getViewInterval() {
//        return this.getPropertyAsInt("VIEWINTERVAL", 0);
//    }
//
//    public boolean setViewInterval(int var1) throws WCMException {
//        return this.setProperty("VIEWINTERVAL", (long)var1);
//    }
//
//    public int getPrice() {
//        return this.getPropertyAsInt("PRICE", 0);
//    }
//
//    public boolean setPrice(int var1) throws WCMException {
//        return this.setProperty("PRICE", (long)var1);
//    }
//
//    public String getLoginIP() {
//        return this.getAttributeValue("LOGINIP");
//    }
//
//    public boolean isDeleted() {
//        return this.getPropertyAsBoolean("ISDELETED", true);
//    }
//
//    public boolean setDeleted(boolean var1) throws WCMException {
//        return this.setProperty("ISDELETED", var1);
//    }
//
//    public int getPasswordLev() {
//        return this.getPropertyAsInt("PASSWORDLEV", 0);
//    }
//
//    public boolean setPasswordLev(int var1) throws WCMException {
//        return this.setProperty("PASSWORDLEV", (long)var1);
//    }
//
//    private String cryptPassword(String var1) {
//        return var1 == null ? null : (new CMyEncrypt()).getMD5OfStr(var1).substring(0, 15);
//    }
//
//    public boolean isValid() throws WCMException {
//        if (this.isAddMode()) {
//            String var1 = (String)this.getAllProperty().get("USERNAME");
//            if (var1 == null) {
//                throw new WCMException(1106, I18NMessage.get(User.class, "User.label4", "属性Name没有设置(User.isValid)"));
//            }
//
//            this.assertValidName(var1);
//            if ((String)this.getAllProperty().get("PASSWORD") == null) {
//                throw new WCMException(1106, I18NMessage.get(User.class, "User.label5", "属性Password没有设置(User.isValid)"));
//            }
//
//            var1 = var1.toLowerCase();
//            if (var1.compareTo("system") == 0) {
//                throw new WCMException(1104, I18NMessage.get(User.class, "User.label6", "用户名") + "system" + I18NMessage.get(User.class, "User.label7", "禁止使用（User.isValid）"));
//            }
//
//            String var2 = "UserName='" + CMyString.filterForSQL(var1) + "'";
//            if (this.checkExists(var2)) {
//                throw new WCMException(1108, I18NMessage.get(User.class, "User.label6", "用户名") + var1 + I18NMessage.get(User.class, "User.label8", "已经存在（User.isValid）"));
//            }
//
//            this.setName(var1);
//        } else if (!this.isModified()) {
//            throw new WCMException(1107, I18NMessage.get(User.class, "User.label9", "对象属性没有更改(User.isValid)"));
//        }
//
//        return true;
//    }
//
//    public void insert(User var1) throws WCMException {
//        if (this.isAddMode() && this.getAllProperty() != null) {
//            if (this.getProperty("ISDELETED") == null) {
//                this.setDeleted(false);
//            }
//
//            if (this.getProperty("CRTIME") == null) {
//                this.setCrTime();
//            }
//
//            if (this.getProperty("STATUS") == null) {
//                this.setProperty("STATUS", 0L);
//            }
//
//            this.setProperty("LOGINTIMES", 0L);
//            this.setProperty("PASSWORD", this.cryptPassword((String)this.getAllProperty().get("PASSWORD")));
//            super.insert(var1);
//        }
//
//    }
//
//    public void update(User var1) throws WCMException {
//        if (this.isChangeProperty("PASSWORD")) {
//            this.setProperty("PASSWORD", this.cryptPassword(this.getNewPropertyAsString("PASSWORD")));
//        }
//
//        super.update(var1);
//    }
//
//    public boolean isLogined() {
//        return this.bLogined;
//    }
//
//    public boolean passwordIs(String var1) {
//        String var2 = (String)this.getAllProperty().get("PASSWORD");
//        if (var2 == null) {
//            return false;
//        } else {
//            return var2.compareTo(this.cryptPassword(var1)) == 0;
//        }
//    }
//
//    public int login(String var1, String var2) throws WCMException {
//
//        System.out.println("v1====>"+var1);
//
//        System.out.println("v2====>"+var2);
//
//        if (this.getAllProperty() == null) {
//            return -1;
//        } else if (this.isDeleted()) {
//            return 10;
//        } else {
//            int var3 = this.getStatus();
//            if (var3 != 30) {
//                return var3;
//            } /*else if (!this.passwordIs(var1)) {
//                return -1;
//            } */else {
//                if (this.bLogined) {
//                    this.logout();
//                }
//
//                User var4 = this;
//                System.out.println("v4====>"+var4);
//                try {
//                    var4 = ContextHelper.getLoginUser();
//                } catch (Exception var12) {
//                    ;
//                }
//
//                if (!this.canEdit(var4)) {
//                    return -2;
//                } else {
//                    int var7;
//                    try {
//                        CMyDateTime var5 = this.getPropertyAsDateTime("LOGINTIME");
//                        this.dtLastLoginTime = var5 == null ? null : (CMyDateTime)var5.clone();
//                        if (var5 == null) {
//                            var5 = new CMyDateTime();
//                        }
//
//                        var5.setDateTimeWithCurrentTime();
//                        this.setProperty("LOGINTIME", var5);
//                        int var6 = this.getLoginTimes();
//                        this.setProperty("LOGINTIMES", (long)(var6 + 1));
//                        this.setAttribute("LoginIP", var2);
//                        this.update(var4);
//                        this.bLogined = true;
//                        var7 = var3;
//                    } finally {
//                        if (this.isLocked()) {
//                            this.cancelUpdate(this);
//                        }
//
//                    }
//
//                    return var7;
//                }
//            }
//        }
//    }
//
//    public void logout() {
//        if (this.bLogined) {
//            this.bLogined = false;
//        }
//    }
//
//    public static String getStatusString(int var0) {
//        switch(var0) {
//            case 0:
//                return I18NMessage.get(User.class, "User.label10", "待开通");
//            case 10:
//                return I18NMessage.get(User.class, "User.label3", "已删除");
//            case 20:
//                return I18NMessage.get(User.class, "User.label11", "已停用");
//            case 30:
//                return I18NMessage.get(User.class, "User.label12", "已开通");
//            default:
//                return I18NMessage.get(User.class, "User.label13", "未知");
//        }
//    }
//
//    public boolean isAdministrator() throws WCMException {
//        return Users.findAdministrators().indexOf(this.getId()) >= 0;
//    }
//
//    public void setCacheGroupsAndRoles(boolean var1) {
//        this.m_bCacheGroupsAndRoles = var1;
//        if (!var1) {
//            if (this.m_oGroups != null) {
//                this.m_oGroups.clear();
//                this.m_oGroups = null;
//            }
//
//            if (this.m_oRoles != null) {
//                this.m_oRoles.clear();
//                this.m_oRoles = null;
//            }
//        }
//
//    }
//
//    public Groups getGroups() throws WCMException {
//        if (this.m_bCacheGroupsAndRoles) {
//            if (this.m_oGroups == null) {
//                synchronized(this) {
//                    if (this.m_oGroups == null) {
//                        this.m_oGroups = this.initGroups();
//                    }
//                }
//            }
//
//            return this.m_oGroups;
//        } else {
//            return this.initGroups();
//        }
//    }
//
//    private Groups initGroups() throws WCMException {
//        Groups var1 = ((GroupUserCacheMgr)DreamFactory.createObjectById("GroupUserCacheMgr")).getGroups(this);
//        Groups var2 = new Groups(this);
//
//        for(int var3 = 0; var3 < var1.size(); ++var3) {
//            Group var4 = (Group)var1.getAt(var3);
//            if (var4 != null) {
//                var2.addElement(var4);
//            }
//        }
//
//        return var2;
//    }
//
//    public Roles getRoles() throws WCMException {
//        if (this.m_bCacheGroupsAndRoles) {
//            if (this.m_oRoles == null) {
//                synchronized(this) {
//                    if (this.m_oRoles == null) {
//                        this.m_oRoles = this.initRoles();
//                    }
//                }
//            }
//
//            return this.m_oRoles;
//        } else {
//            return this.initRoles();
//        }
//    }
//
//    private Roles initRoles() throws WCMException {
//        Roles var1 = ((RoleUserCacheMgr)DreamFactory.createObjectById("RoleUserCacheMgr")).getRoles(this);
//        Roles var2 = new Roles(this);
//
//        for(int var3 = 0; var3 < var1.size(); ++var3) {
//            Role var4 = (Role)var1.getAt(var3);
//            if (var4 != null) {
//                var2.addElement(var4);
//            }
//        }
//
//        if (var1.indexOf(2) < 0) {
//            Role var5 = Role.findById(2);
//            var2.addElement(var5);
//        }
//
//        return var2;
//    }
//
//    public Rights getMyRights(int var1) throws WCMException {
//        WCMFilter var2 = null;
//        Rights var3 = null;
//
//        try {
//            var2 = new WCMFilter("", "OprType=" + this.getWCMType() + " and OprId=" + this.getId() + " and ObjType=" + var1, "");
//            var3 = new Rights((User)null);
//            var3.open(var2);
//            return var3;
//        } catch (Exception var5) {
//            throw new WCMException(1100, I18NMessage.get(User.class, "User.label14", "获取用户的权限对象集合时失败(User.getMyRights)"), var5);
//        }
//    }
//
//    public RightValue getMyRightValue(CMSObj var1) throws WCMException {
//        if (var1 != null && var1.isValidInstance()) {
//            return this.getMyRightValue(var1.getWCMType(), var1.getId());
//        } else {
//            throw new WCMException(10, I18NMessage.get(User.class, "User.label15", "无效的操作对象(User.getMyRightValue)"));
//        }
//    }
//
//    public RightValue getMyRightValue(int var1, int var2) throws WCMException {
//        RightValue var3 = new RightValue();
//
//        try {
//            var3.load(this, var1, var2);
//            return var3;
//        } catch (Exception var5) {
//            throw new WCMException(1100, I18NMessage.get(User.class, "User.label16", "获取用户在指定对象上的权限值时失败(User.getMyRightValue)"), var5);
//        }
//    }
//
//    public DocLevel getDocLevel() throws WCMException {
//        int var1 = this.getDocLevleId();
//        return var1 == 0 ? DocLevel.findById(1) : DocLevel.findById(var1);
//    }
//
//    public boolean setDocLevel(DocLevel var1) throws WCMException {
//        return var1 == null ? this.setDocLevel(0) : this.setDocLevel(var1.getId());
//    }
//
//    public int getDocLevleId() {
//        return this.getPropertyAsInt("VIEWDOCLEVEL", 0);
//    }
//
//    public boolean setDocLevel(int var1) throws WCMException {
//        return this.setProperty("VIEWDOCLEVEL", (long)var1);
//    }
//
//    public static final User findById(int var0) throws WCMException {
//        if (var0 <= 0) {
//            return null;
//        } else {
//            return var0 == 2147483647 && USER_SYSTEM != null ? USER_SYSTEM : (User)BaseObj.findById(User.class, var0);
//        }
//    }
//
//    public static final User createNewInstance() throws WCMException {
//        return (User)BaseObj.createNewInstance(User.class);
//    }
//
//    public static final User findByKey(Object var0) throws WCMException {
//        return (Integer)var0 == 2147483647 ? getSystem() : (User)BaseObj.findByKey(User.class, var0);
//    }
//
//    public static IUserCacheMgr getUserCacheMgr() throws WCMException {
//        IUserCacheMgr var0 = (IUserCacheMgr)DreamFactory.createObjectById("IUserCacheMgr");
//        if (var0 == null) {
//            throw new WCMException(10, "IUserCacheMgr config invalid!");
//        } else {
//            return var0;
//        }
//    }
//
//    public static final User findByName(String var0) {
//        if ("system".equals(var0)) {
//            return getSystem();
//        } else {
//            try {
//                return getUserCacheMgr()._getUserByName(var0);
//            } catch (WCMException var2) {
//                s_logger.error(I18NMessage.get(User.class, "User.label17", "获取用户失败！"), var2);
//                return null;
//            }
//        }
//    }
//
//    public static final User getSystem() {
//        if (USER_SYSTEM == null) {
//            makeSystem();
//        }
//
//        return USER_SYSTEM;
//    }
//
//    private static final synchronized void makeSystem() {
//        if (USER_SYSTEM == null) {
//            try {
//                User var0 = new User();
//                var0.setName("system");
//                var0.setTrueName(I18NMessage.get(User.class, "User.label18", "虚拟的系统用户"));
//                var0.setAttribute("LOGINIP", "127.0.0.1");
//                var0.id = 2147483647;
//                USER_SYSTEM = var0;
//            } catch (Exception var1) {
//                var1.printStackTrace();
//            }
//
//        }
//    }
//
//    public String toString() {
//        return this.IS_ENGVERSION ? this.getName() : this.getName() + I18NMessage.get(User.class, "User.label19", "[用户-") + this.getId() + "]";
//    }
//
//    private void assertValidName(String var1) throws WCMException {
//        String var2 = "";
//        char[] var3 = INVALID_FOR_NAME;
//
//        for(int var4 = 0; var4 < var3.length; ++var4) {
//            if (var1.indexOf(var3[var4]) != -1) {
//                var2 = var2 + var3[var4];
//            }
//        }
//
//        if (var2.length() > 0) {
//            throw new WCMException(10, I18NMessage.get(User.class, "User.label20", "无效的用户名[") + var1 + I18NMessage.get(User.class, "User.label21", "],用户名含有特殊字符[") + var2 + "]!");
//        }
//    }
//
//    private void writeObject(ObjectOutputStream var1) throws IOException {
//        var1.defaultWriteObject();
//        var1.writeObject(this.getAllProperty());
//        var1.writeObject(this.getNewProperties());
//        var1.writeInt(this.getId());
//        var1.writeBoolean(this.isAddMode());
//    }
//
//    private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
//        var1.defaultReadObject();
//        this.setProperties((Hashtable)var1.readObject());
//        this.setNewProperties((Hashtable)var1.readObject());
//        this.id = var1.readInt();
//        this.bAddMode = var1.readBoolean();
//    }
//
//    public static void main(String[] var0) {
//        String[] var1 = new String[]{"trsdemo123", "trswcmadmin", "trsadmin@196", "trsadmin", "11111111", "12345678", "88888888"};
//
//        for(int var2 = 0; var2 < var1.length; ++var2) {
//            System.out.println(var1[var2]);
//            System.out.println((new CMyEncrypt()).getMD5OfStr(var1[var2]).substring(0, 15));
//        }
//
//    }
//}
