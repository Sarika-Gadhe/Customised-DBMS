import java.util.*;
import java.io.*;

class Employee implements Serializable              // Marker Interface
{
    public int EmpID;
    public String EmpName;
    public int EmpAge;

    public String EmpAddress;
    public int EmpSalary;

    private static int Counter;

    static 
    {
        Counter = 1;
    }

    public Employee(String b, int c, String d,int e )
    {
        this.EmpID = Counter++;
        this.EmpName = b;
        this.EmpAge = c;
        this.EmpAddress = d;
        this.EmpSalary= e;
    }

    public void DisplayInformation()
    {
        System.out.println("Id : "+this.EmpID+ " Name : "+this.EmpName+" Age : " +this.EmpAge+ " Address : "+this.EmpAddress+" Salary :"+this.EmpSalary);
    }

    public String toString()
    {
        return "Id : "+this.EmpID + " Name : "+this.EmpName+" Age : " +this.EmpAge+ " Address : "+this.EmpAddress+" Salary :"+this.EmpSalary;
    }
}

class MarvellousDBMS implements Serializable
{
    private LinkedList <Employee>Table;

    public MarvellousDBMS()
    {
        System.out.println("Marvellous DBMS started Successfully...");
        Table =  new LinkedList<>();
    }

    // Insert into employee values(1,'Amit',21,'Pune',21000)
    public void InsertIntoTable(     
                                        String name, 
                                        int age, 
                                        String Address, 
                                        int salary
                                    )
    {
        Employee eobj = new Employee(name, age, Address, salary);

        Table.add(eobj);

        System.out.println("Marvellous DBMS : > New record inserted succesfully");
    }

    // select *from Employee

    public void SelectStarFrom()
    {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Data From the Employee Table");
        System.out.println("-----------------------------------------------------------------------------");

        for(Employee eref : Table)
        {
            System.out.println(eref);
        }

        System.out.println("-----------------------------------------------------------------------------");
    }
    
    public void TakeBackup()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("MarvellousDBMS.ser");
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
    
            oos.writeObject(this);
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured...");
        }

    }
    
    public static MarvellousDBMS RestoreBackup(String path)
    {
        try
        {
             MarvellousDBMS ret = null;

            FileInputStream fis = new FileInputStream("MarvellousDBMS.ser");
            
            ObjectInputStream ois = new ObjectInputStream(fis);
    
           ret = (MarvellousDBMS)ois.readObject();
           return ret;
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured...");
            return null;
        }

    }

    // select * from Employee where EmpId = 11
    public void SelectSpecificID(int id)
    {
        boolean found = false;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                found = true;
                System.out.println(eref);
                break;
            }

        }
        if(found == false)
        {
            System.out.println("There is no such record");
        }

       
    }

    // select * from Employee where EmpName = "Sarika"
    public void SelectSpecificName(String name)
    {
        boolean found = false;

        for(Employee eref : Table)
        {
            if(name.equals(eref.EmpName))
            {
                found = true;
                System.out.println(eref);
            }

        }
        if(found == false)
        {
            System.out.println("There is no such record");
        }
       
    }

    // delete from Employee where EmpId = 11    
    public void DeleteSpecificID(int id)
    {
        boolean found = false;
        int index = 0;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                found = true;
                break;
            }
            index++;

        }
        if(found == false)
        {
            System.out.println("There is no such record");
        }
        else
        {
            Table.remove(index);
            System.out.println("Records succesfully deleted");

        }

       
    }



}  // End of MarvellousDBMS

public class Program847
{
    public static void main(String A[]) throws Exception
    {
        MarvellousDBMS mobj = MarvellousDBMS.RestoreBackup("MarvellousDBMS.ser");

        if(mobj == null)
        {
            System.out.println("Unable to restore backup");

            mobj = new MarvellousDBMS();
        }

        Scanner sobj = new Scanner(System.in);

        int iOption = 0;
        int salary = 0;
        int age = 0;
        int id = 0;

        String name = "";
        String address = "";

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("-------------------------- Marvellous DBMS ----------------------------------");
        System.out.println("-----------------------------------------------------------------------------");

        while(iOption != 0)
        {
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("1 : Insert into employee " );
            System.out.println("2 : select * from employee " );
            System.out.println("3 : Take a backup of table " );
            System.out.println("4 : select * from employee where EmpId = ____" );
            System.out.println("5 : select * from employee where EmpName = ____ " );
            System.out.println("6 : delete from employee where EmpId = ____ " );

            System.out.println("0 : Terminate the DBMS " );
            System.out.println("-----------------------------------------------------------------------------");


            System.out.println("Please Select the desired operation on the database");
            iOption = sobj.nextInt();

            if(iOption == 1)
            {
                System.out.println("Please enter the data that you want to insert : ");

                sobj.nextLine();
                System.out.println("Enter the name of employee : ");
                name = sobj.nextLine();
                
                
                System.out.println("Enter the age of employee : ");
                age = sobj.nextInt();

                sobj.nextLine();

                System.out.println("Enter the Address of employee : ");
                address = sobj.nextLine();
                
                
                System.out.println("Enter the salary of employee : ");
                salary = sobj.nextInt();


                mobj.InsertIntoTable(name,age,address,salary);
            }
            else if(iOption == 2)
            {
                mobj.SelectStarFrom();
            }
            else if(iOption == 3)
            {
                mobj.TakeBackup();
                System.out.println("Database gets succesfully stored into secondary storage");

            }
            else if(iOption == 4)
            {
                System.out.println("Enter the employee ID : ");
                id = sobj.nextInt();

                mobj.SelectSpecificID(id);
            }
            else if(iOption == 5)
            {
                sobj.nextLine();
                System.out.println("Enter the name of employee  : ");
                name = sobj.nextLine();

                mobj.SelectSpecificName(name);
            }
            else if(iOption == 6)
            {
                System.out.println("Enter the employee ID that you want to delete : ");
                id = sobj.nextInt();

                mobj.DeleteSpecificID(id);
            }
            else if(iOption == 0)
            {
                System.out.println("Thank you for using Marvellous DBMS");
                mobj =  null;
                System.gc();
                break;
            }// End of while
            
        }// End of main method
        
    } // End of main class
}

