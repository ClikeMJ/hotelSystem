package service.impl;

import dao.adminDao;
import dao.impl.adminDaoImpl;
import domain.admin;
import service.adminService;

import java.sql.SQLException;
import java.util.Scanner;

public class adminServiceImpl  implements adminService {
    @Override
    public Boolean adminLogin()  {
        adminDao admindao= new adminDaoImpl();
        admin ad = new admin();

        Scanner input= new Scanner(System.in);
        System.out.println("请输入账号：");
        ad.setId(input.nextInt());
        System.out.println("请输入密码：");
        ad.setPassword(input.next());
        try {
            admin an = admindao.getadminByidBypassword(ad);
            if(an == null){
                System.out.println("账号或密码错误");
                return false;
            }
            else
                System.out.println("登录成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
