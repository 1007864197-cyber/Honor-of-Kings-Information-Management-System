import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataManager dm = new DataManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 王者荣耀信息管理系统（控制台） ===");

        System.out.print("请输入用户名 (或输入 exit 退出): ");
        String username = scanner.nextLine();
        if ("exit".equalsIgnoreCase(username)) {
            System.out.println("已退出。");
            scanner.close();
            return;
        }

        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        if (!dm.verifyCredentials(username, password)) {
            System.out.println("登录失败：用户名或密码错误。");
            scanner.close();
            return;
        }

        Person user = dm.getUserByUsername(username);
        if (user instanceof Admin) {
            adminMenu((Admin) user, dm, scanner);
        } else if (user instanceof Player) {
            playerMenu((Player) user, dm, scanner);
        } else {
            System.out.println("未知用户类型，无法进入系统。");
        }

        scanner.close();
    }

    private static void adminMenu(Admin admin, DataManager dm, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Admin 菜单（" + admin.getName() + "）---");
            System.out.println("1. 创建玩家（示例）");
            System.out.println("2. 删除玩家（示例）");
            System.out.println("3. 修改玩家（示例）");
            System.out.println("4. 查询数据（展示统计）");
            System.out.println("0. 退出");
            System.out.print("请选择: ");
            String line = scanner.nextLine();
            int choice = -1;
            try { choice = Integer.parseInt(line); } catch (Exception e) { }
            switch (choice) {
                case 1:
                    System.out.println("创建玩家：此处放置创建逻辑（练习时实现）。");
                    break;
                case 2:
                    System.out.println("删除玩家：此处放置删除逻辑（练习时实现）。");
                    break;
                case 3:
                    System.out.println("修改玩家：此处放置修改逻辑（练习时实现）。");
                    break;
                case 4:
                    printDataSummary(dm);
                    break;
                case 0:
                    System.out.println("管理员已退出。");
                    return;
                default:
                    System.out.println("无效选项，请重试。");
            }
        }
    }

    private static void playerMenu(Player player, DataManager dm, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Player 菜单（" + player.getName() + "）---");
            System.out.println("1. 查看个人资料");
            System.out.println("2. 查看我的英雄");
            System.out.println("3. 查看战队列表");
            System.out.println("4. 查看排行榜（示例）");
            System.out.println("0. 退出");
            System.out.print("请选择: ");
            String line = scanner.nextLine();
            int choice = -1;
            try { choice = Integer.parseInt(line); } catch (Exception e) { }
            switch (choice) {
                case 1:
                    printPlayerProfile(player);
                    break;
                case 2:
                    printPlayerHeroes(player);
                    break;
                case 3:
                    printTeams(dm.getTeams());
                    break;
                case 4:
                    System.out.println("排行榜：此处展示示例排行榜（练习时实现）。");
                    break;
                case 0:
                    System.out.println("玩家已退出。");
                    return;
                default:
                    System.out.println("无效选项，请重试。");
            }
        }
    }

    private static void printDataSummary(DataManager dm) {
        System.out.println("--- 数据统计 ---");
        System.out.println("玩家数量: " + dm.getPlayers().size());
        System.out.println("管理员数量: " + dm.getAdmins().size());
        System.out.println("战队数量: " + dm.getTeams().size());
        System.out.println("英雄数量: " + dm.getHeroes().size());
        System.out.println("装备数量: " + dm.getEquipments().size());
        System.out.println("比赛记录数量: " + dm.getMatchRecords().size());
    }

    private static void printPlayerProfile(Player p) {
        System.out.println("--- 个人资料 ---");
        System.out.println("ID: " + p.getId());
        System.out.println("姓名: " + p.getName());
        System.out.println("Email: " + p.getEmail());
        System.out.println("等级: " + p.getLevel());
        System.out.println("位置: " + p.getRole());
    }

    private static void printPlayerHeroes(Player p) {
        System.out.println("--- 我的英雄 ---");
        if (p.getHeroes() == null || p.getHeroes().isEmpty()) {
            System.out.println("暂无英雄信息。");
            return;
        }
        for (Hero h : p.getHeroes()) {
            System.out.println(h.getId() + " - " + h.getName() + " (" + h.getType() + ")");
        }
    }

    private static void printTeams(List<Team> teams) {
        System.out.println("--- 战队列表 ---");
        for (Team t : teams) {
            System.out.println(t.getId() + " - " + t.getName() + " (成员: " + t.getMembers().size() + ")");
        }
    }
}
