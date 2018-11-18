import com.bmsoft.canteensystem.dao.MenuDao;
import com.bmsoft.canteensystem.entity.Menu;
import com.bmsoft.canteensystem.service.MenuService;
import com.bmsoft.canteensystem.serviceimpl.MenuServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.io.IOException;
import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class test {
    private MenuDao menuDao;
    private MenuService menuService;

    @Test
    public void test(){
        Menu menu=menuService.findById(1);
        System.out.println(menu);
    }
}
