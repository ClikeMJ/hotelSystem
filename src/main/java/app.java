
import work.adminWork;
import work.roomWork;

import java.text.ParseException;
import java.util.Scanner;


public class app {
    public static void main(String[] args) throws ParseException {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("|\t\t\t\t 酒店后台管理系统 \t\t\t\t|");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(" 请选择你的身份： 1.系统管理员  2.房间管理员 ");
        Scanner input = new Scanner(System.in);
        int choose = input.nextInt();
        if(choose==1) {
            adminWork aw = new adminWork();
            aw.admin();
        }
        else {
            roomWork au = new roomWork();
            au.room();
        }
    }
}
