package na.com;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
	{
	    showMenu();
	    int action = getAction();

	    if (action == 1)
        {
            Caesar.start(1);
        }
        else if (action == 2)
        {
            Caesar.start(2);
        }
        else
        {
            System.out.println("Invalid input");
        }
    }

    private static void showMenu()
    {
        System.out.println("This program encrypts data using Caesar Cipher, " +
                "select action");
        System.out.println("1)cipher mode");
        System.out.println("2)decipher mode");
    }

    private static int getAction()
    {
        Scanner scanner = new Scanner(System.in);
        int action = Integer.parseInt(scanner.nextLine());
        return action;
    }
}

final class Caesar
{
    Caesar() { }

    public static void start(int action)
    {
        if (action == 1)
        {
            cipher();
        }
        if (action == 2)
        {
            decipher();
        }
    }

    private static void cipher()
    {
        StringBuilder sb = new StringBuilder();
        char[] plain_text = getText().toCharArray();
        int key = getKey();

        int ch;
        for (int i = 0; i < plain_text.length; i++)
        {
            // ASCII: 32 = ' ', 44 = ',', 46 = '.'
            if (plain_text[i] == 32 || plain_text[i] == 44 || plain_text[i] == 46)
            {
                sb.append(plain_text[i]);
                continue;
            }
            ch = (plain_text[i] - 65);
            ch += key;
            ch %= 26;
            ch += 65;
            sb.append((char)ch);
        }
        System.out.println("Output text:");
        System.out.println(sb.toString());
    }

    private static void decipher()
    {
        StringBuilder sb = new StringBuilder();
        char[] plain_text = getText().toCharArray();
        int key = getKey();

        int ch;
        for (int i = 0; i < plain_text.length; i++)
        {
            if (plain_text[i] == 32 || plain_text[i] == 44 || plain_text[i] == 46)
            {
                sb.append(plain_text[i]);
                continue;
            }
            ch = (plain_text[i] - 65);
            if ((ch - key) < 0 || ch == 0)
            {
                ch += 26;
            }

            ch -= key;
            ch %= 26;
            ch += 65;
            sb.append((char)ch);
        }
        System.out.println(sb.toString());

    }

    private static String getText()
    {
        System.out.println("Enter plaint text: ");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toUpperCase();
        return text;
    }

    private static int getKey()
    {
        System.out.println("Enter the key: ");
        Scanner scanner = new Scanner(System.in);
        int key = Integer.parseInt(scanner.nextLine());
        scanner.close();
        return key;
    }
}