package cn.edu.jsu.jyt.io;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import cn.edu.jsu.jyt.vo.Management;


public class ManageIO implements Serializable 
{
    private File fileName;

    public ManageIO(File fileName) {
        this.fileName = fileName;
    }
    	public void  save(ArrayList<Management> list) throws Exception{
    	       // Object[] obj=list.toArray();
    	        ObjectOutputStream oos=null;
    	        OutputStream out=new FileOutputStream(fileName);
    	        oos=new ObjectOutputStream(out);
    	        for(Object obj:list.toArray())
    	        oos.writeObject(obj);
    	        oos.close();
    	    }
    	
    	public ArrayList<Management> showAll() throws Exception 
    	    {
    		ArrayList<Management> list=new ArrayList<>();
    	        ObjectInputStream ois=null;
    	        InputStream input=new FileInputStream(fileName);
    	        ois=new ObjectInputStream(input);
    	       // Object[] obj=(Object[])ois.readObject();
    	        Object obj=null;

    	try {
    	    while ((obj=ois.readObject())!=null)
    	    {
    	        list.add((Management)obj);
    	    }
    	}
    	//EOFException异常表示输入过程中意外地到达文件尾或流尾的信号,导致异常。
    	      catch (EOFException e){
    	    ois.close();
    	      }
    	return list;
    	    }
    	   
    	public Management get(String mno) throws Exception 
    	   {
    	        ObjectInputStream ois=null;
    	        InputStream input=new FileInputStream(fileName);
    	        ois=new ObjectInputStream(input);
    	        //Object[] obj=(Object[]) ois.readObject();
    	        Object obj=null;
    	        try {

    	            while((obj=ois.readObject())!=null)
    	            {
    	                Management a=(Management) obj;
    	                if (a.getMno().equals(mno))
    	                {
    	                   return a;
    	                }
    	            }
    	        }
    	      catch (EOFException e)
    	      {
    	          ois.close();
    	      }
    	        return null;
    	    }
    public boolean update(ArrayList<Management> list,String mno,String password) throws Exception{
 	       // Object[] obj=list.toArray();
    	boolean flag=false;
 	        ObjectOutputStream oos=null;
 	        OutputStream out=new FileOutputStream(fileName);
 	        oos=new ObjectOutputStream(out);
 	        for(Object obj:list.toArray())
 	        {
 	        	Management m=(Management) obj;
 	        	if(m.getMno()==mno)
 	        	{
 	        		m.setPassword(password);
 	        		flag=true;
 	        	}
 	        	oos.writeObject(obj);
 	        }
 	        oos.close();
 	        return flag;
 	    }

}
