package com.a3k.utils.db;

import com.a3k.entity.Collection;
import com.a3k.utils.logger.BasicLogger;
import com.a3k.utils.properties.DataBaseProperties;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class DatabaseReader {

    private Session session;
    private Connection connect;
    private String url;
    private String dbName;
    private String dbDriver;
    protected static Logger logger = BasicLogger.getInstance();

    private DataBaseProperties dbp = new DataBaseProperties();

    private String autoReconnect;

    private String userName;
    private String password1;

    private Pattern portalAndHisServersRegular = Pattern.compile("http(s)?://([^.]*.)?portal.achieve3000.com");


    private static boolean sshConnecetCreated = false;

    public DatabaseReader(String browserUrl) {
//********* Here take some config from databaseconfig.properties ******/////
        userName = dbp.getUserName();
        password1 = dbp.getPassword1();
        url = dbp.getUrl();
        dbName = dbp.getDbName();
        dbDriver = dbp.getDbDriver();
        autoReconnect = dbp.getAutoReconnect();

 //*************************************************/////////

        try {
            Class.forName(dbDriver).newInstance();
            String rhost = "";
            String urlPart = browserUrl.split("//")[1].split(".portal.achieve3000")[0];
            logger.info("URL part " + urlPart);

      /********************* Here new realisation chose config**********************/
            switch (urlPart) {
                case "-main" :
                    rhost = "qa-master32";
                    break;

                case "dr-portal" :
                    rhost = "dr-master501";
                    dbName = "mykidbiz";
                    break;

                case "pre-prod-portal" :
                    rhost = "172.30.101.147";
                    dbName = "mykidbiz";
                    break;

                case "trunk-portal" :
                    logger.info("Trunk portal settings");
                    rhost = "trunk-slave501";
                    //rhost = "master11";
                    dbName = "mykidbiz";
                    break;

                case "-esc" :
                    rhost = "qa-main-master";
                    break;

                case "trunk.qa-esc":
                    rhost = "qa-main-master";
                    break;

                case "bts-portal" :
                    rhost = "172.30.101.156";
                    dbName = "mykidbiz";
                    break;

                case "bts" :
                    rhost = "bts-master501";
                    dbName = "mykidbiz";
                    break;

                case "bts.portal" :
                    rhost = "bts-master501";
                    dbName = "mykidbiz";
                    break;

                case "load" :
                    dbName = "mykidbiz";
                    rhost = "load-master31";
                    userName = "asoyko";
                    password1 = "J4xt9Kpaq";
                    break;

                case "dev" :
                    rhost = "172.30.101.119";
                    dbName = "mykidbiz";
                    userName = "evolk";
                    password1 = "vHkcWjJj";
                    break;

                default:
                    if (!browserUrl.contains("dev") & !browserUrl.contains("qa") & !browserUrl.contains("bts") & !browserUrl.contains("load") ){
                        rhost = "master11";
                        dbName = "mykidbiz";
                    }
            }

//            rhost = "qa-main-master";
			sschConnect(rhost);
            logger.info("Try connect to BD url = " + url + " ; name = " + dbName + " ; user name = " + userName + " ; password = " + password1);
            connect = DriverManager.getConnection(url + dbName, userName, password1);
            //createdGood = true;
            logger.info("Connected.");

		} catch (Exception e) {
			//createdGood = false;
            Assert.fail("Some problems with database connection! :( \n" + e.getMessage());
			//logger.debug();
		}
	}

	private void sschConnect(String rhost){
		try {
/************** Second take config from prop file*********************************************/
            String user = dbp.getUser();
            String password = dbp.getPassword();
			String host = dbp.getHost();

            int port = dbp.getPort();
            JSch jsch = new JSch();
			session = jsch.getSession(user, host, port);
			int lport = dbp.getLport();
			int rport = dbp.getRport();
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			logger.info("Establishing Connection...");
			session.connect();
			int assinged_port = session.setPortForwardingL(lport, rhost, rport);
			logger.debug("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
			sshConnecetCreated = true;
            logger.info("connected");
		} catch (Exception e) {
			if(!sshConnecetCreated)
				logger.debug("Some problem with ssh connect :\n" + e.getMessage());
		}
	}

	public Connection getConnect(){
		return connect;
	}

	public void finalize() throws Throwable {
		try {
			logger.info("Close connection...");
			connect.close();// close connection to database
			session.disconnect();// close SSH connection
		}catch(SQLException e){
			logger.debug("Some problem");
		} finally {
            super.finalize();
        }
    }

    public void reconnect() {
        try {
            if (!connect.isClosed()){
                connect.close();
            }
            connect = DriverManager.getConnection(url + dbName + autoReconnect, userName, password1);
        } catch (SQLException e) {
            logger.debug("Problem with reconnect: " + e);
        }
    }

    public String[] query(String query, String... returnValue) {
        ResultSet result = null;
        Statement state;
        String[] res = new String[returnValue.length];
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            result.next();
            for (int i = 0; i < returnValue.length; ++i) {
                res[i] = result.getString(returnValue[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String query(String query, String returnValue) {
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);

            System.out.println(result.next()); //dbg
            result.beforeFirst(); //dbg

            if (result.next()) {
                res = result.getString(returnValue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<String> queryArray(String query, String returnValue) {
        ResultSet result = null;
        Statement state;
        List<String> res = new ArrayList<String>();
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            while (result.next())
                res.add(result.getString(returnValue).trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<String[]> queryArrayList(String query, String... returnValue) {
        ResultSet result = null;
        Statement state;
        List<String[]> res = new ArrayList<String[]>();
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            String buff[] = new String[returnValue.length];
            while (result.next()) {
                for (int i = 0; i < returnValue.length; ++i)
                    buff[i] = (result.getString(returnValue[i]));
                res.add(buff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String[] queryArray(String query, String... returnValue) {
        ResultSet result = null;
        Statement state;
        String[] buff = new String[returnValue.length];
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            while (result.next()) {
                for (int i = 0; i < returnValue.length; ++i) {
                    buff[i] = result.getString(returnValue[i]);
                }
                //	res.add(buff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buff;
    }

    public Object query1(String query, String returnValue) {
        ResultSet result = null;
        Statement state;
        Object res = "";
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            result.next();
            res = result.getObject(returnValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void update(String query) {
        Statement state;
        try {
            state = connect.createStatement();
            state.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserId(String loginName) {
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            result = state.executeQuery("select user_id from subscriber where login_name = '" + loginName + "'");
            result.next();
            res = result.getString("user_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean haveResultOfQuery(String query) {
        ResultSet result = null;
        Statement state;
        boolean res = false;
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            res = result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Map<String, String> queryMap(String query, String returnValue1, String returnValue2) {
        ResultSet result = null;
        Statement state;
        Map<String, String> res = new HashMap<String, String>();
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            while (result.next()) {
                res.put(result.getString(returnValue1), result.getString(returnValue2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<String> query(String query) {
        ResultSet result = null;
        Statement state;
        ArrayList<String> res = new ArrayList<String>();

        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            while (result.next()) {
                res.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getSchoolId(String schoolCode) {
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();

            String query = "select * from schools where school_code='" + schoolCode + "'";
            result = state.executeQuery(query);
            result.next();
            res = result.getString("school_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;

    }

    public String getClassId(String login, String password, String className) {
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
			/*
			 String query = "select c.class_id from classes c inner join subscriber_class sc on c.class_id = sc.class_id "
					+ "inner join subscriber s on sc.user_id = s.user_id "
					+ "where s.login_name = '"+login+"' and c.class_name =\"" + className + "\"";
			*/
            String query = "SELECT distinct c.class_Id"
                    + " FROM subscriber s"
                    + " JOIN subscriber_class sc"
                    + " ON s.user_id=sc.user_id AND NOW() BETWEEN profile_start AND profile_end"
                    + " JOIN classes c"
                    + " ON sc.class_Id=c.class_id AND c.class_name =\"" + className + "\""
                    + " JOIN schools sch"
                    + " ON c.school_Id=sch.school_id"
                    + " AND s.district_Id=sch.district_id"
                    + " WHERE s.login_name = '"+login+"'  AND s.password= '"+password+"' ";

            result = state.executeQuery(query);
            result.next();
            res = result.getString("class_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getSchoolDistrictId(String school) {
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select district_id from schools where school_code='" + school + "'";
            result = state.executeQuery(query);
            result.next();
            res = result.getString("district_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getLoginNameById(String user_id){
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select login_name from subscriber where now()<subscription_end and user_id="+user_id;
            result = state.executeQuery(query);
            result.next();
            res = result.getString("login_name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public String getPasswordById(String user_id){
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select password from subscriber where now()<subscription_end and user_id="+user_id;
            result = state.executeQuery(query);
            result.next();
            res = result.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static int ENGLISH_US = 1;
    public static int FULL_SPANISH = 2;

    public List<String> getLessonInCollection(String startDate, String endDate, String collectionName, String districtId, int laguage) {
        ResultSet result = null;
        Statement state;
        List<String> res = new ArrayList<>();
        try {
            state = connect.createStatement();
            String query = "select distinct l.lesson_id, l.lesson_name, lcl.collection_id, lcl.lesson_id, lcl.start_date, lcl.end_date " +
                    "from lesson_collection_lessons lcl " +
                    "join lesson_collections lc on lc.collection_id=lcl.collection_id " +
                    "join profile_lessons l on l.lesson_id=lcl.lesson_id " +
                    "where lc.collection_name ='" + collectionName + "' " +
                    "and lc.start_date>='" + startDate + "' " +
                    "and lc.end_date<='" + endDate + "' " +
                    "and lc.shared=1 " +
                    "and lc.status=0 " +
                    "and l.language_id=" + laguage +
                    " and lc.district_id=" + districtId +
                    " order by l.lesson_id desc";
            result = state.executeQuery(query);
            while (result.next()) {

                res.add(result.getString("lesson_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Collection> getCollections(String userId, String schoolId, int yearId, int programId, String classId) {
        ResultSet result = null;
        Statement state;
        List<Collection> collections = new ArrayList<>();

        try {
            state = connect.createStatement();
            String query = "select distinct lc.collection_id, lc.collection_name, lc.start_date, lc.end_date, lc.program_id, lc.grade_level " +
                    "from lesson_collections lc " +
                    "join lesson_collection_classes lcc on lcc.collection_id=lc.collection_id " +
                    "join schools sch on sch.school_id=lcc.school_id " +
                    "join school_academic_years say on sch.school_id=say.school_id " +
                    "where lc.start_date>=say.academic_year_start " +
                    "and lc.start_date<=say.extended_end " +
                    "and say.academic_year_id=" + yearId + " " +
                    "and (lcc.class_id in (select class_id from subscriber_class where user_id=" + userId + " and profile_end>now()) " +
                    "or (lcc.class_id=" + classId + " and lcc.school_id=" + schoolId + ")) " +
                    "and lc.shared=1 " +
                    "and lc.status=0 " +
                    "and (lc.program_id in (select c.program_id from classes c join subscriber_class sc on sc.class_id=c.class_id where user_id=" + userId + " and profile_end>now() and c.grade=lc.grade_level) " +
                    "or lc.program_id=" + programId + ") " +
                    "and (lc.grade_level in (select c.grade from classes c join subscriber_class sc on sc.class_id=c.class_id where sc.user_id=" + userId + " and profile_end>now() and c.program_id=lc.program_id) " +
                    "or lc.program_id=" + programId + ") " +
                    "order by lc.collection_name ";
            result = state.executeQuery(query);
            while (result.next()) {
                collections.add(new Collection(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collections;
    }
    public int getAmountOfClassesForUser(String user_id){
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select count(distinct class_id) class_id from subscriber_class where profile_end>now() and user_id="+user_id;
            result = state.executeQuery(query);
            result.next();
            res = result.getString("class_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(res);
    }
    public String getProfileLessonIdByLessonId(String profile_lesson_id,String language_id){
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select * from profile_lessons where profile_lesson_id = "+profile_lesson_id+" and language_id= "+language_id;
            result = state.executeQuery(query);
            result.next();
            res = result.getString("profile_lesson_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public String getLessonNameByProfileLessonId(String profile_lesson_id, String language_id) throws UnsupportedEncodingException{
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select * from profile_lessons where profile_lesson_id = "+profile_lesson_id+" and language_id ="+language_id;

            result = state.executeQuery(query);
            result.next();
            res = result.getString("lesson_name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String(res.getBytes("Cp1252"), "UTF-8");
    }
    public String getReadingLevelFromSubmissionsByUserId(String user_id, String profile_lesson_id) {
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select * from submissions where user_id ="+user_id+" and profile_lesson_id="+profile_lesson_id;
            //	String query = "select * from subscriber_profile where profile_end > now() and user_id ="+user_id;
            result = state.executeQuery(query);
            result.next();
            res = result.getString("reading_level");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public String getLexileNumFromSubmissionsByUserId(String user_id, String profile_lesson_id){
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select * from submissions where user_id ="+user_id+" and profile_lesson_id="+profile_lesson_id;
            result = state.executeQuery(query);
            result.next();
            res = result.getString("lexile_num");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getEndTimeFromSectionLogDaily(String user_id, String section_id){
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String query = "select * from section_log_daily where user_id =" + user_id +
                    " and section_id=" + section_id + " order by date_modified desc";
            result = state.executeQuery(query);
            result.next();
            res = result.getString("end_time");
        } catch (SQLException e) {
            if (e.toString().contains("Cannot convert value '0000-00-00 00:00:00'")) {
                res = "0000-00-00 00:00:00";
            }
        }
        return res;
    }
    public String getNewReadingLevel(String profile_lesson_id, String reading_level, String lexile_num){
        ResultSet result = null;
        Statement state;
        String res = "";
        try {
            state = connect.createStatement();
            String condition = "reading_high>-1";
            if(reading_level.equals("-1"))
                condition = "reading_low =-1";

            String query = "SELECT if ("+reading_level+" < reading_high, reading_low, reading_high) new_reading_level "
                    + "FROM contents c, profile_lesson_contents pc WHERE c.content_id = pc.content_id "
                    + "AND step_id IN (11) AND content_type_id = 1 AND lexile_num > 0 and " +condition
                    + " AND pc.profile_lesson_id = "+profile_lesson_id
                    + " ORDER BY abs(lexile_num-"+lexile_num+") ASC, (lexile_num-"+lexile_num+") DESC, (reading_high-"+reading_level+") ASC LIMIT 1";

            result = state.executeQuery(query);
            result.next();
            res = result.getString("new_reading_level");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public List<String> queryArrayForStringsWithWrongEncoding(String query, String returnValue) throws UnsupportedEncodingException{
        ResultSet result = null;
        Statement state;
        List<String> res = new ArrayList<String>();
        try {
            state = connect.createStatement();
            result = state.executeQuery(query);
            while(result.next())
                res.add(new String (result.getString(returnValue).trim().getBytes("Cp1252"), "UTF-8"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
} 
	 
