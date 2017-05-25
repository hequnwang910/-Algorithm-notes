import java.util.Scanner;

public class Main {
    public static String[] result;
    
    public char[] check(char[][] map, int i, int j) {
        char[] r = {'+', '+'};
        //检测行
        boolean ring = true;
        if(j == 0) {
            if(map[i][j + 1] == '-' || (map[i][j + 1] == '0' && map[i][j + 2] == '-'))
                ring = false;
        } else if(j == 1) {
            if(map[i][j - 1] == '-' || map[i][j + 1] == '-')
                ring = false;
        } else if(j == 2) {
            if(map[i][j - 1] == '-' || (map[i][j - 1] == '0' && map[i][j - 2] == '-'))
                ring = false;
        }
        //检测列
        boolean row = true;
        if(i == 0) {
            if(map[i + 1][j] == '1' || (map[i + 1][j] == '0' && map[i + 2][j] == '1'))
                row = false;
        } else if(i == 1) {
            if(map[i - 1][j] == '1' || map[i - 1][j] == '1')
                row = false;
        } else if(i == 2) {
            if(map[i - 1][j] == '1' || (map[i - 1][j] == '0' && map[i - 2][j] == '1'))
                row = false;
        }
        
        if(ring == true && row == true) {
            r[0] = '1';
            r[1] = '-';
        }
        if(ring == true && row == false) {
            r[0] = '-';
            r[1] = '+';
        }
        if(ring == false && row == true) {
            r[0] = '1';
            r[1] = '+';
        }
        if(ring == false && row == false){
            r[0] = '+';
            r[1] = '+';
        }
        return r;
    }
    
    public int getCount(char[][] tempMap) {
        int count = 0;
        for(int i = 0;i < 3;i++)
            for(int j = 0;j < 3;j++) {
                char[] r = check(tempMap, i, j);
                if(r[0] != '+')
                    count++;
            }
        return count;
    }
    
    public void getResult(char[][] map, int x) {
        char[][] tempMap = new char[3][3];
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < 3;i++)
            for(int j = 0;j < 3;j++)
                tempMap[i][j] = map[i][j];
        for(int i = 0;i < 3;i++) {
            for(int j = 0;j < 3;j++) {
                if(map[i][j] == '0') {
                    char[] r = check(map, i, j);
                    for(int k = 0;k < 2;k++) {
                        if(r[k] != '+') {
                            tempMap[i][j] = r[k];
                            int count = getCount(tempMap);
                            tempMap[i][j] = '0';
                            if(count < min) {
                                min = count;
                                result[x] = "" + i+ j + r[k];
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Main test = new Main();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        result = new String[n];
        for(int i = 0;i < n;i++) {
            char[][] map = new char[3][3];
            String[] temp = new String[3];
            for(int j = 0;j < 3;j++) {
                temp[j] = in.next();
                map[j] = temp[j].toCharArray();
            }
            test.getResult(map, i);
        }
        for(int i = 0;i < result.length;i++)
            System.out.println(result[i]);
    }
}
