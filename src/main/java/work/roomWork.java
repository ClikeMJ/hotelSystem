package work;

import domain.room;
import service.roomService;
import service.impl.roomServiceImpl;
import service.impl.touristServiceImpl;

import java.util.Scanner;

public class roomWork {
    public  void  room(){
        //登录
        roomService as = new roomServiceImpl();
        Scanner input = new Scanner(System.in);

        touristServiceImpl bsi =new touristServiceImpl() ;
        room au  = as.roomLogin();

        while (true){
            if(au!=null){
                System.out.println("==========1.所有房客 2.搜索房客 3.删除房客 4.添加房客 5.修改房客 6.修改密码 7.修改房间信息 8.退出==========");
                System.out.println("请输入你的选择:");
                int choose=input.nextInt();
                switch (choose) {
                    case 1:
                        bsi.getAllTourist(au.getId());
                        break;
                    case 2:
                        System.out.println("请输入房客的订单编号");
                        bsi.getTouristById(input.nextInt());
                        break;
                    case 3:
                        System.out.println("请输入要删除房客的订单编号");
                        bsi.deleteTourist(input.nextInt());
                        break;
                    case 4:
                        bsi.save(au.getId());
                        break;
                    case 5:
                        System.out.println("请输入要修改房客的订单编号");
                        bsi.updateTourist(input.nextInt());
                        break;
                    case 6:
                        as.updateRoomPassword(au.getId());
                        break;
                    case 7:
                        as.updateRoom(au.getId());
                        break;
                    case 8:
                        return;
                }
            }
            else
                au  = as.roomLogin();
        }
    }
}
