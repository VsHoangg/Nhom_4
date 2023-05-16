/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Cart;
import model.Movie;
import model.Schedule;
import model.User;


public class DAO extends DBContext {

    public User check(String username, String password) {
        String sql = "SELECT *"
                + "  FROM [dbo].[User]\n"
                + "  where name=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            System.out.println(username + " " + password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // User b=new User
                User a = new User(rs.getInt("role"), rs.getInt("userID"), rs.getString("password"), rs.getString("name"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insert(User c) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([password]\n"
                + "           ,[name]\n"
                + "           ,[email]\n"
                + "           ,[sex]\n"
                + "           ,[address]\n"
                + "           ,[phonenumber]\n"
                + "           ,[Role])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getPassword());
            st.setString(2, c.getName());
            st.setString(3, c.getEmail());
            st.setBoolean(4, c.isSex());
            st.setString(5, c.getAddress());
            st.setString(6, c.getPhonenumber());
            st.setInt(7, 0);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void insert(Movie c) {
        String sql = "INSERT INTO [dbo].[Movie]\n"
                + "           ([FilmID]\n"
                + "           ,[HotLevel]\n"
                + "           ,[Information]\n"
                + "           ,[Price]\n"
                + "           ,[Publish_date]\n"
                + "           ,[Duration]\n"
                + "           ,[status]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getFilmID());
            st.setInt(2, c.getHotLevel());
            st.setString(3, c.getInformation());
            st.setFloat(4, c.getPrice());
            st.setDate(5, c.getPublish_date());
            st.setFloat(6, c.getDuration());
            st.setString(7, c.getStatus());
            st.setString(8, c.getImage());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public List<String> getListDay() {
        String sql = "SELECT distinct day\n"
                + "  FROM [PRJ301_TICKET_FILM].[dbo].[Schedule]";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getDate("day").toString());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertFilmWithSchedule(Movie e) {
        try {

            String sql1 = "INSERT INTO [dbo].[Movie-Schedule]\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            for (Schedule i : e.getSchedules()) {
                PreparedStatement st1 = connection.prepareStatement(sql1);
                st1.setString(1, e.getFilmID());
                st1.setInt(2, i.getSID());
                st1.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public User getUserByName(String name) {
        String sql = "select * from [dbo].[User] where name=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User(rs.getInt("role"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getString("phonenumber"), rs.getBoolean("sex"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "select * FROM [dbo].[User] where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User c = new User(rs.getInt("role"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getString("phonenumber"), rs.getInt("userID"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Movie getMovieById(String id) {
        String sql = "select * FROM [dbo].[Movie] where FilmID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            List<Schedule> list = new ArrayList<>();
            if (rs.next()) {
                Movie c = new Movie(rs.getString("FilmID"), rs.getString("Information"), rs.getString("status"),
                        rs.getString("image"), rs.getInt("HotLevel"), rs.getFloat("Price"), rs.getFloat("Duration"), rs.getDate("Publish_date"));
                String sql1 = "select * from [dbo].[Movie-Schedule] where FilmID=?";
                try {
                    PreparedStatement st1 = connection.prepareStatement(sql1);
                    st1.setString(1, id);
                    ResultSet rs1 = st1.executeQuery();

                    while (rs1.next()) {
                        Schedule s = getScheduleById(rs1.getInt("SID"));
//                        Schedule s=new Schedule();
//                        s.setDay(rs1.getDate("day"));
//                        s.setEndTime(rs1.getString("EndTime"));
//                        s.setSID(rs1.getInt("SID"));
//                        s.setStartTime(rs1.getString("StartTime"));
                        list.add(s);
                    }
                } catch (SQLException e) {
                    System.out.println("asdf" + e);
                }
                c.setSchedules(list);
                int male = 0, female = 0;
                String sqls = "select distinct UserID,FilmID,SID from bill where FilmID=?";
                PreparedStatement sts = connection.prepareStatement(sqls);
                sts.setString(1, c.getFilmID());
                ResultSet rss = sts.executeQuery();
                while (rss.next()) {
                    int uid = rss.getInt("UserID");
                    if (getUserById(uid).isSex()) {
                        female++;
                    } else {
                        male++;
                    }
                }
                c.setFemale(female);
                c.setMale(male);
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Schedule getScheduleById(int id) {
        String sql = "select * from [dbo].[Schedule] where SID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Schedule s = new Schedule();
                s.setDay(rs.getDate("day"));
                s.setEndTime(rs.getString("EndTime"));
                s.setSID(id);
                s.setStartTime(rs.getString("StartTime"));
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Movie> getListByPage(List<Movie> list, int start, int end) {
        List<Movie> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Movie> getAllMovieNotPublish() {

        List<Movie> list = new ArrayList<>();
        String sql = "select * from Movie where [Publish_date]>?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, LocalDate.now().toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie d = new Movie(rs.getString("FilmID"), rs.getString("Information"), rs.getString("status"), rs.getString("image"),
                        rs.getInt("HotLevel"), rs.getFloat("Price"), rs.getFloat("Duration"), rs.getDate("Publish_date"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Movie> getAllMovie() {
        List<Movie> list = new ArrayList<>();
        String sql = "select * from Movie where [Publish_date]<=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, LocalDate.now().toString());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie d = new Movie(rs.getString("FilmID"), rs.getString("Information"), rs.getString("status"), rs.getString("image"),
                        rs.getInt("HotLevel"), rs.getFloat("Price"), rs.getFloat("Duration"), rs.getDate("Publish_date"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Schedule getSchedule(String starttime, String day) {
        String sql = "select * from [dbo].[Schedule] where day=? and StartTime=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(2, starttime);
            st.setString(1, day);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Schedule c = new Schedule();
                c.setDay(rs.getDate("day"));
                c.setEndTime(rs.getString("EndTime"));
                c.setSID(rs.getInt("SID"));
                c.setStartTime(rs.getString("StartTime"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Schedule> getAllSchedule() {
        List<Schedule> list = new ArrayList<>();
        String sql = "select * from [dbo].[Schedule] where day='2022-07-17'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Schedule d = new Schedule(rs.getInt("SID"), rs.getString("StartTime"), rs.getString("EndTime"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void update(Movie c) {
        String sql = "UPDATE [dbo].[Movie]\n"
                + "   SET [HotLevel] = ?\n"
                + "      ,[Information] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Publish_date] = ?\n"
                + "      ,[Duration] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE FilmID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getHotLevel());
            st.setString(2, c.getInformation());
            st.setFloat(3, c.getPrice());
            st.setDate(4, c.getPublish_date());
            st.setFloat(5, c.getDuration());
            st.setString(6, c.getStatus());
            st.setString(7, c.getImage());
            st.setString(8, c.getFilmID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(User c) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [name] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phonenumber] = ?\n"
                + " WHERE UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getEmail());
            st.setString(3, c.getAddress());
            st.setString(4, c.getPhonenumber());
            st.setInt(5, c.getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(int id, String pass) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [password] = ?\n"
                + " WHERE UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getUserIDByUserAndPass(String user, String pass) {
        String sql = "select * FROM [dbo].[User] where name=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("userID");

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public List<Movie> search(String name) {
        List<Movie> list = new ArrayList<>();
        String sql = "select * FROM [dbo].[Movie] where 1=1 ";
        if (name != null && !name.equals("")) {
            sql += " and FilmID like '%" + name + "%' or Information like '%" + name + "%'";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movie p = new Movie();
                p.setDuration(rs.getFloat("Duration"));
                p.setFilmID(rs.getString("FilmID"));
                p.setHotLevel(rs.getInt("HotLevel"));
                p.setImage(rs.getString("image"));
                p.setInformation(rs.getString("Information"));
                p.setPrice(rs.getFloat("Price"));
                p.setPublish_date(rs.getDate("Publish_date"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public void addSeat() {
        try {
            for (int i = 1; i <= 8; i++) {
                String sql = "INSERT INTO [dbo].[seat]\n"
                        + "           ([Position])\n"
                        + "     VALUES\n"
                        + "           ('F" + i + "')";
                PreparedStatement st = connection.prepareStatement(sql);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void addCart(Cart c) {
        try {
            String sql = "INSERT INTO [dbo].[cart]\n"
                    + "           ([UserID]\n"
                    + "           ,[FilmID]\n"
                    + "           ,[SID]\n"
                    + "           ,[Position]\n"
                    + "           ,[price])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            for (int i = 0; i < c.getPositions().size(); i++) {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, c.getUserID());
                st.setString(2, c.getFilmID());
                st.setInt(3, c.getSch().getSID());
                st.setString(4, c.getPositions().get(i));
                st.setDouble(5, c.getPrice());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addSeatSchedule(Movie m) {
        try {
            String sql = "SELECT [SID]\n"
                    + "  FROM [dbo].[Schedule]";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt("SID");
                String sql1 = "SELECT [Position] FROM [dbo].[seat]";
                PreparedStatement st1 = connection.prepareStatement(sql1);
                ResultSet rs1 = st1.executeQuery();
                while (rs1.next()) {
                    String seat = rs1.getString("Position");
                    String sql2 = "INSERT INTO [dbo].[seatSchedule]\n"
                            + "     VALUES\n"
                            + "           (?\n"
                            + "           ,?\n"
                            + "           ,?\n"
                            + "           ,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, sid);
                    st2.setString(2, seat);
                    st2.setBoolean(4, true);
                    st2.setString(3, m.getFilmID());
                    st2.executeUpdate();;
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addSeatSchedule() {
        List<Movie> movie = getAllMovie();
        try {
            for (Movie m : movie) {
                String sql = "SELECT [SID]\n"
                        + "  FROM [dbo].[Schedule]";
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    int sid = rs.getInt("SID");
                    String sql1 = "SELECT [Position] FROM [dbo].[seat]";
                    PreparedStatement st1 = connection.prepareStatement(sql1);
                    ResultSet rs1 = st1.executeQuery();
                    while (rs1.next()) {
                        String seat = rs1.getString("Position");
                        String sql2 = "INSERT INTO [dbo].[seatSchedule]\n"
                                + "     VALUES\n"
                                + "           (?\n"
                                + "           ,?\n"
                                + "           ,?\n"
                                + "           ,?)";
                        PreparedStatement st2 = connection.prepareStatement(sql2);
                        st2.setInt(1, sid);
                        st2.setString(2, seat);
                        st2.setBoolean(4, true);
                        st2.setString(3, m.getFilmID());
                        st2.executeUpdate();;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Cart> getCartById(int id) {
        List<Cart> list = new ArrayList<>();
        String sql = "select distinct SID,FilmID from cart where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt("SID");
                String film = rs.getString("FilmID");
                String sql1 = "select * from cart where UserID=? and SID=? and FilmID=?";
                PreparedStatement st1 = connection.prepareStatement(sql1);
                st1.setInt(1, id);
                st1.setInt(2, sid);
                st1.setString(3, film);
                ResultSet rs1 = st1.executeQuery();
                List<String> positions = new ArrayList<>();
                Cart c = new Cart();
                while (rs1.next()) {
                    if (positions.isEmpty()) {
                        c.setOID(rs1.getInt("OID"));
                        c.setUserID(rs1.getInt("UserID"));
                        c.setFilmID(rs1.getString("FilmID"));
                        c.setSch(getScheduleById(rs1.getInt("SID")));
                        positions.add(rs1.getString("Position"));
                        c.setPriceCart(rs1.getFloat("price"));
                    } else {
                        positions.add(rs1.getString("Position"));
                    }

                }
                c.setPositions(positions);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Cart getCartByOID(int oid) {
        String sql = "select * from cart where OID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cart c = new Cart();
                c.setFilmID(rs.getString("FilmID"));
                c.setUserID(rs.getInt("UserID"));
                c.setSch(getScheduleById(rs.getInt("SID")));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteCart(int uid, int sid, String fid) {
        String sql = "DELETE FROM [dbo].[cart]\n"
                + "      WHERE SID=? and FilmID=? and UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            st.setInt(3, uid);
            st.setString(2, fid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean getStatusPosition(int sid, String posit, String film) {
        String sql = "SELECT *\n"
                + "  FROM [PRJ301_TICKET_FILM].[dbo].[seatSchedule] where SID=? and Position=? and FilmID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            st.setString(2, posit);
            st.setString(3, film);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("status");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public String checkPosition(int uid) {
        List<Cart> list = getCartById(uid);
        for (Cart i : list) {

            for (String p : i.getPositions()) {
                if (!getStatusPosition(i.getSch().getSID(), p, i.getFilmID())) {
                    return p;
                }
            }
        }
        return null;
    }

    public void makeOrder(int uid) {
        List<Cart> list = getCartById(uid);
        for (Cart i : list) {

            for (String p : i.getPositions()) {
                String sql = "UPDATE [dbo].[seatSchedule]\n"
                        + "   SET [Status] = 0\n"
                        + " WHERE SID=? and Position=? and FilmID=?";
                String sql1 = "INSERT INTO [dbo].[bill]\n"
                        + "           ([UserID]\n"
                        + "           ,[FilmID]\n"
                        + "           ,[SID]\n"
                        + "           ,[Position]\n"
                        + "           ,[price]\n"
                        + "           ,[time])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?)";
                try {
                    PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, i.getSch().getSID());
                    st.setString(2, p);
                    st.setString(3, i.getFilmID());
                    st.executeUpdate();
                    PreparedStatement st1 = connection.prepareStatement(sql1);
                    st1.setInt(1, uid);
                    st1.setString(2, i.getFilmID());
                    st1.setInt(3, i.getSch().getSID());
                    st1.setString(4, p);
                    st1.setFloat(5, i.getPrice());
                    st1.setDate(6, Date.valueOf(LocalDate.now()));
                    st1.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }

            }
        }
        String sql = "DELETE FROM [dbo].[cart]\n"
                + "      WHERE UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<String> getPositionBooked(int sid, String film) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "  FROM [PRJ301_TICKET_FILM].[dbo].[seatSchedule] where SID=? and FilmID=? and Status=0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);
            st.setString(2, film);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Position"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteFilm(String id) {
        String sql1 = "DELETE FROM [dbo].[seatSchedule]\n"
                + "      WHERE FilmID=?";
        String sql2 = "DELETE FROM [dbo].[Movie-Schedule]\n"
                + "      WHERE FilmID=?";
        String sql = "DELETE FROM [dbo].[Movie]\n"
                + "      WHERE FilmID=?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, id);
            st1.executeUpdate();
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setString(1, id);
            st2.executeUpdate();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public float getTurnoverOfMonth() {
        float ans = 0;
        String sql = "select distinct UserID,FilmID,SID,price from bill where time>='2022-7-1' and time<='2022-7-30'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ans += rs.getFloat("price");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ans;
    }

    public List<Movie> getAllTurnover() {
        List<Movie> list = getAllMovie();
        List<Movie> ans = new ArrayList<>();
        for (Movie i : list) {
            String sql = "select distinct UserID,FilmID,SID,price from bill where FilmID=?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, i.getFilmID());
                ResultSet rs = st.executeQuery();
                float sum = 0;
                while (rs.next()) {
                    sum += rs.getFloat("price");
                }
                if (sum > 0) {
                    i.setSumturnover(sum);
                    String sql1 = "select count(FilmID) as [sum] from bill where FilmID=?";
                    PreparedStatement st1 = connection.prepareStatement(sql1);
                    st1.setString(1, i.getFilmID());
                    ResultSet rs1 = st1.executeQuery();
                    if (rs1.next()) {
                        i.setSum(rs1.getInt("sum"));
                    }
                    ans.add(i);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        return ans;
    }

    public List<Movie> getMovieByWeek() {
        List<Movie> list = getAllMovie();
        List<Movie> ans = new ArrayList<>();
        for (Movie i : list) {
            String sql = "select distinct UserID,FilmID,SID,price from bill where FilmID=? and time>='2022-07-10' and time<='2022-07-16'";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, i.getFilmID());
                ResultSet rs = st.executeQuery();
                float sum = 0;
                while (rs.next()) {
                    sum += rs.getFloat("price");
                }
                if (sum > 0) {
                    i.setSumturnover(sum);
                    ans.add(i);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        return ans;
    }

    public List<Schedule> getStatSchedule() {
        List<Schedule> list = new ArrayList<>();
        try {
            for (int i = 1; i <= 5; i++) {
                Schedule s = getScheduleById(i);
                String sql = "select count(SID%10)as [Time] from bill where SID%10=?";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, i);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt("Time");
                    s.setCount(count);
                    list.add(s);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<User> getAllUser()
    {
        List<User> list=new ArrayList<>();
        String sql="SELECT *  FROM [dbo].[User]";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while (rs.next())
            {
                User u=new User();
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setName(rs.getString("name"));
                u.setUserID(rs.getInt("UserID"));
                u.setPhonenumber(rs.getString("phonenumber"));
                list.add(u);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public List<User> getUserCost()
    {
        List<User> list=getAllUser();
        try{
            for (User i: list)
        {
            String sql="select distinct UserID,FilmID,SID,price from bill where UserID=?";
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, i.getUserID());    
            ResultSet rs=st.executeQuery();
            float sum=0;
            while (rs.next())
            {
                sum+=rs.getFloat("price");
            }
            i.setCost(sum);
        }
        }catch(SQLException e){
            System.out.println(e);
        }
        Collections.sort(list,new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return (int)(o2.getCost()-o1.getCost());
            }
        });
        List<User> ans=new ArrayList<>();
        for (int i=0;i<Math.min(5, list.size());i++)
        {
            ans.add(list.get(i));
        }
        return ans;
    }
    public List<Movie> getMovieByMonth() {
        List<Movie> list = getAllMovie();
        List<Movie> ans = new ArrayList<>();
        for (Movie i : list) {
            String sql = "select distinct UserID,FilmID,SID,price from bill where FilmID=? and time>='2022-07-01' and time<='2022-07-31'";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, i.getFilmID());
                ResultSet rs = st.executeQuery();
                float sum = 0;
                while (rs.next()) {
                    sum += rs.getFloat("price");
                }
                if (sum > 0) {
                    i.setSumturnover(sum);
                    ans.add(i);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        return ans;
    }
    
    public static void main(String[] args) {
        DAO d = new DAO();
//        List<User> list = d.getUserCost();
//
//        for (User i : list) {
//            System.out.println(i.getCost());
//        }
    int a = d.getUserIDByUserAndPass("admin", "admin");
        System.out.println(a);
    }
}
