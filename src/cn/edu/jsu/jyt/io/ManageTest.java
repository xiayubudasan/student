package cn.edu.jsu.jyt.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.edu.jsu.jyt.vo.Management;

public class ManageTest {
	public static void main(String[]args) throws Exception
	{
		ArrayList<Management> list=new ArrayList<>();
		list.add(new Management("2016001","123456"));
		list.add(new Management("2016002","123456"));
		File file=new File("e:"+File.separator+"act.txt");
		ManageIO m=new ManageIO(file);
		m.save(list);
		System.out.println(m.get("2016001"));
		System.out.println(m.update(list,"2016001","12345"));
		System.out.println(m.get("2016001"));
		ArrayList<Management> list2=m.showAll();
		for(Object o:list2.toArray())
		{
			System.out.println(o);
		}
	}

}
