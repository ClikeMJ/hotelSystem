package service.impl;

import dao.touristDao;
import dao.impl.touristDaoImpl;
import domain.tourist;
import service.touristService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class touristServiceImpl implements touristService {
    @Override
    public void getAllTourist(Integer roomId) {
        touristDao touristdao = new touristDaoImpl();
        System.out.println("订单编号 \t房客姓名 \t房客详情 \t  ");
        List<tourist> list = new ArrayList<>();
        list = touristdao.ListTouristByBusinessid(roomId);
        for (tourist b :
                list) {
            System.out.println(b.getId() + " \t\t" + b.getName() + " \t\t" + b.getIntroduction() + " \t\t");
        }
    }

    @Override
    public void save(Integer roomId) {
        tourist bk= new tourist();
        touristDao touristdao = new touristDaoImpl();
        Scanner input= new Scanner(System.in);
        bk.setRoomId(roomId);
        System.out.println("请输入房客的名字");
        bk.setName(input.next());
        System.out.println("请输入房客详情");
        bk.setIntroduction(input.next());
        int res = touristdao.saveTourist(bk);
        if(res>0)
            System.out.println("新增成功");
        else
            System.out.println("新增失败");
    }

    @Override
    public void getTouristById(Integer touristid) {
        touristDao touristdao = new touristDaoImpl();
        tourist tourist = touristdao.getTouristByid(touristid);
        if(tourist==null){
            System.out.println("输入有误，无法查询");
            return;
        }

        System.out.println("房客编号 \t房客姓名 \t房客详情 \t  ");
        System.out.println(tourist.getId() + " \t\t" + tourist.getName() + " \t\t" + tourist.getIntroduction() + " \t\t");
    }

    @Override
    public void updateTourist(Integer touristid) {
        Scanner input = new Scanner(System.in);
        touristDao touristDao = new touristDaoImpl();
        tourist bk = touristDao.getTouristByid(touristid);
        if(bk!=null) {
            System.out.println(bk);
        }
        else {
            System.out.println("输入有误，无法查询");
            return;
        }
        System.out.println("是否更新房客姓名（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入房客姓名：");
            bk.setName(input.next());
        }
        System.out.println("是否更新房客详情（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入房客详情：");
            bk.setIntroduction(input.next());
        }


        int result = touristDao.upDateMenu(bk);
        if(result>0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");

    }

    @Override
    public void deleteTourist(Integer touristid) {
        Scanner input = new Scanner(System.in);
        touristDao touristDao = new touristDaoImpl();
        int res = touristDao.removeTourist(touristid);
        if(res>0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");

    }
}
