package cn.edu.jsu.jyt.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import cn.edu.jsu.jyt.vo.CClass;
import cn.edu.jsu.jyt.vo.Management;

public class ClassIO {
	
	private File file;

	public ClassIO() {
		super();
	}

	public ClassIO(File file) {
		super();
		this.file = file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * 传入对象数组，写入文件
	 * @param file
	 * @param c
	 */
	public static void writeClass(File file,CClass c[])
	{
		try(FileWriter fw=new FileWriter(file);)
		{
			for(int i=0;i<c.length;i++)
			{
				fw.write(c[i].getCno()+"\t"+c[i].getCname()+"\t"+c[i].getCcredit()+"\r\n");
			}
			
		}catch (Exception e) {
				// TODO: handle exception
			}
	}
	/**
	 * 添加课程文件信息
	 * @param file
	 * @param s1
	 * @param s2
	 * @param s3
	 */
	public static void addClass(File file,String s1,String s2,String s3)
	{
		try(FileWriter fw=new FileWriter(file,true);)
		{
			
				fw.write(s1+"\t"+s2+"\t"+s3+"\r\n");
			
			
		}catch (Exception e) {
				// TODO: handle exception
			}
	}
	/**
	 * 读取课程文件信息
	 * @param file
	 * @return
	 */
	public static Vector<Vector> readClass(File file)
	{
		Vector<Vector> rows=new Vector<>();
		try(FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);)
		{
			String line=null;
			while((line=br.readLine())!=null)
			{
				String[] column=line.split("\t");
				Vector row =new Vector<>();
				for(int i=0;i<column.length;i++)
				{
					if(i==2)
					{
						row.add(Double.valueOf(column[2]));
					}
					else
					{
						row.add(column[i]);
					}
					
				}
				rows.add(row);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return rows;
	}
	/**
	 * 查找课程文件信息
	 * @param file
	 * @param s
	 * @return
	 */
	public static Vector<Vector> searchClass(File file,String s)
	{
		Vector<Vector> rows=new Vector<>();
		try(FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);)
		{
			String line=null;
			while((line=br.readLine())!=null)
			{
				if(line.contains(s))//如果包含
				{
				String[] column=line.split("\t");
				Vector row =new Vector<>();
				for(int i=0;i<column.length;i++)
				{
					if(i==2)
					{
						row.add(Double.valueOf(column[2]));
					}
					else
					{
						row.add(column[i]);
					}
					
				}
				rows.add(row);
			}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return rows;
	}
	/**
	 * 删除课程文件信息
	 * @param file
	 * @param s
	 * @return
	 */
	public static Vector<Vector> deleteClass(File file,String s)
	{
		Vector<Vector> rows=new Vector<>();
		try(FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);)
		{
			String line=null;
			while((line=br.readLine())!=null)
			{
				if(!line.contains(s))//如果不包含
				{
				String[] column=line.split("\t");
				Vector row =new Vector<>();
				for(int i=0;i<column.length;i++)
				{
					if(i==2)
					{
						row.add(Double.valueOf(column[2]));
					}
					else
					{
						row.add(column[i]);
					}
					
				}
				rows.add(row);
			}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return rows;
	}
	/**
	 * 课程文件信息排序
	 * @param file
	 * @return
	 */
	public static Vector<Vector> sortClass(File file)
	{
		Vector<Vector> rows=new Vector<>();
		Map<String,String> hMap=new HashMap<>();
		try(FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);)
				{
					String line=null;
					while((line=br.readLine())!=null)
					{
						String []column=line.split("\t");
						hMap.put(column[2], column[0]+"\t"+column[1]);
					}
				
					Set<Map.Entry<String, String>> set=hMap.entrySet();
					for(Map.Entry<String, String> entry:set)
					{
						String key=entry.getKey();
						String value[]=entry.getValue().split("\t");
						Vector row=new Vector<>();
					    row.add(value[0]);
					    row.add(value[1]);
					    row.add(Double.valueOf(key));
					    rows.add(row);	
					}
				}
		catch (Exception e) {
			// TODO: handle exception
		}
		return rows;
	}
	/**
	 * 修改文件信息
	 */
	public static void updateClass(File file,String s1,String s2,String s3) 
	{
	        try(FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
	        )
	        {
	        	CClass c[]=new CClass[1000];
	        	String line=null;
	        	int j=0;
				while((line=br.readLine())!=null)
				{
					
					String[] column=line.split("\t");
					System.out.println(line);
					
						if(column[0].contains(s1))
						{
							c[j]=new CClass();
							c[j].setCno(s1);
							c[j].setCname(s2);
							c[j].setCcredit(Double.parseDouble(s3));
						}
						else
						{
							c[j]=new CClass();
							c[j].setCno(column[0]);
							c[j].setCname(column[1]);
							c[j].setCcredit(Double.parseDouble(column[2]));
						}
                       j++;
				}
				ClassIO.writeClass(file, c);
	        }
	        catch (Exception e) {
				// TODO: handle exception
			}
	        
	}
}

