package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

public class Utilidades {

    /**
     * Variável para armazenar o retorno da SQL executada com executeQuery()
     */
    private static ResultSet resultSet;
    /**
     * Variável para armazenar o retorno do número de linhas afetadas com
     * executeUpdate();
     */
    private static int numLinhas;

    private static Session session;

    public static Session getSession() {
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = HibernateUtil.getSessionFactory().openSession();
        }
        return session;
    }

    public static void setSession(Session s) {
        session = s;
    }

    public static String getDataString(Date data) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(data);
    }

    /**
     * Executa uma SQL no banco de dados
     *
     * @param sql SQL a ser executada
     * @return O número de linhas afetadas pelo comando
     * @throws SQLException
     */
    public static int executeUpdate(String sql) throws SQLException {
        return executeUpdate(sql, new Object[]{});
    }

    /**
     * Executa uma SQL no banco de dados, pasando os parâmetros definidos em
     * parms para a SQL Ex.: executeUpdate("DELETE FROM tbl WHERE id = ? AND
     * nome = ?", new Object[]{id, name})
     *
     * @param sql SQL a ser executada
     * @param parms Array de parâmetros. Ex.: new Object[]{par1, par2, par3}
     * @return O número de linhas afetadas pelo comando
     * @throws SQLException
     */
    public static int executeUpdate(final String sql, final Object[] parms) throws SQLException {
        numLinhas = 0;
        Transaction t = getSession().beginTransaction();
        getSession().doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                if (parms.length > 0) {
                    PreparedStatement st = connection.prepareStatement(sql);
                    setParmsByType(st, parms);
                    numLinhas = st.executeUpdate();
                } else {
                    Statement st = connection.createStatement();
                    numLinhas = st.executeUpdate(sql);
                }
            }
        });
        t.commit();
        return numLinhas;
    }

    /**
     * Executa uma consulta no banco de dados
     *
     * @param sql SQL a ser executada
     * @return O ResultSet com o resultado da execução
     * @throws SQLException
     */
    public static ResultSet executeQuery(final String sql) throws SQLException {
        return executeQuery(sql, new Object[]{});
    }

    /**
     * Executa uma consulta no banco de dados, pasando os parâmetros definidos
     * em parms para a SQL Ex.: executeUpdate("SELECT * FROM tbl WHERE id = ?
     * AND nome = ?", new Object[]{id, name})
     *
     * @param sql SQL a ser executada
     * @param parms Array de parâmetros. Ex.: new Object[]{par1, par2, par3}
     * @return O ResultSet com o resultado da execução
     * @throws SQLException
     */
    public static ResultSet executeQuery(final String sql, final Object[] parms) throws SQLException {
        resultSet = null;
        getSession().doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                if (parms.length > 0) {
                    PreparedStatement st = connection.prepareStatement(sql);
                    setParmsByType(st, parms);
                    resultSet = st.executeQuery();
                } else {
                    Statement st = connection.createStatement();
                    resultSet = st.executeQuery(sql);
                }
            }
        });
        return resultSet;
    }

    private static void setParmsByType(PreparedStatement st, Object[] parms) throws SQLException {
        int count = 0;
        for (Object o : parms) {
            count++;
            // Como determinar o tipo da variável [http://stackoverflow.com/a/4964359/3136474]
            if (o instanceof String) {
                st.setString(count, (String) o);
            } else if (o instanceof Integer) {
                st.setInt(count, (Integer) o);
            } else if (o instanceof Double) {
                st.setDouble(count, (Double) o);
            } else if (o instanceof Float) {
                st.setFloat(count, (Float) o);
            } else if (o instanceof java.sql.Date) {
                st.setDate(count, (java.sql.Date) o);
            } else if (o instanceof java.sql.Time) {
                st.setTime(count, (java.sql.Time) o);
            } else if (o instanceof java.sql.Timestamp) {
                st.setTimestamp(count, (java.sql.Timestamp) o);
            } else if (o instanceof Boolean) {
                st.setBoolean(count, (boolean) o);
            } else if (o instanceof Character) {
                st.setString(count, String.valueOf((char) o));
            } else if (o == null) //Deficiência do Java, null = Integer. Não tente entender
            {
                st.setNull(count, java.sql.Types.INTEGER);
            }
        }
    }

    public static Object getSessionObject(String objName) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extCtx = ctx.getExternalContext();
        Map<String, Object> sessionMap = extCtx.getSessionMap();
        return sessionMap.get(objName);
    }

//    public static LoginBean getLoginBean() {
//        return (LoginBean) Utilidades.getSessionObject("loginBean");
//    }

    public static String encryptSHA(String text) {
        String output;

        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            java.math.BigInteger bigInt = new java.math.BigInteger(1, hash);
            output = bigInt.toString(16);
            while (output.length() < 64) {
                output = "0" + output;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }

        return output;
    }
}
