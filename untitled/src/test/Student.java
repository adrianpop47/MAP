package test;

public class Student extends Persoana{
    int anStudiu;
    public Student(String nume, byte varsta, int anStudiu)
    {
        super(nume, varsta);
        this.anStudiu = anStudiu;
    }

    public void seDistreaza()
    {
        super.socializeaza();
        System.out.println("Si canta.....");
    }

    @Override
    public String toString() {
        return super.toString()+", "+ "an de studiu="+anStudiu;
    }
}
