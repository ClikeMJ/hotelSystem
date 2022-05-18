package service.impl;

import dao.roomDao;
import dao.impl.roomDaoImpl;
import domain.room;
import service.roomService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class roomServiceImpl implements roomService {
    @Override
    public void getAllRoomList() {
        roomDao roomDao = new roomDaoImpl();
        System.out.println("订单编号 \t房间序号 \t房间简介 \t退房日期 ");
        List<room> list = new ArrayList<>();
        list = roomDao.listRoom();
        for (room a:
                list) {
            System.out.println(a.getId()+" \t\t"+a.getName()+" \t\t"+a.getIntroduction()+" \t\t"+a.getCheckout_data()+" \t");
        }
    }

    @Override
    public void save() {
        roomDao roomdao = new roomDaoImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入房间序号：");
        String name = input.next();
        int number = roomdao.saveRoom(name);
        if (number!=0){
            System.out.println("添加成功");
        }
        else
            System.out.println("添加失败");

    }

    @Override
    public void delete() {
        Scanner input = new Scanner(System.in);
        roomDao roomDao = new roomDaoImpl();
        System.out.println("请输入要删除的订单编号：");
        int res = roomDao.removeRoom(input.nextInt());
        if(res>0)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
    }

    @Override
    public room roomLogin() {
        roomDao roomdao = new roomDaoImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入订单编号：");
        int id = input.nextInt();
        System.out.println("请输入房间密码：");
        String name = input.next();
        room au = roomdao.getRoomByidBypassword(id, name);
        if(au != null){
            System.out.println("登录成功");
            return au;
        }
        else
            System.out.println("账号或密码错误");
        return au;
    }

    @Override
    public void getRoomByid() {
        Scanner input   =   new Scanner(System.in);
        roomDao roomdao = new roomDaoImpl();
        System.out.println("请输入订单编号：");
        room au = roomdao.getRoomByid(input.nextInt());
        if(au== null){
            System.out.println("输入有误，无法查询");
            return;
        }
        System.out.println("订单编号 \t房间序号 \t房间简介 \t退房日期 ");
        System.out.println(au.getId()+" \t\t"+au.getName()+" \t\t"+au.getIntroduction()+" \t\t"+au.getCheckout_data()+"\t");
    }

    @Override
    public void updateRoom(Integer id)  {
        Scanner input = new Scanner(System.in);
        roomDao roomdao = new roomDaoImpl();
        room room = roomdao.getRoomByid(id);
        System.out.println(room);

        System.out.println("是否更新房间序号（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入房间序号：");
            room.setName(input.next());
        }
        System.out.println("是否更新房间介绍（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入房间介绍：");
            room.setIntroduction(input.next());
        }
        System.out.println("是否更新退房日期（y/n）");
        if(input.next().equals("y")){
            System.out.println("请输入退房日期：");
            String da = input.next();
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(da);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            room.setCheckout_data(date);
        }

        int result = roomdao.updateRoom(room);
        if(result>0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");
    }

    @Override
    public void updateRoomPassword(Integer id) {
        Scanner input= new Scanner(System.in);
        roomDao roomdao = new roomDaoImpl();
        System.out.println("请输入新密码");
        String oldPass = input.next();
        System.out.println("请再次输入密码");
        String newPass =input.next();
        if(oldPass.equals(newPass)){
            int res = roomdao.upDateRoomBypassword(id, newPass);
            if(res>0)
                System.out.println("修改成功");
            else
                System.out.println("修改失败");
        }
        else
            System.out.println("操作失败，两次密码不相同");
    }
}
