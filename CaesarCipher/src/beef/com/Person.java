package beef.com;

import java.util.Random;

public class Person
{
    private static int nextId;

    private String login;
    private String pass;
    private int id;

    static
    {
        Random rand = new Random();
        nextId = rand.nextInt(1000);
    }

    public Person(String login, String pass)
    {
        this.login = login;
        this.pass = pass;
        this.id = nextId;
        nextId++;
    }

   public String getLogin() { return this.login; }

   public int getId() { return this.id; }
}
