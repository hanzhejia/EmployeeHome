import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class T0632Z_2 {
    static Scanner input = new Scanner(System.in);
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        m10();
    }

    static void m1() {
        System.out.println("Enter point 1 (latitude and longitude) in degrees:");
        System.out.println("Enter point 2 (latitude and longitude) in degrees:");
        Scanner input = new Scanner(System.in);
        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();
        double d = input.nextDouble();
        double x1 = Math.toRadians(a);
        double y1 = Math.toRadians(b);
        double x2 = Math.toRadians(c);
        double y2 = Math.toRadians(d);
        double e = Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2);
        double f = 6371.01 * Math.acos(e);
        System.out.println("The distance between the points is" + f + "km");

    }

    static void m2() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of sides:");
        System.out.println("Enter the side:");
        double n = input.nextDouble();
        double s = input.nextDouble();
        double c = (n * s * s) / (4 * Math.tan(Math.PI / n));
        System.out.println("The area of the poiygon is" + c);

    }

    static void m3() {
        double a1 = (double) (Math.random() * Math.PI * 2);
        double a2 = (double) (Math.random() * Math.PI * 2);
        double a3 = (double) (Math.random() * Math.PI * 2);
        int r = 40;
        double x1 = r * Math.cos(a1);
        double y1 = r * Math.sin(a1);
        double x2 = r * Math.cos(a2);
        double y2 = r * Math.sin(a2);
        double x3 = r * Math.cos(a3);
        double y3 = r * Math.sin(a3);
        double a = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
        double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
        double c = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        double C = Math.toDegrees(Math.acos((c * c - a * a - b * b) / (-2 * a * b)));
        A = Math.round(A * 100) / 100.0;
        B = Math.round(B * 100) / 100.0;
        C = Math.round(C * 100) / 100.0;
        System.out.println("A=" + A + "B=" + B + "C=" + C);
    }

    static void m4() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an ASCII code :");
        int a = input.nextInt();
        int b = a;
        char c = (char) b;
        System.out.println("The character for ASCII code " + a + " " + "is" + c);
    }

    static void m5() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a character :");
        char a = (char) input.next().charAt(0);
        int b = (int) a;
        System.out.println("The Unicode for the character " + a + " " + "is" + b);
    }

    static void m6() {
        System.out.println("Enter a hex character :");
        char mon = (char) input.next().charAt(0);
        if (mon < 'G') {
            switch (mon) {
                case '0':
                    System.out.println("0000");
                    break;
                case '1':
                    System.out.println("0001");
                    break;
                case '2':
                    System.out.println("0010");
                    break;
                case '3':
                    System.out.println("0011");
                    break;
                case '4':
                    System.out.println("0100");
                    break;
                case '5':
                    System.out.println("0101");
                    break;
                case '6':
                    System.out.println("0110");
                    break;
                case '7':
                    System.out.println("0111");
                    break;
                case '8':
                    System.out.println("1000");
                    break;
                case '9':
                    System.out.println("1001");
                    break;
                case 'A':
                    System.out.println("1010");
                    break;
                case 'B':
                    System.out.println("1011");
                    break;
                case 'C':
                    System.out.println("1100");
                    break;
                case 'D':
                    System.out.println("1101");
                    break;
                case 'E':
                    System.out.println("1110");
                    break;
                case 'F':
                    System.out.println("1111");
                    break;
            }
            System.out.println("The numeric walue grade:" + mon);
        } else
            System.out.println(mon + " " + "is an invalid grade");
    }

    static void m7() {
        System.out.println("Enter a hex character :");
        char mon = (char) input.next().charAt(0);
        if (mon < 'E' || mon == 'F') {
            switch (mon) {
                case 'A':
                    System.out.println("0");
                    break;
                case 'B':
                    System.out.println("1");
                    break;
                case 'C':
                    System.out.println("2");
                    break;
                case 'D':
                    System.out.println("3");
                    break;
                case 'F':
                    System.out.println("4");
                    break;
            }
            System.out.println("The numeric walue for grade:" + mon);
        } else
            System.out.println(mon + " " + "is an invalid grade");
    }

    static void m8() {
        System.out.println("Enter an uppercase letter :");
        char a = (char) input.next().charAt(0);
        int mon = (int) a;
        if ((65 <= mon && mon <= 67) || (97 <= mon && mon <= 99))
            mon = 67;
        if ((68 <= mon && mon <= 70) || (100 <= mon && mon <= 102))
            mon = 70;
        if ((71 <= mon && mon <= 73) || (103 <= mon && mon <= 105))
            mon = 73;
        if ((74 <= mon && mon <= 76) || (106 <= mon && mon <= 108))
            mon = 76;
        if ((77 <= mon && mon <= 79) || (109 <= mon && mon <= 111))
            mon = 79;
        if ((80 <= mon && mon <= 83) || (112 <= mon && mon <= 115))
            mon = 83;
        if ((84 <= mon && mon <= 86) || (116 <= mon && mon <= 118))
            mon = 86;
        if ((87 <= mon && mon <= 90) || (119 <= mon && mon <= 122))
            mon = 90;
        if ((65 <= mon & mon <= 90)) {
            switch (mon) {
                case 67:
                    System.out.println("The corresponding number is:2");
                    break;
                case 70:
                    System.out.println("The corresponding number is:3");
                    break;
                case 73:
                    System.out.println("The corresponding number is:4");
                    break;
                case 76:
                    System.out.println("The corresponding number is:5");
                    break;
                case 79:
                    System.out.println("The corresponding number is:6");
                    break;
                case 83:
                    System.out.println("The corresponding number is:7");
                    break;
                case 86:
                    System.out.println("The corresponding number is:8");
                    break;
                case 90:
                    System.out.println("The corresponding number is:9");
                    break;
            }
        } else
            System.out.println(mon + " " + "is an invalid input");
    }

    static void m9() {
        System.out.println("Enter a year:");
        System.out.println("Enter a month(first three letters with the first ):");
        int a = input.nextInt();
        String mon = input.nextLine();
        String Feb;
        if (a % 4.0 == 0)
            Feb = "Feb1";
        else
            Feb = "Feb2";
        switch (mon) {
            case "Jan":
                System.out.println(mon + " " + a + " " + "has 31 days");
                break;
            case "Feb1":
                System.out.println(mon + " " + a + " " + "has 29 days");
                break;
            case "Feb2":
                System.out.println(mon + " " + a + " " + "has 28 days");
                break;
            case "Mar":
                System.out.println(mon + " " + a + " " + "has 31 days");
                break;
            case "Apr":
                System.out.println(mon + " " + a + " " + "has 30 days");
                break;
            case "May":
                System.out.println(mon + " " + a + " " + "has 31 days");
                break;
            case "Jun":
                System.out.println(mon + " " + a + " " + "has 30 days");
                break;
            case "Jul":
                System.out.println(mon + " " + a + " " + "has 31 days");
                break;
            case "Aug":
                System.out.println(mon + " " + a + " " + "has 31 days");
                break;
            case "Sep":
                System.out.println(mon + " " + a + " " + "has 30 days");
                break;
            case "Oct":
                System.out.println(mon + " " + a + " " + "has 31 days");
                break;
            case "Nov":
                System.out.println(mon + " " + a + " " + "has 30 days");
                break;
            case "Dec":
                System.out.println(mon + " " + a + " " + "has 31 days");
                break;
        }
    }

    static void m10() {
        //java��330
        String[] SSN = new String[11];
        for (int i = 0; i < SSN.length; ) {
            SSN[i] = input.next();
            i++;
        }
        if (SSN[4] == "-" & SSN[7] == "-")
            System.out.println("SSN" + " " + "is a valid social security number");
        else
            System.out.println("SSN" + " " + "is a invalid social security number");
    }

    static void m11() {
        String str1 = input.next();
        String str2 = input.next();
        if (str1.indexOf(str2) >= 0) {
            System.out.println(str2 + " " + "is a substring of" + str1 + " ");
        } else {
            System.out.println(str2 + " " + "is not a substring of" + str1 + " ");
        }
    }

    static void m12() {
        System.out.println("Enter employee's name:");
        System.out.println("Enter anumber of hours worked in a week:");
        System.out.println("Enter hourly pay rate:");
        System.out.println("Enter federal tax withholding rate:");
        System.out.println("Enter state tax withholding rate:");
        String str1 = input.next();
        double a = input.nextDouble();
        double b = input.nextDouble();
        double c = input.nextDouble();
        double d = input.nextDouble();
        double e = a * b;
        double f = e * c;
        double g = e * d;
        double h = f + g;
        double i = e - h;
        NumberFormat nt = NumberFormat.getPercentInstance();
        System.out.println("Employee Name :" + str1);
        System.out.println("Hour worked:" + a);
        System.out.println("Pay Rate:$" + b);
        System.out.println("Gross Pay$:" + e);
        System.out.println("Deduction:");
        System.out.println("\tFederal Withholding(" + nt.format(c) + "):$" + f);
        System.out.println("\tState Withholding(" + nt.format(d) + "):$" + g);
        System.out.println("\tTotal Deduction:$" + h);
        System.out.println("Net Pay:\t$" + i);
    }

    static void m13() {
        System.out.println("Enter the first city:");
        System.out.println("Enter the second city:");
        System.out.println("Enter the third city:");
        List<String> list = new ArrayList<String>();
        list.add(input.nextLine());
        list.add(input.nextLine());
        list.add(input.nextLine());
        Collections.sort(list);
        System.out.println("The names are :" + list);
    }

    static void m14() {
        char a1, b1, c1, d1, f1, g1, h1;
        a1 = (char) (65 + (Math.random() * 90 + 1 - 65));
        b1 = (char) (65 + (Math.random() * 90 + 1 - 65));
        c1 = (char) (65 + (Math.random() * 90 + 1 - 65));
        d1 = (char) ('0' + Math.random() * 10);
        f1 = (char) ('0' + Math.random() * 10);
        g1 = (char) ('0' + Math.random() * 10);
        h1 = (char) ('0' + Math.random() * 10);
        char[] hao = new char[7];
        hao[0] = a1;
        hao[1] = b1;
        hao[2] = c1;
        hao[3] = d1;
        hao[4] = f1;
        hao[5] = g1;
        hao[6] = h1;
        String hao1 = new String(hao);
        System.out.println("The names are :" + hao1);
    }

    static void m15() {
        System.out.println("ǧ��" + "\t��");
        for (int i = 1; i < 200; i += 2) {
            double b = i * 2.2;
            System.out.println(i + "  " + "\t" + b);
        }
    }

    static void m16() {
        System.out.println("Ӣ��" + "\tǧ��");
        for (int i = 1; i <= 10; i++) {
            double b = i * 1.609;
            System.out.println(i + "  " + "\t " + b);
        }
    }

    static void m17() {
        System.out.println("Ӣ��" + "\tǧ��" + "\tǧ��" + "\tӢ��");
        for (int i = 0; i < 10; i++) {
            for (int k = 1; k <= 10; k++) {
                double b = i * 1.609;
                System.out.println(k + "  " + b + "\t ");
            }
            for (int p = 20; p <= 65; p += 5) {
                double e = p / 1.609;
                System.out.println(p + "\t " + e + "\t ");
            }
            System.out.println();
        }
    }

    static void m18() {
        int num = input.nextInt();
        String[] name = new String[num];
        int[] cheng = new int[num];
        for (int i = 0; i < num; i++) {
            name[i] = input.next();
            cheng[i] = input.nextInt();
        }
        int Max = cheng[0];
        int a = 0;
        for (int k = 1; k < cheng.length; k++) {
            if (Max < cheng[k]) {
                Max = cheng[k];
                a = k;
            }
        }
        System.out.println("�����" + name[a]);
    }

    static void m19() {
        int num = in.nextInt();
        int[] flag = new int[2];
        double max, second;
        String[] name = new String[num];
        double[] score = new double[num];
        for (int i = 0; i < num; i++) {
            name[i] = in.next();
            score[i] = in.nextDouble();
        }
        max = score[0];
        for (int i = 1; i < num; i++) {
            if (max < score[i]) {
                max = score[i];
                flag[0] = i;
            }
        }
        if (flag[0] != 0)
            second = score[0];
        else {
            second = score[1];
            flag[1] = 1;
        }
        for (int i = 1; i < num; i++) {
            if (i != flag[0]) {
                if (second < score[i]) {
                    second = score[i];
                    flag[1] = i;
                }
            }
        }
        System.out.println(name[flag[0]] + "       " + name[flag[1]]);
    }

    static void m20() {
        int flag = 0;
        for (char i = '!'; i <= '~'; i++) {
            System.out.printf("%c", i);
            flag++;
            if (flag % 10 == 0) {
                System.out.println();
            }
        }
    }

    static void m21() {
        int num = in.nextInt();
        int[] x = new int[20];
        int n = 0;
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                num /= i;
                x[n++] = i;
            }
        }
        for (int i = 0; i < x.length; i++)
            if (x[i] != 0)
                System.out.printf("%d ", x[i]);
    }

    static void m22() {
        int n = in.nextInt();
        int num;
        int l = 2 * (n - 1);
        int m = 0;
        for (int i = 0; i < n; i++) {
            num = 1;
            m = 0;
            for (int b = l; b > 0; b--)
                System.out.print(" ");
            l -= 2;
            for (int k = 0; k < 2 * i + 1; k++) {
                System.out.print(num + " ");
                if (m < i) {
                    num *= 2;
                    m++;
                } else {
                    num /= 2;
                }

            }
            System.out.println();
        }
    }

    static void m23() {
        double all = in.nextDouble();
        int year = in.nextInt();
        double rate = in.nextDouble();
        rate = 7.0 / 100 / 12;
        double x = (all * rate * Math.pow(1 + rate, year * 12));
        double x2 = Math.pow(1 + rate, year * 12) - 1;
        double Month_Pay = x / x2;
        double Total_Pay = Month_Pay * 12;
        double Inter = all * rate;
        double Prin = Month_Pay - Inter;
        double Balance = all;
        System.out.println("Monthly Payment " + Month_Pay);
        System.out.println("Total Payment  " + Total_Pay);
        System.out.println("Payment   Interest  Principal  Balance");
        for (int i = 1; i <= year * 12; i++) {
            Inter = Balance * rate;
            Prin = Month_Pay - Inter;
            Balance -= Prin;
            System.out.printf("   %d          %.2f         %.2f        %.2f\n", i, Inter, Prin, Balance);
        }
    }

    static void m24() {
        double sum = 0;
        for (int i = 1; i <= 97; i += 2) {
            sum += ((double) i / (i + 2));
        }
        System.out.println(sum);
    }

    static void m25() {
        double PI = 0;
        int k = 1;
        int x = 1;
        // int n = in.nextInt();
        for (int p = 10000; p <= 100000; p += 10000) {
            for (int i = 1; i <= p; i++) {
                PI += (double) k / x;
                x += 2;
                k = -k;
            }
            System.out.println(p + "" + PI * 4);
        }

    }

    static void m26() {
        int year = in.nextInt();
        int week = in.nextInt();
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 && year % 4 == 0 && year % 100 != 0)
            days[1] += 1;
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sec", "Oct", "Nov", "Dec"};
        for (int i = 0; i < 12; i++) {
            if (week == 0)
                week += 7;
            System.out.println(months[i] + " 1," + year + " is " + week);
            week += (days[i] % 7);
            week %= 7;
        }
    }

    static void m27() {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sec", "Oct", "Nov", "Dec"};
        int year = in.nextInt();
        int week = in.nextInt();
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 400 == 0 && year % 4 == 0 && year % 100 != 0)
            days[1] += 1;
        for (int i = 0; i < 12; i++) {
            System.out.println(months[i] + " " + year);
            System.out.println("-------------------------");
            System.out.println("Sun  Mon  Tue  Wed  Thu  Fri  Sat");
            for (int n = 0; n < week; n++) {
                System.out.print("     ");
            }
            for (int k = 1; k < days[i]; k++) {
                System.out.print(k + "    ");
                if ((k + week) % 7 == 0)
                    System.out.println();
            }
            System.out.println();
            week += (days[i] % 7);
            week %= 7;
        }
    }

    static void m28() {
        int sum;
        for (int i = 2; i <= 10000; i++) {
            sum = 0;
            for (int n = 1; n <= i / 2; n++) {
                if (i % n == 0) {
                    sum += n;
                }
                if (sum > i)
                    break;
            }
            if (sum == i)
                System.out.println(i);
        }
    }

    static void m29() {
        int your, computer;
        int flag = 0;
        String[] str = new String[3];
        str[0] = "scissor";
        str[1] = "rock";
        str[2] = "paper";
        while (flag < 2) {
            your = in.nextInt();
            computer = (int) (Math.random() * 3);
            if (your == computer)
                System.out.println("your is " + str[your] + "  computer is " + str[computer] + "       no winner");
            else if (your - computer == 2 || your - computer == -1) {
                System.out.println("your is " + str[your] + "  computer is " + str[computer] + "       your are winner");
                flag++;
            } else
                System.out.println("your is " + str[your] + "  computer is " + str[computer] + "       computer are winner");
        }
    }

    static void m30() {
        int num = in.nextInt();
        System.out.printf("%o", num);
    }

    static void m31() {
        double x;
        x = (30000 - 5000) - 5000 * 0.08 - 5000 * 0.1;
        x /= 0.12;
        System.out.println(x + 10000);
    }

    static void m32() {
        int coin;
        int a = 0, b = 0;
        for (int i = 0; i < 1000000; i++) {
            coin = (int) (Math.random() * 2);
            if (coin == 0)
                a += 1;
            else b += 1;
        }
        System.out.println("����:" + a + "��" + "  ����" + b + "��");
    }

    static void m33() {
        int count = 1;
        int max;
        int i = in.nextInt();
        max = i;
        while (i != 0) {
            i = in.nextInt();
            if (i > max) {
                max = i;
                count = 1;
            } else if (i == max)
                count++;
        }
        System.out.println(max + "   " + count);
    }

    static void m34() {
        System.out.print("Ten Numbers:  ");
        double[] nums = new double[10];
        double average = 0;
        double sum = 0;
        double s;
        for (int i = 0; i < 10; i++) {
            nums[i] = in.nextDouble();
            sum += Math.pow(nums[i], 2);
            average += nums[i];
        }
        s = Math.sqrt((sum - Math.pow(average, 2) / 10) / 9);
        average /= 10;
        System.out.println(average + "   " + s);
    }

    static void m35() {
        in.nextLine();
        String str = in.nextLine();
        if (str.length() == 12) {
            int[] s = new int[12];
            for (int i = 0; i < 12; i++)
                s[i] = (int) str.charAt(i) - 48;
            int x = 10 - (s[0] + 3 * s[1] + s[2] + 3 * s[3] + s[4] + 3 * s[5] + s[6] + 3 * s[7] + s[8] + 3 * s[9] + s[10] + 3 * s[11]) % 10;
            if (x == 10)
                x = 0;
            for (int i = 0; i < 12; i++) {
                System.out.print(s[i]);
            }
            System.out.println(x);
        } else System.out.println("ERROR");
    }

    static void m36() {
        in.nextLine();
        String str1 = in.nextLine();
        String str2 = in.nextLine();
        char x1, x2;
        int flag = 1;
        x1 = str1.charAt(0);
        x2 = str2.charAt(0);
        if (x1 == x2) {
            if (str1.length() > str2.length()) {
                for (int i = 0; i < str2.length(); i++) {
                    x1 = str1.charAt(i);
                    x2 = str2.charAt(i);
                    if (x1 == x2) {
                        System.out.print(x1);
                    } else {
                        flag = 0;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < str1.length(); i++) {
                    x1 = str1.charAt(i);
                    x2 = str2.charAt(i);
                    if (x1 == x2) {
                        System.out.print(x1);
                    } else {
                        flag = 0;
                        break;
                    }
                }
            }
        } else
            System.out.print("None");
    }
}
