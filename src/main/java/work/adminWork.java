package work;

import service.adminService;
import service.roomService;
import service.impl.adminServiceImpl;
import service.impl.roomServiceImpl;

import java.util.Scanner;

public class adminWork {
    public  void  admin(){
    //登录
    roomService as = new roomServiceImpl();
    Scanner input = new Scanner(System.in);

    adminService ads = new adminServiceImpl();
    Boolean login  = ads.adminLogin();

    while (true){
    if(login==true){
        System.out.println("==========1.所有房间 2.搜索房间 3.删除房间 4.新建房间 5.修改房间 6.退出==========");
        System.out.println("请输入你的选择:");
        int choose=input.nextInt();
        switch (choose) {
            case 1:
                as.getAllRoomList();
                //打印作家目录
                break;
            case 2:
                as.getRoomByid();
                //查找作家
                break;
            case 3:
                as.delete();
                //删除作家
                break;
            case 4:
                as.save();
                //新增作家
                break;
            case 5:
                System.out.println("请输入订单编号");
                as.updateRoom(input.nextInt());
                break;
            case 6:
                return;

        }
    }
    else
         login  =  ads.adminLogin();;
    }
}
}
