package V2.Uppg6;

public class MEDICINE extends Thread
{
    private String medicineName;
    private double Interval;

    public MEDICINE(String medicineName,double Interval){
        this.medicineName = medicineName;
        this.Interval = (60/Interval) * 1000;
    }

    @Override
    public void run()
    {
        while (!Thread.interrupted())
        {
            try {
                Thread.sleep((long)Interval);
                System.out.println("Dags o ta " + medicineName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.run();
    }
}
