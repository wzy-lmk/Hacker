package team.AI.serviceIMP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import team.AI.service.ResultPageService;
import team.AI.utils.DBUtiles;
import java.sql.SQLException;
import java.util.List;

/***
 * @author 王智源
 */
public class ResultPageServiceImpl implements ResultPageService {

    private QueryRunner query=new QueryRunner(DBUtiles.getDataSource());
    @Override
    public List<String> getTaskIds(String email, String type) {
        String sql="select taskid from tasks where email=? and type=?";
        try {
            return this.query.query(sql, new ColumnListHandler<>("taskid"),new Object[]{email,type});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getResultBean(String taskId) {
        String sql = "select content from result where taskid = ?";
        try {
            String query = this.query.query(sql, new ScalarHandler<String>(), taskId);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPageHtml(java.lang.String email, java.lang.String type){
        StringBuilder builder = new StringBuilder();
        List<String> taskIds = getTaskIds(email, type);
        for (String taskId : taskIds) {
            String resultBean = getResultBean(taskId);
            builder.append(resultBean);
        }
        return builder.toString();
    }


}
